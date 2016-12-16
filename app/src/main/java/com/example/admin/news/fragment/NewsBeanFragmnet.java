package com.example.admin.news.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.admin.news.R;
import com.example.admin.news.Utils.Constant;
import com.example.admin.news.Utils.NohttpInstance;
import com.example.admin.news.adapter.MyDatasListviewAdapter;
import com.example.admin.news.enty.NewsBean;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

/**
 * Created by admin on 2016-12-14.
 */

public class NewsBeanFragmnet extends Fragment{
    private SwipeRefreshLayout layout;
    private String url = null;
    private ListView listview;
    private MyDatasListviewAdapter adapter;
    private List<NewsBean.ResultBean.DataBean> datas;
    public NewsBeanFragmnet(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (SwipeRefreshLayout) inflater.inflate(R.layout.layout_newsbean_fragment_activity_main, null);
        listview = (ListView) layout.findViewById(R.id.lv_news_fragment_activity_main);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        adapter.datas.remove(0);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                                layout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL +url);
        NohttpInstance.getInstance().add(Constant.WHAT_NENS_REQUEST, stringRequest, new OnResponseListener<String>() {



            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                NewsBean newsBean = JSON.parseObject(result, NewsBean.class);
                datas = newsBean.getResult().getData();
                adapter = new MyDatasListviewAdapter(datas,getContext());
                listview.setAdapter(adapter);

            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Toast.makeText(getContext(), "response.getException():" + response.getException(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
