package com.qihuan.commonmodule.net

/**
 * Api
 *
 * @author qi
 * @date 2018/5/17
 */
interface Api {
    companion object {

        const val DOMAIN_KEY = "Domain-Name: "

        const val ZHIHU_DOMAIN_VALUE = "zhihu"

        const val ZHIHU_DOMAIN = DOMAIN_KEY + ZHIHU_DOMAIN_VALUE

        const val ZHIHU_BASE_URL = "https://news-at.zhihu.com"

        const val DOUBAN_DOMAIN_VALUE = "douban"

        const val DOUBAN_DOMAIN = DOMAIN_KEY + DOUBAN_DOMAIN_VALUE

        const val DOUBAN_BASE_URL = "https://api.douban.com"
    }
}
