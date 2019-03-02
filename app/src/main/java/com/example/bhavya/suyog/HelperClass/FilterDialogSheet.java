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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhavya.suyog.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterDialogSheet extends BottomSheetDialogFragment  {

    RadioGroup radioGroup;
    RadioButton radioButton_north;
    RadioButton radioButton_south;
    RadioButton radioButton_east;
    RadioButton radioButton_west;
    RadioButton radioButton_none;
    CheckBox checkbox_north;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    private BottomSheetListener mListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filter_bottom_sheet, container, false);
       // radioGroup = v.findViewById(R.id.radioGroup);
      /*  radioButton_north=v.findViewById(R.id.radio_north);
        radioButton_south=v.findViewById(R.id.radio_south);
        radioButton_east=v.findViewById(R.id.radio_east);
        radioButton_west=v.findViewById(R.id.radio_west);
        radioButton_none=v.findViewById(R.id.radio_none);*/ //uncoment for radio buttons
       // checkbox_north=v.findViewById(R.id.checkBox_north);
     /*   Spinner spinner=v.findViewById(R.id.spinner_zone);
        adapter=ArrayAdapter.createFromResource(getContext(),R.array.Zone,android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*///spinner methods are commented
        /* int radioId = radioGroup.getCheckedRadioButtonId();
*/
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                mListener.onButtonClicked( expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).trim());

                Toast.makeText(
                        getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });



       /* radioButton_north.setOnClickListener(new View.OnClickListener() {
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

        checkbox_north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("north");
            }
        });*/
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


