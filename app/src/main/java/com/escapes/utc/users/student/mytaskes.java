package com.escapes.utc.users.student;

import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.support.v4.app.ListFragment;
import android.widget.TextView;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.escapes.utc.R;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                        .replace(R.id.container, todolistFragment.newInstance(position + 1))
                        .commit();

                break;
            case 3:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, studentTaskes.messagesFragment.newInstance(position + 1))
                        .commit();
                break;
            case 4:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, meetingsFragment.newInstance(position + 1))
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


    /**
     * TabListener for todoList
     */



    public static class EditNameDialog extends DialogFragment {

        private EditText mEditText;
        public static EditNameDialog newInstance(int num){

            EditNameDialog dialogFragment = new EditNameDialog();
            Bundle bundle = new Bundle();
            bundle.putInt("num", num);
            dialogFragment.setArguments(bundle);

            return dialogFragment;

        }
        public EditNameDialog() {
            // Empty constructor required for DialogFragment
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_todolist_ope, container);
        //    mEditText = (EditText) view.findViewById(R.id.txt_your_name);
            getDialog().setTitle("Hello");

            return view;
        }
    }
    public static class todolistFragment extends Fragment  {

        private static final String ARG_SECTION_NUMBER = "section_number";


        public static  todolistFragment newInstance(int sectionNumber) {
            todolistFragment fragment = new todolistFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }




        public todolistFragment() {
        }


        public void t_addbt_click(View v){

            FragmentManager fm = getSherlockActivity().getSupportFragmentManager();

            DialogFragment dialogFrag = EditNameDialog.newInstance(111);
           // dialogFrag.setTargetFragment(this, DIALOG_FRAGMENT);\

            DialogFragment.setTargetFragment(this, 0);

            dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            // Show Alert DialogFragment
/*


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
 */


        }



uitls u=new uitls();
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_student_task_todolist, container, false);
            String tid = "9";

            Map m = user.getTaskByid(tid);


            TextView todo_tTitle = (TextView) rootView.findViewById(R.id.todo_tTitle);

            TextView todo_tStatus = (TextView) rootView.findViewById(R.id.todo_tStatus);

            TextView todo_tStartData = (TextView) rootView.findViewById(R.id.todo_tStartData);

            TextView todo_tEndData = (TextView) rootView.findViewById(R.id.todo_tEndData);

            TextView todo_tDes = (TextView) rootView.findViewById(R.id.todo_tDes);


            user.getData("task_todolist" , "task_id='" +tid + "' and users_id='"+ user.data.get("id") + "'");

           ListView lw= (ListView) rootView.findViewById(R.id.todolist);


            ArrayList<ListItem> listData = u.getListData(user.todoList);
            lw.setAdapter(new CustomListAdapter(inflater.getContext(), listData));



            todo_tTitle.setText((String) m.get("title"));
            todo_tStatus.setText(user.getStatus((String) m.get("status")));
            todo_tStartData.setText((String) m.get("created"));
            todo_tEndData.setText((String) m.get("ended"));
            todo_tDes.setText((String) m.get("dec")+"\n"+(String) m.get("requests"));


            TabHost th = (TabHost) rootView.findViewById(R.id.student_taske_todo);
            th.setup();
            TabHost.TabSpec tc = th.newTabSpec("Taske");
            tc.setIndicator("Taske");
            tc.setContent(R.id.student_taske_tab_todotsk);
            th.addTab(tc);


            tc = th.newTabSpec("tab3");
            tc.setIndicator("TodoList");
            tc.setContent(R.id.student_taske_tab_todo);
            th.addTab(tc);


            return rootView;
        }
    }


    /**
     * TabListener for messages
     */

    public static class messagesFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";


        public static messagesFragment newInstance(int sectionNumber) {
            messagesFragment fragment = new messagesFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        public messagesFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_student_task_messages, container, false);
            return rootView;
        }
    }


    /**
     * TabListener for meeting
     */

    public static class meetingsFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";


        public static meetingsFragment newInstance(int sectionNumber) {
            meetingsFragment fragment = new meetingsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        public meetingsFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_student_taskes_meetings, container, false);
            return rootView;
        }
    }


    /**
     * TabListener for main Tske
     */
    public static class reportFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static mainFragment newInstance(int sectionNumber) {
            mainFragment fragment = new mainFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public reportFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_student_taskes_report, container, false);
            TabHost th = (TabHost) rootView.findViewById(R.id.student_taske_tab);

            th.setup();
            TabHost.TabSpec tc = th.newTabSpec("Taske");
            tc.setIndicator("tabtext");
            tc.setContent(R.id.student_taske_tab_tsk);
            th.addTab(tc);


            tc = th.newTabSpec("tab2");
            tc.setIndicator("Report");
            tc.setContent(R.id.student_taske_tab_rqs);
            th.addTab(tc);


            tc = th.newTabSpec("tab3");
            tc.setIndicator("Markes");
            tc.setContent(R.id.student_taske_tab_mark);
            th.addTab(tc);

            return rootView;
        }
    }


    /**
     * TabListener for main Tske
     */


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
