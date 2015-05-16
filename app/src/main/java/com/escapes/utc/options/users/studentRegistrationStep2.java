package com.escapes.utc.options.users;

import android.content.Intent;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.escapes.utc.R;
import com.escapes.utc.libs.serverOperations;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.StrictMode;

public class studentRegistrationStep2 extends ActionBarActivity {
    public static final int LOAD_IMAGE_RESULTS = 1;
    ImageView image;String name = "",Passsword = "",age = "",email = "",username = "",group = "";

    void UpdateTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_step2);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        UpdateTitle("student registration ");
        name = getIntent().getExtras().getString("name");
        Passsword = getIntent().getExtras().getString("Passsword");
        age = getIntent().getExtras().getString("age");
        email = getIntent().getExtras().getString("email");
        username = getIntent().getExtras().getString("username");
        group = getIntent().getExtras().getString("group");
        image = (ImageView) findViewById(R.id.img_sudent_image);

          }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            try {
                image.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(pickedImage)));
            } catch (FileNotFoundException e) {
            } }
    }
    public void saveNewStudent() {

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


    public void btHomeAction(View view) {
        Intent i = new Intent(studentRegistrationStep2.this, lgoin.class);
        startActivity(i);
    }

    public void btSaveAction(View view) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("title", name));
        nameValuePairs.add(new BasicNameValuePair("password", Passsword.toLowerCase()));
        nameValuePairs.add(new BasicNameValuePair("age", age));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("username", username.toLowerCase()));
        nameValuePairs.add(new BasicNameValuePair("group", group));
        nameValuePairs.add(new BasicNameValuePair("status", "savestudent"));
        serverOperations.sendToServer(nameValuePairs);
        Toast t = Toast.makeText(this, "Your account has bvbeen created successfully ", Toast.LENGTH_SHORT);
        t.show();
    }

    public void btgetImageAction(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, LOAD_IMAGE_RESULTS);
    }
}
