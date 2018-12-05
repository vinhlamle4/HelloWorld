package com.example.lamlv.sample1.screens.screena.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lamlv.sample1.screens.screena.fragments.FragmentA;
import com.example.lamlv.sample1.screens.screena.fragments.FragmentB;
import com.example.lamlv.sample1.screens.screena.fragments.FragmentC;
import com.example.lamlv.sample1.screens.screenb.activities.Main2Activity;
import com.example.lamlv.sample1.R;
import com.example.lamlv.sample1.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();


    }

    // button click listener
    public void onClickBtnReplaceFragment(View view) {
        if(FragmentA.getInstance().isAdded() == false)
        {
            Utils.replaceFragment(R.id.layout_fragment,FragmentA.getInstance(),fragmentManager,true);
        }
        else
        {
            Utils.replaceFragment(R.id.layout_fragment,FragmentB.getInstance(),fragmentManager,true);
        }
    }

    public void onClickBtnAddFragment(View view) {


        if(FragmentA.getInstance().isAdded() == false)
        {
            Utils.addFragment(R.id.layout_fragment,FragmentA.getInstance(),"Fragment1",fragmentManager,true);
        }
        else
        {
            Utils.addFragment(R.id.layout_fragment,FragmentB.getInstance(),"Fragment1",fragmentManager,true);
        }
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        String str = "";
//        str = String.valueOf(fragmentList.get(1).getTag());
        String fragmentTag  = "";

        if(fragmentManager.getBackStackEntryCount() > 0) {
            fragmentTag = String.valueOf(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getId());
        }
        else
        {
            fragmentTag = fragmentManager.getBackStackEntryAt(0).getName();
        }
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);


        boolean handle= false;

        for(Fragment f : fragmentList)
        {
            if(f instanceof FragmentC)
            {
                handle = ((FragmentC)f).onBackPressed();
                if(handle)
                {
                    break;
                }
            }
        }

        if(!handle)
        {
//            fragmentManager.popBackStack();
//            fragmentManager.popBackStack();

            Toast.makeText(this,fragmentTag, Toast.LENGTH_LONG).show();
        }
    }
}

