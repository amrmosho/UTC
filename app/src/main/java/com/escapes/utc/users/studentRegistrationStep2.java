package com.escapes.utc.users;

import android.content.Intent;
import android.graphics.BitmapFactory;

import android.net.Uri;
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

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.StrictMode;


public class studentRegistrationStep2 extends ActionBarActivity {


    public static final int LOAD_IMAGE_RESULTS = 1;

    ImageView image;
    String name = "";
    String Passsword = "";
    String age = "";
    String email = "";
    String username = "";
    String group = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_step2);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }





        name = getIntent().getExtras().getString("name");
        Passsword = getIntent().getExtras().getString("Passsword");
        age = getIntent().getExtras().getString("age");
        email = getIntent().getExtras().getString("email");
        username = getIntent().getExtras().getString("username");
        group = getIntent().getExtras().getString("username");






        image = (ImageView) findViewById(R.id.img_sudent_image);


        Button bt_save = (Button) findViewById(R.id.bt_sudent_save);


        Button bt_getImage = (Button) findViewById(R.id.bt_student_getimage);

        Button bt_home = (Button) findViewById(R.id.bt_student_home);




        bt_getImage.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, LOAD_IMAGE_RESULTS);
                    }
                }

        );





        bt_home.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(studentRegistrationStep2.this, lgoin.class);
                        startActivity(i);
                    }
                }

        );


        bt_save.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveNewStudent();

                    }
                }
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);




        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();


            try {
                image.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(pickedImage)));
            } catch (FileNotFoundException e) {
            }


        }


    }


    public void saveNewStudent() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("title", name));
        nameValuePairs.add(new BasicNameValuePair("password", Passsword));
        nameValuePairs.add(new BasicNameValuePair("age", age));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("group", group));
        nameValuePairs.add(new BasicNameValuePair("status", "savestudent"));



        serverOperations.sendToServer(nameValuePairs);
        Toast t = Toast.makeText(this, "Your account has been created successfully ", Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_registration_step2, menu);
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
