package com.example.egar.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class PayPalController {

    private static final int PAYPAL_REQUEST_CODE = 123;
    private static final String PAYPAL_CLIENT_ID = "AZyo-5QX1zZrcFVk6PiOsTt6UlD1A3iZUbD0XsPHPuymI3G7qrAOh12KVE0uf7Jy14zoyPMzD4SPGvar";

    private static PayPalConfiguration paypalConfig;

    public static void init(Context context) {
        paypalConfig = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(PAYPAL_CLIENT_ID);

        Intent intent = new Intent(context, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        context.startService(intent);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, PayPalService.class));
    }

    public static void makePayment(Activity activity, String paymentAmount, String currencyCode) {
        PayPalPayment payment = new PayPalPayment(new BigDecimal(paymentAmount), currencyCode, "Payment Description", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(activity, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        activity.startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    public static void handleActivityResult(int requestCode, int resultCode, Intent data, PayPalPaymentListener listener) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    String paymentId = confirmation.getProofOfPayment().getPaymentId();
                    listener.onPaymentSuccess(paymentId);
                } else {
                    listener.onPaymentFailure("Payment confirmation is null.");
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                listener.onPaymentCancel();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                listener.onPaymentFailure("Invalid payment or PayPalConfiguration. Please check your credentials.");
            }
        }
    }

    public interface PayPalPaymentListener {
        void onPaymentSuccess(String paymentId);
        void onPaymentCancel();
        void onPaymentFailure(String errorMessage);
    }
}

