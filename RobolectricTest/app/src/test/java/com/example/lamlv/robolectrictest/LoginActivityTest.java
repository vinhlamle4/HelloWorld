package com.example.lamlv.robolectrictest;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    EditText edt_number_a, edt_number_b;
    TextView tv_result;
    Button btn_sum;
    LoginActivity mLoginActivity;

    @Before
    public void setUp()
    {
        mLoginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
        btn_sum = mLoginActivity.findViewById(R.id.btn_sum);
        tv_result = mLoginActivity.findViewById(R.id.tv_result);
        edt_number_a = mLoginActivity.findViewById(R.id.edt_number_a);
        edt_number_b = mLoginActivity.findViewById(R.id.edt_number_b);
    }

    @Test
    public void testViewStart() {
        assertThat(btn_sum.getText().toString(), equalTo("sum") );
        assertThat(tv_result.getText().toString(), equalTo("0"));
    }

    @Test
    public void testSumClicking()
    {
        edt_number_a.setText("3");
        edt_number_b.setText("5");
        btn_sum.performClick();

        assertNotNull(edt_number_a);
        assertNotNull(edt_number_b);
    }

}
