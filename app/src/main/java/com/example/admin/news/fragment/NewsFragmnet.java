package com.example.admin.news.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.news.R;
import com.example.admin.news.Utils.Constant;
import com.example.admin.news.adapter.MyNewsPageAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by admin on 2016-12-14.
 */

public class NewsFragmnet extends Fragment {

    private List<Fragment> newsBeanFragmnets;
    private ViewPager vp;
    private TabLayout tab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.news_fragment_activity_main, null);
        vp = (ViewPager) ll.findViewById(R.id.vp_news_fragment_activity_main);
        tab = (TabLayout) ll.findViewById(R.id.tabs_news_fragment_activity_main);
        tab.setupWithViewPager(vp);
        tab.setBackgroundColor(Color.LTGRAY);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabTextColors(Color.BLACK,Color.BLUE);
        return ll;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsBeanFragmnets = new ArrayList<>();
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.TOP_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.SHEHUI_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.GUONEI_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.GUOJI_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.YULE_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.TIYU_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.JUNSHI_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.KEJI_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.CAIJING_NEWS_QUERY_STRING));
        newsBeanFragmnets.add(new NewsBeanFragmnet(Constant.SHISHANG_NEWS_QUERY_STRING));
        MyNewsPageAdapter myNewsPageAdapter = new MyNewsPageAdapter(getFragmentManager(), newsBeanFragmnets);
        vp.setAdapter(myNewsPageAdapter);

    }
}
