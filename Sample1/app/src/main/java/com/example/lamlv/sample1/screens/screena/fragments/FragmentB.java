package com.example.lamlv.sample1.screens.screena.fragments;


import android.content.Context;
import android.os.Bundle;
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

public class FragmentB extends Fragment {

    private static FragmentB fragmentB;
    private Button btnAddFrag3, btnReplaceFrag3;

    public FragmentB() {
    }

    public static FragmentB getInstance() {
        if (fragmentB == null) {
            fragmentB = new FragmentB();
        }
        return fragmentB;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Fragment BB", "On Attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment BB", "On Create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("Fragment B", "On Create View");
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        btnAddFrag3 = view.findViewById(R.id.btn_add_frag_3);
        btnReplaceFrag3 = view.findViewById(R.id.btn_replace_frag_3);
        final FragmentManager fragmentManager = getFragmentManager();

        btnAddFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (FragmentC.getInstance().isAdded() == false) {
                    Utils.addFragment(R.id.layout_fragment, FragmentC.getInstance(),"Fragment1", fragmentManager,true);

                } else {
                    Utils.removeFragment(FragmentC.getInstance(), fragmentManager);
                }
            }
        });

        btnReplaceFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentC.getInstance().isAdded() == false) {
                    Utils.replaceFragment(R.id.layout_fragment, FragmentC.getInstance(), fragmentManager,true);

                } else {
                    Utils.removeFragment(FragmentC.getInstance(), fragmentManager);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment BB", "On Activity Create");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment BB", "On Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment BB", "On Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment BB", "On Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment BB", "On Stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment BB", "On Destroy View");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment BB", "On Destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment BB", "On Detach");
    }
}
