package com.qihuan.commonmodule.bus

/**
 * event
 * @author qi
 * @date 2018/10/19
 */
const val EVENT_REFRESH_DAILY = "event_refresh_daily"

const val EVENT_OPEN_BROWSER = "event_open_browser"
data class BrowserEvent(var title: String, var url: String)