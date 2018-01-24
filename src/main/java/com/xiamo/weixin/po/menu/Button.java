package com.xiamo.weixin.po.menu;

/**
 * <dl>
 * <dt>Button</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/24 0024</dd>
 * </dl>
 * 菜单项基类
 *
 * @author chenglitao
 */
public class Button {

    private String name;//所有一级菜单、二级菜单都共有一个相同的属性，那就是name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
