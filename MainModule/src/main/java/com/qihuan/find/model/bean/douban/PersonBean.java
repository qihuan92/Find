package com.qihuan.find.model.bean.douban;

import java.io.Serializable;

public class PersonBean implements Serializable {
    protected AvatarsBean avatars;
    protected String name;
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }
}
