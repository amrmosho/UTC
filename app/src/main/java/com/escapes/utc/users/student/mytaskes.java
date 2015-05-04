package com.escapes.utc.users.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import com.escapes.utc.R;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;
import com.escapes.utc.users.student.Fragments.*;

import java.util.ArrayList;

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


        switch (position) {

            case 0:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, mainFragment.newInstance(position + 1)).commit();

                break;

            case 1:


                fragmentManager.beginTransaction()
                        .replace(R.id.container, todolistFragment.newInstance(position + 1))
                        .commit();

                break;

            case 2:


                fragmentManager.beginTransaction()
                        .replace(R.id.container, messagesFragment.newInstance(position + 1))
                        .commit();

                break;

            case 3:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, meetingsFragment.newInstance(position + 1))
                        .commit();
                break;
            case 4:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, reportFragment.newInstance(position + 1))
                        .commit();
                break;
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





    public static class mainFragment extends ListFragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static mainFragment newInstance(int sectionNumber) {
            mainFragment fragment = new mainFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public mainFragment() {
        }

        uitls u=new uitls();
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            ArrayList<ListItem> listData = u.getListData(user.taskesList);
            setListAdapter(new CustomListAdapter(inflater.getContext(), listData));
            return super.onCreateView(inflater, container, savedInstanceState);
        }



    }


}
