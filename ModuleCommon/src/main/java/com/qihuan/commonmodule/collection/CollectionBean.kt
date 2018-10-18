package com.qihuan.commonmodule.collection

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * CollectionBean
 * @author qi
 * @date 2018/10/17
 */
@Entity(tableName = "collection")
data class CollectionBean constructor(
        @PrimaryKey
        var id: String = "",
        var type: Int = 0,
        var title: String? = null,
        var img: String? = null)