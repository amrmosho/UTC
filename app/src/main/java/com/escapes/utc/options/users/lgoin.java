package com.escapes.utc.options.users;

import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.escapes.utc.R;
import com.escapes.utc.home;
import com.escapes.utc.libs.serverOperations;
import com.escapes.utc.options.user;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class lgoin extends ActionBarActivity {


    void UpdateTitle(String title) {


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(title);
    }


    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgoin);


        UpdateTitle("Lgoin");


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lgoin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btloginAction(View view) {

        EditText txt_username = (EditText) findViewById(R.id.txt_login_uaername);
        EditText txt_password = (EditText) findViewById(R.id.txt_lgoin_password);


        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


        if (txt_password.getText().toString().equalsIgnoreCase("") || txt_username.getText().toString().equalsIgnoreCase("")) {
            Toast t = Toast.makeText(lgoin.this, "Username & password errors :( ", Toast.LENGTH_LONG);
            t.show();

        } else {
            nameValuePairs.add(new BasicNameValuePair("username", txt_username.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", txt_password.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("status", "setlogin"));

            String r = serverOperations.sendToServer(nameValuePairs);


            if (r.trim().equalsIgnoreCase("-1")) {
                Toast t = Toast.makeText(lgoin.this, "Username & password errors :( ", Toast.LENGTH_LONG);
                t.show();
            } else {
                user.addUserData(r);
                try {

                    if (user.data.get("logintype").equalsIgnoreCase("student")) {

                        Intent i = new Intent(lgoin.this, home.class);
                        startActivity(i);

                    } else {
                        if (user.data.get("group").equalsIgnoreCase("2")) {

                            Intent i = new Intent(lgoin.this,  tSuperHome.class);
                            startActivity(i);
                        } else {

                            Intent i = new Intent(lgoin.this, superHome.class);
                            startActivity(i);
                        }
                    }


                } catch (Exception e) {
                    Toast t = Toast.makeText(lgoin.this, "Error :(  " + r+e.getMessage()
                            , Toast.LENGTH_LONG);
                    t.show();


                }
            }
        }
    }

    public void btCreateAction(View view) {
        RadioButton rdo_student = (RadioButton) findViewById(R.id.rdo_lgoin_student);
        RadioButton rdo_superVisor = (RadioButton) findViewById(R.id.rdo_lgoin_SuperVisor);

        if (rdo_superVisor.isChecked()) {


            Intent i = new Intent(lgoin.this, supervisorRegistration.class);


            startActivity(i);


        } else {
            Intent i = new Intent(lgoin.this, studentRegistration.class);
            startActivity(i);
        }

    }
}
