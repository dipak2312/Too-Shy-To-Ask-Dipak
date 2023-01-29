package com.example.tooshytoask.API;

import com.example.tooshytoask.AuthModels.HealthCateModel;
import com.example.tooshytoask.AuthModels.HealthIssueModel;
import com.example.tooshytoask.AuthModels.OtpAuthModel;
import com.example.tooshytoask.AuthModels.SaveHealthCateAuthModel;
import com.example.tooshytoask.AuthModels.SignInAuthModel;
import com.example.tooshytoask.AuthModels.SignupAuthModel;
import com.example.tooshytoask.AuthModels.UserDetailAuthModel;
import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.Models.HealthCateResponse;
import com.example.tooshytoask.Models.HealthIssueResponse;
import com.example.tooshytoask.Models.LanguageResponse;
import com.example.tooshytoask.Models.OnBordingResponse;
import com.example.tooshytoask.Models.OtpInResponse;
import com.example.tooshytoask.Models.SaveHealthCategoryResponse;
import com.example.tooshytoask.Models.SignInResponse;
import com.example.tooshytoask.Models.SignupResponse;
import com.example.tooshytoask.Models.UserDetailResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebServices {
    //web_services/login_with_otp.php

    @Headers("Content-Type: application/json")
    @POST("api/send_otp")
    Observable<SignInResponse> sendOtp(@Body SignInAuthModel model);

    //@Headers("Content-Type: application/json")
    @Headers({"Content-Length:0","Accept: application/json"})
    @POST("api/verify_otp")
    Observable<OtpInResponse> signIn(@Body OtpAuthModel model);

    @GET("api/onboarding_details")
    Observable<OnBordingResponse> getOnBorading();

    @GET("api/languages")
    Observable<LanguageResponse> languageget();

    @Headers("Content-Type: application/json")
    @POST("api/register_user")
    Observable<SignupResponse> signup(@Body SignupAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/health_category")
    Observable<HealthCateResponse> healthcategory(@Body HealthCateModel model);

    @Headers("Content-Type: application/json")
    @POST("api/save_healthcategory")
    Observable<SaveHealthCategoryResponse> saveHealthCategory(@Body SaveHealthCateAuthModel model);

    @GET("api/avatars")
    Observable<AvatarResponse> healthcategory();

    @Headers("Content-Type: application/json")
    @POST("api/healthissues")
    Observable<HealthIssueResponse> healthIssues(@Body HealthIssueModel model);

    @Headers("Content-Type: application/json")
    @POST("api/save_userdetails")
    Observable<UserDetailResponse> userDetails(@Body UserDetailAuthModel model);

}
