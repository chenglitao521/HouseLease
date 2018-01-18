package com.xiamo.privilege.dao.impl;

import com.xiamo.common.dao.impl.BaseJdbcMysqlDao;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.privilege.dao.IUserDao;
import com.xiamo.privilege.po.UserPo;
import org.apache.commons.lang3.StringUtils;
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
        sql.append("SELECT *  FROM HL_USER ");

        if (pageInfo != null && pageInfo.getResults() > 0) {
            return this.queryByPage(sql.toString(), pageInfo, UserPo.class);
        } else {
            return this.queryForList(sql.toString(), UserPo.class);
        }
    }

    public UserPo loginByName(String loginName) throws DataAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT *  FROM HL_USER WHERE  1=1 ");
        if (StringUtils.isNotBlank(loginName)){
            sql.append(" AND LOGIN_NAME = '").append(loginName).append("'");
        }

        return this.queryForObject(sql.toString(),UserPo.class);

    }
}
