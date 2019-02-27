package com.example.bhavya.suyog.HelperClass;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhavya.suyog.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FilterDialogSheet extends BottomSheetDialogFragment {

    RadioGroup radioGroup;
    RadioButton radioButton_north;
    RadioButton radioButton_south;
    RadioButton radioButton_east;
    RadioButton radioButton_west;
    RadioButton radioButton_none;
    private BottomSheetListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filter_bottom_sheet, container, false);
        radioGroup = v.findViewById(R.id.radioGroup);
        radioButton_north=v.findViewById(R.id.radio_north);
        radioButton_south=v.findViewById(R.id.radio_south);
        radioButton_east=v.findViewById(R.id.radio_east);
        radioButton_west=v.findViewById(R.id.radio_west);
        radioButton_none=v.findViewById(R.id.radio_none);
        int radioId = radioGroup.getCheckedRadioButtonId();


        radioButton_north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton_north.setChecked(true);
                radioButton_south.setChecked(false);
                radioButton_east.setChecked(false);
                radioButton_west.setChecked(false);
                radioButton_none.setChecked(false);
                mListener.onButtonClicked("north");

                dismiss();
            }
        });
        radioButton_south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton_north.setChecked(false);
                radioButton_south.setChecked(true);
                radioButton_east.setChecked(false);
                radioButton_west.setChecked(false);
                radioButton_none.setChecked(false);
                mListener.onButtonClicked("south");

                dismiss();
            }
        });
        radioButton_east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("east");
                radioButton_north.setChecked(false);
                radioButton_south.setChecked(false);
                radioButton_east.setChecked(true);
                radioButton_west.setChecked(false);
                radioButton_none.setChecked(false);
                dismiss();
            }
        });
        radioButton_west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton_north.setChecked(false);
                radioButton_south.setChecked(false);
                radioButton_east.setChecked(false);
                radioButton_west.setChecked(true);
                radioButton_none.setChecked(false);
                mListener.onButtonClicked("west");

                dismiss();
            }
        });
        radioButton_none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton_north.setChecked(false);
                radioButton_south.setChecked(false);
                radioButton_east.setChecked(false);
                radioButton_west.setChecked(false);
                radioButton_none.setChecked(true);
                mListener.onButtonClicked("none");

                dismiss();
            }
        });
        return v;
    }
    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

}


