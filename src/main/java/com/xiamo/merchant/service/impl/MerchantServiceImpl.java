package com.xiamo.merchant.service.impl;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.dao.IMerchantDao;
import com.xiamo.merchant.po.MerchantPo;
import com.xiamo.merchant.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @date:2018/1/10 0010 15:27
 * @className:MerchantServiceImpl
 * @author:chenglitao
 * @description:商户接口
 *
 */
public class MerchantServiceImpl implements IMerchantService {
    @Autowired
    IMerchantDao merchantDaoImpl;

    public int delete(Integer id) {
        try {
            return merchantDaoImpl.delete(id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<MerchantPo> query(MerchantPo po, PageInfo pageInfo) {
        List<MerchantPo> merchantPos = null;
        try {
            merchantPos = merchantDaoImpl.query(po, pageInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return merchantPos;
    }

    public int add(MerchantPo po) {
        try {
            return merchantDaoImpl.add(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(MerchantPo po) {
        try {
            return merchantDaoImpl.update(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
