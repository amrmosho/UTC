package com.escapes.utc.options;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eScapes on 2/18/15.
 */
public class user {


    public static HashMap<String, String> data = new HashMap<String, String>();
  // public static HashMap<String, String>[] taskes[] = new HashMap<String, String>()[];

    public static List<Map<String, String>> taskesList = new ArrayList<Map<String, String>>();
    public static void addUserData(String Data) {
        String[] ds = Data.split(";");
        for (String d : ds) {
            String[] thisdata = d.split(",");
            Log.d("getData", thisdata[0] + "::" + thisdata.length);
            String myv = "";
            if (thisdata.length == 1) {
                myv = "-";
            } else {
                myv = thisdata[1];
            }
            user.data.put(thisdata[0], myv);
        }


    }


    public static void ClaerUserData(String Data) {
        user.data.clear();

    }

    public static void fillTaskesData(String Data) {

        String[] rows = Data.split("@");
        int rownum=0;
        for (String row : rows) {
            String[] ds = row.split(";");
            Map<String, String> tmap = new HashMap<String,String>();
            for (String d : ds) {
                String[] thisdata = d.split(",");
                String myv = "";
                if (thisdata.length == 1) {
                    myv = "-";
                } else {
                    myv = thisdata[1];
                }
                tmap.put(thisdata[0], myv);
            }
            taskesList.add(rownum,tmap);
            rownum++;
        }

    }


    public static void clearTaskesData(String Data) {
        taskesList.clear();

    }
}
