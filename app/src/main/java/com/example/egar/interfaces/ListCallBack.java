package com.example.egar.interfaces;

import java.util.List;

public interface ListCallBack<Model> {
    void onSuccess(List<Model> list);
    void onFul(String message);
}
