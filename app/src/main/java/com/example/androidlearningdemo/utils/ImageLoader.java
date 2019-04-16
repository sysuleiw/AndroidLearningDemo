package com.example.androidlearningdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.example.androidlearningdemo.R;
import com.example.androidlearningdemo.data.model.Meizi;

import java.util.concurrent.ExecutionException;

/**
 * Created by Spark on 8/2/2016.
 * Github: github/SparkYuan
 */
public class ImageLoader {

    public static void loadImage(String url, ImageView imageView, Context context) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static Bitmap loadImageBitmap(String url, Context context) {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            e.printStackTrace();
        }
        return bitmap;
    }
}
