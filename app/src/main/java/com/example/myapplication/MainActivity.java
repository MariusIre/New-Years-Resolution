package com.example.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.fragments.ListFragment;
import com.example.myapplication.fragments.WelcomeFragment;
import com.example.myapplication.helper_classes.FragmentHelper;
import com.example.myapplication.resolution.Resolution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private Button startApp;
    private ArrayList<Resolution> resolutions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolutions = new ArrayList<>();
        loadData();

        manager = getSupportFragmentManager();
        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setData(resolutions);
        FragmentHelper.addFragment(manager,fragment,R.id.fragment_container,"startFragment");


    }

    public  void loadData(){
        resolutions.clear();
        File file = getApplicationContext().getFileStreamPath("res.txt");
        String line;
        if(file.exists()){
            //Toast.makeText(this, "File exists", Toast.LENGTH_SHORT).show();
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("res.txt")));
                while((line = reader.readLine()) != null){
                    String[] arr = line.split(",");
                    //StringTokenizer tokenizer = new StringTokenizer(line,",");
                    resolutions.add(new Resolution(arr[0],arr[1],arr[2]));
                }
                reader.close();
                //Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else{
            //Toast.makeText(this, "File doesn't exist", Toast.LENGTH_SHORT).show();
        }
    }
}
