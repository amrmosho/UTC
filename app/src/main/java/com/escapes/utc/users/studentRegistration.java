package com.escapes.utc.users;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.escapes.utc.R;
import com.escapes.utc.libs.serverOperations;
import com.escapes.utc.libs.uitls;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class studentRegistration extends ActionBarActivity {


//EditText txt_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        sp_group = (Spinner) findViewById(R.id.sp_sudent_groip);
        updateGroup();
        Button bt_send = (Button) findViewById(R.id.bt_sudent_send);




        bt_send.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sendData( v);

                    }
                }
        );


    }




    Spinner sp_group;

    void sendData(View v){




        final EditText txt_name = (EditText) findViewById(R.id.txt_sudent_name);
        final EditText txt_password = (EditText) findViewById(R.id.txt_sudent_password);
        final EditText txt_age = (EditText) findViewById(R.id.txt_sudent_age);
        final EditText txt_email = (EditText) findViewById(R.id.txt_sudent_email);
        final EditText txt_username = (EditText) findViewById(R.id.txt_sudent_username);





        Intent i = new Intent(studentRegistration.this, studentRegistrationStep2.class);


        i.putExtra("name", txt_name.getText().toString());
        i.putExtra("Passsword", txt_password.getText().toString());
        i.putExtra("age", txt_age.getText().toString());
        i.putExtra("email", txt_email.getText().toString());
        i.putExtra("username", txt_username.getText().toString());
        i.putExtra("group", sp_group.getSelectedItem().toString());


        startActivity(i);


    }


    void updateGroup() {





       // ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        String  ddataList= serverOperations.getData("studentes_groups", "title");




        List<String> list = new ArrayList<String>();


        String getData[] = ddataList.split(";");
        for (String a : getData) {
            list.add(a);
        }





        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);



        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        sp_group.setAdapter(dataAdapter);

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }


}
