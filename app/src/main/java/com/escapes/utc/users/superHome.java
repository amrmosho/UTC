package com.escapes.utc.users;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.escapes.utc.R;
import com.escapes.utc.libs.serverOperations;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;
import com.escapes.utc.users.student.mytaskes;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class superHome extends ActionBarActivity {


    void UpdateTitle(String title) {


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(title);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_home);

        UpdateTitle("Supervisor Home");

        TextView in = (TextView) findViewById(R.id.super_info);

        in.setText(user.data.get("title"));


        RotateAnimation rotate = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);
        in.setAnimation(rotate);
        uitls u = new uitls();
        ImageView mImgView1 = (ImageView) findViewById(R.id.super_img);
        mImgView1.setImageBitmap(u.getRoundedShape(u.getImageFromUrl(user.data.get("Image"))));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_super_home, menu);
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

    public void Exit_click(View view) {


        Intent i = new Intent(superHome.this, lgoin.class);

        user.allDataClear();
        startActivity(i);

    }







    public void taskesSButtonClick(View view) {

            Intent i = new Intent(superHome.this, mytaskes.class);
            startActivity(i);

    }
}
