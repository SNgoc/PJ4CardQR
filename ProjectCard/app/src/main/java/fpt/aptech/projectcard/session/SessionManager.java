package fpt.aptech.projectcard.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static Long saveUserID;
    private static String saveUsername;
    private static String saveEmail;
    private static String saveFullname;
    private static String saveToken;
    private static String saveLinkImage;
    private static String facebookLink;
    private static String twitterLink;
    private static String instagramLink;
    private static boolean stopCode = false;

    public SessionManager() {
    }

    public static Long getSaveUserID() {
        return saveUserID;
    }

    public static void setSaveUserID(Long saveUserID) {
        SessionManager.saveUserID = saveUserID;
    }

    public static String getSaveUsername() {
        return saveUsername;
    }

    public static void setSaveUsername(String saveUsername) {
        SessionManager.saveUsername = saveUsername;
    }

    public static String getSaveEmail() {
        return saveEmail;
    }

    public static void setSaveEmail(String saveEmail) {
        SessionManager.saveEmail = saveEmail;
    }

    public static String getSaveFullname() {
        return saveFullname;
    }

    public static void setSaveFullname(String saveFullname) {
        SessionManager.saveFullname = saveFullname;
    }

    public static String getSaveToken() {
        return saveToken;
    }

    public static void setSaveToken(String saveToken) {
        SessionManager.saveToken = saveToken;
    }

    public static String getSaveLinkImage() {
        return saveLinkImage;
    }

    public static void setSaveLinkImage(String saveLinkImage) {
        SessionManager.saveLinkImage = saveLinkImage;
    }

    public static String getFacebookLink() {
        return facebookLink;
    }

    public static void setFacebookLink(String facebookLink) {
        SessionManager.facebookLink = facebookLink;
    }

    public static String getTwitterLink() {
        return twitterLink;
    }

    public static void setTwitterLink(String twitterLink) {
        SessionManager.twitterLink = twitterLink;
    }

    public static String getInstagramLink() {
        return instagramLink;
    }

    public static void setInstagramLink(String instagramLink) {
        SessionManager.instagramLink = instagramLink;
    }

    public static boolean isStopCode() {
        return stopCode;
    }

    public static void setStopCode(boolean stopCode) {
        SessionManager.stopCode = stopCode;
    }
}
