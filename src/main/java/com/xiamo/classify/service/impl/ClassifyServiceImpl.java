package com.xiamo.classify.service.impl;

import com.xiamo.classify.dao.IClassifyDao;
import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.classify.service.IClassifyService;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.service.IShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <dl>
 * <dt>ClassifyServiceImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 *
 * @author CLT
 */
public class ClassifyServiceImpl implements IClassifyService {
    @Autowired
    IShopsService shopsServiceImpl;

    @Autowired
    IClassifyDao classifyDaoImpl;
    public List<ClassifyPo> query(ClassifyPo classifyPo, PageInfo pageInfo) {

        List<ClassifyPo> classifyPos = null;
        try {
            classifyPos = classifyDaoImpl.query(classifyPo, pageInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return classifyPos;
    }

    public int add(ClassifyPo classifyPo) {
        try {
             return classifyDaoImpl.add(classifyPo);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(ClassifyPo classifyPo) {
        try {
            return classifyDaoImpl.update(classifyPo);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(Integer id) {
        try {
           int j= shopsServiceImpl.deleteByClassifyId(id);
            return classifyDaoImpl.delete(id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
