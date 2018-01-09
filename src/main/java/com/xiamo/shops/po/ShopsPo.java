package com.xiamo.shops.po;

import java.io.Serializable;
import java.util.Date;

/**
 * <dl>
 * <dt>ShopsPo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/8</dd>
 * </dl>
 * 商铺
 *
 * @author CLT
 */
public class ShopsPo implements Serializable {
    private Integer id;
    private String name;//商铺名称
    private String position;//商铺位置
    private Integer area;//商铺面积
    private Integer status;//商铺状态
    private Date expireTime;//到期时间
    private  Integer floor;//楼层
    private String structure;//构造
    private Integer leaseTime;//租期
    private Integer leaseMoney;//租金
    private String descp;//描述
    private String photoUrl;//图片地址


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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(Integer leaseTime) {
        this.leaseTime = leaseTime;
    }

    public Integer getLeaseMoney() {
        return leaseMoney;
    }

    public void setLeaseMoney(Integer leaseMoney) {
        this.leaseMoney = leaseMoney;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
