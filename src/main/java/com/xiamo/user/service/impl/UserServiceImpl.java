package com.xiamo.user.service.impl;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.user.dao.IUserDao;
import com.xiamo.user.po.UserPo;
import com.xiamo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <dl>
 * <dt>UserServiceImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/2 0002</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserDao userDaoImpl;

    public List<UserPo> query(UserPo po, PageInfo pageInfo) {
        List<UserPo> userPos =userDaoImpl.query(po,pageInfo);
        return userPos;
    }
}
