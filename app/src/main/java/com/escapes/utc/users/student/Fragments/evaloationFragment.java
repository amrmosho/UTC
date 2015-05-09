package com.escapes.utc.users.student.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.escapes.utc.R;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;

import java.util.ArrayList;

/**
 * Created by empcl_000 on 03/05/2015.
 */



public class evaloationFragment extends Fragment  {

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
                user.act_todo = user.evaloation.get(position).get("id");
                view.setSelected(true);

            }
        });


        updateList();

        return rootView;
    }


    void updateList() {
        user.getData("evaloation","1=1");
        ArrayList<ListItem> listData = u.getListData(user.evaloation,"student_id","value","value");
        lw.setAdapter(new CustomListAdapter(inf.getContext(), listData));

    }


}