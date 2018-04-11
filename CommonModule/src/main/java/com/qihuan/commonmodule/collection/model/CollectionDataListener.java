package com.qihuan.commonmodule.collection.model;

import com.qihuan.commonmodule.collection.bean.CollectionBean;

import java.util.List;

public interface CollectionDataListener {
    void onCollectionData(List<CollectionBean> collectionList);
}
