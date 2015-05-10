package com.escapes.utc.options;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.escapes.utc.libs.serverOperations;
import com.escapes.utc.users.student.mytaskes;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eScapes on 2/18/15.
 */
public class user {


    public static HashMap<String, String> data = new HashMap<String, String>();

    public static List<Map<String, String>> taskesList = new ArrayList<Map<String, String>>();
    public static List<Map<String, String>> messagesList = new ArrayList<Map<String, String>>();

    public static List<Map<String, String>> meetingsList = new ArrayList<Map<String, String>>();
    public static List<Map<String, String>> evaloation = new ArrayList<Map<String, String>>();
    public static List<Map<String, String>> reportsList = new ArrayList<Map<String, String>>();
    public static List<Map<String, String>> marksList = new ArrayList<Map<String, String>>();
    public static List<Map<String, String>> studentesList = new ArrayList<Map<String, String>>();
    public static List<Map<String, String>> finale_reportList = new ArrayList<Map<String, String>>();


    public static String act_student = "";

    public static String act_mark = "";
    public static String act_taske = "";
    public static String act_todo = "";
    public static String act_message = "";
    public static String act_meetings = "";
    public static String act_report = "";


    public static String act_finale_report = "";


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


    public static List<Map<String, String>> fillListData(String Data) {
        //    Log.d("mytest", Data);

        List<Map<String, String>> r = new ArrayList<Map<String, String>>();


        String[] rows = Data.split("#");
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
            r.add(rownum, tmap);
            rownum++;

        }
        return r;
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

    public static Map geDataByid(String id, List<Map<String, String>> liat) {
        Map<String, String> tmap = new HashMap<String, String>();
        for (Map<String, String> m : liat) {
            if (m.get("id") != null) {
                if (m.get("id").equals(id)) {
                    tmap = m;
                }
            }

        }
        return tmap;

    }


    public static int gePostionByid(String id, List<Map<String, String>> liat) {
        int tmap = -1;
        int i = 0;
        for (Map<String, String> m : liat) {


            if (m.get("id") != null) {
                if (m.get("id").equals(id)) {
                    tmap = i;
                }
            }
            i++;

        }
        return tmap;

    }


    public static void fillTaskesData(String Data) {

        taskesList = fillListData(Data);

    }


    public static Map getDataByID(String id, String Type) {

        Map<String, String> r;
        switch (Type) {
            case "task_messages"
                    :
                r = geDataByid(id, messagesList);
                break;

            case "task_meetings"
                    :
                r = geDataByid(id, meetingsList);

                break;

            case "evaloation"
                    :
                r = geDataByid(id, evaloation);
                break;
            case "task_requests"
                    :
                r = geDataByid(id, reportsList);
                break;


            case "marks"
                    :
                r = geDataByid(id, marksList);
                break;



            case "studentes"
                    :
                r = geDataByid(id, studentesList);
                break;


            case "finale_report"
                    :
                r = geDataByid(id, finale_reportList);
                break;


            default:
                r = geDataByid(id, taskesList);

                break;
        }

        return r;

    }

    public static void fillListData(String Data, String Type) {
        switch (Type) {
            case "task_messages"
                    :

                messagesList = fillListData(Data);


                break;

            case "task_meetings"
                    :
                meetingsList = fillListData(Data);

                break;

            case "evaloation"
                    :
                evaloation = fillListData(Data);
                break;
            case "task_requests"
                    :
                reportsList = fillListData(Data);
                break;


            case "marks"
                    :
                marksList = fillListData(Data);
                break;

            case "finale_report"
                    :
                finale_reportList = fillListData(Data);

                break;

            case "studentes"
                    :
                studentesList = fillListData(Data);
                break;




            default:
                taskesList = fillListData(Data);

                break;
        }

    }


    public static void clearTaskesData(String Data) {
        taskesList.clear();


    }


    public static String getStatus(String id) {
        String r = "";
        switch (id) {

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


    public static void allDataClear() {

        taskesList.clear();
        data.clear();


        messagesList.clear();
        meetingsList.clear();
        evaloation.clear();
        reportsList.clear();


        act_message = "";

        act_todo = "";
        act_report = "";
        act_taske = "";
    }

    public static String getData(String Type, String Where) {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("where", Where));

        nameValuePairs.add(new BasicNameValuePair("table", Type));
        nameValuePairs.add(new BasicNameValuePair("status", "fillData"));
        String r = serverOperations.sendToServer(nameValuePairs);
        if (!r.trim().equalsIgnoreCase("-1")) {
            fillListData(r, Type);
        }
        return r.trim();
    }


    public static String set_insert(String table, Map<String, String> setdata) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String k : setdata.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(k, setdata.get(k)));
        }
        nameValuePairs.add(new BasicNameValuePair("table", table));
        nameValuePairs.add(new BasicNameValuePair("status", "insert"));
        String r = serverOperations.sendToServer(nameValuePairs);
        return r.trim();
    }

    public static String set_update(String table, Map<String, String> setdata, String where) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String k : setdata.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(k, setdata.get(k)));
        }
        nameValuePairs.add(new BasicNameValuePair("where", where));
        nameValuePairs.add(new BasicNameValuePair("table", table));
        nameValuePairs.add(new BasicNameValuePair("status", "edit"));
        String r = serverOperations.sendToServer(nameValuePairs);
        return r.trim();
    }


    public static String set_delete(String table, String where) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("where", where));
        nameValuePairs.add(new BasicNameValuePair("table", table));
        nameValuePairs.add(new BasicNameValuePair("status", "delete"));
        String r = serverOperations.sendToServer(nameValuePairs);
        return r.trim();
    }

}
