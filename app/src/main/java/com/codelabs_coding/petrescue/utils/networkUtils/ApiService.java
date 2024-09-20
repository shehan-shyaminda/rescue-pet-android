package com.codelabs_coding.petrescue.utils.networkUtils;

import com.codelabs_coding.petrescue.models.PetsModal;
import com.codelabs_coding.petrescue.models.UserModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/owner/register")
    Call<ResponseWrapper<UserModel>> createUser(@Body RequestBody body);

    @POST("/owner/login")
    Call<ResponseWrapper<UserModel>> userLogin(@Body RequestBody body);

    @POST("/pet/getPet")
    Call<ResponseWrapper<PetsModal>> getPet(@Header("authorization") String auth, @Body RequestBody body);

    @POST("/pet/addPet")
    Call<ResponseWrapper<UserModel.User.Pet>> addNewPet(@Header("authorization") String auth, @Body RequestBody body);

    @POST("/owner/setLocation")
    Call<ResponseWrapper<UserModel.User>> updateOwnerLocation(@Header("authorization") String auth, @Body RequestBody body);

    @GET("/owner/getMySelf")
    Call<ResponseWrapper<UserModel.User>> getMySelf(@Header("authorization") String auth);

    @POST("/owner/renewTokenFCM")
    Call<ResponseWrapper<String>> renewTokenFCM(@Header("authorization") String auth, @Body RequestBody body);

    @GET("/owner/revokeTokenFCM")
    Call<ResponseWrapper<String>> revokeTokenFCM(@Header("authorization") String auth);
}
