package com.barhochman.theproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.barhochman.theproject.Activities.DrawerActivity;
import com.barhochman.theproject.Nodes.DBBank;
import com.barhochman.theproject.Nodes.Transfers;
import com.barhochman.theproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddTransfer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddTransfer#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AddTransfer extends DialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // Other fragment / activity parameters
    DrawerActivity drawerActivity;
    MainList mainList;
    TextInputEditText name, category, amount;
    RadioGroup radioButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddTransfer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTransfer.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTransfer newInstance(String param1, String param2) {
        AddTransfer fragment = new AddTransfer();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_transfer, container, false);
        name = view.findViewById(R.id.transfer_edit_name);
        category = view.findViewById(R.id.transfer_edit_cat);
        amount = view.findViewById(R.id.transfer_edit_amount);
        radioButton = view.findViewById(R.id.income_outcomes_radios);

        view.findViewById(R.id.submit_button).setOnClickListener(this);
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        drawerActivity = (context instanceof DrawerActivity) ? (DrawerActivity) context : null;
        try{
            //((DrawerActivity) context).floatingBarShow(false);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_button) {
            try {
                //drawerActivity.floatingBarShow(true);
                Transfers t = new Transfers(name.getText().toString(), Double.parseDouble(amount.getText().toString()), category.getText().toString());

                switch (radioButton.getCheckedRadioButtonId()) {
                    case R.id.income_radio:
                        DBBank.addIncome(t);
                        if (getFragmentManager() != null) {
                            getFragmentManager().popBackStackImmediate();
                        }
                        break;
                    case R.id.outcome_radio:
                        DBBank.addOutcome(t);
                        if (getFragmentManager() != null) {
                            getFragmentManager().popBackStackImmediate();
                        }
                        break;
                }
                drawerActivity.invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //MainList mainList = (MainList)getFragmentManager().findFragmentById(R.id.mainFragmentId);
            //mainList.updateUI(StringsHelper.UpdateUI.getTotalUpdated());


        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
