package com.example.bhavya.suyog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AlarmAdapter mAdapter;
    private TextView filter_zone;
    public static String search_type;
    private TextView filter_location;
    private Boolean location_selected=false;
    private Boolean Zone_selected=false;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Alarm> alarm_list=dataset();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AlarmAdapter(alarm_list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Alarm> dataset(){
        ArrayList<Alarm> alarm_list_data=new ArrayList<>();
        alarm_list_data.add(new Alarm("STL_TP_11","south","Trombay","Trombay-Parcel 1 T-2","S"));
        alarm_list_data.add(new Alarm("STL_MH_103","North Central","Bhiwandi","Tawre Bldg","NC"));
        alarm_list_data.add(new Alarm("STL_MH_115","North West","Nalasopara","Ayan Apartment","NW"));
        alarm_list_data.add(new Alarm("STL_MH_110","North Central","Bhiwandi","Romiya Apartment","NC"));
        alarm_list_data.add(new Alarm("STL_MH_112","North Central","Bhiwandi","Roshan Baug Chaudhary Bunglow","NC"));
        alarm_list_data.add(new Alarm("STL_MH_113","North Central","Bhiwandi","Prabhushet Building","NC"));
        return alarm_list_data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.alarm_menu,menu);
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem SearchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) SearchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_Location:
               /*search_type="Location";
                Toast.makeText(this,"Location Selected",Toast.LENGTH_SHORT).show();
                return true;*/

                if(item.isChecked()){
                    // If item already checked then unchecked it
                    item.setChecked(false);
                    location_selected = false;
                }else{
                    // If item is unchecked then checked it
                    item.setChecked(true);
                    location_selected = true;
                }
                // Update the text view text style
                updateSearchType();
                return true;
            case R.id.search_Zone:
                /*search_type="Zone";
                Toast.makeText(this,"Zone Selected",Toast.LENGTH_SHORT).show();
                return  true;*/

                if(item.isChecked()){
                    // If item already checked then unchecked it
                    item.setChecked(false);
                    Zone_selected = false;
                }else{
                    // If item is unchecked then checked it
                    item.setChecked(true);
                    Zone_selected = true;
                }
                // Update the text view text style
                updateSearchType();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void updateSearchType()
    {
        if(location_selected && !Zone_selected)
        {
            search_type="Location";
        }
        else if(!location_selected && Zone_selected)
        {
            search_type="Zone";
        }
        else if(location_selected && Zone_selected)
        {
            search_type="Both";
        }
        else if(!location_selected && !Zone_selected) {
            search_type="Both";
        }
    }
}
