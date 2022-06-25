package fpt.aptech.projectcard.callApiService;

import fpt.aptech.projectcard.Payload.request.LoginRequest;
import fpt.aptech.projectcard.Payload.request.ProductRequest;
import fpt.aptech.projectcard.Payload.request.SignupRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST(ApiConstant.URL_LOGIN)
    Call<LoginRequest> signin(@Body LoginRequest loginRequest);

    @POST(ApiConstant.URL_SIGNUP)
    Call<SignupRequest> signup(@Body SignupRequest signupRequest);

    @GET(ApiConstant.URL_GETPRODUCT_INFO_BY_USERID)
    Call<ProductRequest> getProduct( @Path("user_id") Long user_id, @Query("Authorization") String token);//use @Query to Add access token to Authorization
}
