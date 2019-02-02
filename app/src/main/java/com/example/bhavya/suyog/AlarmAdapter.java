package com.example.bhavya.suyog;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ExampleViewHolder> implements Filterable {
    private ArrayList<Alarm> mAlarmList;
    private ArrayList<Alarm> mAlarmListFull;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView zoneInitials;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mTextView1 = itemView.findViewById(R.id.textView_zone);
            mTextView2 = itemView.findViewById(R.id.textView_location);
            mTextView3 = itemView.findViewById(R.id.textView_site_name);
            mTextView4 = itemView.findViewById(R.id.textView_site_id);
            zoneInitials=itemView.findViewById(R.id.zone_initials);
        }
    }

    public AlarmAdapter(ArrayList<Alarm> exampleList) {
        mAlarmList = exampleList;
        mAlarmListFull=new ArrayList<>(mAlarmList);
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_list_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Alarm currentItem = mAlarmList.get(position);
        holder.mTextView1.setText(currentItem.getZone());
        holder.mTextView2.setText(currentItem.getLocation());
        holder.mTextView3.setText(currentItem.getSite_Name());
        holder.mTextView4.setText(currentItem.getSuyog_Site_ID());
        holder.zoneInitials.setText(currentItem.getImage_zoneInitials());

    }

    @Override
    public int getItemCount() {
        return mAlarmList.size();
    }
    @Override
    public Filter getFilter() {
        return alarmFilter;
    }

    public Filter alarmFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Alarm> filteredList=new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredList.addAll(mAlarmListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                if (MainActivity.search_type.equals("Location")) {
                    Log.i("Adapter","Inside Location");
                    for (Alarm item : mAlarmListFull) {
                        if (item.getLocation().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }


                } else {
                    if (MainActivity.search_type.equals("Zone")) {
                        Log.i("Adapter","Inside Zone");
                        for (Alarm item : mAlarmListFull) {
                            if (item.getZone().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }
                        }

                    }
                    else {
                        Log.i("Adapter","Inside Both");
                        for (Alarm item : mAlarmListFull) {
                            if (item.getZone().toLowerCase().contains(filterPattern)||item.getLocation().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }
                        }

                    }

                }

            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAlarmList.clear();
            mAlarmList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

}