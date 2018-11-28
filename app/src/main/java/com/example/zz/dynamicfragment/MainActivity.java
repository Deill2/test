package com.example.zz.dynamicfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends baseActivity {
    private IntentFilter    intentFilter;
    private LocalReceiver   receiver;
    private LocalBroadcastManager   localBroadcastManager;


    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "this is ", Toast.LENGTH_SHORT).show();
            abortBroadcast();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver    =   new    LocalReceiver();
        intentFilter    =   new IntentFilter();
        intentFilter.addAction("com.example.test");
        localBroadcastManager   =   LocalBroadcastManager.getInstance(this);
        registerReceiver(receiver, intentFilter);
        Button  btn =   findViewById(R.id.test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent  =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(receiver);
    }
}
