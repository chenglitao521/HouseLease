package com.xiamo.merchant.po;

import java.util.List;

/**
 * <dl>
 * <dt>MerchantVo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/3/14 0014</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class MerchantVo {
    private Integer id;
    private String name;
    private String concats;
    private String addr;
    private String tel;
    private Integer state;

    private Integer shopNum;

    private List<RecordFilePo> recordFiles;


    public Integer getShopNum() {
        return shopNum;
    }

    public void setShopNum(Integer shopNum) {
        this.shopNum = shopNum;
    }

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

    public String getConcats() {
        return concats;
    }

    public void setConcats(String concats) {
        this.concats = concats;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }



    public List<RecordFilePo> getRecordFiles() {
        return recordFiles;
    }

    public void setRecordFiles(List<RecordFilePo> recordFiles) {
        this.recordFiles = recordFiles;
    }
}
