package fpt.aptech.projectcard.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fpt.aptech.projectcard.Payload.request.SignupRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
import fpt.aptech.projectcard.retrofit.RetrofitService;
import fpt.aptech.projectcard.retrofit.catchError.APIError;
import fpt.aptech.projectcard.retrofit.catchError.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edUsername,edPassword,edConfirmPassword,edEmail,edPhone,edFullname,edLastname,edGender,edAddress,edProvince,edDescription;
    private TextView edbirth;
    RadioGroup rgGender;
    RadioButton getGenderSelected;
    //date picker: to get current date for click
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private View view;
    private Button btnRegister, btnBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize your view here for use view.findViewById("your view id")
        edUsername = findViewById(R.id.editUsername);
        edPassword = findViewById(R.id.editPassword);
        edConfirmPassword = findViewById(R.id.editConfirmPassword);
        edEmail = findViewById(R.id.editEmail);
        edFullname = findViewById(R.id.editFullname);
        edLastname = findViewById(R.id.editLastname);
        edbirth = findViewById(R.id.txtEdBirth);
        edPhone = findViewById(R.id.editPhone);
        //gender
        rgGender = (RadioGroup)findViewById(R.id.rgGender);
        edAddress = findViewById(R.id.editAddress);
        edDescription = findViewById(R.id.editDescription);
        edProvince = findViewById(R.id.editProvince);
        btnBirthday = findViewById(R.id.btnBirthday);
        btnRegister = findViewById(R.id.btnRegister);

        //==========================DATE PICKER=========================================
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        //date picker
        btnBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (monthOfYear <10) {
                            if (dayOfMonth <10) {
                                //thêm số 0 vào đúng định dạng mới dùng order by date trong db
                                //month + 1 vì month trong calendar có values từ 0-11
                                edbirth.setText(year+ "-" + "0"+(monthOfYear + 1) + "-" + "0"+dayOfMonth);
                            }
                            else{
                                edbirth.setText(year+ "-" + "0"+(monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }
                        else {
                            if (dayOfMonth <10) {
                                edbirth.setText(year+ "-" + (monthOfYear + 1) + "-" + "0"+dayOfMonth);
                            }
                            else {
                                edbirth.setText(year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }
                        lastSelectedYear = year;
                        lastSelectedMonth = monthOfYear;
                        lastSelectedDayOfMonth = dayOfMonth;
                    }
                };
                DatePickerDialog datePickerDialog = null;
                // Calendar Mode (Default):
                datePickerDialog = new DatePickerDialog(RegisterActivity.this, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
                // Show
                datePickerDialog.show();
            }
        });
        //=====================end date picker================================

        //register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String fullname = edFullname.getText().toString().trim();
                String lastname = edLastname.getText().toString().trim();
                String phone = edPhone.getText().toString().trim();
                String address = edAddress.getText().toString().trim();
                String description = edDescription.getText().toString().trim();
                String province = edProvince.getText().toString().trim();
                String birthday = edbirth.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String confirmPass = edConfirmPassword.getText().toString().trim();
                boolean gender;
                //gender
                getGenderSelected = (RadioButton) rgGender.findViewById(rgGender.getCheckedRadioButtonId());
                if (getGenderSelected.getText().equals("Male")) {
                    gender = true;
                } else {
                    gender = false;
                }
                //validate
                if (validateRegister(username,email,fullname,lastname,phone,address,description,
                        province,birthday,password,confirmPass)) {
                    //Date type
                    //compare date, check beginDate and endDate
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date start =sdf.parse(birthday);
                        Calendar c = Calendar.getInstance();
                        c.setTime(start);
                        c.add(Calendar.DATE,1);// cộng 1 ngày so với ngày hiện tại đề dùng hàm after add đc ngày hiên tại
                        start = c.getTime();
                        if (start.after(sdf.parse("2004-01-01"))) { //check date, new Date to get current date
                            edbirth.setError("Birthday must be less than or equal 2004-01-01");
                            edbirth.requestFocus();
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Set<String> role = new HashSet<String>();
                    ////
                    SignupRequest signupRequest = new SignupRequest
                            (
                                    username, email, password, role,phone,address,fullname,lastname,description,birthday,gender,province
                            );
                    // call login api
                    ApiService apiService = RetrofitService.getInstance().create(ApiService.class);
                    apiService.signup(signupRequest).enqueue(new Callback<SignupRequest>() {
                        @Override
                        public void onResponse(@NonNull Call<SignupRequest> call, @NonNull Response<SignupRequest> response) {//connect spring boot success
                            if (response.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Sign up successful, please check mail to active", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                // parse the response body …
                                APIError error = ErrorUtils.parseError(response);
                                // … and use it to show error information
                                Toast.makeText(RegisterActivity.this, error.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<SignupRequest> call, @NonNull Throwable t) {//when can't connect to spring boot
                            Toast.makeText(RegisterActivity.this, "Successful, please check mail to active", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    //validate
    private boolean validateRegister(String username,String email,String fullname,String lastname,String phone,
                                     String address,String description,String province,String birthday, String password,String confirmPass) {
        if (TextUtils.isEmpty(username)) {
            edUsername.setError("Username cannot be empty");
            edUsername.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(email)) {
            edEmail.setError("Email cannot be empty");
            edEmail.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(fullname)) {
            edFullname.setError("Fullname cannot be empty");
            edFullname.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(lastname)) {
            edLastname.setError("Lastname cannot be empty");
            edLastname.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(phone)) {
            edPhone.setError("Phone cannot be empty");
            edPhone.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(birthday)) {
            edbirth.setError("Birthday cannot be empty");
            edbirth.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(address)) {
            edAddress.setError("Address cannot be empty");
            edAddress.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(province)) {
            edProvince.setError("Province cannot be empty");
            edProvince.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(description)) {
            edDescription.setError("Description cannot be empty");
            edDescription.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(password)) {
            edPassword.setError("Password cannot be empty");
            edPassword.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(confirmPass)
                || !TextUtils.equals(confirmPass,password)) {
            edConfirmPassword.setError("Confirm password don't match Password");
            edConfirmPassword.requestFocus();
            return false;
        }
        return true;
    }

}