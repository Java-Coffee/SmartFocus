package com.example.javacoffee.smartfocus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.javacoffee.smartfocus.R;
import com.example.javacoffee.smartfocus.entity.ZhihuData;
import com.example.javacoffee.smartfocus.utils.L;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ViewHolder> {

    private Context mContext;
    private List<ZhihuData.TopStoriesBean> mStoriesBeanList;

    public ZhihuAdapter(Context context, List<ZhihuData.TopStoriesBean> beanList) {
        mContext = context;
        mStoriesBeanList = beanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zhihu_item, parent, false);
        ZhihuAdapter.ViewHolder viewHolder = new ZhihuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.mImgPreview.getLayoutParams();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        holder.mImgPreview.setLayoutParams(layoutParams);
        final ZhihuData.TopStoriesBean data = mStoriesBeanList.get(position);
        L.i("zhihuData:"+data.getImage()+" title = "+data.getTitle());
        Glide.with(mContext).load(data.getImage())
                .asBitmap()
                .placeholder(R.drawable.add_pic)
                //                .centerCrop()
                .into(holder.mImgPreview);
        holder.mContentTv.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return mStoriesBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_preview)
        ImageView mImgPreview;
        @BindView(R.id.content_tv)
        TextView mContentTv;

        ViewHolder(View view) {

            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
