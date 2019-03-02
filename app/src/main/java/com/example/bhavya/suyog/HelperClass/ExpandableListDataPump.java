package com.example.bhavya.suyog.HelperClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> zone = new ArrayList<String>();
        zone.add("North");
        zone.add("South");
        zone.add("East");
        zone.add("West");
        zone.add("None");

        List<String> alarm = new ArrayList<String>();
        alarm.add("Alarm 1");
        alarm.add("Alarm 2");
        alarm.add("Alarm 3");
        alarm.add("Alarm 4");
        alarm.add("Alarm 5");


        expandableListDetail.put("Zone ", zone);
        expandableListDetail.put("Alarm ", alarm);

        return expandableListDetail;
    }
    
}
