package fpt.aptech.projectcard.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Product {

    private Long id;

    private String description;

    private String name;

    private String avatar;

    private String url;

    private String imageUrlcode;

    private User user;

    private int status;
    private int year;
    private int count;

    private String token;

    private Date createdAt;

    private Date update_at;

    private Date delete_at;

    public Product() {
    }

    public Product(String description, String name, String avatar, String url, String imageUrlcode, User user, Date createdAt, int status, int year, String token) {
        this.description = description;
        this.name = name;
        this.avatar = avatar;
        this.url = url;
        this.imageUrlcode = imageUrlcode;
        this.user = user;
        this.createdAt = createdAt;
        this.status = status;
        this.year = year;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Date getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(Date delete_at) {
        this.delete_at = delete_at;
    }
}
