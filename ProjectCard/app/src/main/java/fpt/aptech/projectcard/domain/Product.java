package fpt.aptech.projectcard.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Product {

    private Long id;

    private String description;

    private String name;

    private String url;

    private String imageUrlcode;

    private User user;

    private int status;

    private String token;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    private LocalDateTime delete_at;

    public Product(String description, String name, String url, String imageUrlcode, User user, LocalDateTime create_at,int status,String token) {
        this.description = description;
        this.name = name;
        this.url = url;
        this.imageUrlcode = imageUrlcode;
        this.user = user;
        this.create_at = create_at;
        this.status = status;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrlcode() {
        return imageUrlcode;
    }

    public void setImageUrlcode(String imageUrlcode) {
        this.imageUrlcode = imageUrlcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public LocalDateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        this.update_at = update_at;
    }

    public LocalDateTime getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(LocalDateTime delete_at) {
        this.delete_at = delete_at;
    }
}
