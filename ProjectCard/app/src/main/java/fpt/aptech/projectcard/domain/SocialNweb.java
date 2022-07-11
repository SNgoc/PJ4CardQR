package fpt.aptech.projectcard.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialNweb {

    @SerializedName("social_id") // key name of key:value when retrieve data from json type
    @Expose
    private Long social_id;

    @SerializedName("facebook")
    @Expose
    private String facebook;

    @SerializedName("twitter")
    @Expose
    private String twitter;

    @SerializedName("instagram")
    @Expose
    private String instagram;

    @SerializedName("tiktok")
    @Expose
    private String tiktok;

    @SerializedName("web1")
    @Expose
    private String web1;

    @SerializedName("web2")
    @Expose
    private String web2;

    @SerializedName("company1")
    @Expose
    private String company1;

    @SerializedName("company2")
    @Expose
    private String company2;

    @SerializedName("user")
    @Expose
    private User user;

    public SocialNweb() {
    }

    public SocialNweb(String facebook, String twitter, String instagram, String tiktok, String web1, String web2, String company1, String company2) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.web1 = web1;
        this.web2 = web2;
        this.company1 = company1;
        this.company2 = company2;
    }

    public SocialNweb(String facebook, String twitter, String instagram, String tiktok, String web1, String web2, String company1, String company2, User user) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.web1 = web1;
        this.web2 = web2;
        this.company1 = company1;
        this.company2 = company2;
        this.user = user;
    }

    public Long getSocial_id() {
        return social_id;
    }

    public void setSocial_id(Long social_id) {
        this.social_id = social_id;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public String getWeb1() {
        return web1;
    }

    public void setWeb1(String web1) {
        this.web1 = web1;
    }

    public String getWeb2() {
        return web2;
    }

    public void setWeb2(String web2) {
        this.web2 = web2;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}