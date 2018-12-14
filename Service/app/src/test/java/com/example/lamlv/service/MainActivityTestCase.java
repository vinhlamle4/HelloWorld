package com.example.lamlv.service;

import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lamlv.service.common.Constants;
import com.example.lamlv.service.screens.main.activities.MainActivity;

import org.apache.tools.ant.taskdefs.Length;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)


public class MainActivityTestCase {

    private MainActivity mActivity;
    private ImageButton img_btn_music, img_btn_fast_rewind, img_btn_fast_forward;
    private SeekBar seekbar_music;
    private TextView tv_duration, tv_current_position;

    @Before
    public void setUp() throws Exception {
        // khơi tạo activity
        mActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
        img_btn_music = mActivity.findViewById(R.id.img_btn_music);
        img_btn_fast_forward = mActivity.findViewById(R.id.img_btn_fast_forward);
        img_btn_fast_rewind = mActivity.findViewById(R.id.img_btn_fast_rewind);
        seekbar_music = mActivity.findViewById(R.id.seekbar_music);
        tv_duration = mActivity.findViewById(R.id.tv_duration);
        tv_current_position = mActivity.findViewById(R.id.tv_current_position);
    }

    @Test
    public void checkView() throws Exception {
        assertNotNull(img_btn_music);
        assertNotNull(img_btn_fast_forward);
        assertNotNull(img_btn_fast_rewind);
        assertNotNull(seekbar_music);
        assertNotNull(tv_duration);
        assertNotNull(tv_current_position);
    }
}
