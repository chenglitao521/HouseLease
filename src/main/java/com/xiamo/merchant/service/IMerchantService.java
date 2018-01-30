package com.xiamo.merchant.service;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.po.MerchantPo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <dl>
 * <dt>IMerchantService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/10 0010</dd>
 * </dl>
 * 商户接口
 *
 * @author chenglitao
 */
public interface IMerchantService {
    int delete(Integer id);

    List<MerchantPo> query(MerchantPo po, PageInfo pageInfo);

    int add(MerchantPo po, HttpServletRequest request);

    int update(MerchantPo po, HttpServletRequest request);
}
