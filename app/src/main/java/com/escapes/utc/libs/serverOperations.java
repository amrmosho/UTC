package com.escapes.utc.libs;

import android.util.Log;

import com.escapes.utc.options.config;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

/**
 * Created by eScapes on 2/17/15.
 */
public class serverOperations {

    public static String sendToServer(ArrayList<NameValuePair> nameValuePairs) {

        String r = "";


        try {
        HttpPost httppost = new HttpPost(config.URLServer);



        HttpClient httpclient = new DefaultHttpClient();


            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));



            HttpResponse response = httpclient.execute(httppost);


            String st = EntityUtils.toString(response.getEntity());

            r = st;



        } catch (Exception e) {
            r=e.toString();


        }



        return r;

    }




    public static String getData(String table,String field){

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("field", field));
        nameValuePairs.add(new BasicNameValuePair("table",table));
        nameValuePairs.add(new BasicNameValuePair("status", "getData"));


        return serverOperations.sendToServer(nameValuePairs);



    }


}
