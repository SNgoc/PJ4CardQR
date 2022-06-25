package fpt.aptech.projectcard.Payload.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import fpt.aptech.projectcard.domain.User;
import okhttp3.MultipartBody;

public class ProductRequest {

    @SerializedName("id")
    @Expose
    private Long id ;

    private LocalDateTime create_at;

    private LocalDateTime delete_at;

    private LocalDateTime update_at;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imgQRCode")
    @Expose
    private MultipartBody.Part qrcode;

    @SerializedName("urlQRCode")
    @Expose
    private MultipartBody.Part url;

    private Long user_id;

    @SerializedName("userInfo")
    @Expose
    private User userData;

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public LocalDateTime getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(LocalDateTime delete_at) {
        this.delete_at = delete_at;
    }

    public LocalDateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        this.update_at = update_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartBody.Part getQrcode() {
        return qrcode;
    }

    public void setQrcode(MultipartBody.Part qrcode) {
        this.qrcode = qrcode;
    }

    public MultipartBody.Part getUrl() {
        return url;
    }

    public void setUrl(MultipartBody.Part url) {
        this.url = url;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
