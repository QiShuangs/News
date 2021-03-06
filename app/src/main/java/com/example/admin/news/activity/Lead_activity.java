package com.example.admin.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.news.R;
import com.example.admin.news.Utils.CacheUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Lead_activity extends AppCompatActivity {

    @BindView(R.id.pv_lead)
    ViewPager pvLead;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    @BindView(R.id.ll_indicator_container)
    LinearLayout llIndicatorContainer;

    private int[] imgs = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        ButterKnife.bind(this);
        //初始化视图数据
        initData();
        //initIndicator
        initIndicator();
        MyPagerAdapter adapter = new MyPagerAdapter();
        pvLead.setAdapter(adapter);
        pvLead.setOnPageChangeListener(new MyPagerListener());

    }

    private void initIndicator() {

        for (int i = 0; i < imgs.length; i++) {

            View v = new View(this);
            v.setBackgroundResource(R.drawable.indicator_pager_normal);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);

            if(i != 0){

                params.leftMargin = 30;

            }else {

                v.setBackgroundResource(R.drawable.indicator_pager_pressed);
            }

//
            v.setLayoutParams(params);


            llIndicatorContainer.addView(v);

        }

    }

    private void initData() {

        imgs[0] = R.mipmap.bd;
        imgs[1] = R.mipmap.small;
        imgs[2] = R.mipmap.welcome;
        imgs[3] = R.mipmap.wy;

    }

    @OnClick(R.id.btn_enter)
    public void onClick() {

        Intent intent = new Intent(Lead_activity.this, MainActivity.class);
        startActivity(intent);
        CacheUtil.putBooleanIntoSp(Lead_activity.this,"is_first",false);
        finish();

    }

    class MyPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView iv = new ImageView(Lead_activity.this);
            iv.setBackgroundResource(imgs[position]);
            container.addView(iv);

            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((ImageView) object);

        }
    }

    class MyPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            for (int i = 0; i < imgs.length; i++){


                llIndicatorContainer.getChildAt(i).setBackgroundResource(R.drawable.indicator_pager_normal);

            }


                llIndicatorContainer.getChildAt(position).setBackgroundResource(R.drawable.indicator_pager_pressed);

            if (position >= 3) {

                btnEnter.setVisibility(View.VISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}