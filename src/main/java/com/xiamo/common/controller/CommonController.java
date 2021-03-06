package com.xiamo.common.controller;

import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.vo.AjaxResultPo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <dl>
 * <dt>CommonController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/14 0014</dd>
 * </dl>
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * @date:2018/1/22 0022 10:50
     * @className:CommonController
     * @author:chenglitao
     * @description:上传二维码图片
     */
    @RequestMapping(value = "/getCodeUrl")
    @ResponseBody
    public AjaxResultPo getCodeUrl(HttpServletRequest request) {
        AjaxResultPo po = AjaxResultPo.successDefault();
        String contextPath = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+contextPath+ FileUpload.FILE_PATH;
        try {
          String url = FileUpload.getCodeUrl((MultipartHttpServletRequest) request);

            if (StringUtils.isBlank(url)) {

                return AjaxResultPo.failure("上传图片失败！");
            }
            po.setRows(basePath+url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return po;
    }

    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public AjaxResultPo uploadImage(HttpServletRequest request) {
        AjaxResultPo po = AjaxResultPo.successDefault();
        String contextPath = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+contextPath+ FileUpload.FILE_PATH;
        try {
            String url = FileUpload.uploadImages((MultipartHttpServletRequest) request);

            if (StringUtils.isBlank(url)) {

                return AjaxResultPo.failure("上传图片失败！");
            }
            po.setRows(basePath+url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return po;
    }
}
