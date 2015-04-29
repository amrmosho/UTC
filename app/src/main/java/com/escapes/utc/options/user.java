package com.escapes.utc.options;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by eScapes on 2/18/15.
 */
public class user {

    /*


      [id] => 16
[title] => amrmosho
[email] => empcland@gmail.com
[Image] => 5514a1bfc8c0a.jpg
[age] => 30
[group] => 25
[username] => amrmosho
[password] => f06c22dbb3ce903ab93d9316517a26ef
[phonenumber] =>
[phoneid] =>
[per] => 1
[logintype] => student
     */
   public static     HashMap<String, String>   data =new HashMap<String, String>();;



    public static void  addUserData(String Data){


        String[]ds=Data.split(";");

            for(String d : ds){

                String[] thisdata=d.split(",");
                Log.d("getData",thisdata[0]+"::"+thisdata.length);
String myv="";
if (thisdata.length==1){


  myv="-";



                }else{

    myv=thisdata[1];
}

                user.data.put(thisdata[0], myv);

            }


    }
    public  static void  ClaerUserData(String Data){
        user.data.clear();

    }



}
