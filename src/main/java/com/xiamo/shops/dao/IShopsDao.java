package com.xiamo.shops.dao;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.po.ShopsPo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @date:2018/1/11 0011 20:10
 * @className:IShopsDao
 * @author:chenglitao
 * @description:商铺接口
 */
public interface IShopsDao {
    List<ShopsPo> query(ShopsPo po, PageInfo pageInfo) throws DataAccessException;

    int update(ShopsPo po) throws DataAccessException;

    int add(ShopsPo po) throws DataAccessException;

    int delete(Integer id) throws DataAccessException;
}
