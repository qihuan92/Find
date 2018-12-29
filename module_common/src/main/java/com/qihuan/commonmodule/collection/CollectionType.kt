package com.qihuan.commonmodule.collection

import androidx.annotation.IntDef

/**
 * CollectionType
 * @author qi
 * @date 2018/10/19
 */
@IntDef(COLLECTION_TYPE_DAILY, COLLECTION_TYPE_MOVIE)
@Retention(AnnotationRetention.SOURCE)
annotation class CollectionType

const val COLLECTION_TYPE_DAILY = 0
const val COLLECTION_TYPE_MOVIE = 1