package com.codelabs_coding.petrescue.utils.networkUtils;

public interface RetrofitCallback<T> {
    void onSuccess(T response);

    void onError(int statusCode, String errorMessage);
}
