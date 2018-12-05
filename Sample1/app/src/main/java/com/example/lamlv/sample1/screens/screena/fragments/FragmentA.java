package com.example.lamlv.sample1.screens.screena.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lamlv.sample1.R;
import com.example.lamlv.sample1.utils.Utils;

public class FragmentA extends Fragment {

    private static FragmentA fragmentA;
    private Button btnAddFrag2, btnReplaceFrag2;

    @SuppressLint("ValidFragment")
    private FragmentA() {
    }

    public static FragmentA getInstance() {
        if (fragmentA == null) {
            fragmentA = new FragmentA();
        }
        return fragmentA;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Fragment A","On Attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment A","On Create");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("Fragment A", "On Create View");
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        btnAddFrag2 = view.findViewById(R.id.btn_add_frag_2);
        btnReplaceFrag2 = view.findViewById(R.id.btn_replace_frag_2);

        final FragmentManager fragmentManager = getFragmentManager();

        btnAddFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (FragmentB.getInstance().isAdded() == false) {
                    Utils.addFragment(R.id.layout_fragment, FragmentB.getInstance(),"Fragment1", fragmentManager,true);
                    //Utils.addToBackStack(FragmentB.getInstance().getClass().getSimpleName(), fragmentManager);
                } else {
                    Utils.removeFragment(FragmentB.getInstance(), fragmentManager);
                    //Utils.addFragment(R.id.layout_fragment2,FragmentB.getInstance(),fragmentManager);
                    Log.d("Fragment A add status", "Fragment B is exist");
                }
            }
        });

        btnReplaceFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.replaceFragment(R.id.layout_fragment, FragmentB.getInstance(), fragmentManager,true);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment A","On Activity Create");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment A","On Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment A","On Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment A","On Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment A","On Stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment A","On Destroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment A","On Destroy View");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment A","On Detach");
    }
}
