package com.qihuan.find.bean.zhihu;

import java.io.Serializable;

public class SplashImgEntity implements Serializable {

    /**
     * text : Karl Fredrickson
     * img : https://pic4.zhimg.com/v2-d447d4c4a54119be40fb2c627d648157_xxdpi.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
