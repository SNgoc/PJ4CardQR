package com.example.projectclient.Service;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.Category;
import com.example.projectclient.Models.User;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CategoryService {
    private final static String BASE_URL = "http://localhost:8080";

    public HttpResponse<String> ShowAll(HttpSession session) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/category/list"))
                .GET()
                .headers("Content-Type","application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public HttpResponse<String> delete(int id,HttpSession session) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/category/delete/" + id))
                .GET()
                .headers("Content-Type","application/json")
                .header("Authorization","Bearer " + session.getAttribute("token"))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public Response add(Category cate, File front, File back, HttpSession session) throws IOException, InterruptedException {
        Path currentRelativePath = Paths.get("D:/ProjectClient/user-photos");
        String path = currentRelativePath.toAbsolutePath().toString();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name", cate.getName())
                .addFormDataPart("price", String.valueOf(cate.getPrice()))
                .addFormDataPart("front", path+"/"+front.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(path+"/"+front.getName())))
                .addFormDataPart("back", path+"/"+back.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(path+"/"+back.getName())))
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/category/add")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }


    public Category details(int id, HttpSession session) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/category/details/" + id))
                .GET()
                .headers("Content-Type","application/json")
                .header("Authorization","Bearer " + session.getAttribute("token"))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        JSONObject ob = new JSONObject(response.body());
        Category category = JSONUtils.convertToObject(Category.class,ob.toString());
        return category;
    }

}
