package com.escapes.utc.options.users.student;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.escapes.utc.R;
import com.escapes.utc.options.user;

import java.util.HashMap;
import java.util.Map;

public class todolist_ope extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist_ope);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todolist_ope, menu);
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

    public void t_config_send_click(View view) {
        TextView t_config_title= (TextView) findViewById(R.id.t_config_title);
        TextView t_config_date= (TextView) findViewById(R.id.t_config_date);
        TextView  t_config_data= (TextView) findViewById(R.id.t_config_data);
        Map<String,String> m= new HashMap<String,String>();
        m.put("title",(String)t_config_title.getText());
        m.put("created",(String)t_config_date.getText());
        m.put("dec",(String)t_config_data.getText());
        user.set_insert("task_todolist",m);
    }
}
