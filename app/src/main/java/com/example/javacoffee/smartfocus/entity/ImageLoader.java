package com.example.javacoffee.smartfocus.entity;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

public class ImageLoader {

    private ImageView mImageView;
    private String mUrl;
    private static LruCache<String,Bitmap> mLruCache;

    public ImageLoader(){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/8;
        mLruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public static void addBitmapToCache(String url,Bitmap bitmap){
        if(getBitmapFromCache(url) == null){
            mLruCache.put(url,bitmap);
        }
    }

    public static Bitmap getBitmapFromCache(String url){
        return mLruCache.get(url);
    }
}
