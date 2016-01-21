package com.example.pc_libo.broadstcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pc_libo on 15/11/20.
 */
public class SystemReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Log.e("SystemReceiver", "电量低提示");
            Toast.makeText(context, "您的手机电量偏低，请及时充电", Toast.LENGTH_SHORT).show();
        }
    }
}



