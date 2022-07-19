package fpt.aptech.projectcard.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import fpt.aptech.projectcard.MainActivity;
import fpt.aptech.projectcard.Payload.request.ProductRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
import fpt.aptech.projectcard.domain.Orders;
import fpt.aptech.projectcard.domain.Product;
import fpt.aptech.projectcard.domain.SocialNweb;
import fpt.aptech.projectcard.domain.UrlProduct;
import fpt.aptech.projectcard.domain.User;
import fpt.aptech.projectcard.retrofit.RetrofitService;
import fpt.aptech.projectcard.session.SessionManager;
import fpt.aptech.projectcard.ui.login.LoginActivity;
import fpt.aptech.projectcard.ui.profile.ProfileFragment;
import fpt.aptech.projectcard.ui.social.SocialFragment;
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
    View view;
    //check order status for login;
    Product getProduct;
    List<Orders> ordersListUser;
    //header title card
    TextView txtFrontHeaderCard,txtBehindHeaderCard;
    //front card
    TextView txtName,txtEmail,txtAddress,txtBirthday,txtProvince, txtGender;
    //behind card
    GridView gridViewUrl;
    List<UrlProduct> urlProductList;
    ImageView imgQRInfo, imgQRUrl;
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
        // can't Set title bar to Home will error null at setActionBarTitle because home is start fragment
