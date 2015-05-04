package com.escapes.utc.libs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;

import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.config;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eScapes on 2/17/15.
 */
public class uitls {



 public   Bitmap getImageFromUrl(String imgName){
        String url = config.URLImages+imgName;
        BitmapFactory.Options bmOptions;
        bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;
        Bitmap bm = loadBitmap(url, bmOptions);
        return bm;
    }





    public static Bitmap loadBitmap(String URL, BitmapFactory.Options options) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
        }
        return bitmap;
    }



    private static InputStream OpenHttpConnection(String strURL)
            throws IOException {
        InputStream inputStream = null;
        URL url = new URL(strURL);
        URLConnection conn = url.openConnection();

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }
        } catch (Exception ex) {
        }
        return inputStream;
    }





    public Bitmap getRoundedShape( Bitmap scaleBitmapImage) {
        int targetWidth = 90;
        int targetHeight = 90;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

    public Bitmap getRoundedShape( Bitmap scaleBitmapImage , int targetWidth,int targetHeight) {

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }


    public ArrayList<ListItem> getListData(List<Map<String, String>> taskesList) {
        ArrayList<ListItem> listMockData = new ArrayList<ListItem>();
        for (Map<String, String> m : taskesList) {
            ListItem newsData = new ListItem();


            newsData.setHeadline(m.get("title"));
            newsData.setListDes(m.get("dec"));
            newsData.setId(m.get("id"));
            //newsData.setUrl(m.get("image"));
            //newsData.setDate(m.get("date"));
            listMockData.add(newsData);


        }
        return listMockData;
    }

}
