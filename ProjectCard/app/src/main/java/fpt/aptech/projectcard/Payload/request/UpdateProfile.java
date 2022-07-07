package fpt.aptech.projectcard.Payload.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class UpdateProfile implements Serializable {

    //model to get user info from data json
    @SerializedName("lastname") // key name of key:value when retrieve data from json type
    @Expose
    private String lastname;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("dateOfbirth")
    @Expose
    private String dateOfbirth;

    @SerializedName("gender")
    @Expose
    private Boolean gender;

    @SerializedName("province")
    @Expose
    private String province;

    public UpdateProfile() {
    }

    public UpdateProfile(String lastname, String description, String fullname, String phone, String address, String email, String dateOfbirth, Boolean gender, String province) {
        this.lastname = lastname;
        this.description = description;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.dateOfbirth = dateOfbirth;
        this.gender = gender;
        this.province = province;
    }

    // post raw json manual
//    @Override
//    public String toString() {
//        return '{' +
//                "\"lastname\" : \"" + lastname + '\"' +
//                ", \"description\" : \"" + description + '\"' +
//                ", \"fullname\" : \"" + fullname + '\"' +
//                ", \"phone\" : \"" + phone + '\"' +
//                ", \"address\" : \"" + address + '\"' +
//                ", \"email\" : \"" + email + '\"' +
//                ", \"dateOfbirth\" : \"" + dateOfbirth + '\"' +
//                ", \"gender\" :" + gender +
//                ", \"province\" : \"" + province + '\"' +
//                '}';
//    }
}
