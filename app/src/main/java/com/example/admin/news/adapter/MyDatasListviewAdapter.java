package com.example.admin.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.admin.news.R;
import com.example.admin.news.enty.NewsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2016-12-14.
 */

public class MyDatasListviewAdapter extends BaseAdapter {
    private List<NewsBean.ResultBean.DataBean> datas;
    private Context context;

    public MyDatasListviewAdapter(List<NewsBean.ResultBean.DataBean> datas,Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converview, ViewGroup viewGroup) {
        View view = null;
        ViewHolder holder=null;
        if (converview==null) {
            view = View.inflate(context, R.layout.item_listview_news_fragment_activity_main, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            view = converview;
             holder= (ViewHolder) view.getTag();
        }
        Glide.with(context).load(datas.get(position).getThumbnail_pic_s()).crossFade().into(holder.ivPicNewsFragmentActivityMain);

        holder.tvTitleNewsFragmentActivityMain.setText(datas.get(position).getTitle());
        holder.tvDateNewsFragmentActivityMain.setText(datas.get(position).getDate());
        return view;
    }



    class ViewHolder {
        @BindView(R.id.iv_pic_news_fragment_activity_main)
        ImageView ivPicNewsFragmentActivityMain;
        @BindView(R.id.tv_title_news_fragment_activity_main)
        TextView tvTitleNewsFragmentActivityMain;
        @BindView(R.id.tv_date_news_fragment_activity_main)
        TextView tvDateNewsFragmentActivityMain;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
