package com.escapes.utc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.escapes.utc.options.user;


public class home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView in= (TextView)findViewById(R.id.info);

        /*


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



        in.setText(user.data.get("id"));

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
