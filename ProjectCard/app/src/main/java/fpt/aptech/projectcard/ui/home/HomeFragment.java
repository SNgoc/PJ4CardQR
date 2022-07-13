package fpt.aptech.projectcard.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fpt.aptech.projectcard.MainActivity;
import fpt.aptech.projectcard.Payload.request.ProductRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
import fpt.aptech.projectcard.domain.SocialNweb;
import fpt.aptech.projectcard.domain.User;
import fpt.aptech.projectcard.retrofit.RetrofitService;
import fpt.aptech.projectcard.session.SessionManager;
import fpt.aptech.projectcard.ui.login.LoginActivity;
import fpt.aptech.projectcard.ui.profile.ProfileFragment;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //header title card
    TextView txtFrontHeaderCard,txtBehindHeaderCard;
    //front card
    TextView txtName,txtEmail,txtPhone,txtAddress,txtBirthday,txtProvince, txtGender;
    //behind card
    TextView txtFacebook,txtTwitter,txtInstagram, txtWeb1, txtCompany1;
    ImageView imgAvatar, imgQR;
    //click event for layout
    ConstraintLayout layoutCard_front, layoutCard_behind;
    LinearLayout layout_updateProfile;
    //Button
    Button btnUpdateProfile;
    private String username,password;
    //change fragment
    private FragmentTransaction fragmentTransaction;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        // can't Set title bar to Home will error null at setActionBarTitle because home is start fragment
