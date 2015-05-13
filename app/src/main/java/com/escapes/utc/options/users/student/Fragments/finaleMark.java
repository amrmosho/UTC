package com.escapes.utc.options.users.student.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.escapes.utc.R;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by empcl_000 on 03/05/2015.
 */


public class finaleMark extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static finaleMark newInstance(int sectionNumber) {
        finaleMark fragment = new finaleMark();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public finaleMark() {
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

                newFragment.setTargetFragment(finaleMark.this, 0);

                newFragment.show(getFragmentManager(), "dialog");

            }
        });


        updateList();

        return rootView;
    }


    void updateList() {
        user.getData("finale_marks", "supervisor_id="+user.data.get("id"));
        ArrayList<ListItem> listData = u.getListData(user.finale_markstList, "student_title", "value", "student_id");
        lw.setAdapter(new CustomListAdapter(inf.getContext(), listData));

    }


    public static class student_data extends DialogFragment {
        String me = "tasks";
        uitls u = new uitls();

        static String movie_status = "";

        static student_data newInstance(String status) {
            student_data.movie_status = status;
            return new student_data();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View v = inflater.inflate(R.layout.dialog_report, container, false);

            user.getData("finale_repor", "id='" + user.act_finale_report + "'");
            getDialog().setTitle("Finale Repor Info");

            TextView r_tTitle = (TextView) v.findViewById(R.id.r_tTitle);
            TextView r_dec = (TextView) v.findViewById(R.id.r_dec);
            TextView r_status = (TextView) v.findViewById(R.id.r_status);


            Map m = user.finale_reportList.get(0);

            r_tTitle.setText((String) m.get("title"));
            r_dec.setText((String) m.get("dec"));
            r_status.setText((String) m.get("status"));


            return v;


        }


    }


}