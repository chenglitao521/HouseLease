package com.xiamo.classify.po;

import java.util.Map;

/**
 * <dl>
 * <dt>ClassifyVo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/2/8 0008</dd>
 * </dl>
 * 分类Vo
 *
 * @author chenglitao
 */
public class ClassifyVo {
    private Integer id;  //id
    private String name; //一级目录名称
    private Map<String, Object> icon; //一级目录图片
    private Map<String, Object> subName; //二级目录图片

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getIcon() {
        return icon;
    }

    public void setIcon(Map<String, Object> icon) {
        this.icon = icon;
    }

    public Map<String, Object> getSubName() {
        return subName;
    }

    public void setSubName(Map<String, Object> subName) {
        this.subName = subName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
