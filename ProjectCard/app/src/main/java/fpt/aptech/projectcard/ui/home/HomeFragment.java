package fpt.aptech.projectcard.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import fpt.aptech.projectcard.MainActivity;
import fpt.aptech.projectcard.R;

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
        //get data from other fragment
//        savedInstanceState = this.getArguments();
//        if (savedInstanceState == null) {
//            Toast.makeText(getActivity().getApplicationContext(), "please login", Toast.LENGTH_SHORT).show();
//            //change to fragment_home
//            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//            LoginFragment loginFragment = new LoginFragment();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,loginFragment);
//            fragmentTransaction.commit();
//        }
//        else {
//            username = savedInstanceState.getString("username");
//            password = savedInstanceState.getString("password");
//            Toast.makeText(getActivity().getApplicationContext(), username + " " + password, Toast.LENGTH_SHORT).show();
//        }
        return view;
    }
}