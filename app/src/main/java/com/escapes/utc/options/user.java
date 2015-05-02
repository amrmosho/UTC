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
            //   Log.d("getData", thisdata[0] + "::" + thisdata.length);
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
        Log.d("mytest", Data);
//    3sdfsdfsdfsf;progress,50;@id,9;users_group_id,25;supervisor_id,2;title,java taske;dec,77777;created,2015-04-28 00:00:00;ended,2015-04-30 00:00:00;image,5536669580890.jpg;video,https://www.youtube.com/watch?v=3u1fu6f8Hto;file,;status,3;requests,important parts of Gradle to get your build up and running

        String[] rows = Data.split("@");
        int rownum = 0;
        for (String row : rows) {
            String[] ds = row.split(";");
            Map<String, String> tmap = new HashMap<String, String>();
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
            taskesList.add(rownum, tmap);
            rownum++;

        }

    }


    public static void clearTaskesData(String Data) {
        taskesList.clear();

    }


    public static Map getTaskByid(String id) {
        Map<String, String> tmap = new HashMap<String, String>();
        for (Map<String, String> m : user.taskesList) {

            if (m.get("id") != null) {
                if (m.get("id").equals(id)) {

                    tmap = m;

                }
            }

        }
        return tmap;

    }

    public static String getStatus(String id) {
        String r = "";
        switch (id) {

            //             <option value="1">Draft</option><option value="2">
            // Created</option><option value="3">In Progress</option><option value="4">Put on hold</option><option value="5">Completed</option><option value="6">Cancelled</option><option value="7">Verified</option></select>

            case "1":
                r = "Draft";
                break;
            case "2":
                r = "In Progress";
                break;
            case "3":
                r = "In Progress";
                break;
            case "4":
                r = "Put on hold";
                break;
            case "5":
                r = "Completed";
                break;
            case "6":
                r = "Cancelled";
                break;
            case "7":
                r = "Verified";
                break;

        }
        return r;
    }


}
