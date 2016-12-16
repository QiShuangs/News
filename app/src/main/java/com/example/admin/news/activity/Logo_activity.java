package com.example.admin.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.news.R;
import com.example.admin.news.Utils.CacheUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Logo_activity extends AppCompatActivity {

    @BindView(R.id.iv_logo_activity)
    ImageView ivLogoActivity;
    @BindView(R.id.tv_left_time_activity_logo)
    TextView tvLeftTimeActivityLogo;
    @BindView(R.id.logo_activity)
    RelativeLayout logoActivity;
    private int lefttime = 3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0 :
                    if (lefttime>0){
                        String newText = "广告倒计时:"+lefttime--+"秒";
                        tvLeftTimeActivityLogo.setText(newText);
                        handler.sendEmptyMessageDelayed(0,1000);
                    }else{
                        boolean booleanFromSp = CacheUtil.getBooleanFromSp(Logo_activity.this, CacheUtil.IS_FIRST, true);
                        if(booleanFromSp){
                        Intent intent = new Intent(Logo_activity.this, Lead_activity.class);
                        startActivity(intent);
                        finish();
                        }else {
                            Intent intent = new Intent(Logo_activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    break;

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        initAnimation();
    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        ivLogoActivity.startAnimation(alphaAnimation);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
