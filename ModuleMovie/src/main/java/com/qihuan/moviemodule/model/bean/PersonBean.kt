package com.qihuan.moviemodule.model.bean

import java.io.Serializable

open class PersonBean : Serializable {
    open var avatars: AvatarsBean? = null
    open var name: String = ""
    open var id: String = ""
}
