package com.example.myapplication.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ResolutionAdapter;
import com.example.myapplication.helper_classes.TextFileHelper;
import com.example.myapplication.resolution.Resolution;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private RecyclerView rvResolutions;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<ResolutionAdapter.ResolutionViewHolder> adapter;
    private ProgressBar pbResolutions;
    private FloatingActionButton fab;
    private View view;
    private ArrayList<Resolution> resolutions;
    private EditText dialogEditText;
    private LayoutInflater inflater;
    private String title;
    private String description;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvResolutions = view.findViewById(R.id.rvResolutions);
        rvResolutions.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        rvResolutions.setLayoutManager(layoutManager);
        adapter = new ResolutionAdapter(this.getActivity(),resolutions);
        rvResolutions.setAdapter(adapter);
        inflater = getActivity().getLayoutInflater();

        fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(t->{
            //Toast.makeText(getActivity(), "Floating button", Toast.LENGTH_SHORT).show();
            View viewAlertDialog = inflater.inflate(R.layout.alertdialog_layout,null);
            EditText etTitle = viewAlertDialog.findViewById(R.id.etTitle);
            EditText etDescription = viewAlertDialog.findViewById(R.id.etDescription);
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Add Resolution")
                    .setMessage("Please insert your resolution")
                    .setView(viewAlertDialog)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            title = etTitle.getText().toString().trim();
                            description = etDescription.getText().toString().trim();
                            resolutions.add(new Resolution(title,description,"false"));
                            TextFileHelper.writeToFile(getActivity(),resolutions);
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Cancel",null)
                    .create();
            alertDialog.show();


        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setData(ArrayList<Resolution> resolutions){
        this.resolutions = resolutions;
    }

}
