package com.xiamo.merchant.controller;

import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.po.MerchantPo;
import com.xiamo.merchant.service.IMerchantService;
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
 * <dt>UserController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2017/12/25 0025</dd>
 * </dl>
 * 商户接口
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController {
    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    IMerchantService merchantServiceImpl;

    /**
     * @date:2018/1/10 0010
     * @className:MerchantController
     * @author:chenglitao
     * @description:分页查询商户信息
     */
    @ResponseBody
    @RequestMapping("/query")
    public AjaxResultPo query(Integer page, Integer rows, MerchantPo po) {
        logger.info("进入MerchantController.query方法");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            PageInfo pageInfo = null;
            if (page > 0) {
                pageInfo = new PageInfo((page - 1) * rows, rows);
            }

            List<MerchantPo> list = merchantServiceImpl.query(po, pageInfo);
            if (page > 0) {
                res.setTotal(pageInfo.getTotalRecords());
            } else {
                res.setTotal(list.size());
            }

            res.setRows(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("查询商户信息失败");

        }
        return res;
    }


    /**
     * @date:2018/1/10 0010
     * @className:MerchantController
     * @author:chenglitao
     * @description:添加商户信息
     */
    @ResponseBody
    @RequestMapping("/add")
    public AjaxResultPo add(MerchantPo po, HttpServletRequest request) throws IOException {
        logger.info("进入MerchantController.add方法，MerchantPo={}", JsonUtils.toJson(po));
        try {

            int r = merchantServiceImpl.add(po,request);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("添加商户信息失败");

        }
        return AjaxResultPo.successDefault();
    }

    /**
     * @date:2018/1/10 0010 15:25
     * @className:MerchantController
     * @author:chenglitao
     * @description:更新商户信息
     */

    @ResponseBody
    @RequestMapping("/update")
    public AjaxResultPo update(MerchantPo po) throws IOException {
        logger.info("进入MerchantController.update方法，po={}", JsonUtils.toJson(po));

        try {
            int r = merchantServiceImpl.update(po);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("更新商户信息失败");

        }
        return AjaxResultPo.successDefault();
    }

    /**
     * @date:2018/1/10 0010
     * @className:MerchantController
     * @author:chenglitao
     * @description:删除商户信息
     */
    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResultPo delete(Integer id) {
        logger.info("进入MerchantController.delete方法，id={}", id);

        try {
            int r = merchantServiceImpl.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("删除商户信息失败");

        }
        return AjaxResultPo.successDefault();
    }

}
