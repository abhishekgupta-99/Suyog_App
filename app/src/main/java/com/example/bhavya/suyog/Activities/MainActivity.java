package com.example.bhavya.suyog.Activities;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhavya.suyog.HelperClass.Alarm;
import com.example.bhavya.suyog.HelperClass.AlarmAdapter;
import com.example.bhavya.suyog.HelperClass.FilterDialogSheet;
import com.example.bhavya.suyog.R;
import com.hootsuite.nachos.NachoTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements FilterDialogSheet.BottomSheetListener {
    private RecyclerView mRecyclerView;
    private AlarmAdapter mAdapter;
    private TextView toolbar_title;
    public static String search_type;

  //  private NachoTextView nachotextview;
    List<String> suggestions;



    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar Alarms_toolbar;
    public static String opt="none";
    public ArrayList<Alarm>alarm_list;// buffer on which adapter is set
    public ArrayList<Alarm>alarm_listcopy; //contains all the value
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         alarm_list = dataset();
         alarm_listcopy=new ArrayList<>(alarm_list);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AlarmAdapter(alarm_list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        tool_bar();

        if (Build.VERSION.SDK_INT > 22) {
            //getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.background_grey));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }
        //alarm_nacho_view();
    }

    public ArrayList<Alarm> dataset() {
        ArrayList<Alarm> alarm_list_data = new ArrayList<>();
        alarm_list_data.add(new Alarm("STL_TP_11", "South", "Trombay", "Trombay-Parcel 1 T-2", "S"));
        alarm_list_data.add(new Alarm("STL_MH_103", "North Central", "Bhiwandi", "Tawre Bldg", "NC"));
        alarm_list_data.add(new Alarm("STL_MH_115", "North West", "Nalasopara", "Ayan Apartment", "NW"));
        alarm_list_data.add(new Alarm("STL_MH_110", "North Central", "Bhiwandi", "Romiya Apartment", "NC"));
        alarm_list_data.add(new Alarm("STL_MH_112", "North Central", "Bhiwandi", "Roshan Baug Bunglow", "NC"));
        alarm_list_data.add(new Alarm("STL_MH_113", "North Central", "Bhiwandi", "Prabhushet Building", "NC"));
        alarm_list_data.add(new Alarm("STL_MH_119", "East", "Bhiwandi", "Prabhushet Building", "E"));
        alarm_list_data.add(new Alarm("STL_MH_179", "South", "Bhiwandi", "Prabhushet Building", "S"));
        alarm_list_data.add(new Alarm("STL_MH_69", "West", "Bhiwandi", "Prabhushet Building", "W"));
        return alarm_list_data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("check main","Inside on create opt");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alarm_menu, menu);// this is for the search bar and icon
        inflater.inflate(R.menu.search_menu, menu);// inflating the icon
        MenuItem SearchItem = menu.findItem(R.id.action_search);//find the input in the edit text
        SearchView searchView = (SearchView) SearchItem.getActionView();
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
//this setups the sheet on clicking the icon since on click is added
    public void setup_sheet(MenuItem item){
        FilterDialogSheet filtersheet=new FilterDialogSheet();
        filtersheet.show(getSupportFragmentManager(),"filterbottomsheet");
    }

    public void tool_bar() {
        Alarms_toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(Alarms_toolbar);
        getSupportActionBar().setTitle("Alarm");
        Alarms_toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar_title = findViewById(R.id.app_toolbar_name);
        //toolbar_title.setText("Alarms");
        //toolbar_text.setText(R.string.ongoing_player);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("Alarms");
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));


    }
//alarm list copy contains all the entry and according to the filter selected alarm list is
// manipulated and the adapter is set on alarm_list

    @Override
    public void onButtonClicked(String text) {
        Toast.makeText(this,"Changes Applied.",Toast.LENGTH_SHORT).show();
        opt=text;
       switch(opt){
           case "Zone 1":
               alarm_list.clear();
               Log.i("check Inside",opt);
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("North")){
                       alarm_list.add(alarm_listcopy.get(i));

                   }
               }

               break;
           case "Zone 2":
               alarm_list.clear();
               Log.i("Inside",opt);
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("South")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "Zone 3":
               alarm_list.clear();
               Log.i("Inside",opt);
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("East")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "Zone 4":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("West")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "1":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("North")||alarm_listcopy.get(i).getZone().contains("South")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "2":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("North")||alarm_listcopy.get(i).getZone().contains("East")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "3":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("North")||alarm_listcopy.get(i).getZone().contains("West")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "4":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("South")||alarm_listcopy.get(i).getZone().contains("East")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "5":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("South")||alarm_listcopy.get(i).getZone().contains("West")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }

               break;
           case "6":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("East")||alarm_listcopy.get(i).getZone().contains("West")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "7":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("North")||alarm_listcopy.get(i).getZone().contains("South")||alarm_listcopy.get(i).getZone().contains("East")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "8":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("North")||alarm_listcopy.get(i).getZone().contains("South")||alarm_listcopy.get(i).getZone().contains("West")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "9":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("North")||alarm_listcopy.get(i).getZone().contains("East")||alarm_listcopy.get(i).getZone().contains("West")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "10":
               Log.i("Inside",opt);
               alarm_list.clear();
               for(int i=0;i<alarm_listcopy.size();i++){
                   if(alarm_listcopy.get(i).getZone().contains("South")||alarm_listcopy.get(i).getZone().contains("East")||alarm_listcopy.get(i).getZone().contains("West")){
                       alarm_list.add(alarm_listcopy.get(i));
                       Log.i("Check Added",alarm_listcopy.get(i).getZone());
                   }
               }
               break;
           case "11":
               alarm_list.clear();
               alarm_list.addAll(alarm_listcopy);
               break;
       }
        mAdapter.notifyDataSetChanged();

    }


   /* public void alarm_nacho_view()
    {

        suggestions.add("Hey");
       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
       // nachotextview=findViewById(R.id.nacho_text_view);
       // nachotextview.setText(suggestions);
    }
*/
}
