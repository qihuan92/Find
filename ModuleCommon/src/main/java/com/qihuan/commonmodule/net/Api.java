package com.qihuan.commonmodule.net;

/**
 * Api
 *
 * @author qi
 * @date 2018/5/17
 */
public interface Api {

    String DOMAIN_KEY = "Domain-Name: ";

    String ZHIHU_DOMAIN_VALUE = "zhihu";

    String ZHIHU_DOMAIN = DOMAIN_KEY + ZHIHU_DOMAIN_VALUE;

    String ZHIHU_BASE_URL = "http://news-at.zhihu.com";

    String DOUBAN_DOMAIN_VALUE = "douban";

    String DOUBAN_DOMAIN = DOMAIN_KEY + DOUBAN_DOMAIN_VALUE;

    String DOUBAN_BASE_URL = "https://api.douban.com";
}
