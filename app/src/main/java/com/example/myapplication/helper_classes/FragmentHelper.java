package com.example.myapplication.helper_classes;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public  class FragmentHelper {

    /*
    * add a new fragment to the fragment manager
    * */
    public static void addFragment(FragmentManager manager, Fragment fragment, int fragmentContainer, String tag){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragmentContainer,fragment,tag);
        transaction.commit();
    }

    /*
    * replace the existing content of the fragment container with a new fragment
    * */
    public static void replaceFragment(FragmentManager manager, Fragment fragment, int fragmentContainer){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(fragmentContainer,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
