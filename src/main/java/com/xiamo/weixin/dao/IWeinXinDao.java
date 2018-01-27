package com.xiamo.weixin.dao;

import com.xiamo.weixin.po.Token;

/**
 * <dl>
 * <dt>IWeinXinDao</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/24 0024</dd>
 * </dl>
 *
 * @author chenglitao
 */
public interface IWeinXinDao {

    /**
     * @date:2018/1/24 0024 15:39
     * @className:IWeinXinDao
     * @author:chenglitao
     * @description:获得AccessToken
     */
    Token getAccessToken();

    /**
     * @date:2018/1/24 0024 15:57
     * @className:IWeinXinDao
     * @author:chenglitao
     * @description:添加AccessToken
     *
     */
    int insertAccessToken(String accessToken ,String expiresIn);

    /**
     * @date:2018/1/24 0024 16:00
     * @className:IWeinXinDao
     * @author:chenglitao
     * @description:更新Access_token
     *
     */
    int updateAccessToken(String accessToken ,String expiresIn);
}
