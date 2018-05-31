package com.qihuan.commonmodule.collection.bean

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * CollectionBean
 *
 * @author Qi
 * @date 2017/8/25
 */
@Entity
data class CollectionBean(
        @Id
        var id: Long = 0,
        var collectionId: String = "",
        var type: Int = 0,
        var title: String? = null,
        var img: String? = null
)
