package com.escapes.utc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.user;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView in= (TextView)findViewById(R.id.info);

        /*

04-30 19:26:51.000  13365-13365/? D/new2222? id,16;title,amrmosho;email,empcland@gmail.com;Image,5514a1bfc8c0a.jpg;age,30;group,25;username,amrmosho;password,f06c22dbb3ce903ab93d9316517a26ef;phonenumber,;phoneid,;per,1;enabled,1;logintype,student;

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

//Bitmap

        in.setText(user.data.get("title"));


        RotateAnimation rotate= (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);
        in.setAnimation(rotate);

        uitls u=new uitls();

        ImageView mImgView1 = (ImageView) findViewById(R.id.studen_img);


                mImgView1.setImageBitmap(getRoundedShape(u.getImageFromUrl( user.data.get("Image"))));



    }




    public Bitmap getRoundedShape( Bitmap scaleBitmapImage) {
        int targetWidth = 90;
        int targetHeight = 0;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
