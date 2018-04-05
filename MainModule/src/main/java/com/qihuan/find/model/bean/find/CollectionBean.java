package com.qihuan.find.model.bean.find;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * CollectionBean
 *
 * @author Qi
 * @date 2017/8/25
 */
@Entity
public class CollectionBean {

    @Id
    private Long id;

    private String collectionId;

    private int type;

    private String title;

    private String img;

    public CollectionBean() {
    }

    public CollectionBean(Long id, String collectionId, int type, String title, String img) {
        this.id = id;
        this.collectionId = collectionId;
        this.type = type;
        this.title = title;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public CollectionBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public CollectionBean setCollectionId(String collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    public int getType() {
        return type;
    }

    public CollectionBean setType(int type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CollectionBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImg() {
        return img;
    }

    public CollectionBean setImg(String img) {
        this.img = img;
        return this;
    }
}
