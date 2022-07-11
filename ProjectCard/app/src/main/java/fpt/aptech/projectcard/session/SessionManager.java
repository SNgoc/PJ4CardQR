package fpt.aptech.projectcard.session;

import android.content.Context;
import android.content.SharedPreferences;

import fpt.aptech.projectcard.domain.SocialNweb;
import fpt.aptech.projectcard.domain.User;

public class SessionManager {
    private static Long saveUserID;
    private static String saveUsername;
    private static String saveEmail;
    private static String saveFullname;
    private static String saveToken;
    private static String saveLinkImage;
    private static User saveUser;
    private static SocialNweb saveSocialNweb;

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

    public static User getSaveUser() {
        return saveUser;
    }

    public static void setSaveUser(User saveUser) {
        SessionManager.saveUser = saveUser;
    }

    public static SocialNweb getSaveSocialNweb() {
        return saveSocialNweb;
    }

    public static void setSaveSocialNweb(SocialNweb saveSocialNweb) {
        SessionManager.saveSocialNweb = saveSocialNweb;
    }

    public static boolean isStopCode() {
        return stopCode;
    }

    public static void setStopCode(boolean stopCode) {
        SessionManager.stopCode = stopCode;
    }
}
