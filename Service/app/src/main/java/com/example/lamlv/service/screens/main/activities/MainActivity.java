package com.example.lamlv.service.screens.main.activities;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamlv.service.R;
import com.example.lamlv.service.Utils.Utils;
import com.example.lamlv.service.common.Constants;
import com.example.lamlv.service.services.PlaySongService;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";


    public boolean isBound = false;

    public static SeekBar seekbar_music;
    public static TextView tv_duration, tv_current_position;
    public static ImageButton img_btn_music;

    private ImageButton img_btn_fast_rewind, img_btn_fast_forward;

    private ServiceConnection connection;

    private final Messenger mActivityMessenger = new Messenger(new ActivityHandler());
    private Messenger mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        btnSetOnClick();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);

    }

    //region init View
    private void initView() {

        img_btn_music = findViewById(R.id.img_btn_music);
        this.img_btn_fast_forward = findViewById(R.id.img_btn_fast_forward);
        this.img_btn_fast_rewind = findViewById(R.id.img_btn_fast_rewind);

        seekbar_music = findViewById(R.id.seekbar_music);

        tv_duration = findViewById(R.id.tv_duration);
        tv_current_position = findViewById(R.id.tv_current_position);
    }
    //endregion

    //region initData
    public void initData() {

        Intent intent = new Intent(MainActivity.this, PlaySongService.class);

        this.connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = new Messenger(service);
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
                isBound = false;
            }
        };
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    //endregion

    public void sendMessageToService(int what) {
        if (!isBound) {
            return;
        }

        try {
            Message msg = Message.obtain(null, what, 0, 0);
            msg.replyTo = mActivityMessenger;
            mService.send(msg);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //region Button Click Event
    public void btnSetOnClick() {

        //Play and Pause Music
        img_btn_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable.ConstantState currentBackground = img_btn_music.getBackground().getConstantState();
                final Drawable.ConstantState playDrawable = getResources().getDrawable(R.drawable.ic_play_circle_outline_black_24dp).getConstantState();

                assert currentBackground != null;
                if (currentBackground.equals(playDrawable)) {

                    img_btn_music.setBackgroundResource(R.drawable.ic_pause_circle_outline_black_24dp);

                    sendMessageToService(Constants.PLAY);

                } else {
                    sendMessageToService(Constants.PAUSE);
                    //mHandler.removeCallbacks(mUpdateTimeTask);
                    img_btn_music.setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp);
                    Log.d(TAG, "pause action");
                }
            }
        });

        //fast forward button
        this.img_btn_fast_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToService(Constants.FORWARD);
            }
        });

        //fast rewind button
        this.img_btn_fast_rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToService(Constants.REWIND);
            }
        });

    }

    //endregion

    @SuppressLint("HandlerLeak")
    public class ActivityHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.FINISH:
                    img_btn_music.setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp);
                    Toast.makeText(MainActivity.this, "Music is finish", Toast.LENGTH_SHORT).show();
                default:
                    super.handleMessage(msg);
            }

        }
    }

}
