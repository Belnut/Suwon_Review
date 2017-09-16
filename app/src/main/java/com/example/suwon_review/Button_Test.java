package com.example.suwon_review;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by 연일 on 2017-05-15.
 */

public class Button_Test extends Activity {

    Button sms_btn, sug_btn, filter_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        sms_btn = (Button)findViewById(R.id.sms_btn);
        sug_btn = (Button)findViewById(R.id.suggestion_board_btn);
        filter_btn = (Button)findViewById(R.id.filter_test_btn);
    }

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.suggestion_board_btn: {
                Log.e("1111111111", "1111111111");
                Intent sug_board = new Intent(this, Suggestion_board.class);
                startActivity(sug_board);
                break;
            }
            case R.id.filter_test_btn:{
                Intent filter = new Intent(this, Filter_Main.class);
                Log.e("asdasdasd", "asdasdasdasd");
                startActivity(filter);
                Log.e("1111111111", "1111111111");
                break;
            }
        }
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager smsManager = android.telephony.SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
