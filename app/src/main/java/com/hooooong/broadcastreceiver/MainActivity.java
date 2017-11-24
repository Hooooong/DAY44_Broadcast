package com.hooooong.broadcastreceiver;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends BaseActivity {

    public MainActivity() {
        super(new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS});
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    // App 단위로 Receiver 를 관리하려면
    // Manifest.xml 에 작성한다.

    // App 단위가 아닌 Activity 단위로 Receiver 를 관리하려면
    // registerReceiver 로 Receiver 를 등록해주고,
    // unregisterReceiver 로 Receiver 를 해제한다.
    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(new MyReceiver(), new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));


    }
}
