package com.xiamo.shops.service;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.po.ShopsPo;

import java.util.List;

/**
 * @date:2018/1/11 0011 20:10
 * @className:IShopsService
 * @author:chenglitao
 * @description:商铺接口
 */
public interface IShopsService {
    List<ShopsPo> query(ShopsPo po, PageInfo pageInfo);

    int update(ShopsPo po);

    int add(ShopsPo po);

    int delete(Integer id);

    /**
     * @date:2018/1/14 0014 9:42
     * @className:IShopsService
     * @author:chenglitao
     * @description:删除某一类商铺
     */
    int deleteByClassifyId(Integer id);
}
