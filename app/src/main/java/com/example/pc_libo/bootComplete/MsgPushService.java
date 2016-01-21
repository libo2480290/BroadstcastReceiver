package com.example.pc_libo.bootComplete;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by pc_libo on 15/11/23.
 */
public class MsgPushService extends Service {
    private static final String TAG = "MsgPushService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate called.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand called.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
