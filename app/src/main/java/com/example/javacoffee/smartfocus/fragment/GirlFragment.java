package com.example.javacoffee.smartfocus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javacoffee.smartfocus.R;
import com.example.javacoffee.smartfocus.adapter.GirlAdapter;
import com.example.javacoffee.smartfocus.api.Data;
import com.example.javacoffee.smartfocus.api.GankApi;
import com.example.javacoffee.smartfocus.bean.SearchData;
import com.example.javacoffee.smartfocus.entity.GirlData;
import com.example.javacoffee.smartfocus.service.MyHttpInterceptor;
import com.example.javacoffee.smartfocus.service.RetrofitService;
import com.example.javacoffee.smartfocus.utils.L;
import com.example.javacoffee.smartfocus.utils.StaticClass;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GirlFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<SearchData.ResultsBean>mList = new ArrayList<>();
    private GirlAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl,null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        L.i("java coffee init data");

        GankApi.getInstance()
                .getGankInterface()
                .getGirlImage()
                .subscribeOn(Schedulers.io())
                .map(new Func1<SearchData, List<GirlData>>() {
                    @Override
                    public List<GirlData> call(SearchData searchData) {
                        int size = searchData.getResultsX().size();
                        L.i("size = "+size);
                        if(size > 0) {
                            List<SearchData.ResultsBean> resultsBeanList = searchData.getResultsX();
//                            L.i("url = "+searchData.getResultsX().get(0).getUrl());
                            List<GirlData> girlDataList = new ArrayList<GirlData>();
                            for (int i = 0; i < size; i++) {
                                GirlData girlData = new GirlData();
                                girlData.setImgUrl(resultsBeanList.get(i).getUrl());
                                girlData.setTitle(resultsBeanList.get(i).getType());
                                //L.i("Url = "+resultsBeanList.get(i).getUrl()+"\n"+"Type = "+resultsBeanList.get(i).getType());
                                girlDataList.add(girlData);
                            }
                            return girlDataList;
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GirlData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        L.i(throwable.getMessage());
                    }

                    @Override
                    public void onNext(List<GirlData> girlDatas) {
                        mAdapter = new GirlAdapter(getActivity(),girlDatas);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

}
