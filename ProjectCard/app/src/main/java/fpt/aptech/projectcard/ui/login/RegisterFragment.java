package fpt.aptech.projectcard.ui.login;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import fpt.aptech.projectcard.MainActivity;
import fpt.aptech.projectcard.Payload.request.SignupRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
import fpt.aptech.projectcard.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    private EditText edUsername, edPassword, edConfirmPassword, edEmail, edPhone, edFullname, edLastname, edbirth, edGender, edAddress, edProvince, edDescription;
    private View view;
    private Button btnRegister;
    private NavigationView navMenu;
    //change fragment when click button
    private FragmentTransaction fragmentTransaction;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set title bar to Register
        ((MainActivity) getActivity()).setActionBarTitle("Sign Up");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        //initialize your view here for use view.findViewById("your view id")
        edUsername = view.findViewById(R.id.editUsername);
        edPassword = view.findViewById(R.id.editPassword);
        edConfirmPassword = view.findViewById(R.id.editConfirmPassword);
        edEmail = view.findViewById(R.id.editEmail);
        edFullname = view.findViewById(R.id.editFullname);
        edLastname = view.findViewById(R.id.editLastname);
        edbirth = view.findViewById(R.id.editBirth);
        edPhone = view.findViewById(R.id.editPhone);
        edGender = view.findViewById(R.id.editGender);
        edAddress = view.findViewById(R.id.editAddress);
        edDescription = view.findViewById(R.id.editDescription);
        edProvince = view.findViewById(R.id.editProvince);
        btnRegister = view.findViewById(R.id.btnRegister);

        //register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String confirmPass = edConfirmPassword.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String fullname = edFullname.getText().toString().trim();
                String lastname = edLastname.getText().toString().trim();
                String phone = edPhone.getText().toString().trim();
                String address = edAddress.getText().toString().trim();
                String description = edDescription.getText().toString().trim();
                String province = edProvince.getText().toString().trim();
                boolean gender = Boolean.parseBoolean(edGender.getText().toString());
                //Date type
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = df.parse(edbirth.getText().toString().trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String birth = df.format(date);
                Set<String> role = new HashSet<String>();
                ////
                SignupRequest signupRequest = new SignupRequest
                    (
                        username, email, password, role,phone,address,fullname,lastname,description,birth,gender,province
                    );
                // call login api
                ApiService apiService = RetrofitService.getInstance().create(ApiService.class);
                apiService.signup(signupRequest).enqueue(new Callback<SignupRequest>() {
                    @Override
                    public void onResponse(Call<SignupRequest> call, Response<SignupRequest> response) {
                        if (response.body() != null) {
                            Toast.makeText(getActivity().getApplicationContext(), "Sign up successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignupRequest> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Sign up fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
}