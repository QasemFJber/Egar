package com.example.egar.controllers;

import com.example.egar.Models.Provider;
import com.example.egar.interfaces.OnServiceProviderFetchListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderController {
    private ServiceProviderController instance;
    private ServiceProviderController(){}

    public ServiceProviderController getInstance() {
        if (instance == null){
            instance= new ServiceProviderController();
        }
        return instance;
    }
    public void getAllServiceProviders(OnServiceProviderFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference serviceProvidersRef = db.collection("serviceproviders");

        serviceProvidersRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Provider> serviceProvidersList = new ArrayList<>();

            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Provider serviceProvider = new Provider();
                serviceProvider.setId(document.getId());
                serviceProvider.setName(document.getString("name"));
                serviceProvider.setEmail(document.getString("email"));
                serviceProvider.setPhoneNumber(document.getString("phoneNumber"));
                serviceProvider.setProviderType(document.getString("providerType"));
                serviceProvider.setAddress(document.getString("address"));
                serviceProvider.setCity(document.getString("city"));
                serviceProvider.setBio(document.getString("bio"));
                serviceProvidersList.add(serviceProvider);
            }
            listener.onFetchSuccess(serviceProvidersList);
        }).addOnFailureListener(e -> {
            listener.onFetchFailure("Failed to fetch service providers");
        });
    }

}
