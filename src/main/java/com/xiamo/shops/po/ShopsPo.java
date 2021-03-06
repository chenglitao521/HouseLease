package com.xiamo.shops.po;

import com.xiamo.common.po.ImgPo;
import com.xiamo.common.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

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
    private Integer merchantId;
    private String name;//商铺名称
    private String position;//商铺位置
    private Integer area;//商铺面积
    private Integer status;//商铺状态
    private String expireTime;//到期时间
    private Integer floor;//楼层
    private Integer structure;//构造

    private List<LeasePo> rentType;//租赁类型
    private String leaseTime;//租赁时间
    private String leaseMoney;//租赁金额

    private String descp;//描述
    private List<ImgPo> recordFiles;
    private String photoUrl;//图片地址
    private String qrCodeUrl;//二维码地址

    private ImgPo qrCode;  //二维码
    private String coordinate;//商铺坐标
    private Integer classifyId; //分类ID
    private String catalog;//一级目录
    private String catalog1;//二级目录
    private Integer high;//层高
    private String[] santong;//水电气
    private Integer electricType;//电类型

    private Integer attribute;//房子属性
    private String lessee;//承租人姓名
    private String lessseTel;//承租人电话

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(String leaseTime) {
        this.leaseTime = leaseTime;
    }

    public String getLeaseMoney() {
        return leaseMoney;
    }

    public void setLeaseMoney(String leaseMoney) {
        this.leaseMoney = leaseMoney;
    }

    public List<ImgPo> getRecordFiles() {
        return recordFiles;
    }

    public void setRecordFiles(List<ImgPo> recordFiles) {
        this.recordFiles = recordFiles;
    }

    public ImgPo getQrCode() {
        return qrCode;
    }

    public void setQrCode(ImgPo qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getStructure() {
        return structure;
    }

    public void setStructure(Integer structure) {
        this.structure = structure;
    }


    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public List<LeasePo> getRentType() {
        return rentType;
    }

    public void setRentType(List<LeasePo> rentType) {
        this.rentType = rentType;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalog1() {
        return catalog1;
    }

    public void setCatalog1(String catalog1) {
        this.catalog1 = catalog1;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public String getSantong() {
        return StringUtil.arrayToStrWithComma(this.santong);
    }

    public void setSantong(String[] santong) {
        this.santong = santong;
    }

    public Integer getElectricType() {
        return electricType;
    }

    public void setElectricType(Integer electricType) {
        this.electricType = electricType;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public String getLessseTel() {
        return lessseTel;
    }

    public void setLessseTel(String lessseTel) {
        this.lessseTel = lessseTel;
    }
}
