package com.example.pc_libo.broadstcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pc_libo on 15/11/20.
 */
public class StaticReceiver extends BroadcastReceiver {

    private static final String Tag="com.example.pc_libo.Myservice";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Log.i("onReceive", "StaticReceiver");
        Intent startIntent = new Intent(context, MyService.class);
        startIntent.setAction(Tag);
        if(intent.getAction().equals(intent.ACTION_TIME_TICK)){
            Log.i("time","timer");
        }
        context.startService(startIntent);

       Toast.makeText(context,"StaticReceiver",Toast.LENGTH_LONG).show();
    }
}
