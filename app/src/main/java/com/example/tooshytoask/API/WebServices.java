package com.example.tooshytoask.API;

import com.example.tooshytoask.AuthModels.AllBlogAuthModel;
import com.example.tooshytoask.AuthModels.AllCoursesAuthModel;
import com.example.tooshytoask.AuthModels.AllEventAuthModel;
import com.example.tooshytoask.AuthModels.AllHighlightAuthModel;
import com.example.tooshytoask.AuthModels.AllVideoGalleryAuthModel;
import com.example.tooshytoask.AuthModels.BookmarkBlogAuthModel;
import com.example.tooshytoask.AuthModels.ClearNotificationAuthModel;
import com.example.tooshytoask.AuthModels.ContactFormAuthModel;
import com.example.tooshytoask.AuthModels.FeedbackAuthModel;
import com.example.tooshytoask.AuthModels.HealthCateModel;
import com.example.tooshytoask.AuthModels.HealthIssueModel;
import com.example.tooshytoask.AuthModels.HelpCategoryAuthModel;
import com.example.tooshytoask.AuthModels.HelpContentAuthModel;
import com.example.tooshytoask.AuthModels.HelpFeedbackAuthModel;
import com.example.tooshytoask.AuthModels.HelpSubCategoryAuthModel;
import com.example.tooshytoask.AuthModels.HomeScreenAuthModel;
import com.example.tooshytoask.AuthModels.InsightScreenAuthModel;
import com.example.tooshytoask.AuthModels.ManageNotificationAuthModel;
import com.example.tooshytoask.AuthModels.ManageNotificationListUpdateAuthModel;
import com.example.tooshytoask.AuthModels.NotificationAuthModel;
import com.example.tooshytoask.AuthModels.OtpAuthModel;
import com.example.tooshytoask.AuthModels.RemoveProfileAuthModel;
import com.example.tooshytoask.AuthModels.SaveHealthCateAuthModel;
import com.example.tooshytoask.AuthModels.SaveHealthIssueAuthModel;
import com.example.tooshytoask.AuthModels.SaveProfilePicAuthModel;
import com.example.tooshytoask.AuthModels.SearchAuthModel;
import com.example.tooshytoask.AuthModels.SignInAuthModel;
import com.example.tooshytoask.AuthModels.SignupAuthModel;
import com.example.tooshytoask.AuthModels.SingleBlogAuthModel;
import com.example.tooshytoask.AuthModels.SingleClearNotificationAuthModel;
import com.example.tooshytoask.AuthModels.StoryAuthModel;
import com.example.tooshytoask.AuthModels.StoryLikeAuthModel;
import com.example.tooshytoask.AuthModels.UpdateProfileAuthModel;
import com.example.tooshytoask.AuthModels.UserDetailAuthModel;
import com.example.tooshytoask.AuthModels.UserProfileAuthModel;
import com.example.tooshytoask.Models.AllBlogResponse;
import com.example.tooshytoask.Models.AllCoursesResponse;
import com.example.tooshytoask.Models.AllEventResponse;
import com.example.tooshytoask.Models.AllHighlightResponse;
import com.example.tooshytoask.Models.AllVideoGalleryResponse;
import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.Models.BookmarkBlogResponse;
import com.example.tooshytoask.Models.ClearNotificationResponse;
import com.example.tooshytoask.Models.FeedbackResponse;
import com.example.tooshytoask.Models.HealthCateResponse;
import com.example.tooshytoask.Models.HealthIssueResponse;
import com.example.tooshytoask.Models.Help.ContactFormResponse;
import com.example.tooshytoask.Models.Help.HelpCategoryResponse;
import com.example.tooshytoask.Models.Help.HelpContentResponse;
import com.example.tooshytoask.Models.Help.HelpFeedbackResponse;
import com.example.tooshytoask.Models.Help.HelpSubCategoryResponse;
import com.example.tooshytoask.Models.HomeScreenResponse;
import com.example.tooshytoask.Models.InsightScreen.InsightScreenResponse;
import com.example.tooshytoask.Models.Language.LanguageResponse;
import com.example.tooshytoask.Models.ManageNotificationListUpdateResponse;
import com.example.tooshytoask.Models.ManageNotificationResponse;
import com.example.tooshytoask.Models.NotificationResponse;
import com.example.tooshytoask.Models.OnBordingResponse;
import com.example.tooshytoask.Models.OtpInResponse;
import com.example.tooshytoask.Models.RemoveProfileResponse;
import com.example.tooshytoask.Models.SaveHealthCategoryResponse;
import com.example.tooshytoask.Models.SaveHealthIssueResponse;
import com.example.tooshytoask.Models.SaveProfilePicResponse;
import com.example.tooshytoask.Models.SearchResponse;
import com.example.tooshytoask.Models.SignInResponse;
import com.example.tooshytoask.Models.SignupResponse;
import com.example.tooshytoask.Models.SingleBlogResponse;
import com.example.tooshytoask.Models.SingleClearNotificationResponse;
import com.example.tooshytoask.Models.StoryLikeResponse;
import com.example.tooshytoask.Models.StoryResponse;
import com.example.tooshytoask.Models.UpdateProfile.UpdateProfileResponse;
import com.example.tooshytoask.Models.UserDetailResponse;
import com.example.tooshytoask.Models.UserProfileResponse;

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

    @Headers("Content-Type: application/json")
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
    Observable<AvatarResponse> getProfile();

    @Headers("Content-Type: application/json")
    @POST("api/profile_pic")
    Observable<SaveProfilePicResponse> saveProfilePic(@Body SaveProfilePicAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/healthissues")
    Observable<HealthIssueResponse> healthIssues(@Body HealthIssueModel model);

    @Headers("Content-Type: application/json")
    @POST("api/save_healthissues")
    Observable<SaveHealthIssueResponse> saveHealthIssue(@Body SaveHealthIssueAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/save_userdetails")
    Observable<UserDetailResponse> userDetails(@Body UserDetailAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/homescreen")
    Observable<HomeScreenResponse> homePageResponse(@Body HomeScreenAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/search")
    Observable<SearchResponse> getSearch(@Body SearchAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/categorystory")
    Observable<StoryResponse> getStory(@Body StoryAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/StoryLike")
    Observable<StoryLikeResponse> getStoryLike(@Body StoryLikeAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightscreen")
    Observable<InsightScreenResponse> insightScreenResponse(@Body InsightScreenAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightblogs")
    Observable<AllBlogResponse> getAllBlogs(@Body AllBlogAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insighthighlights")
    Observable<AllHighlightResponse> getHighlightBlogs(@Body AllHighlightAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightevents")
    Observable<AllEventResponse> getEventBlogs(@Body AllEventAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightcourses")
    Observable<AllCoursesResponse> getCoursesBlogs(@Body AllCoursesAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightvideogallery")
    Observable<AllVideoGalleryResponse> getVideoGallery(@Body AllVideoGalleryAuthModel model);


    @Headers("Content-Type: application/json")
    @POST("api/articlesingleblog")
    Observable<SingleBlogResponse> getSingleBlog(@Body SingleBlogAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/helpcategory")
    Observable<HelpCategoryResponse> getHelpCategory(@Body HelpCategoryAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/helpsubcategory")
    Observable<HelpSubCategoryResponse> getHelpSubCategory(@Body HelpSubCategoryAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/helpcontent")
    Observable<HelpContentResponse> getHelpContent(@Body HelpContentAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/helpfeedback")
    Observable<HelpFeedbackResponse> getHelpFeedback(@Body HelpFeedbackAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/contactform")
    Observable<ContactFormResponse> getContact(@Body ContactFormAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/updateuserproile")
    Observable<UpdateProfileResponse> getUserProfile(@Body UpdateProfileAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/userproile")
    Observable<UserProfileResponse> getUserData(@Body UserProfileAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/remove_profile_pic")
    Observable<RemoveProfileResponse> RemoveProfile(@Body RemoveProfileAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/usernotification")
    Observable<NotificationResponse> getNotification(@Body NotificationAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/ManageNotificationList")
    Observable<ManageNotificationResponse> getManageNotification(@Body ManageNotificationAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/ManageNotificationListUpdate")
    Observable<ManageNotificationListUpdateResponse> getManageNotificationUpdate(@Body ManageNotificationListUpdateAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/clearnotification")
    Observable<ClearNotificationResponse> ClearNotification(@Body ClearNotificationAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/deletenotification")
    Observable<SingleClearNotificationResponse> SingleClearNotification(@Body SingleClearNotificationAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/articlebookmark")
    Observable<BookmarkBlogResponse> getBookmarkBlogs(@Body BookmarkBlogAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/feedback")
    Observable<FeedbackResponse> getFeedback(@Body FeedbackAuthModel model);

}
