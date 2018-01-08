package com.xiamo.classify.po;

import java.io.Serializable;

/**
 * <dl>
 * <dt>ClassifyPo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/8</dd>
 * </dl>
 * 分类
 *
 * @author CLT
 */
public class ClassifyPo implements Serializable {

    private Integer id;
    private String name;
    private String catalog1;
    private String iconUrl1;
    private String catalog2;
    private String iconUrl2;
    private String catalog3;
    private String iconUrl3;
    private String catalog4;
    private String iconUrl4;
    private String catalog5;
    private String iconUrl5;
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

    public String getCatalog1() {
        return catalog1;
    }

    public void setCatalog1(String catalog1) {
        this.catalog1 = catalog1;
    }

    public String getIconUrl1() {
        return iconUrl1;
    }

    public void setIconUrl1(String iconUrl1) {
        this.iconUrl1 = iconUrl1;
    }

    public String getCatalog2() {
        return catalog2;
    }

    public void setCatalog2(String catalog2) {
        this.catalog2 = catalog2;
    }

    public String getIconUrl2() {
        return iconUrl2;
    }

    public void setIconUrl2(String iconUrl2) {
        this.iconUrl2 = iconUrl2;
    }

    public String getCatalog3() {
        return catalog3;
    }

    public void setCatalog3(String catalog3) {
        this.catalog3 = catalog3;
    }

    public String getIconUrl3() {
        return iconUrl3;
    }

    public void setIconUrl3(String iconUrl3) {
        this.iconUrl3 = iconUrl3;
    }

    public String getCatalog4() {
        return catalog4;
    }

    public void setCatalog4(String catalog4) {
        this.catalog4 = catalog4;
    }

    public String getIconUrl4() {
        return iconUrl4;
    }

    public void setIconUrl4(String iconUrl4) {
        this.iconUrl4 = iconUrl4;
    }

    public String getCatalog5() {
        return catalog5;
    }

    public void setCatalog5(String catalog5) {
        this.catalog5 = catalog5;
    }

    public String getIconUrl5() {
        return iconUrl5;
    }

    public void setIconUrl5(String iconUrl5) {
        this.iconUrl5 = iconUrl5;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
