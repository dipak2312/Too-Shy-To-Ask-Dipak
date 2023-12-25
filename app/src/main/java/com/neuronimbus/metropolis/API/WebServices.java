package com.neuronimbus.metropolis.API;

import com.neuronimbus.metropolis.AuthModels.AddComplaintAuthModel;
import com.neuronimbus.metropolis.AuthModels.AskFeedbackAuthModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.AuthModels.NGOSignupAuthModel;
import com.neuronimbus.metropolis.AuthModels.QRCodeCountAuthModel;
import com.neuronimbus.metropolis.Models.AddComplaintResponse;
import com.neuronimbus.metropolis.AuthModels.AllBlogAuthModel;
import com.neuronimbus.metropolis.AuthModels.AllCoursesAuthModel;
import com.neuronimbus.metropolis.AuthModels.AllEventAuthModel;
import com.neuronimbus.metropolis.AuthModels.AllStoreHouseAuthModel;
import com.neuronimbus.metropolis.AuthModels.AllVideoGalleryAuthModel;
import com.neuronimbus.metropolis.AuthModels.AskIssuesAuthModel;
import com.neuronimbus.metropolis.AuthModels.AskIssuesFeedbackAuthModel;
import com.neuronimbus.metropolis.AuthModels.AskQuestionsAuthModel;
import com.neuronimbus.metropolis.AuthModels.BlogCategoryAuthModel;
import com.neuronimbus.metropolis.AuthModels.BlogCommentsAuthModel;
import com.neuronimbus.metropolis.AuthModels.BlogLikeAuthModel;
import com.neuronimbus.metropolis.AuthModels.BookmarkBlogAuthModel;
import com.neuronimbus.metropolis.AuthModels.ClearLMSQuizAuthModel;
import com.neuronimbus.metropolis.AuthModels.ClearNotificationAuthModel;
import com.neuronimbus.metropolis.AuthModels.ContactFormAuthModel;
import com.neuronimbus.metropolis.AuthModels.CoursesDetailAuthModel;
import com.neuronimbus.metropolis.AuthModels.CoursesEnrollAuthModel;
import com.neuronimbus.metropolis.AuthModels.ExpertReplyAuthModel;
import com.neuronimbus.metropolis.AuthModels.FAQContentAuthModel;
import com.neuronimbus.metropolis.AuthModels.FeedbackAuthModel;
import com.neuronimbus.metropolis.AuthModels.FeedbackListAuthModel;
import com.neuronimbus.metropolis.AuthModels.GameScoreAuthModel;
import com.neuronimbus.metropolis.AuthModels.HealthCateModel;
import com.neuronimbus.metropolis.AuthModels.HealthIssueModel;
import com.neuronimbus.metropolis.AuthModels.HelpCategoryAuthModel;
import com.neuronimbus.metropolis.AuthModels.HelpContentAuthModel;
import com.neuronimbus.metropolis.AuthModels.HelpFeedbackAuthModel;
import com.neuronimbus.metropolis.AuthModels.HelpSubCategoryAuthModel;
import com.neuronimbus.metropolis.AuthModels.HomeScreenAuthModel;
import com.neuronimbus.metropolis.AuthModels.InsightScreenAuthModel;
import com.neuronimbus.metropolis.AuthModels.LMSQuizAttemptAuthModel;
import com.neuronimbus.metropolis.AuthModels.LMSQuizAuthModel;
import com.neuronimbus.metropolis.AuthModels.LessonDetailAuthModel;
import com.neuronimbus.metropolis.AuthModels.LessonEnrollAuthModel;
import com.neuronimbus.metropolis.AuthModels.LessonUpdateAuthModel;
import com.neuronimbus.metropolis.AuthModels.ManageNotificationAuthModel;
import com.neuronimbus.metropolis.AuthModels.ManageNotificationListUpdateAuthModel;
import com.neuronimbus.metropolis.AuthModels.NotificationAuthModel;
import com.neuronimbus.metropolis.AuthModels.OtpAuthModel;
import com.neuronimbus.metropolis.AuthModels.ProcessingFeedbackChatAuthModel;
import com.neuronimbus.metropolis.AuthModels.QuizAnswerAuthModel;
import com.neuronimbus.metropolis.AuthModels.QuizQueAuthModel;
import com.neuronimbus.metropolis.AuthModels.RemoveProfileAuthModel;
import com.neuronimbus.metropolis.AuthModels.SaveHealthCateAuthModel;
import com.neuronimbus.metropolis.AuthModels.SaveHealthIssueAuthModel;
import com.neuronimbus.metropolis.AuthModels.SaveProfilePicAuthModel;
import com.neuronimbus.metropolis.AuthModels.SearchAuthModel;
import com.neuronimbus.metropolis.AuthModels.SignInAuthModel;
import com.neuronimbus.metropolis.AuthModels.SignupAuthModel;
import com.neuronimbus.metropolis.AuthModels.SingleBlogAuthModel;
import com.neuronimbus.metropolis.AuthModels.SingleClearNotificationAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoreHouseCategoryAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoreHouseLikeAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoreHouseRelatedAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoreHouseSinglePageAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoryAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoryLikeAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoryShareAuthModel;
import com.neuronimbus.metropolis.AuthModels.UpdateProfileAuthModel;
import com.neuronimbus.metropolis.AuthModels.UserDetailAuthModel;
import com.neuronimbus.metropolis.AuthModels.UserProfileAuthModel;
import com.neuronimbus.metropolis.Models.AllBlogResponse;
import com.neuronimbus.metropolis.Models.AllCoursesResponse;
import com.neuronimbus.metropolis.Models.AllEventResponse;
import com.neuronimbus.metropolis.AuthModels.BookmarkAuthModel;
import com.neuronimbus.metropolis.Models.AskExpert.AskIssuesResponse;
import com.neuronimbus.metropolis.Models.AskIssuesFeedbackResponse;
import com.neuronimbus.metropolis.Models.AskQuestionsResponse;
import com.neuronimbus.metropolis.Models.BookmarkResponse;
import com.neuronimbus.metropolis.Models.CommonResponse;
import com.neuronimbus.metropolis.Models.ComplaintListResponse;
import com.neuronimbus.metropolis.Models.Courses.CoursesDetailResponse;
import com.neuronimbus.metropolis.Models.Courses.CoursesEnrollResponse;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.ClearLMSQuizesponse;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.LMSQuizAttemptesponse;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.LMSQuizesponse;
import com.neuronimbus.metropolis.Models.Courses.Lesson.LessonDetailResponse;
import com.neuronimbus.metropolis.Models.ExpertReplyResponse;
import com.neuronimbus.metropolis.Models.FeedbackListResponse;
import com.neuronimbus.metropolis.Models.GameScoreResponse;
import com.neuronimbus.metropolis.Models.GameTimeAuthModel;
import com.neuronimbus.metropolis.Models.LeaderboardResponse;
import com.neuronimbus.metropolis.Models.Courses.Lesson.LessonEnrollResponse;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.LessonUpdateResponse;
import com.neuronimbus.metropolis.Models.NGOProfileResponse;
import com.neuronimbus.metropolis.Models.OldFeedbackChattingResponse;
import com.neuronimbus.metropolis.Models.ProcessingFeedbackChatResponse;
import com.neuronimbus.metropolis.Models.QRCode.QRCodeResponse;
import com.neuronimbus.metropolis.Models.SelectOrganisationAuthModel;
import com.neuronimbus.metropolis.Models.SelectOrganisationResponse;
import com.neuronimbus.metropolis.Models.SplashScreenResponse;
import com.neuronimbus.metropolis.Models.StoreHouse.AllStoreHouseResponse;
import com.neuronimbus.metropolis.Models.AllVideoGalleryResponse;
import com.neuronimbus.metropolis.Models.AvatarResponse;
import com.neuronimbus.metropolis.Models.BlogCategoryResponse;
import com.neuronimbus.metropolis.Models.BlogCommentsResponse;
import com.neuronimbus.metropolis.Models.BlogLikeResponse;
import com.neuronimbus.metropolis.Models.BookmarkBlogResponse;
import com.neuronimbus.metropolis.Models.ClearNotificationResponse;
import com.neuronimbus.metropolis.Models.FAQCategoryResponse;
import com.neuronimbus.metropolis.Models.FAQContentResponse;
import com.neuronimbus.metropolis.Models.FeedbackResponse;
import com.neuronimbus.metropolis.Models.HealthCateResponse;
import com.neuronimbus.metropolis.Models.HealthIssueResponse;
import com.neuronimbus.metropolis.Models.Help.ContactFormResponse;
import com.neuronimbus.metropolis.Models.Help.HelpCategoryResponse;
import com.neuronimbus.metropolis.Models.Help.HelpContentResponse;
import com.neuronimbus.metropolis.Models.Help.HelpFeedbackResponse;
import com.neuronimbus.metropolis.Models.Help.HelpSubCategoryResponse;
import com.neuronimbus.metropolis.Models.HomeScreenResponse;
import com.neuronimbus.metropolis.Models.InsightScreen.InsightScreenResponse;
import com.neuronimbus.metropolis.Models.Language.LanguageResponse;
import com.neuronimbus.metropolis.Models.ManageNotificationListUpdateResponse;
import com.neuronimbus.metropolis.Models.ManageNotificationResponse;
import com.neuronimbus.metropolis.Models.NotificationResponse;
import com.neuronimbus.metropolis.Models.OnBordingResponse;
import com.neuronimbus.metropolis.Models.OtpInResponse;
import com.neuronimbus.metropolis.Models.QuizAnswerResponse;
import com.neuronimbus.metropolis.Models.QuizQueResponse;
import com.neuronimbus.metropolis.Models.RemoveProfileResponse;
import com.neuronimbus.metropolis.Models.SaveHealthCategoryResponse;
import com.neuronimbus.metropolis.Models.SaveHealthIssueResponse;
import com.neuronimbus.metropolis.Models.SaveProfilePicResponse;
import com.neuronimbus.metropolis.Models.SearchResponse;
import com.neuronimbus.metropolis.Models.SignInResponse;
import com.neuronimbus.metropolis.Models.SignupResponse;
import com.neuronimbus.metropolis.Models.SingleBlogResponse;
import com.neuronimbus.metropolis.Models.SingleClearNotificationResponse;
import com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.StoreHouseCategoryResponse;
import com.neuronimbus.metropolis.Models.StoreHouseLikeResponse;
import com.neuronimbus.metropolis.Models.StoreHouseRelatedResponse;
import com.neuronimbus.metropolis.Models.StoreHouseSinglePageResponse;
import com.neuronimbus.metropolis.Models.StoryLikeResponse;
import com.neuronimbus.metropolis.Models.StoryResponse;
import com.neuronimbus.metropolis.Models.StoryShareResponse;
import com.neuronimbus.metropolis.Models.SuccessResponse;
import com.neuronimbus.metropolis.Models.UpdateNGOProfile;
import com.neuronimbus.metropolis.Models.UpdateProfile.UpdateProfileResponse;
import com.neuronimbus.metropolis.Models.UserDetailResponse;
import com.neuronimbus.metropolis.Models.UserProfileResponse;
import com.neuronimbus.metropolis.Models.WordResponse;

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
    @POST("api/StoryShare")
    Observable<StoryShareResponse> getStoryShare(@Body StoryShareAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightscreen")
    Observable<InsightScreenResponse> insightScreenResponse(@Body InsightScreenAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightcategories")
    Observable<BlogCategoryResponse> getBlogCategory(@Body BlogCategoryAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/articlecategoryblogs")
    Observable<AllBlogResponse> getAllBlogs(@Body AllBlogAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightevents")
    Observable<AllEventResponse> getEventBlogs(@Body AllEventAuthModel model);

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

    @GET("api/faqcategory")
    Observable<FAQCategoryResponse> getFAQCategory();

    @Headers("Content-Type: application/json")
    @POST("api/faqcontent")
    Observable<FAQContentResponse> getFAQContent(@Body FAQContentAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/articlelikehelp")
    Observable<BlogLikeResponse> blogLike(@Body BlogLikeAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/quizanswer")
    Observable<QuizAnswerResponse> getQuizAns(@Body QuizAnswerAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/quiz")
    Observable<QuizQueResponse> getQuizQue(@Body QuizQueAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/articlecomments")
    Observable<BlogCommentsResponse> getBlogComment(@Body BlogCommentsAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/allstorhouse")
    Observable<AllStoreHouseResponse> getAllStoreHouse(@Body AllStoreHouseAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/articles_from_category")
    Observable<StoreHouseCategoryResponse> getStoreHouseCategory(@Body StoreHouseCategoryAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/storhouseDetails")
    Observable<StoreHouseSinglePageResponse> getStoreHouseSinglePage(@Body StoreHouseSinglePageAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/storehouseLike")
    Observable<StoreHouseLikeResponse> getStoreHouseLike(@Body StoreHouseLikeAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/relatedstorehouse")
    Observable<StoreHouseRelatedResponse> getStoreHouseRelated(@Body StoreHouseRelatedAuthModel model);

    @GET("api/leaderboard")
    Observable<LeaderboardResponse> getLeaderboard();

    @GET("api/wordgame")
    Observable<WordResponse> getword();

    @Headers("Content-Type: application/json")
    @POST("api/gamesubmit")
    Observable<SuccessResponse> submitwordtime(@Body GameTimeAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/gamescore")
    Observable<GameScoreResponse> gamescore(@Body GameScoreAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/articlebookmarked")
    Observable<BookmarkResponse> getBookmark(@Body BookmarkAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/experthelpstatus")
    Observable<AskIssuesFeedbackResponse> getAskIssuesFeedback(@Body AskIssuesFeedbackAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/askquestion")
    Observable<AskQuestionsResponse> getAskQuestions(@Body AskQuestionsAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/expertchats")
    Observable<ExpertReplyResponse> getExpertReply(@Body ExpertReplyAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/infostorehouse_titles")
    Observable<AskIssuesResponse> getAskIssues(@Body AskIssuesAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/insightcourses")
    Observable<AllCoursesResponse> getCoursesBlogs(@Body AllCoursesAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/enrollcourse")
    Observable<CoursesEnrollResponse> getCoursesEnroll(@Body CoursesEnrollAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/coursebyid")
    Observable<CoursesDetailResponse> getCoursesDetail(@Body CoursesDetailAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/enrolllesson")
    Observable<LessonEnrollResponse> getLessonEnroll(@Body LessonEnrollAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/lessonbyid")
    Observable<LessonDetailResponse> getLessonDetail(@Body LessonDetailAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/lmsquiz")
    Observable<LMSQuizesponse> getLMSQuiz(@Body LMSQuizAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/quizattempt")
    Observable<LMSQuizAttemptesponse> getLMSQuizAttempt(@Body LMSQuizAttemptAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/clearquizattempt")
    Observable<ClearLMSQuizesponse> getClearLMSQuiz(@Body ClearLMSQuizAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/update_lesson_status")
    Observable<LessonUpdateResponse> getLessonUpdate(@Body LessonUpdateAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/feedbackhistorychats")
    Observable<FeedbackListResponse> getFeedbackList(@Body FeedbackListAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/processingfeedbackchat")
    Observable<ProcessingFeedbackChatResponse> getProcessingFeedback(@Body ProcessingFeedbackChatAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/addcomplaints")
    Observable<AddComplaintResponse> getAddComplaint(@Body AddComplaintAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/complainthistorychats")
    Observable<ComplaintListResponse> getComplaintList(@Body CommonAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/resolvedfeedbackchat")
    Observable<OldFeedbackChattingResponse> getOldFeedback(@Body ProcessingFeedbackChatAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/askfeedback")
    Observable<CommonResponse> askFeedback(@Body AskFeedbackAuthModel model);

    //NGO API

    @Headers("Content-Type: application/json")
    @POST("api/register_ngo")
    Observable<SignupResponse> ngoRegister(@Body NGOSignupAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/ngo_projects")
    Observable<SelectOrganisationResponse> ngoRegister(@Body CommonAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/save_organizations")
    Observable<CommonResponse> saveSelectOrganisation(@Body SelectOrganisationAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/splashscreen")
    Observable<SplashScreenResponse> splashScreen(@Body CommonAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/ngoprofile")
    Observable<NGOProfileResponse> getNGOProfile(@Body CommonAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/updatengoprofile")
    Observable<CommonResponse> updateNGOProfile(@Body UpdateNGOProfile model);

    @Headers("Content-Type: application/json")
    @POST("api/myqrcode")
    Observable<QRCodeResponse> qrCodeInfo(@Body CommonAuthModel model);

    @Headers("Content-Type: application/json")
    @POST("api/qrcodecount")
    Observable<CommonResponse> qrCodeCount(@Body QRCodeCountAuthModel model);
}
