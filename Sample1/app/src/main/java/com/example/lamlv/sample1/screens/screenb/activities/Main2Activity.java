package com.example.lamlv.sample1.screens.screenb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lamlv.sample1.R;
import com.example.lamlv.sample1.Model.Message;

public class Main2Activity extends AppCompatActivity {

    private static ISendMessage iSendMessage;

    private TextView tvMessage;
    private Button btnSendMessage;
    private Message message;

    public static void newInstance(AppCompatActivity activity, ISendMessage iSendMessage) {
        Intent intent = new Intent(activity, Main2Activity.class);
        activity.startActivity(intent);
        Main2Activity.iSendMessage = iSendMessage;
    }

    @Override
    protected void onDestroy() {
        iSendMessage = null;
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Anh xa
        tvMessage = findViewById(R.id.tv_message);
        btnSendMessage = findViewById(R.id.btn_get_message);

        //khoi tao class MessageModel
        message = Message.getInstance();
        message.setStrtext("TEST");

        //button click
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getStrtext() != null) {
                    if(iSendMessage != null) {
                        iSendMessage.sendMessage("Hello ");
                    }
                }
            }
        });

    }

    //buton finish click
    public void setOnClickFinishBtn(View view) {
        iSendMessage = null;
        message = null;
        finish();
    }

    //region Interface
    public interface ISendMessage {
        void sendMessage(String message);
    }
    //endregion

}

