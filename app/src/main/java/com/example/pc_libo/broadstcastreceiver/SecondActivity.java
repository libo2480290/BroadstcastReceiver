package com.example.pc_libo.broadstcastreceiver;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by pc_libo on 15/11/20.
 */
public class SecondActivity  extends Activity{

    private Context mcontext;
    private Button button1;
    private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_view);
        Log.i("life","onCreate");
        initview();
        addevent();
    }

    private void initview(){
        mcontext=this;
        button1=(Button)findViewById(R.id.btn_1);
        button2=(Button) findViewById(R.id.btn_2);
        button3=(Button) findViewById(R.id.btn_3);

    }

    private void addevent(){
        button1.setOnClickListener(myOnclikListener);
        button2.setOnClickListener(myOnclikListener);
        button3.setOnClickListener(myOnclikListener);
    }

    View.OnClickListener myOnclikListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_1:
                    Dialog dialog=new Dialog(mcontext);
                    dialog.setTitle("nihao");
                    dialog.show();
                    break;
                case R.id.btn_2:
                    Intent intent=new Intent(mcontext,MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_3:
                    Intent intent2=new Intent(mcontext,MainActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life", "onDestroy");
    }
}
