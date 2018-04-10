package com.qihuan.find.model.local;

import com.qihuan.find.model.bean.find.CollectionBean;

import java.util.List;

public interface CollectionDataListener {
    void onCollectionData(List<CollectionBean> collectionList);
}
