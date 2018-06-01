package com.qihuan.commonmodule.utils

import android.webkit.WebView

private const val BASE_URL = "file:///android_asset/"
private const val MIME_TYPE = "text/html"
private const val ENCODING = "utf-8"
private const val FAIL_URL = "https://daily.zhihu.com/"

private const val CSS_LINK_PATTERN = " <link href=\"%s\" type=\"text/css\" rel=\"stylesheet\" />"
private const val NIGHT_DIV_TAG_START = "<div class=\"night\">"
private const val NIGHT_DIV_TAG_END = "</div>"

private const val DIV_IMAGE_PLACE_HOLDER = "class=\"img-place-holder\""
private const val DIV_IMAGE_PLACE_HOLDER_IGNORED = "class=\"img-place-holder-ignored\""

fun WebView.loadHtmlWithCss(html: String, cssUrls: List<String>, isNightMode: Boolean) {
    val result = StringBuilder()
    for (cssUrl in cssUrls) {
        result.append(String.format(CSS_LINK_PATTERN, cssUrl))
    }

    if (isNightMode) {
        result.append(NIGHT_DIV_TAG_START)
    }
    result.append(html.replace(DIV_IMAGE_PLACE_HOLDER, DIV_IMAGE_PLACE_HOLDER_IGNORED))
    if (isNightMode) {
        result.append(NIGHT_DIV_TAG_END)
    }
    loadDataWithBaseURL(BASE_URL, result.toString(), MIME_TYPE, ENCODING, FAIL_URL)
}

fun WebView.buildHtmlForIt(content: String, isNightMode: Boolean) {
    val modifiedHtml = StringBuilder()
    modifiedHtml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\" \"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\">"
            + "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + "<head>" + "<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\"/>"
            + "<meta http-equiv=\"Cache-control\" content=\"public\" />" + "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0\" />"
            + "<link rel=\"stylesheet\" href=\"file:///android_asset/news.css\" type=\"text/css\">"
            + "<script src=\"file:///android_asset/jquery.min.js\"></script>" + "<script src=\"file:///android_asset/info.js\"></script>")
    modifiedHtml.append("<body ")
    if (isNightMode) {
        modifiedHtml.append("class=\'night\'")
    }
    modifiedHtml.append(">")
    modifiedHtml.append(content)
    modifiedHtml.append("</body></html>")
    loadDataWithBaseURL(BASE_URL, modifiedHtml.toString(), MIME_TYPE, ENCODING, FAIL_URL)
}
