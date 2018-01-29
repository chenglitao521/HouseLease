package com.xiamo.common.controller;

import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.vo.AjaxResultPo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
     * @description:上传图片
     */
    @RequestMapping(value = "/uploadImage")
    public AjaxResultPo uploadImage(HttpServletRequest request) {
        AjaxResultPo po = AjaxResultPo.successDefault();
        try {
            String url = FileUpload.uploadFile(request);

            if (StringUtils.isBlank(url)) {

                return AjaxResultPo.failure("上传图片失败！");
            }
            po.setRows(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return po;
    }


    @ResponseBody
    @RequestMapping(value="upload")
    public ModelAndView testUpload() throws IOException {
       // FileUpload.uploadImages(request);

       // /tmp/2018012915540012345. png
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trueFileName","/tmp/2018012915540012345.png");
        modelAndView.setViewName("/zxingcoder");
        return modelAndView;
    }

}
