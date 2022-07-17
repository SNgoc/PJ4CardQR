package fpt.aptech.projectcard.callApiService;

import java.util.List;

import fpt.aptech.projectcard.Payload.request.LoginRequest;
import fpt.aptech.projectcard.Payload.request.ProductRequest;
import fpt.aptech.projectcard.Payload.request.SignupRequest;
import fpt.aptech.projectcard.Payload.request.SocialNWebRequest;
import fpt.aptech.projectcard.Payload.request.UpdateProfile;
import fpt.aptech.projectcard.domain.Orders;
import fpt.aptech.projectcard.domain.SocialNweb;
import fpt.aptech.projectcard.domain.UrlProduct;
import fpt.aptech.projectcard.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST(ApiConstant.URL_LOGIN)
    Call<LoginRequest> signin(@Body LoginRequest loginRequest);

    @POST(ApiConstant.URL_SIGNUP)
    Call<SignupRequest> signup(@Body SignupRequest signupRequest);

    @GET(ApiConstant.URL_GET_ORDER_BY_USERNAME)
    Call<List<Orders>> getOrdersByUsername(@Path("username") String username);//use @Query to Add access token to Authorization

    @GET(ApiConstant.URL_GETPRODUCT_INFO_BY_USERID)
    Call<ProductRequest> getProduct(@Path("username") String username, @Query("Authorization") String token);//use @Query to Add access token to Authorization

    @GET(ApiConstant.URL_PROFILE)
    Call<User> getProfile(@Path("username") String username);

    @POST(ApiConstant.URL_UPDATE_PROFILE)
    Call<UpdateProfile> updateProfile(@Path("user_id") Long user_id, @Body UpdateProfile updateProfile);

    @GET(ApiConstant.URL_GETSOCIAL_BY_USERID)
    Call<SocialNweb> getSocialInfo(@Path("user_id") Long user_id, @Query("Authorization") String token);

    @POST(ApiConstant.URL_ADD_SOCIAL)
    Call<SocialNWebRequest> addSocialNWeb(@Body SocialNWebRequest socialNWebRequest, @Query("Authorization") String token);

    @POST(ApiConstant.URL_UPDATE_SOCIAL)
    Call<SocialNWebRequest> updateSocialNWeb(@Path("social_id") Long social_id, @Body SocialNWebRequest socialNWebRequest, @Query("Authorization") String token);

    //new
    @GET(ApiConstant.URL_GET_SOCIALURL_BY_USERNAME)
    Call<List<UrlProduct>> getUrlProduct(@Path("username") String username);


    // post raw json
//    @Headers("Content-Type: application/json")
//    @POST(ApiConstant.URL_UPDATE_PROFILE)
//    Call<UpdateProfile> updateProfile(@Path("user_id") Long user_id, @Body String updateProfile);
}
