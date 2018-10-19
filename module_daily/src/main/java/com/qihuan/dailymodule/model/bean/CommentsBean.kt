package com.qihuan.dailymodule.model.bean

class CommentsBean {
    var author: String = ""
    var content: String = ""
    var avatar: String = ""
    var time: Int = 0
    var id: Int = 0
    var likes: Int = 0
    var reply_to: ReplyToBean? = null
}