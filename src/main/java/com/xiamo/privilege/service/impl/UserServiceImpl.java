package com.xiamo.privilege.service.impl;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.privilege.dao.IUserDao;
import com.xiamo.privilege.po.UserPo;
import com.xiamo.privilege.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

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
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao userDaoImpl;

    public List<UserPo> query(UserPo po, PageInfo pageInfo) {

        List<UserPo> userPos = null;
        try {
            userPos = userDaoImpl.query(po, pageInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return userPos;
    }

    public UserPo loginByName(String loginName) {
        UserPo user = null;
        try {
            user = userDaoImpl.loginByName(loginName);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
