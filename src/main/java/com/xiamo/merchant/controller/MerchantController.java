package com.xiamo.merchant.controller;

import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.po.MerchantPo;
import com.xiamo.merchant.po.MerchantVo;
import com.xiamo.merchant.po.RecordFilePo;
import com.xiamo.merchant.service.IMerchantService;
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
    public AjaxResultPo query(Integer page, Integer rows, MerchantPo po, HttpServletRequest request) {
        logger.info("进入MerchantController.query方法");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            PageInfo pageInfo = null;
            if (page > 0) {
                pageInfo = new PageInfo((page - 1) * rows, rows);
            }

            List<MerchantPo> list = merchantServiceImpl.query(po, pageInfo);
            //转换po为Vo
            String contextPath = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                    request.getServerPort() + contextPath + FileUpload.FILE_PATH;
            List<MerchantVo> voList= new ArrayList<>();

            for (MerchantPo merchantPo : list) {

                MerchantVo vo =new MerchantVo();
                vo.setId(merchantPo.getId());
                vo.setName(merchantPo.getName());
                vo.setAddr(merchantPo.getAddress());
                vo.setConcats(merchantPo.getContactName());
                vo.setTel(merchantPo.getTelephone());
                vo.setState(merchantPo.getStatus());
                vo.setShopNum(merchantPo.getNum());

                List<RecordFilePo> recordFiles = new ArrayList<>();

                if (StringUtils.isNotBlank(merchantPo.getImageUrl1())) {
                    RecordFilePo recordFilePo = new RecordFilePo();
                    recordFilePo.setName(merchantPo.getImageUrl1());
                    recordFilePo.setSrc(basePath+merchantPo.getImageUrl1());
                    recordFiles.add(recordFilePo);
                }
                if (StringUtils.isNotBlank(merchantPo.getImageUrl2())) {
                    RecordFilePo recordFilePo = new RecordFilePo();
                    recordFilePo.setName(merchantPo.getImageUrl2());
                    recordFilePo.setSrc(basePath+merchantPo.getImageUrl2());
                    recordFiles.add(recordFilePo);
                }
                if (StringUtils.isNotBlank(merchantPo.getImageUrl3())) {
                    RecordFilePo recordFilePo = new RecordFilePo();
                    recordFilePo.setName(merchantPo.getImageUrl3());
                    recordFilePo.setSrc(basePath+merchantPo.getImageUrl3());
                    recordFiles.add(recordFilePo);
                }
                vo.setRecordFiles(recordFiles);

                voList.add(vo);
            }

            if (page > 0) {
                res.setTotal(pageInfo.getTotalRecords());
            } else {
                res.setTotal(voList.size());
            }

            res.setRows(voList);
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
    public AjaxResultPo add(@RequestBody String param, HttpServletRequest request) throws IOException {
        logger.info("进入MerchantController.add方法，MerchantPo={}", JsonUtils.toJson(param));
        try {

            int r = merchantServiceImpl.add(param, request);
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
    public AjaxResultPo update(@RequestBody String param, HttpServletRequest request) throws IOException {
        logger.info("进入MerchantController.update方法，po={}", JsonUtils.toJson(param));

        try {
            int r = merchantServiceImpl.update(param, request);
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

    /**
     * @date:2018/3/14 0014 16:11
     * @className:MerchantController
     * @author:chenglitao
     * @description:更新状态
     *
     */
    @ResponseBody
    @RequestMapping("/changeState")
    public AjaxResultPo changeState(Integer id,Integer state) {
        logger.info("进入MerchantController.changeState，id={}", id);

        try {
            int r = merchantServiceImpl.changeState(id,state);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResultPo.failure("更新商户信息失败");

        }
        return AjaxResultPo.successDefault();
    }

}
