package com.example.javacoffee.smartfocus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javacoffee.smartfocus.R;
import com.example.javacoffee.smartfocus.adapter.GirlAdapter;
import com.example.javacoffee.smartfocus.adapter.ZhihuAdapter;
import com.example.javacoffee.smartfocus.api.GankApi;
import com.example.javacoffee.smartfocus.bean.SearchData;
import com.example.javacoffee.smartfocus.entity.GirlData;
import com.example.javacoffee.smartfocus.entity.ZhihuData;
import com.example.javacoffee.smartfocus.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ButlerFragment extends Fragment {
    @BindView(R.id.zhihurecyclerView)
    RecyclerView mZhihurecyclerView;
    Unbinder unbinder;
    private ZhihuAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler, null);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        GankApi.getInstance()
                .getGankInterface()
                .getDailyNews()
                .subscribeOn(Schedulers.io())
                .map(new Func1<ZhihuData, List<ZhihuData.StoriesBean>>() {
                    @Override
                    public List<ZhihuData.StoriesBean> call(ZhihuData zhihuData) {
                        int size = zhihuData.getStories().size();
                        List<ZhihuData.StoriesBean> storiesBean = new ArrayList<ZhihuData.StoriesBean>();
                        return storiesBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ZhihuData.StoriesBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(List<ZhihuData.StoriesBean> storiesBeen) {
                        mAdapter = new ZhihuAdapter(getActivity(),storiesBeen);
                        mZhihurecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }


    private void initView(View view) {
        mZhihurecyclerView.setHasFixedSize(true);
        mZhihurecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
