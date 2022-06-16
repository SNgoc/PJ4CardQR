package fpt.aptech.projectcard.ui.login;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.logging.Level;
import java.util.logging.Logger;

import fpt.aptech.projectcard.Payload.request.LoginRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
import fpt.aptech.projectcard.retrofit.RetrofitService;
import fpt.aptech.projectcard.ui.home.HomeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private View view;
    private NavigationView navMenu;
    //update menu
    private MenuItem item;
    //login
    private TextView txtError;
    private EditText editUsername,editPassword;
    private Button btnLogin, btnRegister;
    //change fragment when click button
    private FragmentTransaction fragmentTransaction;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//get navigation menu from activity_main_drawer
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        //initialize your view here for use view.findViewById("your view id")
        editUsername = view.findViewById(R.id.editUsername);
        editPassword = view.findViewById(R.id.editPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        //Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                if (validateUsername() && validatePassword()) {

                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(username);
                    loginRequest.setPassword(password);
                    // call login api
                    ApiService apiService =RetrofitService.getInstance().create(ApiService.class);
                    apiService.signin(loginRequest).enqueue(new Callback<LoginRequest>() {
                        @Override
                        public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                            if (response.body() != null) {
                                Toast.makeText(getActivity().getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                //reset fill after login success
                                editUsername.setText("");
                                editPassword.setText("");
                                //set to user email when login success
                                TextView txtEmail = getActivity().findViewById(R.id.textViewMail);
                                txtEmail.setText(response.body().getUsername());
                                //if login success, change Login item to Logout item
                                navMenu = getActivity().findViewById(R.id.nav_view);
                                item = navMenu.getMenu().findItem(R.id.nav_login);
                                item.setTitle("Logout");
                                //change to fragment_home
                                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                HomeFragment homeFragment = new HomeFragment();
                                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,homeFragment);
                                fragmentTransaction.commit();
                            }
                            if(response.code() == 401){
                                Toast.makeText(getActivity().getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginRequest> call, Throwable t) {
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change to fragment_register
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                RegisterFragment registerFragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,registerFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    //login
    //validate
    private boolean validateUsername() {
        if (TextUtils.isEmpty(editUsername.getText().toString())) {
            editUsername.setError("username cannot be empty");
            editUsername.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (TextUtils.isEmpty(editPassword.getText().toString())) {
            editPassword.setError("password cannot be empty");
            editPassword.requestFocus();
            return false;
        }
        return true;
    }

    public void loginMain(String username, String password){
        if (username.isEmpty() || password.isEmpty()) {
            editUsername.setError("Please fill your username");
            editPassword.setError("Please fill your password");
//            txtError.setText("Please fill your username and password");
//            txtError.setTextColor(Color.GREEN);
            return;
        }
        if (username.equals("admin") && password.equals("123")) {
            txtError.setText("");
            //set to user email when login success
            TextView txtEmail = getActivity().findViewById(R.id.textViewMail);
            txtEmail.setText(username + "@gmail.com");

            //if login success, change Login item to Logout item
            navMenu = getActivity().findViewById(R.id.nav_view);
            item = navMenu.getMenu().findItem(R.id.nav_login);
            item.setTitle("Logout");
            //change to fragment_home
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,homeFragment);
            fragmentTransaction.commit();
        } else{
            txtError.setText("Invalid username or password");
            txtError.setTextColor(Color.RED);
            editUsername.setText("");
            editPassword.setText("");
        }
    }
}