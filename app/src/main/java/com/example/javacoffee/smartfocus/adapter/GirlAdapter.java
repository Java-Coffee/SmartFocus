package com.example.javacoffee.smartfocus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javacoffee.smartfocus.R;
import com.example.javacoffee.smartfocus.entity.GirlData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donghw.fnst on 2017/8/21.
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder>{

    private Context mContext;
    private List<GirlData> mList;
    private LayoutInflater inflater;
    private GirlData data;
    private WindowManager wm;
    private List<Integer> mHeights;
    //屏幕宽
    private int width;

    public GirlAdapter(Context context,List<GirlData> list){
        this.mContext = context;
        this.mList = list;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_girl,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        holder.imageView.setLayoutParams(layoutParams);
        final GirlData data = mList.get(position);
        if(data.getImgUrl() != null){
            Picasso.with(mContext)
                    .load(data.getImgUrl())
                    .centerCrop().error(R.drawable.china_mobile).placeholder(R.drawable.china_telecom)
                    .into(holder.imageView);
        }
        holder.textView.setText(data.getTitle());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_img);
            textView = itemView.findViewById(R.id.item_title);
        }
    }

}
