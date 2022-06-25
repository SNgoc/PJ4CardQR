package fpt.aptech.projectcard.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import fpt.aptech.projectcard.Payload.request.ProductRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
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
        //call api to get user info from product
        ProductRequest productRequest = new ProductRequest();//25062022-Stopped at 2h49AM
        productRequest.setUser_id(SessionManager.getSaveUserID());
        //call getProduct api
        ApiService apiService = RetrofitService.proceedToken().create(ApiService.class);
        apiService.getProduct(SessionManager.getSaveUserID(), SessionManager.getSaveToken()).enqueue(new Callback<ProductRequest>() {
            @Override
            public void onResponse(Call<ProductRequest> call, Response<ProductRequest> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
                if (response.body() == null){
                    Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 401){
                    Toast.makeText(getActivity().getApplicationContext(), "Error Auth", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductRequest> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
        //get data from other fragment or activity
        return view;
    }
}