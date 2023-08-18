package com.codelabs_coding.petrescue.utils.networkUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
//    private static final String BASE_URL = "http://192.168.8.103:3000";
        private static final String BASE_URL = "https://rescue-pet.onrender.com";
    private final ApiService apiService;

    public RetrofitProvider() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

    public <T> void makeRequest(Call<ResponseWrapper<T>> call, final RetrofitCallback<T> callback) {
        call.enqueue(new Callback<ResponseWrapper<T>>() {
            @Override
            public void onResponse(Call<ResponseWrapper<T>> call, Response<ResponseWrapper<T>> response) {
                if (response.isSuccessful()) {
                    ResponseWrapper<T> wrapper = response.body();
                    if (wrapper != null && wrapper.isStatus()) {
                        callback.onSuccess(wrapper.getData());
                    } else if (wrapper != null) {
                        callback.onError(response.code(), wrapper.getMessage());
                    }
                } else {
                    callback.onError(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseWrapper<T>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
