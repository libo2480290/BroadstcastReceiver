package com.example.pc_libo.bootComplete;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pc_libo on 15/11/23.
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MsgPushService.class);
        context.startService(service);
        Log.i(TAG, "Boot Complete. Starting MsgPushService...");
    }
}
