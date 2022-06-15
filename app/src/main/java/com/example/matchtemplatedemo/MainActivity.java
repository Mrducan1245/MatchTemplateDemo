package com.example.matchtemplatedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //各种控件
    private TextView tvShowPointINfo;
    private Button btnConfirm;
    private ImageView showIvRestul;
    //handler
    private MyHandler mHandler;

    private static class MyHandler extends Handler {

        private MainActivity mainActivity;

        public MyHandler(MainActivity mainActivity){
            this.mainActivity = mainActivity;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    //在界面显示出来
                    mainActivity.tvShowPointINfo.setText("["+msg.arg1+","+msg.arg2+"]");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new MyHandler(this);
        bindView();
    }

    private void bindView() {
        tvShowPointINfo = findViewById(R.id.tv_showPoint);
        btnConfirm = findViewById(R.id.btn_confirm);
        showIvRestul = findViewById(R.id.iv_showImageResutl);

        //设置各种监听器
        btnConfirm.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                //点击完成这个按钮后就开启新的线程去执行模板匹配
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //通过handler将信息发送到主线程来更新UI
                        Message message = Message.obtain();
                        message.what = 1;
                        message.arg1 = (int) pointX;
                        message.arg2 = (int) pointY;
                        mHandler.sendMessage(message);

                    }
                }).start();
                break;
        }
    }
}