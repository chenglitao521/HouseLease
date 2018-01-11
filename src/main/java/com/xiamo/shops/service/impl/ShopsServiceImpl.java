package com.xiamo.shops.service.impl;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.dao.IShopsDao;
import com.xiamo.shops.po.ShopsPo;
import com.xiamo.shops.service.IShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @date:2018/1/11 0011 20:10
 * @className:ShopsServiceImpl
 * @author:chenglitao
 * @description:商铺接口
 */
public class ShopsServiceImpl implements IShopsService {

    @Autowired
    IShopsDao shopsDaoImpl;

    public List<ShopsPo> query(ShopsPo po, PageInfo pageInfo) {
        List<ShopsPo> shopsPos = null;
        try {
            shopsPos = shopsDaoImpl.query(po, pageInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return shopsPos;
    }

    public int update(ShopsPo po) {


        try {
            return shopsDaoImpl.update(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int add(ShopsPo po) {
        try {
            return shopsDaoImpl.add(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(Integer id) {
        try {
            return shopsDaoImpl.delete(id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
