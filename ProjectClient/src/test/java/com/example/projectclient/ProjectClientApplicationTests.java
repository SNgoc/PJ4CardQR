package com.example.projectclient;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

@SpringBootTest
class ProjectClientApplicationTests {





    @Test
    void contextLoads() {
    }
    private final static String BASE_URL = "http://localhost:8080";
    @Test
     void login() throws IOException, InterruptedException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("username","Nghia1")
                .addFormDataPart("image","C:\\Users\\nghia\\Downloads\\imager_15908.jpg",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("C:\\Users\\nghia\\Downloads\\imager_15908.jpg")))
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/auth/profile/changeImageProfile")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response);

    }
}
