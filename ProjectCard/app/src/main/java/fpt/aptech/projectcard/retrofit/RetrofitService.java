package fpt.aptech.projectcard.retrofit;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    //connect api for login and register
    public static Retrofit getInstance() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())//post raw json
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS) // connect timeout
                        .writeTimeout(5, TimeUnit.SECONDS) // write timeout
                        .readTimeout(5, TimeUnit.SECONDS) // read timeout
                        .addInterceptor(httpLoggingInterceptor).build());
        return builder.build();
    }

    //add access bearer token auth for access authorized to get info from db
    public static Retrofit proceedToken() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();//view error
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS) // connect timeout
                        .writeTimeout(5, TimeUnit.SECONDS) // write timeout
                        .readTimeout(5, TimeUnit.SECONDS) // read timeout
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
