package com.escapes.utc.users.student.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.escapes.utc.R;

/**
 * Created by empcl_000 on 03/05/2015.
 */

public  class meetingsFragment extends Fragment {

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
