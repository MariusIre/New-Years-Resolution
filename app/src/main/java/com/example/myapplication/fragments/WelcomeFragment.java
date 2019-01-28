package com.example.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.helper_classes.FragmentHelper;
import com.example.myapplication.models.Resolution;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {
    private Button btnApp;
    private FragmentManager manager;
    private ArrayList<Resolution> resolutions;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.welcome_fragment,container,false);
        manager = getFragmentManager();

        /*
        * connecting my button to the view button from the fragment lyaout
        * */
        btnApp = view.findViewById(R.id.btnStart);

        /*
        * action to move from one fragment to another by replacing them in the fragment container
        * */
        btnApp.setOnClickListener(t->{
            ListFragment fragment = new ListFragment();
            fragment.setData(resolutions);
            FragmentHelper.replaceFragment(manager,fragment,R.id.fragment_container);
        });

        return view;
    }

    public void setData(ArrayList<Resolution> resolutions){
        this.resolutions = resolutions;
    }
}
