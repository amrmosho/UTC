package com.escapes.utc.users.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.escapes.utc.R;
import com.escapes.utc.home;
import com.escapes.utc.options.user;
import com.escapes.utc.users.lgoin;
import com.escapes.utc.users.student.Fragments.*;
import com.escapes.utc.users.superHome;
import com.escapes.utc.users.tSuperHome;

public class mytaskes extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    String show_status="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mytaskes);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));




    }




    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (getIntent().hasExtra("showStatus"))

        {


            show_status = getIntent().getStringExtra("showStatus");




            if (user.data.get("logintype").equalsIgnoreCase("student")) {

                    switch (show_status) {
                        case "Taskes":
                            position = 0;
                            break;
                        case "Messages":
                            position = 1;
                            break;
                        case "Reports":
                            position = 2;
                            break;
                        case "Marks":
                            position = 3;
                            break;
                        case "ADS":
                            position = 4;
                            break;
                    }


                } else {
                    if (user.data.get("group").equalsIgnoreCase("2")) {



                        switch (show_status) {
                            case "Taskes":
                                position = 0;
                                break;
                            case "StudentMessages":
                                position = 1;
                                break;
                            case "adminMessages":
                                position = 2;
                                break;
                            case "Marks":
                                position = 3;
                                break;
                            case "ads":
                                position = 4;
                                break;
                        }

                    } else {

                        switch (show_status) {


                            case "adminMessages":
                                position = 0;
                                break;
                            case "finaleMarks":
                                position = 1;
                                break;
                            case "Evaloation":
                                position = 2;
                                break;

                            case "ads":
                                position = 3;
                                break;
                        }


                    }
                }


            getIntent().removeExtra("showStatus");
            show_status = "";

        }


        if (user.data.get("logintype").equalsIgnoreCase("student")) {



            switch (position) {

            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mainFragment.newInstance(position + 1)).commit();
                break;

            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, messagesFragment.newInstance(position + 1))
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, reportFragment.newInstance(position + 1))
                        .commit();
                break;

            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, reportFragment.newInstance(position + 1))
                        .commit();
                break;
            case 4:

                fragmentManager.beginTransaction()
                        .replace(R.id.container,  reportFragment.newInstance(position + 1))
                        .commit();
                break;
            case 5:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, meetingsFragment.newInstance(position + 1))
                        .commit();


                if (user.data.get("logintype").equalsIgnoreCase("student")) {

                    Intent i = new Intent(mytaskes.this, home.class);
                    startActivity(i);

                } else {
                    if (user.data.get("group").equalsIgnoreCase("2")) {

                        Intent i = new Intent(mytaskes.this,  tSuperHome.class);
                        startActivity(i);
                    } else {

                        Intent i = new Intent(mytaskes.this, superHome.class);
                        startActivity(i);
                    }
                }

                break;
            case 6:
                Intent i = new Intent(mytaskes.this, lgoin.class);
                user.allDataClear();
                startActivity(i);
                break;
        }




        } else {
            if (user.data.get("group").equalsIgnoreCase("2")) {



                switch (position) {

                    case 0:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, mainFragment.newInstance(position + 1)).commit();
                        break;

                    case 1:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, messagesFragment.newInstance(position + 1))
                                .commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, messagesFragment.newInstance(position + 1))
                                .commit();
                        break;

                    case 3:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, reportFragment.newInstance(position + 1))
                                .commit();
                        break;
                    case 4:

                        fragmentManager.beginTransaction()
                                .replace(R.id.container,  evaloationFragment.newInstance(position + 1))
                                .commit();
                        break;
                    case 5:

                        fragmentManager.beginTransaction()
                                .replace(R.id.container, meetingsFragment.newInstance(position + 1))
                                .commit();


                        if (user.data.get("logintype").equalsIgnoreCase("student")) {

                            Intent i = new Intent(mytaskes.this, home.class);
                            startActivity(i);

                        } else {
                            if (user.data.get("group").equalsIgnoreCase("2")) {

                                Intent i = new Intent(mytaskes.this,  tSuperHome.class);
                                startActivity(i);
                            } else {

                                Intent i = new Intent(mytaskes.this, superHome.class);
                                startActivity(i);
                            }
                        }

                        break;
                    case 6:
                        Intent i = new Intent(mytaskes.this, lgoin.class);
                        user.allDataClear();
                        startActivity(i);
                        break;
                }


            } else {

/*


 "admin messages",
                        "finale marks",
                        "Evaloation",
                        "ADS",
 */
                switch (position) {

                    case 0:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, evaloationFragment.newInstance(position + 1)).commit();
                        break;

                    case 1:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, finaleReport.newInstance(position + 1))
                                .commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, evaloationFragment.newInstance(position + 1))
                                .commit();
                        break;

                    case 3:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, evaloationFragment.newInstance(position + 1))
                                .commit();
                        break;

                    case 5:

                        fragmentManager.beginTransaction()
                                .replace(R.id.container, reportFragment.newInstance(position + 1))
                                .commit();


                        if (user.data.get("logintype").equalsIgnoreCase("student")) {

                            Intent i = new Intent(mytaskes.this, home.class);
                            startActivity(i);

                        } else {
                            if (user.data.get("group").equalsIgnoreCase("2")) {

                                Intent i = new Intent(mytaskes.this, tSuperHome.class);
                                startActivity(i);
                            } else {

                                Intent i = new Intent(mytaskes.this, superHome.class);
                                startActivity(i);
                            }
                        }

                        break;
                    case 6:
                        Intent i = new Intent(mytaskes.this, lgoin.class);
                        user.allDataClear();
                        startActivity(i);
                        break;
                }




            }






        }
















            }

    public void onSectionAttached(int number) {


        switch (number) {
            case 0:
                mTitle = "Taskes";
                break;
            case 1:
                mTitle = "Todolist";
                break;
            case 2:
                mTitle = "Messages";
                break;
            case 3:
                mTitle = "Meetings";
                break;
            case 4:
                mTitle = "Reportes";
                break;
        }




        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.mytaskes, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
