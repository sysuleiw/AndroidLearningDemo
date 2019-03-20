package com.example.androidlearningdemo.data.net;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.androidlearningdemo.data.net.api.GankApi;
import com.example.androidlearningdemo.data.dao.MeiziDAO;
import com.example.androidlearningdemo.data.model.Meizi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Spark on 12/13/2015.
 */
public class SparkRetrofit {
    private Retrofit retrofit;
    private GankApi gankApi;
    private Context context;
    public static final int REQUEST_MEIZI_COUNT = 10;

    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setExclusionStrategies(new ExclusionStrategy() {   //把RealmObject排除在外,不然会报错。
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    public SparkRetrofit(Context context) {
        this.context = context;

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(12,TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.avosapps.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gankApi = retrofit.create(GankApi.class);
    }

    public List<Meizi> getLatest(int page) {

        GankApi.Result<List<Meizi>> result = null;
        try {
            result = gankApi.latest(REQUEST_MEIZI_COUNT, page).execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result == null || result.results.size() == 0) {
            return null;
        }
        if (result.error) {
            return null;
        } else {
            Bitmap bitmap = null;
            for (Meizi meizi : result.results) {
                try {
                    bitmap = Glide.with(context)
                            .asBitmap()
                            .load(meizi.getUrl())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(-1, -1)
                            .get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    meizi.setWidth(bitmap.getWidth());
                    meizi.setHeight(bitmap.getHeight());
                }else {
                    meizi.setWidth(0);
                    meizi.setHeight(0);
                }
                MeiziDAO.bulkInsert(result.results);
            }
            return result.results;
        }
    }
}
