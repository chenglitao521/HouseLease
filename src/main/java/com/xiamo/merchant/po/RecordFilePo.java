package com.xiamo.merchant.po;

/**
 * <dl>
 * <dt>RecordFilePo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/3/14 0014</dd>
 * </dl>
 * 备案文件信息
 * @author chenglitao
 */
public class RecordFilePo {
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
