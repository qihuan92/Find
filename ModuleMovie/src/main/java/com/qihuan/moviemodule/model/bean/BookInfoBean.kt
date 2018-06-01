package com.qihuan.moviemodule.model.bean

class BookInfoBean {
    var rating: RatingBean? = null
    var subtitle: String? = null
    var pubdate: String? = null
    var origin_title: String? = null
    var image: String? = null
    var binding: String? = null
    var catalog: String? = null
    var ebook_url: String? = null
    var pages: String? = null
    var images: ImagesBean? = null
    var alt: String? = null
    var id: String? = null
    var publisher: String? = null
    var isbn10: String? = null
    var isbn13: String? = null
    var title: String? = null
    var url: String? = null
    var alt_title: String? = null
    var author_intro: String? = null
    var summary: String? = null
    var ebook_price: String? = null

    var series: SeriesBean? = null
    var price: String? = null
    var author: List<String>? = null

    var tags: List<TagsBean>? = null
    var translator: List<String>? = null

    class RatingBean {
        var max: Int = 0
        var numRaters: Int = 0
        var average: String? = null
        var min: Int = 0
    }

    class ImagesBean {
        var small: String? = null
        var large: String? = null
        var medium: String? = null
    }

    class SeriesBean {
        var id: String? = null
        var title: String? = null
    }

    class TagsBean {
        var count: Int = 0
        var name: String? = null
        var title: String? = null
    }
}
