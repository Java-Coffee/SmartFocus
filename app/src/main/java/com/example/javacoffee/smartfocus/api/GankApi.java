package com.example.javacoffee.smartfocus.api;

import com.example.javacoffee.smartfocus.bean.SearchData;
import com.example.javacoffee.smartfocus.entity.GirlData;
import com.example.javacoffee.smartfocus.service.MyHttpInterceptor;
import com.example.javacoffee.smartfocus.utils.StaticClass;
import com.google.gson.Gson;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.functions.Func1;

public class GankApi {
    public static GankApi INSTANCE;
    public static Gson gson;
    private static GankInterface gankInterface;

    public static GankApi getInstance(){
        if(INSTANCE == null){
            synchronized (GankApi.class){
                if(INSTANCE == null){
                    INSTANCE = new GankApi();
                    gson = new Gson();
                }
            }
        }
        return INSTANCE;
    }

    public GankApi(){
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new MyHttpInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticClass.GIRL_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        gankInterface = retrofit.create(GankInterface.class);

    }

    public GankInterface getGankInterface(){
        return gankInterface;
    }

    public interface GankInterface{
        @GET("data/福利/10/1")
        rx.Observable<SearchData> getGirlImage();
    }

    public static final Func1<SearchData, List<GirlData>> DECODE_HELPER = new Func1<SearchData, List<GirlData>>() {
        @Override
        public List<GirlData> call(SearchData searchData) {
            int size = searchData.getResultsX().size();
            List<SearchData.ResultsBean> resultsBeanList = searchData.getResultsX();
            List<GirlData> girlDataList = null;
            GirlData girlData = null;
            for(int i = 0;i < size;i++){
                girlData.setImgUrl(resultsBeanList.get(i).getUrl());
                girlData.setTitle(resultsBeanList.get(i).getType());
                girlDataList.add(girlData);
            }
            return girlDataList;
        }
    };

    public static class GsonFormatHelper<R> implements Func1<Data,R>{

        Class<R> mRClass;

        public GsonFormatHelper(Class<R> rClass){
            mRClass = rClass;
        }

        @Override
        public R call(Data data) {
            return gson.fromJson(data.getData(),mRClass);
        }
    }

}
