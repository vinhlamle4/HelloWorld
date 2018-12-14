package com.example.lamlv.robolectrictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private TextView tv_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this, LoginActivity.class));
                tv_hello.setText("Hello Android");
            }
        });
    }

    public void initView() {
        btn_login = findViewById(R.id.btn_login);
        tv_hello = findViewById(R.id.tv_hello);
    }
}
