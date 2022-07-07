package com.example.demo.Payload.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Data
public class UpdateProfile {

    @NotBlank(message = "Lastname is not blank")
    @Size(min = 5,max = 15,message = "Lastname is min 5 character and max 15 character")
    private String lastname;


    @NotBlank(message = "Lastname is not blank")
    @Size(min = 20,max = 200,message = "description is min 20 character and max 200 character")
    private String description;

    @NotBlank(message = "Lastname is not blank")
    @Size(min = 10,max = 30,message = "Lastname is min 10 character and max 30 character")
    private String fullname;

    @NotBlank(message = "Phone is not blank")
    @Pattern(regexp="^[0-9]+$", message="the value must be positive integer")
    private String phone;

    @NotBlank(message = "address is not blank")
    private String address;

    @Email
    @NotBlank(message = "email is not blank")
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfbirth;

    private Boolean gender;

    @NotBlank(message = "Province is not blank")
    private String province;//add when merge code
}
