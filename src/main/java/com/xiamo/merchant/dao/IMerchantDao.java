package com.xiamo.merchant.dao;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.po.MerchantPo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @date:2018/1/10 0010 15:27
 * @className:IMerchantDao
 * @author:chenglitao
 * @description:商户接口
 */
public interface IMerchantDao {
    List<MerchantPo> query(MerchantPo po, PageInfo pageInfo) throws DataAccessException;

    int add(MerchantPo po) throws DataAccessException;

    int update(MerchantPo po) throws DataAccessException;

    int delete(Integer id);

}
