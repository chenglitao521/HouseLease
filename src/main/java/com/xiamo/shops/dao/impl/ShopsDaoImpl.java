package com.xiamo.shops.dao.impl;

import com.xiamo.common.dao.impl.BaseJdbcMysqlDao;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.dao.IShopsDao;
import com.xiamo.shops.po.ShopsPo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <dl>
 * <dt>ShopsDaoImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/11 0011</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class ShopsDaoImpl extends BaseJdbcMysqlDao implements IShopsDao {
    public List<ShopsPo> query(ShopsPo po, PageInfo pageInfo) throws DataAccessException {
        return null;
    }

    public int update(ShopsPo po) throws DataAccessException {
        return 0;
    }

    public int add(ShopsPo po) throws DataAccessException {
        return 0;
    }

    public int delete(Integer id) throws DataAccessException {
        return 0;
    }
}
