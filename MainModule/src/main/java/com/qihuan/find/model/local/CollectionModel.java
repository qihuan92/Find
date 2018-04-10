package com.qihuan.find.model.local;

import com.qihuan.find.app.App;
import com.qihuan.find.model.base.BaseModel;
import com.qihuan.find.model.bean.find.CollectionBean;
import com.qihuan.find.model.bean.find.CollectionBean_;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;

public class CollectionModel implements BaseModel {

    private final Box<CollectionBean> collectionBox;

    public CollectionModel() {
        collectionBox = App.getInstance().getBoxStore().boxFor(CollectionBean.class);
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
