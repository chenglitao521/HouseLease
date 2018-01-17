package com.xiamo.weixin.service.impl;

import javax.servlet.http.HttpServletRequest;

/**
 * <dl>
 * <dt>IWeiXinService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 *
 * @author chenglitao
 */
public interface IWeiXinService {
    String processRequest(HttpServletRequest request);
}
