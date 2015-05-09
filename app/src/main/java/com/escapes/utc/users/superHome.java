package com.escapes.utc.users;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.escapes.utc.R;
import com.escapes.utc.libs.serverOperations;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;
import com.escapes.utc.users.student.Fragments.evaloationFragment;
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






    public void taskesButtonClick(View view) {

        Intent i = new Intent(superHome.this, mytaskes.class);
        startActivity(i);
    }

    public void reportsBtClick(View view) {
        DialogFragment newFragment = taskes_list.newInstance("reports");
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void marksBtClick(View view) {

        DialogFragment newFragment = taskes_list.newInstance("marks");
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void messageBtClick(View view) {
        DialogFragment newFragment = taskes_list.newInstance("messages");
        newFragment.show(getSupportFragmentManager(), "dialog");

    }

    public void adsBtClick(View view) {
        DialogFragment newFragment = taskes_list.newInstance("ads");
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void evaloationBtClick(View view) {

        Intent i = new Intent(superHome.this,mytaskes.class);
        i.putExtra("showStatus", "");
        startActivity(i);
    }


    public static class taskes_list extends DialogFragment {
        String me = "tasks";
        uitls u = new uitls();

        static   String movie_status="";
        static taskes_list newInstance(String status) {
            taskes_list.movie_status=status;
            return new taskes_list();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View v = inflater.inflate(R.layout.taskes_list, container, false);

            String w;
            if (user.taskesList.size() == 0) {

                if (user.data.get("logintype").equalsIgnoreCase("student")) {
                    w = "supervisor_id='" + user.data.get("id") + "'";
                } else {
                    w = "(users_group_id='" + user.data.get("group") + "') and (status=3 or status=5 or status=6)";
                }
                user.getData(me, w);
            }

            ListView tsk_list = (ListView) v.findViewById(R.id.taske_list);
            ArrayList<ListItem> listData = u.getListData(user.taskesList);
            tsk_list.setAdapter(new CustomListAdapter(v.getContext(), listData));
            tsk_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    user.act_taske = user.taskesList.get(position).get("id");

                    view.setSelected(true);
                    gotoi(v);

                }
            });

            return v;


        }
        public void gotoi( View v) {
            Intent i = new Intent(v.getContext(),mytaskes.class);
            i.putExtra("showStatus", movie_status);
            startActivity(i);

        }

    }
}
