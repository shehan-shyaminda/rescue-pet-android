package com.codelabs_coding.petrescue.utils.networkUtils;

import android.net.ParseException;

import androidx.annotation.NonNull;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLProtocolException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private static final String BASE_URL = "https://e96b-112-134-134-75.ngrok-free.app/";
//        private static final String BASE_URL = "https://rescue-pet.onrender.com";
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
            public void onResponse(@NonNull Call<ResponseWrapper<T>> call, @NonNull Response<ResponseWrapper<T>> response) {
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
            public void onFailure(@NonNull Call<ResponseWrapper<T>> call, @NonNull Throwable t) {
                callback.onError(handleException(t), t.getLocalizedMessage());
            }
        });
    }

    public int handleException(Throwable e) {
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            return ERROR.PARSE_ERROR;
        } else if (e instanceof ConnectException) {
            return ERROR.NETWORK_ERROR;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            return ERROR.SSL_ERROR;
        } else if (e instanceof ConnectTimeoutException) {
            return ERROR.TIMEOUT_ERROR;
        } else if (e instanceof java.net.SocketTimeoutException) {
            return ERROR.TIMEOUT_ERROR;
        } else if (e instanceof java.io.EOFException) {
            return ERROR.TIMEOUT_ERROR;
        } else if (e instanceof UnknownHostException) {
            return ERROR.TIMEOUT_ERROR;
        } else if (e instanceof SSLProtocolException) {
            return ERROR.TIMEOUT_ERROR;
        } else {
            return ERROR.UNKNOWN;
        }
    }


    static class ERROR {
        public static final int UNKNOWN = 1000;
        public static final int PARSE_ERROR = 1001;
        public static final int NETWORK_ERROR = 1002;
        public static final int HTTP_ERROR = 1003;
        public static final int SSL_ERROR = 1005;
        public static final int TIMEOUT_ERROR = 1006;
    }
}
