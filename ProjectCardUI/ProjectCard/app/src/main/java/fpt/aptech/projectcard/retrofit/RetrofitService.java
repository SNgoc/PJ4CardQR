package fpt.aptech.projectcard.retrofit;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fpt.aptech.projectcard.callApiService.ApiConstant;
import fpt.aptech.projectcard.session.SessionManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    //connect api
    public static Retrofit getInstance() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS) // connect timeout
                        .writeTimeout(5, TimeUnit.SECONDS) // write timeout
                        .readTimeout(5, TimeUnit.SECONDS) // read timeout
                        .addInterceptor(httpLoggingInterceptor).build());
        return builder.build();
    }

    //add access bearer token auth
    public static Retrofit proceedToken() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @NonNull
                            @Override
                            public Response intercept(@NonNull Chain chain) throws IOException {
                                Request request=chain.request().newBuilder()
                                        .addHeader("Authorization", "Bearer " + SessionManager.getSaveToken())
                                        .build();
                                return chain.proceed(request);
                            }
                        })
                        .addInterceptor(httpLoggingInterceptor).build());
        return builder.build();
    }
}
