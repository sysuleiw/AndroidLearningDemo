package com.example.androidlearningdemo.data.dao;

import android.util.Log;

import com.example.androidlearningdemo.utils.DateUtil;
import com.example.androidlearningdemo.data.model.Meizi;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Spark on 12/13/2015.
 */
public class MeiziDAO {
    static Realm realm;

    public static void bulkInsert(List<Meizi> meiziList) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (Meizi meizi : meiziList) {
            realm.copyToRealmOrUpdate(meizi);
        }
        realm.commitTransaction();
        realm.close();
    }

    public static String[] getLatestPicDate() {
        String[] date;
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Meizi> results = realm.where(Meizi.class).
                findAll();
        date = DateUtil.format(results.get(0).getPublishedAt());
        Log.d("MeiziDAO", "getDateYear" + date[0]);
        realm.close();

        return date;
    }

}
