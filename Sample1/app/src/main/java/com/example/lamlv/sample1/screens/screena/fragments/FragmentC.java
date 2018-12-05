package com.example.lamlv.sample1.screens.screena.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lamlv.sample1.R;
import com.example.lamlv.sample1.utils.Utils;

import java.security.KeyStore;

public class FragmentC extends Fragment {
    private static FragmentC fragmentC;
    private Button btnAddFrag4, btnDetachFrag3;

    public FragmentC() {
    }

    public static FragmentC getInstance() {
        if (fragmentC == null) {
            fragmentC = new FragmentC();
        }
        return fragmentC;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Fragment CCC", "On Attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment CCC", "On Create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("Fragment CCC", "On Create View");
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        btnAddFrag4 = view.findViewById(R.id.btn_add_frag_4);

        final FragmentManager fragmentManager = getFragmentManager();

//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    Log.d("ON BACK PRESSED", "CLICKED");
//                }
//
//                return false;
//            }
//        });
//        for(int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++){
//            Log.i("Found fragment: " , String.valueOf(fragmentManager.getBackStackEntryAt(entry).getName()));
//        }


        btnDetachFrag3 = view.findViewById(R.id.btn_detach_frag_3);

        btnAddFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (FragmentD.getInstance().isAdded() == false) {
                    Utils.addFragment(R.id.layout_fragment, FragmentD.getInstance(),"Fragment1", fragmentManager, true);
                   // Utils.addToBackStack(FragmentD.getInstance().getClass().getSimpleName(), fragmentManager);
                } else {
                    Utils.removeFragment(FragmentD.getInstance(), fragmentManager);
                }

            }
        });

        btnDetachFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManagerremove = getFragmentManager();
                Fragment fragment = fragmentManagerremove.findFragmentById(R.id.layout_fragment3);
                FragmentTransaction fragmentTransactionremove = fragmentManagerremove.beginTransaction();
                fragmentTransactionremove.remove(fragment);
                fragmentTransactionremove.commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment CCC", "On Activity Create");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment CCC", "On Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment CCC", "On Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment CCC", "On Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment CCC", "On Stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment CCC", "On Destroy View");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment CCC", "On Destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment CCC", "On Detach");
    }

    public boolean onBackPressed()
    {
        Log.d("back pressed", "Clicked");
        return false;
    }
}
