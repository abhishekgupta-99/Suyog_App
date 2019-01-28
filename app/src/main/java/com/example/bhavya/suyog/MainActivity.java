package com.example.bhavya.suyog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AlarmAdapter mAdapter;
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
}
