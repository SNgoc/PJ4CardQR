package fpt.aptech.projectcard.ui.buycard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fpt.aptech.projectcard.MainActivity;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.ui.login.RegisterFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCardFragment extends Fragment {

    private Button btnSub,btnPlus, btnOrder;
    private EditText editQuantity;
    private TextView txtPrice;
    private int quantity, price, totalPrice;
    //change fragment when click button
    private FragmentTransaction fragmentTransaction;
    //view
    private View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuyCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyCardFragment newInstance(String param1, String param2) {
        BuyCardFragment fragment = new BuyCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set title bar to Buy Card
        ((MainActivity) getActivity()).setActionBarTitle("Buy Card");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_buy_card, container, false);
        //initialize your view here for use view.findViewById("your view id")
        btnSub = view.findViewById(R.id.btnSub);
        btnPlus = view.findViewById(R.id.btnPlus);
        btnOrder = view.findViewById(R.id.btnOrder);
        editQuantity = view.findViewById(R.id.editQuantity);
        txtPrice = view.findViewById(R.id.txtPrice);
        price = 2;

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.parseInt(editQuantity.getText().toString());
                if (quantity <= 1) {
                    Toast.makeText(getContext(),"Quantity can not less than 1",Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity -= 1;
                editQuantity.setText(String.valueOf(quantity));
                totalPrice = price*quantity;
                txtPrice.setText(totalPrice + "$");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.parseInt(editQuantity.getText().toString());
                quantity += 1;
                editQuantity.setText(String.valueOf(quantity));
                totalPrice = price*quantity;
                txtPrice.setText(totalPrice + "$");
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.parseInt(editQuantity.getText().toString());
                totalPrice = price*quantity;
                //change to fragment_payment
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                PaymentFragment paymentFragment = new PaymentFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,paymentFragment);
                fragmentTransaction.commit();

                //send data from buy fragment to pay fragment
                Bundle bundle = new Bundle();
                bundle.putInt("totalPrice", totalPrice);
                paymentFragment.setArguments(bundle);

                //reset quantity and price when click order
                editQuantity.setText("1");
                txtPrice.setText(price + "$");
            }
        });
        return view;
    }
}