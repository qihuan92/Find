package com.qihuan.commonmodule.bus.event;

/**
 * BrowserEvent
 *
 * @author qi
 * @date 2018/5/21
 */
public class BrowserEvent {
    private String title;
    private String url;

    public BrowserEvent() {
    }

    public BrowserEvent(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public BrowserEvent setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BrowserEvent setUrl(String url) {
        this.url = url;
        return this;
    }
}
