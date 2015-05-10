package com.escapes.utc.users.student.Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;

import java.util.ArrayList;

/**
 * Created by empcl_000 on 05/05/2015.
 */
public class mainFragment extends ListFragment {


    String me = "tasks";
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


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        user.act_taske = user.taskesList.get(position).get("id");

        v.setSelected(true);
    }

    uitls u = new uitls();


    @Override
    public void onStart() {


        int s = 1;
        if (user.act_taske == "") {
            user.act_taske = user.taskesList.get(0).get("id");
        }
        final int p = user.gePostionByid(user.act_taske, user.taskesList);
        getListView().post(new Runnable() {
            @Override
            public void run() {
                getListView().setSelection(p);
                getListView().clearFocus();

            }
        });
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String w = "";
        if (user.taskesList.size() == 0) {


            if (!user.data.get("logintype").equalsIgnoreCase("student")) {
                w = "supervisor_id='" + user.data.get("id") + "'";
            } else {
                w = "(users_group_id='" + user.data.get("group") + "') and (status=3 or status=5 or status=6)";
            }
            user.getData(me, w);
        }
        ArrayList<ListItem> listData = u.getListData(user.taskesList);
        setListAdapter(new CustomListAdapter(inflater.getContext(), listData));


        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
