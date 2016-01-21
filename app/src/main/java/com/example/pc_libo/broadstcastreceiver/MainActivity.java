package com.example.pc_libo.broadstcastreceiver;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private Button sendStaticBtn;
    private Button sendDynamicBtn;
    private Button sendSystemBtn;
    private Button sendNotification;
    private Button startService;

    private static final String STATICACTION = "com.byread.static";
    private static final String DYNAMICACTION = "com.byread.dynamic";
    private Context context;
    // USB设备连接
    private static final String SYSTEMACTION = Intent.ACTION_POWER_CONNECTED;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        //fanshe();

    }

    private void initview(){
        context=this;
        sendStaticBtn = (Button) findViewById(R.id.send_static);
        sendDynamicBtn = (Button) findViewById(R.id.send_dynamic);
        sendSystemBtn = (Button) findViewById(R.id.send_system);
        sendNotification = (Button) findViewById(R.id.btn_notification);
        startService = (Button) findViewById(R.id.btn_startservice);
        sendStaticBtn.setOnClickListener(new MyOnClickListener());
        sendDynamicBtn.setOnClickListener(new MyOnClickListener());
        sendSystemBtn.setOnClickListener(new MyOnClickListener());
        sendNotification.setOnClickListener(new MyOnClickListener());
        startService.setOnClickListener(new MyOnClickListener());

        EditText editText;
        editText = (EditText) findViewById(R.id.editText);
        showKeyboard(editText);

    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 发送自定义静态注册广播消息
            if(v.getId() == R.id.send_static){
                Log.e("MainActivity", "发送自定义静态注册广播消息");
                Intent intent = new Intent();
                intent.setAction(STATICACTION);
                intent.putExtra("msg", "接收静态注册广播成功！");
                sendBroadcast(intent);
            }
            // 发送自定义动态注册广播消息
            else if(v.getId() == R.id.send_dynamic){
                Log.e("MainActivity", "发送自定义动态注册广播消息");
                Intent intent = new Intent();
                intent.setAction(DYNAMICACTION);
                intent.putExtra("msg", "接收动态注册广播成功！");
                sendBroadcast(intent);
            }
            // 发送系统动态注册广播消息。当手机连接充电设备时会由系统自己发送广播消息。
            else if(v.getId() == R.id.send_system){
                Log.e("MainActivity", "发送系统动态注册广播消息");
                Intent intent = new Intent();
                intent.setAction(SYSTEMACTION);
                intent.putExtra("msg", "正在充电。。。。");
            }
            // 发送状态栏通知
            else if(v.getId() == R.id.btn_notification){
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
            //开启服务
            else if(v.getId() == R.id.btn_startservice){
                Intent startIntent = new Intent(context, MyService.class);
                context.startService(startIntent);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "注册广播事件");
        // 注册自定义动态广播消息
        IntentFilter filter_dynamic = new IntentFilter("dynamic");//Intent.ACTION_TIME_TICK,该Intent.ACTION_TIME_TICK会一分钟触发一次
        registerReceiver(dynamicReceiver, filter_dynamic);
        // 注册系统动态广播消息
        IntentFilter filter_system = new IntentFilter();
        filter_system.addAction(SYSTEMACTION);
        registerReceiver(systemReceiver, filter_system);
    }
    private BroadcastReceiver dynamicReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("MainActivity", "接收自定义动态注册广播消息");
            String msg = intent.getStringExtra("msg");
            Toast.makeText(context, "接收系统的TIME_TICK广播", Toast.LENGTH_SHORT).show();
        }
    };
    private BroadcastReceiver systemReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("MainActivity", "接收系统动态注册广播消息");
            if(intent.getAction().equals(SYSTEMACTION)){
                String msg = intent.getStringExtra("msg");
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(dynamicReceiver);
        unregisterReceiver(systemReceiver);
    }

    private void fanshe(){

        Class c= null;
        try {
            c = Class.forName("java.lang.Integer");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取所有的属性?
        Field[] fs = c.getDeclaredFields();

        //定义可变长的字符串，用来存储属性
        StringBuffer sb = new StringBuffer();
        //通过追加的方法，将每个属性拼接到此字符串中
        //最外边的public定义
        sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");
        //里边的每一个属性
        for(Field field:fs){
            sb.append("\t");//空格
            sb.append(Modifier.toString(field.getModifiers())+" ");//获得属性的修饰符，例如public，static等等
            sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字
            sb.append(field.getName()+";\n");//属性的名字+回车
        }

        sb.append("}");

        System.out.println("-----------------------------------------");
        System.out.println(sb);
    }

    private void showKeyboard(EditText editText){
        editText.setFocusable(true);

        editText.setFocusableInTouchMode(true);

        editText.requestFocus();

        Timer timer = new Timer();
        mTimerTask timerTask = new mTimerTask(editText);
        timer.schedule(timerTask,998);
    }

    class mTimerTask extends TimerTask{

        EditText editText;

        public mTimerTask(EditText editText) {
            this.editText = editText;
        }


        @Override
        public void run() {
            InputMethodManager inputManager =
                    (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }
}