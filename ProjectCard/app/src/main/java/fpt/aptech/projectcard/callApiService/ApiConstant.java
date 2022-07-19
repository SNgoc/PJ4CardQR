package fpt.aptech.projectcard.callApiService;

public class ApiConstant {

    public static final String STATUS_CODE_SUCCESS = "200";
    public static final String BASE_URL = "http://192.168.241.2:8080";
    public static final String URL_LOGIN = "/api/auth/signin";
    public static final String URL_SIGNUP = "/api/auth/signup";
    public static final String URL_PROFILE = "/api/auth/profile/{username}";
    public static final String URL_UPDATE_PROFILE = "/api/auth/profile/updateProfile/{user_id}";
    public static final String URL_GET_ORDER_BY_USERNAME = "/api/android/listByUsername/{username}";
    public static final String URL_GET_PRODUCT_INFO_BY_USERID = "/api/android/getProduct/{username}";
    public static final String URL_GET_SOCIAL_URL_BY_USERNAME = "/api/urlProduct/list/{username}";
    public static final String URL_GET_LIST_LINK_TYPE_URL = "/api/android/listLinkType";
    public static final String URL_ADD_NEW_URL = "/api/android/addUrl";
}
