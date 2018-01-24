package com.xiamo.weixin.service;

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
    /**
     * @date:2018/1/24 0024 16:52
     * @className:IWeiXinService
     * @author:chenglitao
     * @description: 处理微信发来的请求
     *
     */
    String processRequest(HttpServletRequest request);

    /**
     * @date:2018/1/24 0024 17:08
     * @className:IWeiXinService
     * @author:chenglitao
     * @description:创建菜单，0表示成功
     *
     */
    int createMenu();
}
