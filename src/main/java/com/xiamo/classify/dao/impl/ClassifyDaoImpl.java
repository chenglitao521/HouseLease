package com.xiamo.classify.dao.impl;

import com.xiamo.classify.dao.IClassifyDao;
import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.common.dao.impl.BaseJdbcMysqlDao;
import com.xiamo.common.vo.PageInfo;
import org.springframework.dao.DataAccessException;

import java.sql.Types;
import java.util.List;

/**
 * <dl>
 * <dt>ClassifyDaoImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/8</dd>
 * </dl>
 *
 * @author CLT
 */
public class ClassifyDaoImpl extends BaseJdbcMysqlDao implements IClassifyDao {
    public List<ClassifyPo> query(ClassifyPo classifyPo, PageInfo pageInfo) throws DataAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT *  FROM HL_CLASSIFY ");

        if (pageInfo != null && pageInfo.getResults() > 0) {
            return this.queryByPage(sql.toString(), pageInfo, ClassifyPo.class);
        } else {
            return this.queryForList(sql.toString(), ClassifyPo.class);
        }
    }

    public int add(ClassifyPo po) throws DataAccessException {
        StringBuffer sql = new StringBuffer("INSERT INTO HL_CLASSIFY (NAME,CATALOG,ICON_URL,CATALOG1,ICON_URL1,,CATALOG2,ICON_URL2," +
                "CATALOG3,ICON_URL3,CATALOG4,ICON_URL4,CATALOG5,ICON_URL5,SORT ) ")
                .append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Object[] args = new Object[]{po.getName(), po.getCatalog(), po.getIconUrl(), po.getCatalog1(), po.getIconUrl1(), po.getCatalog2(),
                po.getIconUrl2(), po.getCatalog3(), po.getIconUrl3(), po.getCatalog4(), po.getIconUrl4(), po.getCatalog5(), po.getIconUrl5(), po.getSort()};
        int[] argTypes = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

        return this.update(sql.toString(), args, argTypes);
    }

    public int delete(Integer id) throws DataAccessException {
        String sql = "DELETE FROM HL_CLASSIFY WHERE ID=?";
        return this.update(sql, new Object[]{id}, new int[]{Types.INTEGER});
    }

    public int update(ClassifyPo po) throws DataAccessException {
        StringBuffer sql = new StringBuffer("UPDATE HL_CLASSIFY SET NAME=?, CATALOG=?,ICON_URL=?,CATALOG1=?,ICON_URL1=?,CATALOG2=?,ICON_URL2=?," +
                "CATALOG3=?,ICON_URL3=?,CATALOG4=?,ICON_URL4=?,CATALOG5=?,ICON_URL5=?,SORT=? ");

        sql.append(" UPDATE_TIME= NOW() WHERE ID=?");
        Object[] args = new Object[]{po.getName(), po.getCatalog(), po.getIconUrl(), po.getCatalog1(), po.getIconUrl1(), po.getCatalog2(),
                po.getIconUrl2(), po.getCatalog3(), po.getIconUrl3(), po.getCatalog4(), po.getIconUrl4(), po.getCatalog5(), po.getIconUrl5(),
                po.getSort(), po.getId()};
        int[] argTypes = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        return this.update(sql.toString(), args, argTypes);
    }
}
