package com.xiamo.user.dao.impl;

import com.xiamo.common.dao.impl.BaseJdbcMysqlDao;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.user.dao.IUserDao;
import com.xiamo.user.po.UserPo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <dl>
 * <dt>UserDaoImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/2 0002</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class UserDaoImpl extends BaseJdbcMysqlDao implements IUserDao {
    public List<UserPo> query(UserPo po, PageInfo pageInfo)throws DataAccessException {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT *  FROM CFG_USER ");

        return this.queryForList(sql.toString(),UserPo.class);
    }
}
