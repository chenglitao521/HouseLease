package com.xiamo.user.service;

import com.xiamo.common.vo.PageInfo;
import com.xiamo.user.po.UserPo;

import java.util.List;

/**
 * <dl>
 * <dt>IUserService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 程立涛</dd>
 * <dd>CreateDate: 2018/1/2 0002</dd>
 * </dl>
 *
 * @author chenglitao
 */
public interface IUserService {
    List<UserPo> query(UserPo po, PageInfo pageInfo);
}
