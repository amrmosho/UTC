package com.escapes.utc.users.student.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.escapes.utc.R;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import java.util.ArrayList;

/**
 * Created by empcl_000 on 03/05/2015.
 */



public class evaloationFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static evaloationFragment newInstance(int sectionNumber) {
        evaloationFragment fragment = new evaloationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public evaloationFragment() {
    }


    uitls u = new uitls();
    ListView lw;
    LayoutInflater inf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inf = inflater;
        View rootView = inf.inflate(R.layout.fragment_student_task_todolist, container, false);
        lw = (ListView) rootView.findViewById(R.id.todolist);
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user.act_student = user.evaloation.get(position).get("student_id");
                view.setSelected(true);

                DialogFragment newFragment = student_data.newInstance("");

                newFragment.setTargetFragment(evaloationFragment.this, 0);

                newFragment.show(getFragmentManager(), "dialog");

            }
        });


        updateList();

        return rootView;
    }


    void updateList() {
        user.getData("evaloation","1=1");
        ArrayList<ListItem> listData = u.getListData(user.evaloation,"student_title","value","value");
        lw.setAdapter(new CustomListAdapter(inf.getContext(), listData));

    }




    public static class student_data extends DialogFragment {
        String me = "tasks";
        uitls u = new uitls();

        static   String movie_status="";
        static student_data newInstance(String status) {
            student_data.movie_status=status;
            return new student_data();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View v = inflater.inflate(R.layout.student, container, false);

                user.getData("studentes", "id='" + user.act_student + "'");
getDialog().setTitle("Student Info");

            TextView st_title = (TextView) v.findViewById(R.id.st_title);
            TextView st_age = (TextView) v.findViewById(R.id.st_age);
            TextView st_city = (TextView) v.findViewById(R.id.st_city);
            TextView st_group = (TextView) v.findViewById(R.id.st_group);
            TextView st_state = (TextView) v.findViewById(R.id.st_state);
            TextView st_street = (TextView) v.findViewById(R.id.st_street);
           TextView st_year = (TextView) v.findViewById(R.id.st_year);
            TextView st_email = (TextView) v.findViewById(R.id.st_email);

            ImageView mImgView1 = (ImageView) v.findViewById(R.id.st_img);

            Map m = user.studentesList.get(0);

            st_title.setText((String)m.get("title"));

            st_age.setText((String)m.get("age") );
            st_city.setText((String)m.get("city"));
            st_group.setText((String)m.get("group_title"));
            st_state.setText((String)m.get("state"));
            st_street.setText((String)m.get("street"));

            st_year.setText((String )m.get("title"));

            st_email.setText((String )m.get("email"));
if (!m.get("Image").toString().trim().equals("") &&

        !m.get("Image").toString().trim().equals("-")

        ) {
    mImgView1.setImageBitmap(u.getRoundedShape(u.getImageFromUrl(m.get("Image").toString())));
}


            return v;


        }


    }



















}