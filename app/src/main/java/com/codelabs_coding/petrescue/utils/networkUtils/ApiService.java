package com.codelabs_coding.petrescue.utils.networkUtils;

import com.codelabs_coding.petrescue.models.UserModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {

    @POST("/owner/register")
    Call<ResponseWrapper<UserModel>> CreateUser(@Body RequestBody body);

    @POST("/owner/login")
    Call<ResponseWrapper<UserModel>> UserLogin(@Body RequestBody body);

    @POST("/owner/addPet")
    Call<ResponseWrapper<UserModel.User.Pet>> AddNewPet(@Header("authorization") String auth, @Body RequestBody body);

    @POST("/owner/setLocation")
    Call<ResponseWrapper<UserModel.User>> UpdateOwnerLocation(@Header("authorization") String auth, @Body RequestBody body);

    @GET("/owner/getMySelf")
    Call<ResponseWrapper<UserModel.User>> GetMySelf(@Header("authorization") String auth);
}
