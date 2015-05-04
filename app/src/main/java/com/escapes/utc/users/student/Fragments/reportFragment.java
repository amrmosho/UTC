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

/**
 * Created by empcl_000 on 04/05/2015.
 */



    public  class reportFragment extends Fragment implements DialogClickListener {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static reportFragment newInstance(int sectionNumber) {
            reportFragment fragment = new reportFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public reportFragment() {
        }











    String me = "task_requests";

    @Override
    public void onYesClick() {

    }

    @Override
    public void onNoClick() {

    }


    uitls u = new uitls();
    ListView lw;
    String tid = "";
    LayoutInflater inf;

    void updateList() {
        user.getData(me, "task_id='" + tid + "'");


        ArrayList<ListItem> listData = u.getListData(user.reportsList);
        lw.setAdapter(new CustomListAdapter(inf.getContext(), listData));

    }

//c








    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

        inf = inflater;
        View rootView = inf.inflate(R.layout.fragment_student_task_reports, container, false);
        tid = user.act_taske;


        //////Bar Actions


        ImageButton t_addbt = (ImageButton) rootView.findViewById(R.id.r_addbt);
        t_addbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        DialogFragment newFragment = addFragment.newInstance();

                        newFragment.setTargetFragment(reportFragment.this, 0);

                        newFragment.show(getFragmentManager(), "dialog");
                    }
                }
        );
        ImageButton t_editbt = (ImageButton) rootView.findViewById(R.id.r_editbt);


        t_editbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        DialogFragment eFragment = editFragment.newInstance();

                        eFragment.setTargetFragment(reportFragment.this, 0);

                        eFragment.show(getFragmentManager(), "dialog");

                    }
                }
        );


        ImageButton t_delbt = (ImageButton) rootView.findViewById(R.id.r_delbt);

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


                                        user.set_delete(me, "id='" + user.act_report + "'");
                                        updateList();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show();


                    }
                }
        );


        //////BarActions

        //////ListViwe


        lw = (ListView) rootView.findViewById(R.id.rlist);


        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                user.act_report = user.reportsList.get(position).get("id");

                view.setSelected(true);


            }
        });


        updateList();

        //////ListViwe


        TabHost th = (TabHost) rootView.findViewById(R.id.student_taske_r);
        th.setup();
        TabHost.TabSpec tc = th.newTabSpec("Taske");
        tc.setIndicator("Taske");
        tc.setContent(R.id.student_r_tab_task);
        th.addTab(tc);


        tc = th.newTabSpec("tab3");
        tc.setIndicator("Reports");
        tc.setContent(R.id.student_r_tab_data);
        th.addTab(tc);


        TextView todo_tTitle = (TextView) rootView.findViewById(R.id.r_tTitle);
        TextView todo_tStatus = (TextView) rootView.findViewById(R.id.r_tStatus);
        TextView todo_tStartData = (TextView) rootView.findViewById(R.id.r_tStartData);
        TextView todo_tEndData = (TextView) rootView.findViewById(R.id.r_tEndData);
        TextView todo_tDes = (TextView) rootView.findViewById(R.id.r_tDes);


        Map m = user.getTaskByid(tid);

        todo_tTitle.setText((String) m.get("title"));
        todo_tStatus.setText(user.getStatus((String) m.get("status")));
        todo_tStartData.setText((String) m.get("created"));
        todo_tEndData.setText((String) m.get("ended"));
        todo_tDes.setText((String) m.get("dec") + "\n" + (String) m.get("requests"));


        return rootView;
    }


    /**
     * TabListener for messages
     */
    public static class addFragment extends DialogFragment {


        String me = "task_requests";


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


            getDialog().setTitle("Add New [Report]");


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

                    user.set_insert(me, m);
                    getDialog().dismiss();
                    callback.onYesClick();


                }
            });

            return v;
        }
    }


    public static class editFragment extends DialogFragment {


        String me = "task_requests";

        static editFragment newInstance() {
            return new editFragment();
        }

        private DialogClickListener callback;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            getDialog().setTitle("Edit  [Report]");


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


            Map<String, String> edata = user.getDataByID(user.act_report, me);


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
                    user.set_update(me, m, "id='" + user.act_report + "'");
                    getDialog().dismiss();
                    callback.onYesClick();


                }
            });

            return v;


        }
    }


}