//        ((MainActivity) getActivity()).setActionBarTitle("Home");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)//api > 24
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        txtFrontHeaderCard = view.findViewById(R.id.txt_front_header_card);
        txtBehindHeaderCard = view.findViewById(R.id.txt_behind_header_card);
        txtName = view.findViewById(R.id.txt_Name);
        txtEmail = view.findViewById(R.id.txt_Email);
        txtBirthday = view.findViewById(R.id.txt_Birthday);
        txtAddress = view.findViewById(R.id.txt_Address);
        txtProvince = view.findViewById(R.id.txt_Province);
        txtGender = view.findViewById(R.id.txt_Gender);
        //behind card
        gridViewUrl = view.findViewById(R.id.gvUrl);
        imgQRInfo = view.findViewById(R.id.imgQRInfo);
        imgQRUrl = view.findViewById(R.id.imgQRUrl);
        //set click listener
        layoutCard_front = view.findViewById(R.id.layoutCard_front);
        layoutCard_behind = view.findViewById(R.id.layoutCard_behind);

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
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)//api > 24 for set TooltipText imageView
    //refresh data on click
    @Override
    public void onStart() {
        super.onStart();
        //retrofit connect mysql db
        ApiService apiService = RetrofitService.proceedToken().create(ApiService.class);

        //call api check product order status
        try {
            ordersListUser = apiService.getOrdersByUsername(SessionManager.getSaveUsername()).execute().body();
            getProduct = apiService.getProduct(SessionManager.getSaveUsername(), SessionManager.getSaveToken()).execute().body();
            SessionManager.setSaveProduct(getProduct);
            SessionManager.setSaveUser(apiService.getProfile(SessionManager.getSaveUsername()).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if product had been order but order status not completed, dont run this code to improve performance
        if (getProduct == null){
            Toast.makeText(getActivity().getApplicationContext(), "The your product is not purchased yet", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            //prevent back on click back button
            getActivity().finish();
        }
        else {
            //found order by username, not null, not empty
            //check product status, can only login when have product and status is 1
            boolean productSuccess = false;
            if (getProduct != null && getProduct.getStatus() == 1) {
                productSuccess = true;
            }
//            for (Orders o : ordersListUser) {
//                if (o.getOrder_process().getId() == 3){// 1: Waiting, 2:Delivery, 3:Success, 4:Cancel
//                    foundSuccess = true;
//                    break;
//                }
//            }
            if (productSuccess == false){
                int i = 0;
                for (Orders o : ordersListUser) {
                    i++;
                    Toast.makeText(getActivity().getApplicationContext(), "Now, your order " + i + " status is " + o.getOrder_process().getName() + "! \nPlease waiting for order status is Success!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //prevent back on click back button
                    getActivity().finish();
                }
            }
            ///////////////////////////////////////////////////////////////

            //call api to get user info
            apiService.getProfile(SessionManager.getSaveUsername()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                //can't save session value in onResponse, because it will null after finish run this function
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

            //get url social link by user
            apiService.getUrlProduct(SessionManager.getSaveUsername()).enqueue(new Callback<List<UrlProduct>>() {
                @Override
                public void onResponse(Call<List<UrlProduct>> call, Response<List<UrlProduct>> response) {
                    if (response.isSuccessful()){
                        //save social to Session to use outside this function for create qr code
//                        SessionManager.setSaveUrlProduct(response.body());
                    }

                    if (response.body() == null){
                        Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
                    }
                    if (response.code() == 401){
                        Toast.makeText(getActivity().getApplicationContext(), "Error Auth", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<UrlProduct>> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(),"Get url product failed: " + t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
            //save data user and social, url product to static session
            try {
                SessionManager.setSaveUser(apiService.getProfile(SessionManager.getSaveUsername()).execute().body());
                urlProductList = apiService.getUrlProduct(SessionManager.getSaveUsername()).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //display smart card info, url link product and qr cde
//            Toast.makeText(getActivity().getApplicationContext(),"Session: " + SessionManager.getSaveUser().getFullname(),Toast.LENGTH_LONG).show();
            showCardInfo();
            showUrlProduct();
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
                        oa2.start();
                    }
                });
                oa1.start();
            }
        });
        //================================================================

        // to update layout
        layoutCard_front.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Update profile layout", Toast.LENGTH_SHORT).show();
                //change to fragment_payment
                //getChildFragmentManager() using for nested fragment back to previous fragment when click back device
                fragmentTransaction = getChildFragmentManager().beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                fragmentTransaction.replace(R.id.fl_content_home, profileFragment);
                fragmentTransaction.addToBackStack(null).commit();
                return false;
            }
        });
        //to add url layout
        layoutCard_behind.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Add URL layout", Toast.LENGTH_SHORT).show();
                //change to fragment_payment
                //getChildFragmentManager() using for nested fragment back to previous fragment when click back device
                fragmentTransaction = getChildFragmentManager().beginTransaction();
                SocialFragment socialFragment = new SocialFragment();
                fragmentTransaction.replace(R.id.fl_content_home, socialFragment);
                fragmentTransaction.addToBackStack(null).commit();
                return false;
            }
        });
        //show hint text
        txtFrontHeaderCard.setTooltipText("Touch and hold your touch to go to update layout");
        txtBehindHeaderCard.setTooltipText("Touch and hold your touch to go to add url layout");
    }

    //check order status for login

    //function to display smart card info and qr cde
    @RequiresApi(api = Build.VERSION_CODES.O) //api > 24
    public void showCardInfo(){
        //user info
        String fullname = SessionManager.getSaveUser().getFullname();
        String email = SessionManager.getSaveUser().getEmail();
        String phone = SessionManager.getSaveUser().getPhone();
        String address = SessionManager.getSaveUser().getAddress();
        String description = SessionManager.getSaveUser().getDescription();
        String province = SessionManager.getSaveUser().getProvince();
        //dung split de tach ngay ra khoi time 2000-03-31T00:00:00.000+00:00
        String[] birthday = SessionManager.getSaveUser().getDateOfbirth().split("T");
        SessionManager.getSaveUser().setDateOfbirth(birthday[0]);
        String gender = (SessionManager.getSaveUser().getGender()==true?"Male":"Female");
        //set display user info get from user model
         txtName.setText("Fullname: " + fullname);
        txtEmail.setText("Main email: " + email);
        txtBirthday.setText("Birthday: " + birthday[0]);
        txtAddress.setText("Address: " + address);
        txtProvince.setText("Province: " + province);
        txtGender.setText("Gender: " + gender);

        //create 2 QR img from user info + url
        //note: to qr show button type access link must have https:// + name + .com or .net (follow type web link)
        String dynamicQRInfo = "BEGIN:VCARD"
                      +"\nFN:"+ fullname
                      + "\nTEL:" + phone
                      + "\nEMAIL;TYPE=INTERNET:" + email
                      + "\nBDAY:" + birthday[0]
                      + "\nADR:" + address
                      + "\nNOTE:" + description
                      + "\nURL:" + getProduct.getUrl()
                      + "\nEND:VCARD\n";

        String dynamicQRUrl= getProduct.getUrl();

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(dynamicQRInfo, BarcodeFormat.QR_CODE,450,450);
            BitMatrix matrix2 = writer.encode(dynamicQRUrl, BarcodeFormat.QR_CODE,450,450);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            Bitmap bitmap2 = encoder.createBitmap(matrix2);
            imgQRInfo.setImageBitmap(bitmap);
            imgQRUrl.setImageBitmap(bitmap2);
            imgQRInfo.setTooltipText("Scan this to add contact");
            imgQRUrl.setTooltipText("http://localhost:8081/Display/" + SessionManager.getSaveUsername());
        } catch (WriterException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////
    }

    //show url product from db mysql
    public void showUrlProduct(){
        //sort item by name
        urlProductList.sort(new Comparator<UrlProduct>() {
            public int compare(UrlProduct u1, UrlProduct u2) {
                return u1.getLinkType().getName().compareTo(u2.getLinkType().getName());
            }
        });
        // function create TextView, Imageview by code must be in onCreateView of fragment,
        // if not will create duplicate TextView when click back fragment after click link url;
        // or using it with grid view to fix error duplicate
        //display avatar img from url
        ArrayList<UrlProduct> urlProductArrayList = new ArrayList<>(urlProductList);
        GridViewURLAdapter adapter = new GridViewURLAdapter(getContext(),urlProductArrayList);
        adapter.notifyDataSetChanged();
        gridViewUrl.setAdapter(adapter);
    }
}