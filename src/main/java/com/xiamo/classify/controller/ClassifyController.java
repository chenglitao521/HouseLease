package com.xiamo.classify.controller;

import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.classify.service.IClassifyService;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.common.vo.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <dl>
 * <dt>ClassifyController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 *
 * @author CLT
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController {

    private static final Logger logger = LoggerFactory.getLogger(ClassifyController.class);

    @Autowired
    IClassifyService classifyServiceImpl;

    @ResponseBody
    @RequestMapping("/query")
    public AjaxResultPo query(Integer page, Integer rows, ClassifyPo classifyPo) {
        logger.info("进入ClassifyController.query方法");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            PageInfo pageInfo = null;
            if (page > 0) {
                pageInfo = new PageInfo((page - 1) * rows, rows);
            }

            List<ClassifyPo> list = classifyServiceImpl.query(classifyPo, pageInfo);
            if (page > 0) {
                res.setTotal(pageInfo.getTotalRecords());
            } else {
                res.setTotal(list.size());
            }

            res.setRows(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("查询分类信息失败");

        }
        return res;
    }

    @ResponseBody
    @RequestMapping("/add")
    public AjaxResultPo add(ClassifyPo classifyPo) {
        logger.info("进入ClassifyController.add方法");

        try {

            int r = classifyServiceImpl.add(classifyPo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("添加分类信息失败");

        }
        return AjaxResultPo.successDefault();
    }
}
