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
    public List<ShopsPo> query(ShopsPo po, String date, PageInfo pageInfo) throws DataAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM HL_SHOPS  WHERE 1=1 ");

        if (StringUtils.isNotBlank(po.getName())) {
            sql.append(" AND ( NAME LIKE '%" + po.getName() + "%'");
        }
        if (StringUtils.isNotBlank(po.getCatalog())) {
            sql.append(" OR CATALOG LIKE '%" + po.getName() + "%'");
        }
        if (StringUtils.isNotBlank(po.getCatalog1())) {
            sql.append(" OR CATALOG1 LIKE '%" + po.getName() + "%')");
        }
        /*
        *
        *
        * 		dateOptions:[{value: 'day-7',label: '最近7天'},
							 {value: 'day-15',label: '最近15天'},
							 {value: 'mon-1',label: '最近1个月'},
		        			 {value: 'mon-3',label: '最近3个月'},
		        			 {value: 'mon-6',label: '最近6个月'}],
        * */
        if (StringUtils.isNotBlank(date)) {
            if ("day-7".equals(date)) {
                sql.append(" AND 0<DATEDIFF(DATE(now()), EXPIRE_TIME) <=7");
            } else if ("day-15".equals(date)) {
                sql.append(" AND 0<DATEDIFF(DATE(now()), EXPIRE_TIME) <=15");
            } else if ("mon-1".equals(date)) {
                sql.append(" AND 0<DATEDIFF(DATE(now()), EXPIRE_TIME) <=30");
            } else if ("mon-3".equals(date)) {
                sql.append(" AND 0<DATEDIFF(DATE(now()), EXPIRE_TIME) <=(30*3)");
            } else if ("mon-6".equals(date)) {
                sql.append(" AND 0<DATEDIFF(DATE(now()), EXPIRE_TIME) <=(30*6)");
            }
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
                po.getStructure(), po.getDescp(), po.getId()};
        int[] argTypes = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.DATE, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        return this.update(sql.toString(), args, argTypes);
    }

    public int add(ShopsPo po) throws DataAccessException {
        StringBuffer sql = new StringBuffer("INSERT INTO HL_SHOPS (MERCHANTID,NAME,POSITION,AREA,STATUS,EXPIRE_TIME,FLOOR" +
                ",STRUCTURE,LEASE_TIME,LEASE_MONEY,DESCP,PHOTO_URL,COORDINATE,CATALOG,CATALOG1,HIGH,SANTONG,ELECTRIC_TYPE,ATTRIBUTE,LESSEE" +
                ",LESSEE_TEL,QRCODE_URL) ")
                .append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Object[] args = new Object[]{po.getMerchantId(), po.getName(), po.getPosition(), po.getArea(), po.getStatus(), po.getExpireTime(), po.getFloor(),
                po.getStructure(), po.getLeaseTime(), po.getLeaseMoney(), po.getDescp(), po.getPhotoUrl(), po.getCoordinate(), po.getCatalog(),
                po.getCatalog1(), po.getHigh(), po.getSantong(), po.getElectricType(), po.getAttribute(), po.getLessee(), po.getLessseTel(), po.getQrCodeUrl()};
        int[] argTypes = new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER
                , Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
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
