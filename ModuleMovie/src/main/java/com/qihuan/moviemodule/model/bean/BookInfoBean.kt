package com.qihuan.moviemodule.model.bean

class BookInfoBean {
    /**
     * max : 10
     * numRaters : 216208
     * average : 8.8
     * min : 0
     */

    var rating: RatingBean? = null
    /**
     * rating : {"max":10,"numRaters":216208,"average":"8.8","min":0}
     * subtitle :
     * author : ["[美] 卡勒德·胡赛尼"]
     * pubdate : 2006-5
     * tags : [{"count":48034,"name":"追风筝的人","title":"追风筝的人"},{"count":33548,"name":"阿富汗","title":"阿富汗"},{"count":30608,"name":"小说","title":"小说"},{"count":30530,"name":"人性","title":"人性"},{"count":28681,"name":"救赎","title":"救赎"},{"count":22219,"name":"卡勒德·胡赛尼","title":"卡勒德·胡赛尼"},{"count":20384,"name":"外国文学","title":"外国文学"},{"count":10283,"name":"外国小说","title":"外国小说"}]
     * origin_title : The Kite Runner
     * image : https://img3.doubanio.com/mpic/s1727290.jpg
     * binding : 平装
     * translator : ["李继宏"]
     * catalog : 第一章 第二章 第三章 第四章 第五章 第六章 第七章 第八章 第九章 第十章 第十一章 第十二章 第十三章 第十四章 第十五章 第十六章 第十七章 第十八章 第十九章 第二十章 第二十一章 第二十二章 第二十三章 第二十四章 第二十五章 译后记
     * ebook_url : https://read.douban.com/ebook/1162265/
     * pages : 362
     * images : {"small":"https://img3.doubanio.com/spic/s1727290.jpg","large":"https://img3.doubanio.com/lpic/s1727290.jpg","medium":"https://img3.doubanio.com/mpic/s1727290.jpg"}
     * alt : https://book.douban.com/subject/1770782/
     * id : 1770782
     * publisher : 上海人民出版社
     * isbn10 : 7208061645
     * isbn13 : 9787208061644
     * title : 追风筝的人
     * url : https://api.douban.com/v2/book/1770782
     * alt_title : The Kite Runner
     * author_intro : 卡勒德·胡赛尼（Khaled Hosseini），1965年生于阿富汗喀布尔市，后随父亲迁往美国。胡赛尼毕业于加州大学圣地亚哥医学系，现居加州。“立志拂去蒙在阿富汗普通民众面孔的尘灰，将背后灵魂的悸动展示给世人。”著有小说《追风筝的人》(The Kite Runner，2003）、《灿烂千阳》(A Thousand Splendid Suns，2007)、《群山回唱》（And the Mountains Echoed,2013）。作品全球销量超过4000万册。2006年，因其作品巨大的国际影响力，胡赛尼获得联合国人道主义奖，并受邀担任联合国难民署亲善大使。
     * summary : 12岁的阿富汗富家少爷阿米尔与仆人哈桑情同手足。然而，在一场风筝比赛后，发生了一件悲惨不堪的事，阿米尔为自己的懦弱感到自责和痛苦，逼走了哈桑，不久，自己也跟随父亲逃往美国。
     * 成年后的阿米尔始终无法原谅自己当年对哈桑的背叛。为了赎罪，阿米尔再度踏上暌违二十多年的故乡，希望能为不幸的好友尽最后一点心力，却发现一个惊天谎言，儿时的噩梦再度重演，阿米尔该如何抉择？
     * 故事如此残忍而又美丽，作者以温暖细腻的笔法勾勒人性的本质与救赎，读来令人荡气回肠。
     * ebook_price : 12.99
     * series : {"id":"19760","title":"卡勒德·胡赛尼作品"}
     * price : 29.00元
     */

    var subtitle: String? = null
    var pubdate: String? = null
    var origin_title: String? = null
    var image: String? = null
    var binding: String? = null
    var catalog: String? = null
    var ebook_url: String? = null
    var pages: String? = null
    /**
     * small : https://img3.doubanio.com/spic/s1727290.jpg
     * large : https://img3.doubanio.com/lpic/s1727290.jpg
     * medium : https://img3.doubanio.com/mpic/s1727290.jpg
     */

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
    /**
     * id : 19760
     * title : 卡勒德·胡赛尼作品
     */

    var series: SeriesBean? = null
    var price: String? = null
    var author: List<String>? = null
    /**
     * count : 48034
     * name : 追风筝的人
     * title : 追风筝的人
     */

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
