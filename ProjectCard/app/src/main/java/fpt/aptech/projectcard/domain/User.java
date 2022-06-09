package fpt.aptech.projectcard.domain;

import java.util.*;


public class User {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String linkImage;

    private String phone;

    private String address;
    private String nameImage;
    private Boolean enable;

    private String fullname;

    private String lastname;

    private String description;
    private Boolean locked;

    private Set<Role> roles = new HashSet<>();

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, Set<Role> roles, String nameImage, String linkImage,String phone,String address,String fullname,String lastname,String description) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.linkImage = linkImage;
        this.nameImage = nameImage;
        this.address = address;
        this.phone = phone;
        this.fullname = fullname;
        this.lastname = lastname;
        this.description = description;
    }

    public User(String username, String email, String password, Boolean enable, Boolean locked) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.locked = locked;

    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
