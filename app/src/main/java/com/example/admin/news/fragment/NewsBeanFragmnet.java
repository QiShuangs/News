package com.example.admin.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

/**
 * Created by admin on 2016-12-14.
 */

public class NewsBeanFragmnet extends Fragment{
    private PullToRefreshListView listView;
    private String url = null;

    public NewsBeanFragmnet(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listView = (PullToRefreshListView) inflater.inflate(R.layout.layout_newsbean_fragment_activity_main, null);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getContext(), "正在请求网络数据", Toast.LENGTH_SHORT).show();
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.onRefreshComplete();
                    }
                }, 1000);
            }
        });
        return listView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL +url);
        NohttpInstance.getInstance().add(Constant.WHAT_NENS_REQUEST, stringRequest, new OnResponseListener<String>() {

            private List<NewsBean.ResultBean.DataBean> datas;

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                NewsBean newsBean = JSON.parseObject(result, NewsBean.class);
                datas = newsBean.getResult().getData();
                MyDatasListviewAdapter adapter = new MyDatasListviewAdapter(datas,getContext());
                listView.setAdapter(adapter);

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
