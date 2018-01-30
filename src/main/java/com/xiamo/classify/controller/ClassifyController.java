package com.xiamo.classify.controller;

import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.classify.service.IClassifyService;
import com.xiamo.common.po.ServiceException;
import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /**
     * @date:2018/1/10 0010
     * @className:ClassifyController
     * @author:chenglitao
     * @description:分页查询分类
     */
    @ResponseBody
    @RequestMapping("/query")
    public AjaxResultPo query(Integer page, Integer rows, ClassifyPo classifyPo) {
        logger.info("进入ClassifyController.query方法");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            List<ClassifyPo> list = classifyServiceImpl.query(classifyPo);
            res.setTotal(list.size());
            res.setRows(list);
        } catch (ServiceException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
            return AjaxResultPo.failure("查询分类信息失败");

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("查询分类信息失败");

        }
        return res;
    }

    /**
     * @date:2018/1/10 0010
     * @className:ClassifyController
     * @author:chenglitao
     * @description:添加分类
     */
    @ResponseBody
    @RequestMapping("/add")
    public AjaxResultPo add(ClassifyPo classifyPo, HttpServletRequest request) throws IOException {
        logger.info("进入ClassifyController.add方法，classifyPo={}", JsonUtils.toJson(classifyPo));

        try {
            int r = classifyServiceImpl.add(classifyPo, request);

        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResultPo.failure(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("添加分类信息失败");

        }

        return AjaxResultPo.successDefault();
    }

    /**
     * @date:2018/1/10 0010
     * @className:ClassifyController
     * @author:chenglitao
     * @description:更新分类
     */
    @ResponseBody
    @RequestMapping("/update")
    public AjaxResultPo update(ClassifyPo classifyPo, HttpServletRequest request) throws IOException {
        logger.info("进入ClassifyController.update方法，classifyPo={}", JsonUtils.toJson(classifyPo));

        try {
            int r = classifyServiceImpl.update(classifyPo,request);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("更新分类信息失败");

        }
        return AjaxResultPo.successDefault();
    }

    /**
     * @date:2018/1/10 0010
     * @className:ClassifyController
     * @author:chenglitao
     * @description:删除分类
     */
    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResultPo delete(Integer id) throws IOException {
        logger.info("进入ClassifyController.delete方法，id={}", id);

        try {
            int r = classifyServiceImpl.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("删除分类信息失败");

        }
        return AjaxResultPo.successDefault();
    }
}
