package com.qihuan.commonmodule.collection.model

import com.qihuan.commonmodule.base.BaseApp
import com.qihuan.commonmodule.base.BaseModel
import com.qihuan.commonmodule.collection.bean.CollectionBean
import com.qihuan.commonmodule.collection.bean.CollectionBean_
import io.objectbox.Box
import io.objectbox.android.AndroidScheduler

/**
 * CollectionModel
 *
 * @author Qi
 */
class CollectionModel : BaseModel {

    private val collectionBox: Box<CollectionBean> = BaseApp.instance.boxStore.boxFor(CollectionBean::class.java)

    fun getFavoriteList(id: String, type: Int = 0, callback: (List<CollectionBean>) -> Unit) {
        collectionBox.query()
                .equal(CollectionBean_.collectionId, id)
                .filter { it.type == type }
                .build()
                .subscribe()
                .single()
                .on(AndroidScheduler.mainThread())
                .observer({ callback.invoke(it) })
    }

    fun updateFavorite(collectionBean: CollectionBean, callback: (Boolean) -> Unit) {
        collectionBox.query()
                .equal(CollectionBean_.collectionId, collectionBean.collectionId)
                .build()
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .single()
                .observer { data ->
                    val isFavorite = data.size != 0
                    if (isFavorite) {
                        collectionBox.remove(data)
                    } else {
                        collectionBox.put(collectionBean)
                    }
                    callback.invoke(!isFavorite)
                }
    }

    override fun onDestroy() {

    }
}
