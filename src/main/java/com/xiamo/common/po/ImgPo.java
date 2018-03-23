package com.xiamo.common.po;

import java.io.Serializable;

/**
 * <dl>
 * <dt>ImgPo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/3/23 0023</dd>
 * </dl>
 * 图片
 *
 * @author chenglitao
 */
public class ImgPo implements Serializable {
    private String name;
    private String src;
    private Integer size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
