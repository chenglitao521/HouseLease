package com.xiamo.weixin.po.event;

/**
 * <dl>
 * <dt>MenuEvent</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 * 自定义菜单事件
 *
 * @author chenglitao
 */
public class MenuEvent extends BaseEvent {
    // 事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}