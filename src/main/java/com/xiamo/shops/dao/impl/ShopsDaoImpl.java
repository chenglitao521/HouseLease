package com.xiamo.shops.dao.impl;

import com.xiamo.common.dao.impl.BaseJdbcMysqlDao;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.dao.IShopsDao;
import com.xiamo.shops.po.ShopsPo;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

/**
 * @date:2018/1/12 0012 17:26
 * @className:ShopsDaoImpl
 * @author:chenglitao
 * @description:商铺接口
 */
@Repository
public class ShopsDaoImpl extends BaseJdbcMysqlDao implements IShopsDao {
    @Override
    public List<ShopsPo> query(ShopsPo po, PageInfo pageInfo) throws DataAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM HL_SHOPS  WHERE 1=1 ");

        if(StringUtils.isNotBlank(po.getName())){
            sql.append(" AND NAME LIKE '%"+po.getName()+"%'");
        }
        if(StringUtils.isNotBlank(po.getCatalog())){
            sql.append(" AND CATALOG LIKE '%"+po.getCatalog()+"%'");
        }
        if(StringUtils.isNotBlank(po.getCatalog1())){
            sql.append(" AND CATALOG1 LIKE '%"+po.getCatalog1()+"%'");
        }
        if (pageInfo != null && pageInfo.getResults() > 0) {
            return this.queryByPage(sql.toString(), pageInfo, ShopsPo.class);
        } else {
            return this.queryForList(sql.toString(), ShopsPo.class);
        }
    }

    public int update(ShopsPo po) throws DataAccessException {
        StringBuffer sql = new StringBuffer("UPDATE HL_SHOPS SET NAME=?, POSITION=?,AREA=?,STATUS=?,EXPIRE_TIME=?,FLOOR=?,STRUCTURE=?," +
                "LEASE_TIME=? ,LEASE_MONEY=?,DESCP=?,PHOTO_URL=?,");
        sql.append(" UPDATE_TIME= NOW() WHERE ID=?");
        Object[] args = new Object[]{po.getName(), po.getPosition(), po.getArea(), po.getStatus(), po.getExpireTime(), po.getFloor(),
                po.getStructure(), po.getLeaseTime(), po.getLeaseMoney(), po.getDescp(), po.getPhotoUrl(), po.getId()};
        int[] argTypes = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.DATE, Types.INTEGER, Types.VARCHAR,
                Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        return this.update(sql.toString(), args, argTypes);
    }

    public int add(ShopsPo po) throws DataAccessException {
        StringBuffer sql = new StringBuffer("INSERT INTO HL_SHOPS (NAME,POSITION,AREA,STATUS,EXPIRE_TIME,FLOOR" +
                ",STRUCTURE,LEASE_TIME,LEASE_MONEY,DESCP,PHOTO_URL) ")
                .append(" VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        Object[] args = new Object[]{po.getName(), po.getPosition(), po.getArea(), po.getStatus(), po.getExpireTime(), po.getFloor(),
                po.getStructure(), po.getLeaseTime(), po.getLeaseMoney(), po.getDescp(), po.getPhotoUrl()};
        int[] argTypes = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.DATE, Types.INTEGER, Types.VARCHAR,
                Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR};
        return this.update(sql.toString(), args, argTypes);
    }

    public int delete(Integer id) throws DataAccessException {
        String sql = "DELETE FROM HL_SHOPS WHERE ID=?";
        return this.update(sql, new Object[]{id}, new int[]{Types.INTEGER});
    }

    public int deleteByClassifyId(Integer id) throws DataAccessException {
        String sql = "DELETE FROM HL_SHOPS WHERE ID=?";
        return this.update(sql, new Object[]{id}, new int[]{Types.INTEGER});
    }
}
