package com.qihuan.find.model.bean.zhihu;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.io.Serializable;

/**
 * DailyItem
 * Created by Qi on 2017/7/12.
 */

public class DailyItem extends SectionEntity<StoriesEntity> implements Serializable {
    public DailyItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public DailyItem(StoriesEntity storiesEntity) {
        super(storiesEntity);
    }
}
