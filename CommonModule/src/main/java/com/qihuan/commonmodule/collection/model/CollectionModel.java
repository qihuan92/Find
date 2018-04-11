package com.qihuan.commonmodule.collection.model;

import com.qihuan.commonmodule.base.BaseApp;
import com.qihuan.commonmodule.base.BaseModel;
import com.qihuan.commonmodule.collection.bean.CollectionBean;
import com.qihuan.commonmodule.collection.bean.CollectionBean_;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;

public class CollectionModel implements BaseModel {

    private final Box<CollectionBean> collectionBox;

    public CollectionModel() {
        collectionBox = BaseApp.getInstance().getBoxStore().boxFor(CollectionBean.class);
    }

    public void getFavoriteList(String id, CollectionDataListener listener) {
        collectionBox.query()
            .equal(CollectionBean_.collectionId, id)
            .build()
            .subscribe()
            .single()
            .on(AndroidScheduler.mainThread())
            .observer(listener::onCollectionData);
    }

    public void updateFavorite(CollectionBean collectionBean, CollectionFavoriteListener listener) {
        collectionBox.query()
            .equal(CollectionBean_.collectionId, collectionBean.getCollectionId())
            .build()
            .subscribe()
            .on(AndroidScheduler.mainThread())
            .single()
            .observer(data -> {
                boolean isFavorite = data.size() != 0;
                if (isFavorite) {
                    collectionBox.remove(data);
                } else {
                    collectionBox.put(collectionBean);
                }
                listener.onCollectionFavoriteChange(!isFavorite);
            });
    }

    @Override
    public void onDestroy() {

    }
}
