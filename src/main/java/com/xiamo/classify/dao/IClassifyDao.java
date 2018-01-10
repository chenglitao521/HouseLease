package com.xiamo.classify.dao;

import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.common.vo.PageInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <dl>
 * <dt>IClassifyDao</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 程立涛</dd>
 * <dd>CreateDate: 2018/1/8</dd>
 * </dl>
 *
 * @author CLT
 */
public interface IClassifyDao {
    List<ClassifyPo> query(ClassifyPo classifyPo, PageInfo pageInfo)throws DataAccessException;

    int add(ClassifyPo classifyPo)throws DataAccessException ;

    int delete(Integer id)throws DataAccessException;

    int update(ClassifyPo classifyPo)throws DataAccessException;
}
