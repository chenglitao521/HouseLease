package com.xiamo.shops.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.classify.service.IClassifyService;
import com.xiamo.common.po.ImgPo;
import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.po.ShopsPo;
import com.xiamo.shops.service.IShopsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <dl>
 * <dt>UserController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2017/12/25 0025</dd>
 * </dl>
 * 商铺接口
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/shops")
public class ShopsController {
    private static final Logger logger = LoggerFactory.getLogger(ShopsController.class);

    @Autowired
    IShopsService shopsService;

    @Autowired
    IClassifyService classifyServiceImpl;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true));
    }

    /**
     * @date:2018/1/11 0011 20:14
     * @className:ShopsController
     * @author:chenglitao
     * @description:分页查询商铺信息
     */
    @ResponseBody
    @RequestMapping("/query")
    public AjaxResultPo query(Integer page, Integer rows, ShopsPo po,HttpServletRequest request) {
        logger.info("进入ShopsController.query方法");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            PageInfo pageInfo = null;
            if (page > 0) {
                pageInfo = new PageInfo((page - 1) * rows, rows);
            }
            String contextPath = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                    request.getServerPort()+contextPath+ FileUpload.FILE_PATH;
            List<ShopsPo> list = shopsService.query(po, pageInfo);
            if(list!=null&&list.size()>0){

                for (ShopsPo shopsPo :list){
                    String[] photos= shopsPo.getPhotoUrl().split(",");
                    List<ImgPo> imgPoList = new ArrayList<>();
                    for(String ph:photos){
                        ImgPo imgPo = new ImgPo();
                        imgPo.setName(ph);
                        imgPo.setSrc(basePath+ph);
                        imgPoList.add(imgPo);
                    }

                    shopsPo.setRecordFiles(imgPoList);
                }

            }
            if (page > 0) {
                res.setTotal(pageInfo.getTotalRecords());
            } else {
                res.setTotal(list.size());
            }

            res.setRows(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("查询商铺信息失败");

        }
        return res;
    }

    /**
     * @date:2018/1/11 0011 20:17
     * @className:ShopsController
     * @author:chenglitao
     * @description:添加商铺信息
     */
    @ResponseBody
    @RequestMapping("/add")
    public AjaxResultPo add(@RequestBody  ShopsPo po, HttpServletRequest request) throws IOException {
        logger.info("进入ShopsController.add方法，ShopsPo={}", JsonUtils.toJson(po));
        try {

            int r = shopsService.add(po, request);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("添加商铺信息失败");

        }
        return AjaxResultPo.successDefault();
    }


    /**
     * @date:2018/1/11 0011 20:18
     * @className:ShopsController
     * @author:chenglitao
     * @description:更新商铺信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public AjaxResultPo update(ShopsPo po, HttpServletRequest request) throws IOException {
        logger.info("进入ShopsController.update方法，po={}", JsonUtils.toJson(po));

        try {
            int r = shopsService.update(po, request);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("更新商铺信息失败");

        }
        return AjaxResultPo.successDefault();
    }

    /**
     * @date:2018/1/11 0011 20:18
     * @className:ShopsController
     * @author:chenglitao
     * @description:删除商铺信息
     */
    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResultPo delete(Integer id) {
        logger.info("进入ShopsController.delete方法，id={}", id);

        try {
            int r = shopsService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("删除商铺信息失败");

        }
        return AjaxResultPo.successDefault();
    }

    @ResponseBody
    @RequestMapping("/classfyOpt")
    public AjaxResultPo classfyOpt() throws IOException {
        logger.info("进入ShopsController.classfyOpt方法，ShopsPo={}");
        AjaxResultPo resultPo  = new AjaxResultPo(true,"操作成功");
        try {

            List<ClassifyPo> list = classifyServiceImpl.query(new ClassifyPo());

            if (list != null) {
                JSONArray result = new JSONArray(list.size());

                for (ClassifyPo classifyPo : list) {
                    JSONObject object = new JSONObject();
                    object.put("value", classifyPo.getCatalog());
                    object.put("label", classifyPo.getCatalog());
                    JSONArray children = new JSONArray();

                    if (StringUtils.isNotBlank(classifyPo.getCatalog1())) {
                        JSONObject temp = new JSONObject();
                        temp.put("value", classifyPo.getCatalog1());
                        temp.put("label", classifyPo.getCatalog1());
                        children.add(temp);
                    }

                    if (StringUtils.isNotBlank(classifyPo.getCatalog2())) {
                        JSONObject temp = new JSONObject();
                        temp.put("value", classifyPo.getCatalog2());
                        temp.put("label", classifyPo.getCatalog2());
                        children.add(temp);
                    }
                    if (StringUtils.isNotBlank(classifyPo.getCatalog3())) {
                        JSONObject temp = new JSONObject();
                        temp.put("value", classifyPo.getCatalog3());
                        temp.put("label", classifyPo.getCatalog3());
                        children.add(temp);
                    }
                    if (StringUtils.isNotBlank(classifyPo.getCatalog4())) {
                        JSONObject temp = new JSONObject();
                        temp.put("value", classifyPo.getCatalog4());
                        temp.put("label", classifyPo.getCatalog4());
                        children.add(temp);
                    }
                    if (StringUtils.isNotBlank(classifyPo.getCatalog5())) {
                        JSONObject temp = new JSONObject();
                        temp.put("value", classifyPo.getCatalog5());
                        temp.put("label", classifyPo.getCatalog5());
                        children.add(temp);
                    }
                    object.put("children", children);
                    result.add(object);
                }
                resultPo.setRows(result);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("商铺查询分类信息失败");

        }
        return resultPo;
    }
}
