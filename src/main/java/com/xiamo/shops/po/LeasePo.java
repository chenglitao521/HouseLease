package com.xiamo.shops.po;

/**
 * <dl>
 * <dt>LeasePo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/3/21 0021</dd>
 * </dl>
 *  租赁信息对象
 * @author chenglitao
 */
public class LeasePo {

    private Integer type;//按月租或年
    private Integer time; //时间
    private Integer money;//租金

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
