package com.xiamo.shops.controller;

import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.po.ShopsPo;
import com.xiamo.shops.service.IShopsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    IShopsService shopsServiceImpl;

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
    public AjaxResultPo query(Integer page, Integer rows, ShopsPo po) {
        logger.info("进入ShopsController.query方法");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            PageInfo pageInfo = null;
            if (page > 0) {
                pageInfo = new PageInfo((page - 1) * rows, rows);
            }
            List<ShopsPo> list = shopsServiceImpl.query(po, pageInfo);
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
    public AjaxResultPo add(ShopsPo po, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        logger.info("进入ShopsController.add方法，ShopsPo={}", JsonUtils.toJson(po));
        try {
            FileUpload.uploadFile(file, request);
            int r = shopsServiceImpl.add(po);
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
    public AjaxResultPo update(ShopsPo po) throws IOException {
        logger.info("进入ShopsController.update方法，po={}", JsonUtils.toJson(po));

        try {
            int r = shopsServiceImpl.update(po);
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
            int r = shopsServiceImpl.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("删除商铺信息失败");

        }
        return AjaxResultPo.successDefault();
    }

}
