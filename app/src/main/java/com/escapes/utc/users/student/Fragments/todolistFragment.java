package com.escapes.utc.users.student.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.escapes.utc.R;
import com.escapes.utc.libs.uitls;
import com.escapes.utc.options.CustomListAdapter;
import com.escapes.utc.options.ListItem;
import com.escapes.utc.options.user;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by empcl_000 on 03/05/2015.
 */


interface DialogClickListener {
    public void onYesClick();

    public void onNoClick();
}

public class todolistFragment extends Fragment implements DialogClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static todolistFragment newInstance(int sectionNumber) {
        todolistFragment fragment = new todolistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public todolistFragment() {
    }


    uitls u = new uitls();
    ListView lw;
    String tid = "";
    LayoutInflater inf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inf = inflater;


        View rootView = inf.inflate(R.layout.fragment_student_task_todolist, container, false);
        tid = "9";


        Button t_addbt = (Button) rootView.findViewById(R.id.t_addbt);
        t_addbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {





                        DialogFragment newFragment = addFragment.newInstance();

                        newFragment.setTargetFragment(todolistFragment.this, 0);

                        newFragment.show(getFragmentManager(), "dialog");
                    }
                }
        );
        Button t_editbt = (Button) rootView.findViewById(R.id.t_editbt);


        t_editbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        DialogFragment eFragment = editFragment.newInstance();

                        eFragment.setTargetFragment(todolistFragment.this, 0);

                        eFragment.show(getFragmentManager(), "dialog");

                    }
                }
        );


        Button t_delbt = (Button) rootView.findViewById(R.id.t_delbt);

        t_delbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        new AlertDialog.Builder(inf.getContext())
                                .setTitle("Delete Item")
                                .setMessage("Do you really Delete Item?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                      //  Toast.makeText(inf.getContext(), "Yaay", Toast.LENGTH_SHORT).show();


                                        user.set_delete("task_todolist", "id='" + user.act_todo + "'");
                                        updateList();
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();


                    }
                }
        );




        lw = (ListView) rootView.findViewById(R.id.todolist);


        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                user.act_todo = user.todoList.get(position).get("id");

                view.setSelected(true);


            }
        });


        updateList();


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


        TextView todo_tTitle = (TextView) rootView.findViewById(R.id.todo_tTitle);
        TextView todo_tStatus = (TextView) rootView.findViewById(R.id.todo_tStatus);
        TextView todo_tStartData = (TextView) rootView.findViewById(R.id.todo_tStartData);
        TextView todo_tEndData = (TextView) rootView.findViewById(R.id.todo_tEndData);
        TextView todo_tDes = (TextView) rootView.findViewById(R.id.todo_tDes);


        Map m = user.getTaskByid(tid);

        todo_tTitle.setText((String) m.get("title"));
        todo_tStatus.setText(user.getStatus((String) m.get("status")));
        todo_tStartData.setText((String) m.get("created"));
        todo_tEndData.setText((String) m.get("ended"));
        todo_tDes.setText((String) m.get("dec") + "\n" + (String) m.get("requests"));


        return rootView;
    }


    void updateList() {


        user.getData("task_todolist", "task_id='" + tid + "' and users_id='" + user.data.get("id") + "'");

        ArrayList<ListItem> listData = u.getListData(user.todoList);
        lw.setAdapter(new CustomListAdapter(inf.getContext(), listData));

    }

    @Override
    public void onYesClick() {
        Log.d("mytest", "update");
        updateList();

    }

    @Override
    public void onNoClick() {

    }


    /**
     * TabListener for messages
     */
    public static class addFragment extends DialogFragment {

        @Override
        public void onDismiss(DialogInterface dialog) {
        }


        static addFragment newInstance() {
            return new addFragment();
        }

        private DialogClickListener callback;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,

                                 Bundle savedInstanceState) {


            getDialog().setTitle("Add New [ToDo]");


            try {
                callback = (DialogClickListener) getTargetFragment();
            } catch (ClassCastException e) {
                throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
            }


            final View v = inflater.inflate(R.layout.activity_todolist_ope, container, false);

            final EditText t_config_title = (EditText) v.findViewById(R.id.t_config_title);
            final EditText t_config_date = (EditText) v.findViewById(R.id.t_config_date);
            final EditText t_config_data = (EditText) v.findViewById(R.id.t_config_data);
            final Button t_config_send = (Button) v.findViewById(R.id.t_config_send);
            t_config_send.setText("Save");


            t_config_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, String> m = new HashMap<String, String>();

                    m.put("title", t_config_title.getText().toString());
                    m.put("date", t_config_date.getText().toString());
                    m.put("dec", t_config_data.getText().toString());

                    user.set_insert("task_todolist", m);
                    getDialog().dismiss();
                    callback.onYesClick();


                }
            });

            return v;
        }
    }


    public static class editFragment extends DialogFragment {
        static editFragment newInstance() {
            return new editFragment();
        }

        private DialogClickListener callback;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            getDialog().setTitle("Edit  [ToDo]");


            try {
                callback = (DialogClickListener) getTargetFragment();
            } catch (ClassCastException e) {
                throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
            }


            final View v = inflater.inflate(R.layout.activity_todolist_ope, container, false);

            final EditText t_config_title = (EditText) v.findViewById(R.id.t_config_title);
            final EditText t_config_date = (EditText) v.findViewById(R.id.t_config_date);
            final EditText t_config_data = (EditText) v.findViewById(R.id.t_config_data);
            final Button t_config_send = (Button) v.findViewById(R.id.t_config_send);


            Map<String, String> edata = user.getDataByID(user.act_todo, "task_todolist");


            t_config_title.setText(edata.get("title"));
            t_config_date.setText(edata.get("date"));
            t_config_data.setText(edata.get("dec"));


            t_config_send.setText("Update");


            t_config_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, String> m = new HashMap<String, String>();

                    m.put("title", t_config_title.getText().toString());
                    m.put("date", t_config_date.getText().toString());
                    m.put("dec", t_config_data.getText().toString());
                    user.set_update("task_todolist", m, "id='" + user.act_todo + "'");
                    getDialog().dismiss();
                    callback.onYesClick();


                }
            });

            return v;


        }
    }


}