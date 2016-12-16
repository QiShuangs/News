package com.example.admin.news.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.admin.news.R;
import com.example.admin.news.fragment.ChatFragmnet;
import com.example.admin.news.fragment.ImageFragmnet;
import com.example.admin.news.fragment.LikeFragmnet;
import com.example.admin.news.fragment.NewsFragmnet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.toolbar)
    Toolbar toolbarActivityMain;
    @BindView(R.id.fl_content_activity_main)
    FrameLayout flContentActivityMain;
    @BindView(R.id.navigetion_activity_main)
    NavigationView navigetionActivityMain;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;
    private FragmentManager fragmentManager;
    private Fragment currfragment;
    private Fragment nextFragment;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        toolbarActivityMain.setTitle("新闻");
        toolbarActivityMain.setLogo(R.mipmap.ic_launcher);
        toolbarActivityMain.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbarActivityMain);
        //初始化片段管理者
        fragmentManager = getSupportFragmentManager();
        //初始化所有的fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragmnet());
        fragmentList.add(new ImageFragmnet());
        fragmentList.add(new LikeFragmnet());
        fragmentList.add(new ChatFragmnet());
        //默认选择新闻
        navigetionActivityMain.setCheckedItem(R.id.new_navgation);
        currfragment = fragmentList.get(0);
        fragmentManager.beginTransaction().add(R.id.fl_content_activity_main,currfragment).commit();
        //切换toggle,使toolbar和侧拉菜单关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityMain, toolbarActivityMain, R.string.open, R.string.close);
        toggle.syncState();
        //侧拉菜单的点击事件
        navigetionActivityMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int index = 0;

                switch (item.getItemId()){

                    case R.id.new_navgation:
                        index = 0;
                        toolbarActivityMain.setTitle("新闻");
                        break;
                    case R.id.image_navgation:
                        index = 1;
                        toolbarActivityMain.setTitle("图片");
                        break;
                    case R.id.like_navgation:
                        index = 2;
                        toolbarActivityMain.setTitle("收藏");
                        break;
                    case R.id.chat_navgation:
                        index = 3;
                        toolbarActivityMain.setTitle("聊天");
                        break;

                }
                //切换fragment

                //打开管理事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                nextFragment = fragmentList.get(index);
                if (nextFragment !=currfragment){
                    if(!nextFragment.isAdded()){
                        if (currfragment!=null){
                            transaction.hide(currfragment);
                        }
                        transaction.add(R.id.fl_content_activity_main, nextFragment);
                    }else {
                        if (currfragment!=null){
                            transaction.hide(currfragment);
                        }
                        transaction.show(nextFragment);
                    }
                    currfragment = nextFragment;
                }
                transaction.commit();
                //关闭侧拉菜单
                activityMain.closeDrawers();

                return true;
            }
        });

    }


}


