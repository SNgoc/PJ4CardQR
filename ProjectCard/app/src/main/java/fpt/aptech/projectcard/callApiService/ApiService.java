package fpt.aptech.projectcard.callApiService;

import fpt.aptech.projectcard.Payload.request.LoginRequest;
import fpt.aptech.projectcard.Payload.request.SignupRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST(ApiConstant.URL_LOGIN)
    Call<LoginRequest> signin(@Body LoginRequest loginRequest);

    @POST(ApiConstant.URL_SIGNUP)
    Call<SignupRequest> signup(@Body SignupRequest signupRequest);
}
