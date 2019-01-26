package com.example.bhavya.suyog;

public class Alarm {
    private String suyog_Site_ID;
    private String zone;
    private String location;
    private String site_Name;

    public Alarm(String suyog_Site_ID, String zone, String location, String site_Name) {
        this.suyog_Site_ID = suyog_Site_ID;
        this.zone = zone;
        this.location = location;
        this.site_Name = site_Name;
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
