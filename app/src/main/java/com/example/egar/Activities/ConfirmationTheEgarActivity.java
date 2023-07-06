package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.egar.Models.Order;
import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.controllers.OrderController;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ActivityConfirmationTheEgarBinding;
import com.example.egar.enams.OrderStatus;
import com.example.egar.interfaces.OnOrderFetchListener;
import com.example.egar.interfaces.ProductCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ConfirmationTheEgarActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityConfirmationTheEgarBinding binding;

    Order order;
    String date;

   // List<Product> products = new ArrayList<>();
    //ProductShowProviderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmationTheEgarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d("CONABD", "id"+getIntent().getStringExtra("id_product"));
        Log.d("CONABD", "date_start:"+getIntent().getStringExtra("date_start"));
        Log.d("CONABD", "date_end:"+getIntent().getStringExtra("date_end"));
        Log.d("CONABD", "date_day:"+getIntent().getStringExtra("date_day"));

        date = getIntent().getStringExtra("date_start")+" - "+getIntent().getStringExtra("date_end");
        binding.textdate.setText(date);

        initializeView();



    }

    private void initializeView() {
        getProduct();
        setOnclick();
    }

        private void setOnclick(){
        binding.buttonPay.setOnClickListener(this::onClick);

    }

    private void getProduct(){
        ProductController.getInstance().getProductById(getIntent().getStringExtra("id_product"), new ProductCallback() {
            @Override
            public void onSuccess(List<Product> productList) {

            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onProductFetchSuccess(Product product) {

                binding.textNamePro.setText(product.getProvider().getName());
                Picasso.get().load(product.getProvider().getImage()).into(binding.imagePro);

                binding.tvProductShow.setText(product.getName());
                Picasso.get().load(product.getImageUrl()).into(binding.imgProductShow);
                binding.tvProductPrice.setText(String.valueOf(product.getPrice()));

                //(قيمة الإيجار اليومي ×عدد ساعات التأخير) ÷24×2 = تكلفة قيمة ساعات التأخير
                double amount = (product.getPrice() * 2);

                binding.textValueRentAmount.setText(String.valueOf(amount));
                binding.textTotalAmount.setText(String.valueOf(amount));

                binding.buttonPay.setText("Pay "+amount);


                //(String orderId, String userId, String serviceProviderId, int quantity, double totalAmount, String orderDate, OrderStatus orderStatus, String paymentMethod, String shippingLocation)

                order = new Order("",product.getId(),product.getProvider().getId(),1,amount,date, OrderStatus.PENDING,"PayPal",product.getProvider().getAddress());


            }
        });
    }

    private void addOrder(){
        OrderController.getInstance().addOrder(order, new OnOrderFetchListener() {
            @Override
            public void onAddOrderSuccess(String orderId) {
                //onAddOrderSuccess
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onAddOrderFailure(String message) {

            }

            @Override
            public void onUpdateOrderSuccess() {

            }

            @Override
            public void onUpdateOrderFailure(String message) {

            }

            @Override
            public void onDeleteOrderSuccess() {

            }

            @Override
            public void onDeleteOrderFailure(String message) {

            }

            @Override
            public void onGetOrdersByServiceProviderIdSuccess(List<Order> orders) {

            }

            @Override
            public void onGetOrdersByServiceProviderIdFailure(String message) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_pay){
            addOrder();
        }
    }
}