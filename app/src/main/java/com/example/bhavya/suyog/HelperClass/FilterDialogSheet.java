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
import android.widget.Button;
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

import static java.security.AccessController.getContext;

public class FilterDialogSheet extends BottomSheetDialogFragment implements View.OnClickListener  {

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
    private Button apply;
    private ExpandableListView lvCategory;

    private ArrayList<DataItem> arCategory;
    private ArrayList<SubCategoryItem> arSubCategory;
    private ArrayList<ArrayList<SubCategoryItem>> arSubCategoryFinal;

    private ArrayList<HashMap<String, String>> parentItems;
    private ArrayList<ArrayList<HashMap<String, String>>> childItems;
    private MyCategoriesExpandableListAdapter myCategoriesExpandableListAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filter_bottom_sheet, container, false);
        lvCategory =v. findViewById(R.id.lvCategory);
        arCategory = new ArrayList<>();
        arSubCategory = new ArrayList<>();
        parentItems = new ArrayList<>();
        childItems = new ArrayList<>();
        DataItem dataItem = new DataItem();
        dataItem.setCategoryId("1");
        dataItem.setCategoryName("Alarms");
        apply=v.findViewById(R.id.apply);
        apply.setOnClickListener(this);
        String arr_zone[]={"North","South","East","West"};

        arSubCategory = new ArrayList<>();
        for(int i = 1; i < 6; i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Alarms: "+i);
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("2");
        dataItem.setCategoryName("Zone");
        arSubCategory = new ArrayList<>();

        for(int j = 1; j < 5; j++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(j));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Zone: "+arr_zone[j-1]+" "+j);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);
        Log.d("TAG", "setupReferences: "+arCategory.size());


        Log.d("TAG", "setupReferences: "+arCategory.size());

        for(DataItem data : arCategory){
//                        Log.i("Item id",item.id);
            ArrayList<HashMap<String, String>> childArrayList =new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mapParent = new HashMap<String, String>();

            mapParent.put(ConstantManager.Parameter.CATEGORY_ID,data.getCategoryId());
            mapParent.put(ConstantManager.Parameter.CATEGORY_NAME,data.getCategoryName());

            int countIsChecked = 0;
            for(SubCategoryItem subCategoryItem : data.getSubCategory()) {

                HashMap<String, String> mapChild = new HashMap<String, String>();
                mapChild.put(ConstantManager.Parameter.SUB_ID,subCategoryItem.getSubId());
                mapChild.put(ConstantManager.Parameter.SUB_CATEGORY_NAME,subCategoryItem.getSubCategoryName());
                mapChild.put(ConstantManager.Parameter.CATEGORY_ID,subCategoryItem.getCategoryId());
                mapChild.put(ConstantManager.Parameter.IS_CHECKED,subCategoryItem.getIsChecked());

                if(subCategoryItem.getIsChecked().equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {

                    countIsChecked++;
                }
                childArrayList.add(mapChild);
            }

            if(countIsChecked == data.getSubCategory().size()) {

                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_TRUE);
            }else {
                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            }

            mapParent.put(ConstantManager.Parameter.IS_CHECKED,data.getIsChecked());
            childItems.add(childArrayList);
            parentItems.add(mapParent);

        }

        ConstantManager.parentItems = parentItems;
        ConstantManager.childItems = childItems;
        myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(getActivity(),parentItems,childItems,false);
        lvCategory.setAdapter(myCategoriesExpandableListAdapter);







        return v;
    }

    @Override
    public void onClick(View v) {
        String filter_category;
        ArrayList<String> Zone_filter=new ArrayList<>();
        ArrayList<String>filter_list_zone=new ArrayList<>();
        ArrayList<String>filter_list_Alarm=new ArrayList<>();
        for (int i = 0; i < MyCategoriesExpandableListAdapter.parentItems.size(); i++ ){

            String isChecked = MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.IS_CHECKED);
           // Log.i("checked item",isChecked+" is selected");

            if (isChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE))
            {   Log.i("checked item","****"+MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME));

            }

            for (int j = 0; j < MyCategoriesExpandableListAdapter.childItems.get(i).size(); j++ ){

                String isChildChecked = MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.IS_CHECKED);

                if (isChildChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE))
                {
                     filter_category=MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME) + " "+(j+1);
                    Zone_filter.add(MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME) + " "+(j+1));
                    //filter_list.add(MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME) + " "+(j+1));
                    if(filter_category.equals("Zone 1")||filter_category.equals("Zone 2")||filter_category.equals("Zone 3")||filter_category.equals("Zone 4")){
                        if(filter_category.equals("Zone 1"))
                            filter_list_zone.add("North");
                        if(filter_category.equals("Zone 2"))
                            filter_list_zone.add("South");
                        if(filter_category.equals("Zone 3"))
                            filter_list_zone.add("East");
                        if(filter_category.equals("Zone 4"))
                            filter_list_zone.add("West");

                        Log.i("checked Zone","-->"+filter_category);
                    }
                    else{
                        filter_list_Alarm.add(filter_category);
                        Log.i("checked Alarm","-->"+filter_category);
                    }
                }



            }


        }

        for(int i=0;i<Zone_filter.size();i++){
         //   Log.i("check in loop",filter_list.get(i));
        }
       mListener.onButtonClicked(filter_list_zone,filter_list_Alarm);

    /*    if(Zone_filter.size()==1)
            mListener.onButtonClicked(Zone_filter.get(0));
        if(Zone_filter.size()==2)
        {
            if(Zone_filter.contains("Zone 1")&&Zone_filter.contains("Zone 2")){
                mListener.onButtonClicked("1");
            }
            if(Zone_filter.contains("Zone 1")&&Zone_filter.contains("Zone 3")){
                mListener.onButtonClicked("2");
            }
            if(Zone_filter.contains("Zone 1")&&Zone_filter.contains("Zone 4")){
                mListener.onButtonClicked("3");
            }
            if(Zone_filter.contains("Zone 2")&&Zone_filter.contains("Zone 3")){
                mListener.onButtonClicked("4");
            }
            if(Zone_filter.contains("Zone 2")&&Zone_filter.contains("Zone 4")){
                mListener.onButtonClicked("5");
            }
            if(Zone_filter.contains("Zone 3")&&Zone_filter.contains("Zone 4")){
                mListener.onButtonClicked("6");
            }
        }
        if(Zone_filter.size()==3)
        {
            if(Zone_filter.contains("Zone 1")&&Zone_filter.contains("Zone 2")&&Zone_filter.contains("Zone 3")){
                mListener.onButtonClicked("7");
            }
            if(Zone_filter.contains("Zone 1")&&Zone_filter.contains("Zone 2")&&Zone_filter.contains("Zone 4")){
                mListener.onButtonClicked("8");
            }
            if(Zone_filter.contains("Zone 1")&&Zone_filter.contains("Zone 3")&&Zone_filter.contains("Zone 4")){
                mListener.onButtonClicked("9");
            }
            if(Zone_filter.contains("Zone 2")&&Zone_filter.contains("Zone 3")&&Zone_filter.contains("Zone 4")){
                mListener.onButtonClicked("10");
            }
        }

        if(Zone_filter.size()==4)
        {
            mListener.onButtonClicked("11");
        }
*/
    }



    public interface BottomSheetListener {
        void onButtonClicked(ArrayList<String> filter_list_zone,ArrayList<String> filter_list_Alarm);
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


