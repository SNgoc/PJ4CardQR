package fpt.aptech.projectcard.ui.social;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fpt.aptech.projectcard.Payload.request.SocialNWebRequest;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.callApiService.ApiService;
import fpt.aptech.projectcard.retrofit.RetrofitService;
import fpt.aptech.projectcard.session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SocialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocialFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //SOCIAL
    TextView txtFacebook,txtTwitter,txtInstagram,txtTiktok,txtWeb1,txtWeb2,txtCompany1,txtCompany2;
    EditText edFacebook,edTwitter,edInstagram,edTiktok,edWeb1,edWeb2,edCompany1,edCompany2;
    Button btnAdd_UpdateSocial;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SocialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SocialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SocialFragment newInstance(String param1, String param2) {
        SocialFragment fragment = new SocialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        edFacebook =view.findViewById(R.id.editFacebook);
        edTwitter = view.findViewById(R.id.editTwitter);
        edInstagram =view.findViewById(R.id.editInstagram);
        edTiktok = view.findViewById(R.id.editTiktok);
        edWeb1 = view.findViewById(R.id.editWeb1);
        edWeb2 = view.findViewById(R.id.editWeb2);
        edCompany1 = view.findViewById(R.id.editCompany1);
        edCompany2 = view.findViewById(R.id.editCompany2);
        btnAdd_UpdateSocial = view.findViewById(R.id.btnSave_UpdateSocial);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (SessionManager.getSaveSocialNweb() == null) {
            //add new social
            btnAdd_UpdateSocial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String facebook = edFacebook.getText().toString();
                    String twitter = edTwitter.getText().toString();
                    String instagram = edInstagram.getText().toString();
                    String tiktok = edTiktok.getText().toString();
                    String web1 = edWeb1.getText().toString();
                    String web2 = edWeb2.getText().toString();
                    String company1 = edCompany1.getText().toString();
                    String company2 = edCompany2.getText().toString();
                    //retrofit connect mysql db
                    ApiService apiService = RetrofitService.proceedToken().create(ApiService.class);

                    //add social and web
                    SocialNWebRequest socialNWebRequest = new SocialNWebRequest(facebook,twitter,instagram,tiktok,web1,web2,company1,company2,SessionManager.getSaveUserID());
                    apiService.addSocialNWeb(socialNWebRequest, SessionManager.getSaveToken())
                            .enqueue(new Callback<SocialNWebRequest>() {
                                @Override
                                public void onResponse(Call<SocialNWebRequest> call, Response<SocialNWebRequest> response) {
                                    if (response.isSuccessful()) {
                                        if (response != null) {
                                            Toast.makeText(getActivity().getApplicationContext(), "Added success Social", Toast.LENGTH_SHORT).show();
                                        }
                                        if (response.body() == null){
                                            Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
                                        }
                                        if (response.code() == 401){
                                            Toast.makeText(getActivity().getApplicationContext(), "Error Auth", Toast.LENGTH_SHORT).show();
                                        }
                                        //error validate
                                        if (response.code() == 400) {
                                            Toast.makeText(getActivity().getApplicationContext(), "Add failed, error field", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<SocialNWebRequest> call, Throwable t) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
            //end btn social event
        }
        else {
            //change name btn to update
            btnAdd_UpdateSocial.setText(R.string.btn_updateSocialNWeb);
            //update social
            edFacebook.setText(SessionManager.getSaveSocialNweb().getFacebook());
            edTwitter.setText(SessionManager.getSaveSocialNweb().getTwitter());
            edInstagram.setText(SessionManager.getSaveSocialNweb().getInstagram());
            edTiktok.setText(SessionManager.getSaveSocialNweb().getTiktok());
            edWeb1.setText(SessionManager.getSaveSocialNweb().getWeb1());
            edWeb2.setText(SessionManager.getSaveSocialNweb().getWeb2());
            edCompany1.setText(SessionManager.getSaveSocialNweb().getCompany1());
            edCompany2.setText(SessionManager.getSaveSocialNweb().getCompany2());

            btnAdd_UpdateSocial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String facebook = edFacebook.getText().toString();
                    String twitter = edTwitter.getText().toString();
                    String instagram = edInstagram.getText().toString();
                    String tiktok = edTiktok.getText().toString();
                    String web1 = edWeb1.getText().toString();
                    String web2 = edWeb2.getText().toString();
                    String company1 = edCompany1.getText().toString();
                    String company2 = edCompany2.getText().toString();
                    //retrofit connect mysql db
                    ApiService apiService = RetrofitService.proceedToken().create(ApiService.class);

                    //update social and web
                    SocialNWebRequest socialNWebRequest = new SocialNWebRequest(facebook,twitter,instagram,tiktok,web1,web2,company1,company2);
                    apiService.updateSocialNWeb(SessionManager.getSaveSocialNweb().getSocial_id(),socialNWebRequest, SessionManager.getSaveToken())
                            .enqueue(new Callback<SocialNWebRequest>() {
                                @Override
                                public void onResponse(Call<SocialNWebRequest> call, Response<SocialNWebRequest> response) {
                                    if (response.isSuccessful()) {
                                        if (response != null) {
                                            Toast.makeText(getActivity().getApplicationContext(), "Updated success Social", Toast.LENGTH_SHORT).show();
                                        }
                                        if (response.body() == null){
                                            Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
                                        }
                                        if (response.code() == 401){
                                            Toast.makeText(getActivity().getApplicationContext(), "Error Auth", Toast.LENGTH_SHORT).show();
                                        }
                                        //error validate
                                        if (response.code() == 400) {
                                            Toast.makeText(getActivity().getApplicationContext(), "Add failed, error field", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<SocialNWebRequest> call, Throwable t) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
            //end btn social event
        }

    }
}