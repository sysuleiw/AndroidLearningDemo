package com.example.androidlearningdemo.ui.presenter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

import com.example.androidlearningdemo.MeiziContext;
import com.example.androidlearningdemo.data.model.Meizi;
import com.example.androidlearningdemo.data.model.MeiziRealmEntity;
import com.example.androidlearningdemo.data.net.RequestFactory;
import com.example.androidlearningdemo.data.net.api.GankApi;
import com.example.androidlearningdemo.ui.adapter.base.BaseRecyclerAdapter;
import com.example.androidlearningdemo.ui.presenter.base.BasePresenter;
import com.example.androidlearningdemo.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wanglei
 */
public class MeiziPresenter extends BasePresenter<IMeizi> {
    private Realm realm;
    private GankApi gankApi;
    private final int COUNT = 10;
    public static final String MEIZIS = "meizis";
    public static final String INDEX = "index";

    public MeiziPresenter(IMeizi view) {
        super(view);
        realm = Realm.getDefaultInstance();
        gankApi = RequestFactory.createApi(GankApi.class);
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    @SuppressLint("CheckResult")
    public void requestMeizi(int page) {
        getViewRef().getAdapter().addFooter();
        gankApi.latest(COUNT, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Meizi>() {
                    @Override
                    public void accept(Meizi meizi) throws Exception {
                        Observable.just(meizi).map(new Function<Meizi, Meizi>() {

                            @Override
                            public Meizi apply(Meizi meizi) throws Exception {
                                for (final Meizi.ResultsBean bean : meizi.getResults()) {
                                    Bitmap bitmap = ImageLoader.loadImageBitmap(bean.getUrl(),
                                            MeiziContext.getInstance().getContext());
                                    if (bitmap != null) {
                                        bean.setWidth(bitmap.getWidth());
                                        bean.setHeight(bitmap.getHeight());
                                    }
                                }
                                return meizi;
                            }
                        })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Meizi>() {
                                    @Override
                                    public void accept(Meizi meizi) throws Exception {

                                        // 加载图片缓存，并保存尺寸数据到meizi
                                        //只能在创建的线程使用
                                        for (final Meizi.ResultsBean bean : meizi.getResults()) {
                                            realm.beginTransaction();
                                            realm.copyToRealmOrUpdate(convert2Realm(bean));
                                            realm.commitTransaction();
                                        }

                                        BaseRecyclerAdapter adapter = getViewRef().getAdapter().getWrapped();
                                        List<Meizi.ResultsBean> list = adapter.getData();
                                        if (list != null) {
                                            list.addAll(meizi.getResults());
                                        } else {
                                            list = meizi.getResults();
                                        }
                                        adapter.setData(list);
                                        getViewRef().getAdapter().getWrapped().notifyItemRangeInserted(
                                                list.size() - COUNT - 1,
                                                list.size() - 1);
                                        getViewRef().getAdapter().removeFooter();
                                        getViewRef().setRefresh(false);
                                    }
                                });
                    }
                });
    }
    public List<Meizi.ResultsBean> loadDataFromDB() {
        List<Meizi.ResultsBean> list = new ArrayList<>();
        RealmResults<MeiziRealmEntity> results = realm.where(MeiziRealmEntity.class)
                .findAll();
        for (MeiziRealmEntity entity : results) {
            Meizi.ResultsBean temp = new Meizi.ResultsBean();
            temp.setUrl(entity.getUrl());
            temp.setPublishedAt(entity.getPublishedAt());
            list.add(temp);
        }
        if (list.size() >= 30) {
            return list.subList(0, 29);
        } else {
            return list;
        }
    }

    private MeiziRealmEntity convert2Realm(Meizi.ResultsBean resultsBean) {
        MeiziRealmEntity realmEntity = new MeiziRealmEntity();
        realmEntity.set_id(resultsBean.get_id());
        realmEntity.setCreatedAt(resultsBean.getCreatedAt());
        realmEntity.setDesc(resultsBean.getDesc());
        realmEntity.setPublishedAt(resultsBean.getPublishedAt());
        realmEntity.setUrl(resultsBean.getUrl());
        return realmEntity;
    }
}

