package com.qihuan.moviemodule.model.bean

import java.io.Serializable

class SubjectBean : Serializable {
    /**
     * max : 10
     * average : 9.6
     * stars : 50
     * min : 0
     */

    var rating: RatingBean? = null
    /**
     * rating : {"max":10,"average":9.6,"stars":"50","min":0}
     * reviews_count : 3821
     * wish_count : 61978
     * douban_site :
     * year : 1994
     * images : {"small":"http://img3.douban.com/view/movie_poster_cover/ipst/public/p480747492.jpg","large":"http://img3.douban.com/view/movie_poster_cover/lpst/public/p480747492.jpg","medium":"http://img3.douban.com/view/movie_poster_cover/spst/public/p480747492.jpg"}
     * alt : https://movie.douban.com/subject/1292052/
     * id : 1292052
     * mobile_url : https://movie.douban.com/subject/1292052/mobile
     * title : 肖申克的救赎
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/1292052
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["犯罪","剧情"]
     * collect_count : 927113
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/17525.jpg","large":"http://img3.douban.com/img/celebrity/large/17525.jpg","medium":"http://img3.douban.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/34642.jpg","large":"http://img3.douban.com/img/celebrity/large/34642.jpg","medium":"http://img3.douban.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/5837.jpg","large":"http://img3.doubanio.com/img/celebrity/large/5837.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"},{"alt":"https://movie.douban.com/celebrity/1000095/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/7827.jpg","large":"http://img3.doubanio.com/img/celebrity/large/7827.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/7827.jpg"},"name":"威廉姆·赛德勒","id":"1000095"}]
     * current_season : null
     * original_title : The Shawshank Redemption
     * summary : 20世纪40年代末，小有成就的青年银行家安迪（蒂姆·罗宾斯 Tim Robbins 饰）因涉嫌杀害妻子及她的情人而锒铛入狱。在这座名为肖申克的监狱内，希望似乎虚无缥缈，终身监禁的惩罚无疑注定了安迪接下来灰暗绝望的人生。未过多久，安迪尝试接近囚犯中颇有声望的瑞德（摩根·弗里曼 Morgan Freeman 饰），请求对方帮自己搞来小锤子。以此为契机，二人逐渐熟稔，安迪也仿佛在鱼龙混杂、罪恶横生、黑白混淆的牢狱中找到属于自己的求生之道。他利用自身的专业知识，帮助监狱管理层逃税、洗黑钱，同时凭借与瑞德的交往在犯人中间也渐渐受到礼遇。表面看来，他已如瑞德那样对那堵高墙从憎恨转变为处之泰然，但是对自由的渴望仍促使他朝着心中的希望和目标前进。而关于其罪行的真相，似乎更使这一切朝前推进了一步……
     * 本片根据著名作家斯蒂芬·金（Stephen Edwin King）的原著改编。©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/230.jpg","large":"http://img3.douban.com/img/celebrity/large/230.jpg","medium":"http://img3.douban.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}]
     * comments_count : 159372
     * ratings_count : 695919
     * aka : ["月黑高飞(港)","刺激1995(台)","地狱诺言","铁窗岁月","消香克的救赎"]
     */

    var reviews_count: Int = 0
    var wish_count: Int = 0
    var douban_site: String? = null
    var year: String? = null
    /**
     * small : http://img3.douban.com/view/movie_poster_cover/ipst/public/p480747492.jpg
     * large : http://img3.douban.com/view/movie_poster_cover/lpst/public/p480747492.jpg
     * medium : http://img3.douban.com/view/movie_poster_cover/spst/public/p480747492.jpg
     */

    var images: ImagesBean? = null
    var alt: String? = null
    var id: String? = null
    var mobile_url: String? = null
    var title: String? = null
    var do_count: Any? = null
    var share_url: String? = null
    var seasons_count: Any? = null
    var schedule_url: String? = null
    var episodes_count: Any? = null
    var collect_count: Int = 0
    var current_season: Any? = null
    var original_title: String? = null
    var summary: String? = null
    var subtype: String? = null
    var comments_count: Int = 0
    var ratings_count: Int = 0
    var countries: List<String>? = null
    var genres: List<String>? = null
    /**
     * alt : https://movie.douban.com/celebrity/1054521/
     * avatars : {"small":"http://img3.douban.com/img/celebrity/small/17525.jpg","large":"http://img3.douban.com/img/celebrity/large/17525.jpg","medium":"http://img3.douban.com/img/celebrity/medium/17525.jpg"}
     * name : 蒂姆·罗宾斯
     * id : 1054521
     */

    var casts: List<CastsBean>? = null
    /**
     * alt : https://movie.douban.com/celebrity/1047973/
     * avatars : {"small":"http://img3.douban.com/img/celebrity/small/230.jpg","large":"http://img3.douban.com/img/celebrity/large/230.jpg","medium":"http://img3.douban.com/img/celebrity/medium/230.jpg"}
     * name : 弗兰克·德拉邦特
     * id : 1047973
     */

    var directors: List<DirectorsBean>? = null
    var aka: List<String>? = null

    override fun toString(): String {
        return "$title $id"
    }


}
