package com.example.javacoffee.smartfocus.service;

import com.example.javacoffee.smartfocus.utils.L;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyHttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        L.i("javacoffee " + request.url().toString());

        Response response = chain.proceed(request);
        L.i("javacoffee " + response.body());

        return response;
    }
}
