package com.example.androidlearningdemo.data.net.api;

import com.example.androidlearningdemo.data.model.Meizi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Spark on 12/13/2015.
 */
public interface GankApi {

    @GET("data/%E7%A6%8F%E5%88%A9/{count}/{page}")
    Observable<Meizi> latest(@Path("count") int count, @Path("page") int page);

}
