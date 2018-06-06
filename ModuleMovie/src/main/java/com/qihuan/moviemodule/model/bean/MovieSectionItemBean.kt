package com.qihuan.moviemodule.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * MovieSectionItemBean
 * @author qi
 * @date 2018/6/6
 */
class MovieSectionItemBean : SectionEntity<SubjectBean> {
    constructor(isHeader: Boolean, header: String) : super(isHeader, header)

    constructor(subjectBean: SubjectBean) : super(subjectBean)
}