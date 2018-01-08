package com.xiamo.classify.service;

import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.common.vo.PageInfo;

import java.util.List;

/**
 * <dl>
 * <dt>IClassifyService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 *
 * @author CLT
 */
public interface IClassifyService {

    /**
     * @Author:chenglitao
     * @Description:分页查询分类
     * @Date:20:57 2018/1/8
     **/
    List<ClassifyPo> query(ClassifyPo classifyPo, PageInfo pageInfo);

    /**
     * @Author:chenglitao
     * @Date:21:00 2018/1/8
     * @Description:添加分类
     **/
    int add(ClassifyPo classifyPo);

    /**
     * @Author:chenglitao
     * @Date:21:01 2018/1/8
     * @Description:更新分类
     **/
    int update(ClassifyPo classifyPo);


    /**
     * @Author:chenglitao
     * @Date:21:01 2018/1/8
     * @Description: 删除分类
     **/
    int delete(String id);
}
