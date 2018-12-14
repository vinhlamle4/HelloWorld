package com.example.lamlv.robolectrictest;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {

    MainActivity mActivity;
    Button btn_login;
    TextView tv_hello;

    @Before
    public void setUp() {

        mActivity = Robolectric.buildActivity(MainActivity.class).create().get();

        btn_login = mActivity.findViewById(R.id.btn_login);
        tv_hello = mActivity.findViewById(R.id.tv_hello);

    }

    @Test
    public void clickingLogin_shouldStartLoginActivity() {
        btn_login.performClick();
        assertThat(tv_hello.getText().toString(), equalTo("Hello Android"));
//        Intent expectedIntent = new Intent(mActivity, LoginActivity.class);
        Intent actual = Shadows.shadowOf(mActivity).getNextStartedActivity();
//        assertEquals(expectedIntent.getComponent(), actual.getComponent());

       // assertEquals("Equal", 12, 14);
    }
}
