package fpt.aptech.projectcard.retrofit;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import fpt.aptech.projectcard.callApiService.ApiConstant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
//    private Retrofit retrofit;

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    public static Retrofit getInstance() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.241.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                        .readTimeout(5, TimeUnit.MINUTES) // read timeout
                        .addInterceptor(httpLoggingInterceptor).build());
        return builder.build();
    }

//    public Retrofit getRetrofit() {
//        return retrofit;
//    }
}
