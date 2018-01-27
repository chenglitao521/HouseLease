package com.xiamo.weixin.dao.impl;

import com.xiamo.common.dao.impl.BaseJdbcMysqlDao;
import com.xiamo.weixin.dao.IWeinXinDao;
import com.xiamo.weixin.po.Token;

import java.sql.Types;

/**
 * <dl>
 * <dt>WinXinDaoImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/24 0024</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class WinXinDaoImpl extends BaseJdbcMysqlDao implements IWeinXinDao {


    /**
     * @param
     * @param
     * @date:2018/1/24 0024 15:39
     * @className:IWeinXinDao
     * @author:chenglitao
     * @description:获得AccessToken
     */
    @Override
    public Token getAccessToken() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT *  FROM HL_TOKEN WHERE 1=1 ORDER BY CREATE_TIME DESC LIMIT 0,1 ");

        return this.queryForObject(sql.toString(), Token.class);

    }

    @Override
    public int insertAccessToken(String accessToken, String expiresIn) {
        StringBuffer sql = new StringBuffer("INSERT INTO HL_TOKEN (ACCESS_TOKEN,EXPIRES_IN,CREATE_TIME) ")
                .append(" VALUES(?,?,NOW())");
        Object[] args = new Object[]{accessToken,Integer.valueOf(expiresIn).intValue()};
        int[] argTypes = new int[]{Types.VARCHAR, Types.INTEGER};
        return this.update(sql.toString(), args, argTypes);
    }

    /**
     * @param accessToken
     * @param expiresIn
     * @date:2018/1/24 0024 16:00
     * @className:IWeinXinDao
     * @author:chenglitao
     * @description:更新Access_token
     */
    @Override
    public int updateAccessToken(String accessToken, String expiresIn) {
        StringBuffer sql = new StringBuffer("UPDATE HL_TOKEN SET ACCESS_TOKEN=?, EXPIRES_IN=?");
        sql.append(" CREATE_TIME= NOW() WHERE ID=?");
        Object[] args = new Object[]{accessToken,Integer.valueOf(expiresIn)};
        int[] argTypes = new int[]{Types.VARCHAR,  Types.INTEGER};
        return this.update(sql.toString(), args, argTypes);
    }
}
