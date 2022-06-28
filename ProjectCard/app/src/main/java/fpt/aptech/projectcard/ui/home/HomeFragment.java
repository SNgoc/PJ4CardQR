package fpt.aptech.projectcard.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fpt.aptech.projectcard.Payload.request.ProductRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
import fpt.aptech.projectcard.domain.User;
import fpt.aptech.projectcard.retrofit.RetrofitService;
import fpt.aptech.projectcard.session.SessionManager;
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
    TextView txtName,txtEmail,txtPhone,txtAddress,txtBirthday,txtProvince;
    ImageView imgAvatar;
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
        // Set title bar to Home
//        ((MainActivity) getActivity()).setActionBarTitle("Home");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        txtName = view.findViewById(R.id.txt_Name);
        txtEmail = view.findViewById(R.id.txt_Email);
        txtBirthday = view.findViewById(R.id.txt_Birthday);
        txtPhone = view.findViewById(R.id.txt_Phone);
        txtAddress = view.findViewById(R.id.txt_Address);
        txtProvince = view.findViewById(R.id.txt_Province);
        imgAvatar = view.findViewById(R.id.imgAvatar);

        //fix error android.os.NetworkOnMainThreadException for Bitmap image url
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }

        //call api to get user info from getProduct
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(SessionManager.getSaveUserID());
        //call getProduct api
        ApiService apiService = RetrofitService.proceedToken().create(ApiService.class);
        apiService.getProduct(SessionManager.getSaveUserID(), SessionManager.getSaveToken()).enqueue(new Callback<ProductRequest>() {
            @Override
            public void onResponse(Call<ProductRequest> call, Response<ProductRequest> response) {
                //27-06-2022 04:15AM Stopped at here, got data from getProduct() success
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(getActivity().getApplicationContext(), "Success " + response.body().getUserInfo() +"\n" + "linkurlQRcode: " + response.body().getUrl(), Toast.LENGTH_SHORT).show();
                        //display avatar img from url
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(response.body().getUserInfo().getLinkImage()).getContent());
                            imgAvatar.setImageBitmap(bitmap);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //set display user info get from product info
                        txtName.setText("Fullname: " + response.body().getUserInfo().getFullname());
                        txtEmail.setText("Email: " + response.body().getUserInfo().getEmail());
                        //dung split de tach ngay ra khoi time 2000-03-31T00:00:00.000+00:00
                        String[] birthday = response.body().getUserInfo().getDateOfbirth().split("T");
                        txtBirthday.setText("Birthday: " + birthday[0]);
                        txtPhone.setText("Phone: " + response.body().getUserInfo().getPhone());
                        txtAddress.setText("Address: " + response.body().getUserInfo().getAddress());
                        txtProvince.setText("Province: " + response.body().getUserInfo().getProvince());
                    }
                    if (response.body() == null){
                        Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
                    }
                    if (response.code() == 401){
                        Toast.makeText(getActivity().getApplicationContext(), "Error Auth", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductRequest> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Fail display! Please buy product first", Toast.LENGTH_LONG).show();
            }
        });
        //get data from other fragment or activity
        return view;
    }
}