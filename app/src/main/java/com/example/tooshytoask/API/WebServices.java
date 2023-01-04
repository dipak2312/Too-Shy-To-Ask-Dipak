package com.example.tooshytoask.API;

import com.example.tooshytoask.AuthModels.OtpAuthModel;
import com.example.tooshytoask.AuthModels.SignInAuthModel;
import com.example.tooshytoask.Models.SignInResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebServices {


    @Headers("Content-Type: application/json")
    @POST("apis/send_otp")
    Observable<SignInResponse> signIn(@Body SignInAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("apis/send_otp")
    Observable<SignInResponse> sendOtp(@Body OtpAuthModel model);

}
