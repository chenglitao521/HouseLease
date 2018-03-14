package com.xiamo.merchant.dao.impl;

import com.xiamo.common.dao.impl.BaseJdbcMysqlDao;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.dao.IMerchantDao;
import com.xiamo.merchant.po.MerchantPo;
import org.springframework.dao.DataAccessException;

import java.sql.Types;
import java.util.List;

/**
 * <dl>
 * <dt>MerchantDaoImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/10 0010</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class MerchantDaoImpl extends BaseJdbcMysqlDao implements IMerchantDao {
    public List<MerchantPo> query(MerchantPo po, PageInfo pageInfo) throws DataAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT *  FROM HL_MERCHANT ");

        if (pageInfo != null && pageInfo.getResults() > 0) {
            return this.queryByPage(sql.toString(), pageInfo, MerchantPo.class);
        } else {
            return this.queryForList(sql.toString(), MerchantPo.class);
        }
    }

    public int add(MerchantPo po) throws DataAccessException {
        StringBuffer sql = new StringBuffer("INSERT INTO HL_MERCHANT (NAME,NUM,ADDRESS,TELEPHONE,STATUS" +
                ",CONTACT_NAME,IMAGE_URL1,IMAGE_URL2,IMAGE_URL3) ")
                .append(" VALUES(?,?,?,?,?,?,?,?,?)");
        Object[] args = new Object[]{po.getName(), po.getNum(), po.getAddress(), po.getTelephone(), po.getStatus(),
                po.getContactName(), po.getImageUrl1(), po.getImageUrl2(), po.getImageUrl3()};
        int[] argTypes = new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        return this.update(sql.toString(), args, argTypes);
    }

    public int update(MerchantPo po) throws DataAccessException {
        StringBuffer sql = new StringBuffer("UPDATE HL_MERCHANT SET NAME=?,NUM=?,ADDRESS=?,TELEPHONE=?,STATUS=?" +
                ",CONTACT_NAME=?,IMAGE_URL1=?,IMAGE_URL2=?,IMAGE_URL3=?");

        sql.append(", UPDATE_TIME= NOW() WHERE ID=?");
        Object[] args = new Object[]{po.getName(), po.getNum(), po.getAddress(), po.getTelephone(), po.getStatus(), po.getContactName(),
                po.getImageUrl1(), po.getImageUrl2(), po.getImageUrl3(), po.getId()};

        int[] argTypes = new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        return this.update(sql.toString(), args, argTypes);
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM HL_MERCHANT WHERE ID=?";
        return this.update(sql, new Object[]{id}, new int[]{Types.INTEGER});
    }
}
