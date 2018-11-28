package com.example.zz.dynamicfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

public class baseActivity extends AppCompatActivity {
    forceBroadcast  forceBroadcast;

    @Override
    protected void onResume() {
        super.onResume();
        ActivityCollect.add(this);
        IntentFilter    intentFilter    =   new IntentFilter();
        intentFilter.addAction("com.test");
        forceBroadcast  =   new forceBroadcast();
        registerReceiver(forceBroadcast, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityCollect.remove(this);
        unregisterReceiver(forceBroadcast);
    }

    class forceBroadcast    extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ActivityCollect.Finsh();
            Intent  intent1 =   new Intent(context, MainActivity.class);
            startActivity(intent);
        }
    }
}
