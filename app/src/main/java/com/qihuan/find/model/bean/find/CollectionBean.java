package com.qihuan.find.model.bean.find;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * CollectionBean
 * Created by Qi on 2017/8/25.
 */
@Entity(tableName = "t_collection")
public class CollectionBean {
    @PrimaryKey
    private int id;

    private int type;

    private String title;

    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