//        ((MainActivity) getActivity()).setActionBarTitle("Home");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onStart();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        txtFrontHeaderCard = view.findViewById(R.id.txt_front_header_card);
        txtBehindHeaderCard = view.findViewById(R.id.txt_behind_header_card);
        txtName = view.findViewById(R.id.txt_Name);
        txtEmail = view.findViewById(R.id.txt_Email);
        txtBirthday = view.findViewById(R.id.txt_Birthday);
        txtPhone = view.findViewById(R.id.txt_Phone);
        txtAddress = view.findViewById(R.id.txt_Address);
        txtProvince = view.findViewById(R.id.txt_Province);
        txtGender = view.findViewById(R.id.txt_Gender);
        txtFacebook = view.findViewById(R.id.txt_Facebook);
        txtTwitter = view.findViewById(R.id.txt_Twitter);
        txtInstagram = view.findViewById(R.id.txt_Instagram);
        txtWeb1 = view.findViewById(R.id.txt_Web1);
        txtCompany1 = view.findViewById(R.id.txt_Company1);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        imgQR = view.findViewById(R.id.imgQRInfo);
        //set click listener
        layoutCard_front = view.findViewById(R.id.layoutCard_front);
        layoutCard_behind = view.findViewById(R.id.layoutCard_behind);
        layout_updateProfile = view.findViewById(R.id.layout_btnUpdateProfile);

        //button click listener
        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);

        //fix error android.os.NetworkOnMainThreadException for Bitmap image url
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }

        //hide layout_UpdateProfile and btnUpdateProfile
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart();
                // Set title bar to Home
                ((MainActivity) getActivity()).setActionBarTitle("Home");
                layout_updateProfile.setVisibility(View.GONE);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //to check product if it was bought
        SessionManager.setStopCode(false);
        //retrofit connect mysql db
        ApiService apiService = RetrofitService.proceedToken().create(ApiService.class);

        //call api get product
        apiService.getProduct(SessionManager.getSaveUsername(), SessionManager.getSaveToken()).enqueue(new Callback<ProductRequest>() {
            @Override
            public void onResponse(Call<ProductRequest> call, Response<ProductRequest> response) {
                //27-06-2022 04:15AM Stopped at here, got data from getProfile() success
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity().getApplicationContext(), "This is your smart card", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductRequest> call, Throwable t) {
                SessionManager.setStopCode(true);
                Toast.makeText(getActivity().getApplicationContext(), "Failed to display! Please buy product first", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                //prevent back on click back button
                getActivity().finish();
            }
        });

        //if product havent been bought, dont run this code to improve performance
        //27-06-2022 04:15AM Stopped at here, got data from getProfile() success
        if (SessionManager.isStopCode() == false) {
            //call api to get user info
            apiService.getProfile(SessionManager.getSaveUsername()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                //save user to Session to use outside this function for create qr
                                SessionManager.setSaveUser(response.body());
                                SessionManager.setSaveFullname(response.body().getFullname());
//                                Toast.makeText(getActivity().getApplicationContext(), "Success " + SessionManager.getSaveUser().getDescription(), Toast.LENGTH_SHORT).show();
                            }
                            if (response.body() == null){
                                Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
                            }
                            if (response.code() == 401){
                                Toast.makeText(getActivity().getApplicationContext(), "Error Auth", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed display profile", Toast.LENGTH_SHORT).show();
                }
            });
            ///////////////////////////////////////////////////////////////////////////////////////////

            //call api to get social info
            apiService.getSocialInfo(SessionManager.getSaveUserID(), SessionManager.getSaveToken()).enqueue(new Callback<SocialNweb>() {
                @Override
                public void onResponse(Call<SocialNweb> call, Response<SocialNweb> response) {
                    if (response.isSuccessful()){
                        //save social to Session to use outside this function for create qr code
                        SessionManager.setSaveSocialNweb(response.body());
                    }

                    if (response.body() == null){
                        Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
                    }
                    if (response.code() == 401){
                        Toast.makeText(getActivity().getApplicationContext(), "Error Auth", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SocialNweb> call, Throwable t) {
                    //note: if social's record was delete in db, need re-install or delete cache and data of this app to fix error crash no value present
                    Toast.makeText(getActivity().getApplicationContext(),"Get social failed: " + t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
            ///////////////////////////////////////////////////////////////////////////////////////////

            //save data user and social to static session
            try {
                SessionManager.setSaveUser(apiService.getProfile(SessionManager.getSaveUsername()).execute().body());
                SessionManager.setSaveSocialNweb(apiService.getSocialInfo(SessionManager.getSaveUserID(), SessionManager.getSaveToken()).execute().body());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //display smart card info and qr cde
//            Toast.makeText(getActivity().getApplicationContext(),"Session: " + SessionManager.getSaveUser().getFullname(),Toast.LENGTH_LONG).show();
            showCardInfo();
        }
        //get data from other fragment or activity

        //================================================================
        //animation flip card
        //front
        layoutCard_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Click success", Toast.LENGTH_SHORT).show();

                //set change header card
                txtBehindHeaderCard.setText(R.string.behind_header_card);
//                try {
//                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(qrImg[0]).getContent());
//                    imgQR.setImageBitmap(bitmap);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(layoutCard_front, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(layoutCard_front, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                //animation duration
                oa1.setDuration(3000);
                oa2.setDuration(3000);
                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        layoutCard_front.setVisibility(View.GONE);
                        layoutCard_behind.setVisibility(View.VISIBLE);
                        layout_updateProfile.setVisibility(View.VISIBLE);
                        oa2.start();
                    }
                });
                oa1.start();
            }
        });

        //behind
        layoutCard_behind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Click success", Toast.LENGTH_SHORT).show();

                //set change header card
                txtFrontHeaderCard.setText(R.string.front_header_card);

                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(layoutCard_behind, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(layoutCard_behind, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                oa1.setDuration(3000);
                oa2.setDuration(3000);
                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        layoutCard_behind.setVisibility(View.GONE);
                        layoutCard_front.setVisibility(View.VISIBLE);
                        layout_updateProfile.setVisibility(View.VISIBLE);
                        oa2.start();
                    }
                });
                oa1.start();
            }
        });
        //================================================================

        // to update layout
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Update clicked", Toast.LENGTH_SHORT).show();
                //change to fragment_payment
                //getChildFragmentManager() using for nested fragment back to previous fragment when click back device
                fragmentTransaction = getChildFragmentManager().beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                fragmentTransaction.replace(R.id.fl_content_home, profileFragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

    }

    //function to display smart card info and qr cde
    public void showCardInfo(){
        //user info
        //set display user info get from user model
         txtName.setText("Fullname: " + SessionManager.getSaveUser().getFullname());
        txtEmail.setText("Email: " + SessionManager.getSaveUser().getEmail());
        //dung split de tach ngay ra khoi time 2000-03-31T00:00:00.000+00:00
        String[] birthday = SessionManager.getSaveUser().getDateOfbirth().split("T");
        SessionManager.getSaveUser().setDateOfbirth(birthday[0]);
        txtBirthday.setText("Birthday: " + SessionManager.getSaveUser().getDateOfbirth());
        txtPhone.setText("Phone: " + SessionManager.getSaveUser().getPhone());
        txtAddress.setText("Address: " + SessionManager.getSaveUser().getAddress());
        txtProvince.setText("Province: " + SessionManager.getSaveUser().getProvince());
        txtGender.setText("Gender: " + (SessionManager.getSaveUser().getGender()==true?"Male":"Female"));
        //display avatar img from url
        try {
            //avatar
            Bitmap bitmap1 = BitmapFactory.decodeStream((InputStream)new URL(SessionManager.getSaveUser().getLinkImage()).getContent());
            imgAvatar.setImageBitmap(bitmap1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //social and web info
        //set social default if social is not exist
        List<String> swList = new ArrayList<>();
        List<String> companyList = new ArrayList<>();
        if (SessionManager.getSaveSocialNweb() == null) {
            txtFacebook.setText("Facebook: N/A");
            txtTwitter.setText("Twitter: N/A");
            txtInstagram.setText("Instagram: N/A");
            txtWeb1.setText("Website: N/A");
            txtCompany1.setText("Company: N/A");
        } else {
            String facebook = SessionManager.getSaveSocialNweb().getFacebook();
            String twitter = SessionManager.getSaveSocialNweb().getTwitter();
            String instagram = SessionManager.getSaveSocialNweb().getInstagram();
            String tiktok = SessionManager.getSaveSocialNweb().getTiktok();
            String web1 = SessionManager.getSaveSocialNweb().getWeb1();
            String web2 = SessionManager.getSaveSocialNweb().getWeb2();
            String company1 = SessionManager.getSaveSocialNweb().getCompany1();
            String company2 = SessionManager.getSaveSocialNweb().getCompany2();

            //add field to array list
            swList.add(facebook);
            swList.add(twitter);
            swList.add(instagram);
            swList.add(tiktok);
            swList.add(web1);
            swList.add(web2);
            swList.removeIf(String::isEmpty);//remove if a field is empty

            companyList.add(company1);
            companyList.add(company2);
            companyList.removeIf(String::isEmpty);

            //check logic
            //for social
            if (facebook.trim().isEmpty() && twitter.trim().isEmpty() && instagram.trim().isEmpty()) {
                txtFacebook.setText("Facebook: N/A");
                txtTwitter.setText("Twitter: N/A");
                txtInstagram.setText("Instagram: N/A");
            } else {
                //set display social info get from socialNweb model if social was added before
                txtFacebook.setText("Facebook: " + facebook);
                txtTwitter.setText("Twitter: " + twitter);
                txtInstagram.setText("Instagram: " + instagram);
            }
            if (facebook.trim().isEmpty()){
                txtFacebook.setVisibility(View.GONE);
            }
            if (twitter.trim().isEmpty()){
                txtTwitter.setVisibility(View.GONE);
            }
            if(instagram.trim().isEmpty()){
                txtInstagram.setVisibility(View.GONE);
            }

            //for web
            if (web1.trim().isEmpty()){
                txtWeb1.setText("Website: " + web2);
            } else {
                txtWeb1.setText("Website: " + web1);
            }
            if (web1.trim().isEmpty() && web2.trim().isEmpty()) {
                txtWeb1.setText("Website: N/A");
            }

            //for company
            if (company1.trim().isEmpty()){
                txtCompany1.setText("Company: " + company2);
            } else{
                txtCompany1.setText("Company: " + company1);
            }
            if (company1.trim().isEmpty() && web2.trim().isEmpty()) {
                txtCompany1.setText("Company: N/A");
            }
        }

        //create QR img from user info + Social + web
        String dynamicQR = "Email: " + SessionManager.getSaveUser().getEmail()
                + "\nFullname: " + SessionManager.getSaveUser().getFullname()
                + "\nPhone: " + SessionManager.getSaveUser().getPhone()
                + "\nAddress: " + SessionManager.getSaveUser().getAddress()
                + "\nBirthday: " + SessionManager.getSaveUser().getDateOfbirth()
                + "\nGender: " + (SessionManager.getSaveUser().getGender() == true?"Male":"Female")
                + "\nProvince: " + SessionManager.getSaveUser().getProvince();
        //create qr removed empty field
        for (String s:swList) {
            dynamicQR += "\nWeb link: " + s;
        }
        for (String c:companyList) {
            dynamicQR += "\nCompany: " + c;
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(dynamicQR, BarcodeFormat.QR_CODE,450,450);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            imgQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////
    }
}