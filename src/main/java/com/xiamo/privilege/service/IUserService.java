package com.xiamo.privilege.service;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.privilege.po.UserPo;

import java.util.List;

/**
 * <dl>
 * <dt>IUserService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 程立涛</dd>
 * <dd>CreateDate: 2018/1/2 0002</dd>
 * </dl>
 *
 * @author chenglitao
 */
public interface IUserService {

    /**
    *@Author:chenglitao
    *@Description
    *
    *@Date:17:23 2018/1/6
    *
    **/
    List<UserPo> query(UserPo po, PageInfo pageInfo);

    /**
    *@Author:chenglitao
    *
    *@Description:根据登录名查询用户信息
    *
    *@Date:17:29 2018/1/6
    *
    **/
    UserPo loginByName(String loginName);
}
