package com.example.bhavya.suyog.HelperClass;

import android.support.design.chip.ChipGroup;

import java.util.ArrayList;

public class Alarm {
    private String suyog_Site_ID;
    private String zone;
    private String location;
    private String zone_Initials;
    private String site_Name;
    private ArrayList<String> triggered_alarms;
    private ChipGroup chipgroup;



    public Alarm(String suyog_Site_ID, String zone, String location, String site_Name, String zone_Initials, ArrayList<String> alarms_triggered) {
        this.suyog_Site_ID = suyog_Site_ID;
        this.zone = zone;
        this.location = location;
        this.site_Name = site_Name;
        this.zone_Initials=zone_Initials;
      //  this.chipgroup=alarms_group;
        this.triggered_alarms=alarms_triggered;
    }

    public ArrayList<String> getTriggered_alarms() {
        return triggered_alarms;
    }

    public void setTriggered_alarms(ArrayList<String> triggered_alarms) {
        this.triggered_alarms = triggered_alarms;
    }

    public String getSuyog_Site_ID() {
        return suyog_Site_ID;
    }

    public void setSuyog_Site_ID(String suyog_Site_ID) {
        this.suyog_Site_ID = suyog_Site_ID;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSite_Name() {
        return site_Name;
    }

    public void setSite_Name(String site_Name) {
        this.site_Name = site_Name;
    }

}
