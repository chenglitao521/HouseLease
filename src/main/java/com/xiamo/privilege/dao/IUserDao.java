package com.xiamo.privilege.dao;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.privilege.po.UserPo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <dl>
 * <dt>IUserDao</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 程立涛</dd>
 * <dd>CreateDate: 2018/1/2 0002</dd>
 * </dl>
 *
 * @author chenglitao
 */
public interface IUserDao {
    List<UserPo> query(UserPo po, PageInfo pageInfo)throws DataAccessException;

    UserPo loginByName(String loginName)throws DataAccessException;
}
