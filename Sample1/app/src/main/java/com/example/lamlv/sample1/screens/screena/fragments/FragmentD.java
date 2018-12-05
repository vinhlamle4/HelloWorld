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
import android.widget.Toast;

import com.example.lamlv.sample1.R;
import com.example.lamlv.sample1.screens.screena.activities.MainActivity;
import com.example.lamlv.sample1.utils.Utils;

public class FragmentD extends Fragment {
    private static FragmentD fragmentD;

    public FragmentD() {
    }

    public static FragmentD getInstance() {
        if (fragmentD == null) {
            fragmentD = new FragmentD();
        }
        return fragmentD;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Fragment DDDD  ", "On Attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment DDDD  ", "On Create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("Fragment DDDD", "On Create View");
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        final Button btnDetachFrag4 = view.findViewById(R.id.btn_detach_frag_4);

        final FragmentManager fragmentManager = getFragmentManager();



        btnDetachFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.removeFragment(FragmentD.getInstance(), fragmentManager);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment DDDDD", "On Activity Create");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment DDDDD", "On Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment DDDDD", "On Resume");
        super.onPause();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment DDDDD", "On Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment DDDDD", "On Stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment DDDDD", "On Destroy View");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment DDDDD", "On Destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment DDDDD", "On Detach");
    }
}
