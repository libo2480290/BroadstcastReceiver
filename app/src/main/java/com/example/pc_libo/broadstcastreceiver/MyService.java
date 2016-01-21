package com.example.pc_libo.broadstcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by pc_libo on 15/11/20.
 */
public class MyService extends Service {

    int num=0;
    private Context context;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("broadcast","onBind");
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("broadcast", "onCreate");
        context = this;


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Log.i("broadcast", "onStartCommand");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                num++;
                Log.i("broadcast", "num:" + num);
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 2 * 1000;
        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);

        mystartforeground();
        return START_STICKY;//当内存充足时将会重建service
    }

    @Override
    public void onDestroy() {
        Log.i("broadcast", "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.i("broadcast", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    private void startVibrate(){
        Vibrator vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        //根据指定的模式进行震动
        //第一个参数：该数组中第一个元素是等待多长的时间才启动震动，
        //之后将会是开启和关闭震动的持续时间，单位为毫秒
        //第二个参数：重复震动时在pattern中的索引，如果设置为-1则表示不重复震动
        vibrator.vibrate(new long[]{1000,200,1000,200,1000}, -1);
    }

    private void startNotification(){

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,SecondActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.filter_ico_search)
                .setContentTitle("标题")
                .setContentText("文本内容");
        mBuilder.setTicker("第一次提示消息的时候显示在通知栏上");//第一次提示消息的时候显示在通知栏上
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());

    }

    private void mystartforeground(){
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,SecondActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.filter_ico_search)
                .setContentTitle("标题")
                .setContentText("文本内容");
        mBuilder.setTicker("第一次提示消息的时候显示在通知栏上");//第一次提示消息的时候显示在通知栏上
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
        Notification notification=mBuilder.build();

        startForeground(0x111, notification);

    }
}
