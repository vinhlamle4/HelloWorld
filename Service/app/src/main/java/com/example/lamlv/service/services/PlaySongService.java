package com.example.lamlv.service.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamlv.service.R;
import com.example.lamlv.service.Utils.Utils;
import com.example.lamlv.service.common.Constants;
import com.example.lamlv.service.screens.main.activities.MainActivity;

import java.lang.ref.WeakReference;


public class PlaySongService extends Service implements SeekBar.OnSeekBarChangeListener {

    private static String TAG = "PlaySongService";

    private WeakReference<SeekBar> seekbar_music;
    private WeakReference<TextView> tv_duration, tv_current_position;
    private WeakReference<ImageButton> img_btn_music;

    private Handler mHandler;

    private MediaPlayer mediaPlayer;
    private final Messenger mMessenger = new Messenger(new IncomingHandler());

    private boolean finishMusic = false;

    public PlaySongService() {
    }

    //region Override Method (Lifecycle Service)
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tule_fearless);
        Log.d(TAG, "On Create");
        // iBinder = new MyBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "On Bind");

        tv_current_position = new WeakReference<>(MainActivity.tv_current_position);
        tv_duration = new WeakReference<>(MainActivity.tv_duration);

        mHandler = new Handler();

        seekbar_music = new WeakReference<>(MainActivity.seekbar_music);
        seekbar_music.get().setOnSeekBarChangeListener(this);

        img_btn_music = new WeakReference<>(MainActivity.img_btn_music);

        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "On Unbind");
        stopMusic();
        mediaPlayer.release();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(musicBroadcastReceiver);
        Log.d(TAG, "On Destroy");
    }
    //endregion


    //region Runnable Thread
    private Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {

            int totalPosition = getDuration();
            int currentPosition = getCurrentPosition();

            int delta = totalPosition - currentPosition;
            Log.d(TAG, "run: " + delta);

            tv_duration.get().setText(Utils.milliSecondsToTimer(totalPosition));
            tv_current_position.get().setText(Utils.milliSecondsToTimer(currentPosition));

            int progress = Utils.getProgressPercentage(currentPosition, totalPosition);
            seekbar_music.get().setProgress(progress);
            mHandler.postDelayed(mUpdateTimeTask, 100);

            if (delta < 500)
            {
                seekbar_music.get().setProgress(0);
                tv_current_position.get().setText("0:00");
                img_btn_music.get().setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp);
                mHandler.removeCallbacks(this);
                finishMusic = true;

            }
        }
    };
    //endregion

    //region Seek bar override method
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalPosition = mediaPlayer.getDuration();
        int currentPositionAfterSeek = Utils.progressToTimer(seekBar.getProgress(), totalPosition);

        seekTo(currentPositionAfterSeek);

        mHandler.postDelayed(mUpdateTimeTask, 100);
    }
    //endregion

    //region method
    public void playMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();

            mHandler.postDelayed(mUpdateTimeTask, 100);
            Log.d(TAG, "play: ");

        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mHandler.removeCallbacks(mUpdateTimeTask);
            Log.d(TAG, "pause ");
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mHandler.removeCallbacks(mUpdateTimeTask);
            Log.d(TAG, "stop ");
        }
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public void forward() {
        int forward = getCurrentPosition() + 5000;
        seekTo(forward);
    }

    public void rewind() {
        int rewind = getCurrentPosition() - 5000;
        seekTo(rewind);
    }

    public void seekTo(int position) {
        mediaPlayer.seekTo(position);
    }
    //endregion

    //region Handler message class
    @SuppressLint("HandlerLeak")
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (finishMusic) {
                Messenger msgerReply = msg.replyTo;
                Message msgReply = Message.obtain(null, Constants.FINISH, 0, 0);
                try {
                    msgerReply.send(msgReply);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            switch (msg.what) {

                case Constants.PLAY:
                    playMusic();
                    break;

                case Constants.PAUSE:
                    pauseMusic();
                    break;

                case Constants.FORWARD:
                    forward();
                    break;

                case Constants.REWIND:
                    rewind();
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }
    //endregion
}
