package com.pxlim.bitparse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BitFragment extends Fragment {

    @BindView(R.id.editTextA)
    EditText editTextA;
    @BindView(R.id.editTextB)
    EditText editTextB;
    @BindView(R.id.textViewResult)
    TextView textViewResult;


    public BitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BitFragment.
     */
    public static BitFragment newInstance() {
        BitFragment fragment = new BitFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bit, container, false);
        ButterKnife.bind(this, view);
        return view;

    }


    @OnClick(R.id.calculate)
    public void calculate(View view) {
        boolean checks_passed = true;
        if (editTextA.getText().toString().trim().equals("")) {
            editTextA.setError("Number A required");
            checks_passed = false;
        }
        if (editTextB.getText().toString().trim().equals("")) {
            editTextB.setError("Number B required");
            checks_passed = false;
        }

        if (!checks_passed) return;

        int a = Integer.parseInt(editTextA.getText().toString());
        int b = Integer.parseInt(editTextB.getText().toString());

        int bitsToChange = BitUtils.getBitsToChange(a, b);
        textViewResult.setText(bitsToChange + "");
    }



}
