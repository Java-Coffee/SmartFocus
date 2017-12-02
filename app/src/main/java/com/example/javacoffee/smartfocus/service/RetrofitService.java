package com.example.javacoffee.smartfocus.service;

import com.example.javacoffee.smartfocus.bean.SearchData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface RetrofitService {
    @GET("search/query/listview/category/福利/count/{number}/page/1")
    Observable<SearchData> loadJson(@Path("number") int number);
}
