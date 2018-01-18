package com.xiamo.common.controller;

import com.xiamo.common.utils.ZXingCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

    @RequestMapping(value = "/zxingdecode", method = RequestMethod.POST)
    public ModelAndView zxingdecode(HttpServletRequest request, MultipartFile file, HttpServletResponse response) {
        ModelAndView ret = new ModelAndView();
        try {
            if (file != null) {// 判断上传的文件是否为空
                String path = null;// 文件路径
                String type = null;// 文件类型
                String fileName = file.getOriginalFilename();// 文件原名称
                System.out.println("上传的文件原名称:" + fileName);
                // 判断文件类型
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                if (type != null) {// 判断文件类型是否为空
                    if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                        // 项目在容器中实际发布运行的根路径
                        String realPath = request.getSession().getServletContext().getRealPath("/");
                        // 自定义的文件名称
                        String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                        // 设置存放图片文件的路径
                        path = realPath +/*System.getProperty("file.separator")+*/trueFileName;
                        System.out.println("存放图片文件的路径:" + path);
                        // 转存文件到指定的路径
                        file.transferTo(new File(path));
                        System.out.println("文件成功上传到指定目录下");
                        String s = ZXingCode.getLogoQRCode("https://www.baidu.com/", "跳转到百度的二维码", path,request,response);
                        System.out.println(s);


                    } else {
                        System.out.println("不是我们想要的文件类型,请按要求重新上传");
                        return null;
                    }
                } else {
                    System.out.println("文件类型为空");
                    return null;
                }
            } else {
                System.out.println("没有找到相对应的文件");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        ret.setViewName("zxingcoder");

        return ret;
    }


}
