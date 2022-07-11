package fpt.aptech.projectcard.callApiService;

public class ApiConstant {

    public static final String STATUS_CODE_SUCCESS = "200";
    public static final String BASE_URL = "http://192.168.241.2:8080";
    public static final String URL_LOGIN = "/api/auth/signin";
    public static final String URL_SIGNUP = "/api/auth/signup";
    public static final String URL_GETPRODUCT_INFO_BY_USERID = "/api/product/getProduct/{user_id}";
    public static final String URL_PROFILE = "/api/auth/profile/{username}";
    public static final String URL_UPDATE_PROFILE = "/api/auth/profile/updateProfile/{user_id}";
    public static final String URL_GETSOCIAL_BY_USERID = "/api/social/getSocialAndProfile/{user_id}";
}
