package com.escapes.utc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.escapes.utc.libs.serverOperations;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.user;
import com.escapes.utc.users.student.mytaskes;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView in = (TextView) findViewById(R.id.info);

        in.setText(user.data.get("title"));
        RotateAnimation rotate = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);
        in.setAnimation(rotate);
        uitls u = new uitls();
        ImageView mImgView1 = (ImageView) findViewById(R.id.studen_img);
        mImgView1.setImageBitmap(u.getRoundedShape(u.getImageFromUrl(user.data.get("Image"))));

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



    public void taskesButtonClick(View view) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("group", user.data.get("group")));
        nameValuePairs.add(new BasicNameValuePair("logintype",user.data.get("logintype")));
        nameValuePairs.add(new BasicNameValuePair("status", "tasks"));
        String r = serverOperations.sendToServer(nameValuePairs);
        if (r.trim().equalsIgnoreCase("-1")) {
            Toast t = Toast.makeText(this, "Username & password errors :( ", Toast.LENGTH_LONG);
            t.show();
        } else {
            user.fillTaskesData(r);

            Intent i = new Intent(home.this, mytaskes.class);
            startActivity(i);
        }
    }
}
