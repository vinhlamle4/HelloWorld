package com.example.lamlv.robolectrictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_number_a, edt_number_b;
     TextView tv_result;
    private Button btn_sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_sum = findViewById(R.id.btn_sum);

        edt_number_a = findViewById(R.id.edt_number_a);
        edt_number_b = findViewById(R.id.edt_number_b);

        tv_result = findViewById(R.id.tv_result);

        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edt_number_a.getText().toString());
                int b = Integer.parseInt(edt_number_b.getText().toString());
                int sum = a + b;

                tv_result.setText(String.valueOf(sum));
            }
        });

    }
}
