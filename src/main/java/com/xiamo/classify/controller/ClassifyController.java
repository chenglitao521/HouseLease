package com.xiamo.classify.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.classify.po.ClassifyVo;
import com.xiamo.classify.service.IClassifyService;
import com.xiamo.common.po.ServiceException;
import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/testHttpMessageConverter")
    public ClassifyVo testHttpMessageConverter(@RequestBody ClassifyPo body) {
        System.out.println(body.getId());
        ClassifyVo ClassifyVo = new ClassifyVo();
        ClassifyVo.setId(1);
        ClassifyVo.setName("程立涛");
        return ClassifyVo;
    }

    /**
     * @date:2018/1/10 0010
     * @className:ClassifyController
     * @author:chenglitao
     * @description:分页查询分类
     */
    @ResponseBody
    @RequestMapping("/query")
    public AjaxResultPo query(@RequestBody  ClassifyPo classifyPo,HttpServletRequest request) {
        logger.info("进入ClassifyController.query方法");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            List<ClassifyPo> list = classifyServiceImpl.query(classifyPo);
            String contextPath = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                    request.getServerPort()+contextPath+ FileUpload.FILE_PATH;

            List<ClassifyVo> result = new ArrayList<>();
            for (ClassifyPo po : list) {
                ClassifyVo vo = new ClassifyVo();

                vo.setId(po.getId());
                vo.setName(po.getCatalog());
                Map<String, Object> iconMap = new HashMap<>();

                iconMap.put("name", po.getCatalog());
                iconMap.put("src",basePath+po.getIconUrl());
                vo.setIcon(iconMap);


                List<Map<String, Object>> subName = getSubName(basePath,po);
                vo.setSubName(subName);

                result.add(vo);
            }
            res.setTotal(result.size());
            res.setRows(result);
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

    private List<Map<String, Object>> getSubName(String basePath, ClassifyPo po) {
        List<Map<String, Object>> subName = new ArrayList<>();

        if(StringUtils.isNotBlank(po.getCatalog1())){
            Map<String, Object> temp1 = new HashMap<>();
            temp1.put("name", po.getCatalog1());
            Map<String, Object> file1 = new HashMap<>();
            file1.put("name", po.getCatalog1());
            file1.put("src", basePath+po.getIconUrl1());
            temp1.put("files", file1);
            subName.add(temp1);
        }

        if(StringUtils.isNotBlank(po.getCatalog2())){
            Map<String, Object> temp2 = new HashMap<>();
            temp2.put("name", po.getCatalog2());
            Map<String, Object> file2 = new HashMap<>();
            file2.put("name", po.getCatalog2());
            file2.put("src", basePath+po.getIconUrl2());
            temp2.put("files", file2);
            subName.add(temp2);
        }
        if(StringUtils.isNotBlank(po.getCatalog3())){
            Map<String, Object> temp3 = new HashMap<>();
            temp3.put("name", po.getCatalog3());
            Map<String, Object> file3 = new HashMap<>();
            file3.put("name", po.getCatalog3());
            file3.put("src",basePath+ po.getIconUrl3());
            temp3.put("files", file3);
            subName.add(temp3);
        }

        if(StringUtils.isNotBlank(po.getCatalog4())){
            Map<String, Object> temp4 = new HashMap<>();
            temp4.put("name", po.getCatalog4());
            Map<String, Object> file4 = new HashMap<>();
            file4.put("name", po.getCatalog4());
            file4.put("src", basePath+po.getIconUrl4());
            temp4.put("files", file4);
            subName.add(temp4);
        }
        if(StringUtils.isNotBlank(po.getCatalog5())){
            Map<String, Object> temp5 = new HashMap<>();
            temp5.put("name", po.getCatalog5());
            Map<String, Object> file5 = new HashMap<>();
            file5.put("name", po.getCatalog5());
            file5.put("src", basePath+po.getIconUrl5());
            temp5.put("files", file5);
            subName.add(temp5);
        }
        return subName;
    }

    /**
     * @date:2018/1/10 0010
     * @className:ClassifyController
     * @author:chenglitao
     * @description:添加分类
     */
    @ResponseBody
    @RequestMapping("/add")
    public AjaxResultPo add(@RequestBody String param, HttpServletRequest request) throws IOException {

        logger.info("进入ClassifyController.add方法，classifyPo={}", JsonUtils.toJson(param));

        try {
            int r = classifyServiceImpl.add(param, request);

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
    public AjaxResultPo update(@RequestBody String param, HttpServletRequest request) throws IOException {
        logger.info("进入ClassifyController.update方法，classifyPo={}", JsonUtils.toJson(param));

        try {
            int r = classifyServiceImpl.update(param, request);
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
    public AjaxResultPo delete(@RequestBody String  param) throws IOException {
        JSONObject jo = JSON.parseObject(param);
        logger.info("进入ClassifyController.delete方法，id={}", param);

        try {
            if(jo==null||jo.getInteger("id")==null){
                return AjaxResultPo.failure("删除分类信息失败");
            }
            int r = classifyServiceImpl.delete(jo.getInteger("id"));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("删除分类信息失败");

        }
        return AjaxResultPo.successDefault();
    }
}
