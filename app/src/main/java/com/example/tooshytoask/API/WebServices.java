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
    @POST("web_services/login_with_otp.php")
    Observable<SignInResponse> signIn(@Body SignInAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("web_services/login_with_otp.php")
    Observable<SignInResponse> sendOtp(@Body OtpAuthModel model);

}
