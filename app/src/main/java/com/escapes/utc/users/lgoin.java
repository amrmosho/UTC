package com.escapes.utc.users;

import android.content.Intent;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgoin);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Button bt_login = (Button) findViewById(R.id.bt_login_send);
        Button bt_create = (Button) findViewById(R.id.bt_lgoin_create);











        bt_login.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login(v);
                    }
                }

        );

        bt_create.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        create(v);
                    }
                }

        );





    }


    void login(View v) {


        EditText txt_username = (EditText) findViewById(R.id.txt_login_uaername);
        EditText txt_password = (EditText) findViewById(R.id.txt_lgoin_password);




        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();



        nameValuePairs.add(new BasicNameValuePair("username", txt_username.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("password", txt_password.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("status", "setlogin"));



        String r = serverOperations.sendToServer(nameValuePairs);



user.data=r;


        if (r.trim().equalsIgnoreCase("-1")) {
            Toast t = Toast.makeText(this, "Username & password errors :( ", Toast.LENGTH_LONG);
            t.show();
        } else {

            Intent i = new Intent(lgoin.this, home.class);
            startActivity(i);
        }

    }









    void create(View v) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lgoin, menu);
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
