package com.xiamo.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @date:2018/1/14 0014 10:35
 * @className:FileUpload
 * @author:chenglitao
 * @description:上传文件
 */
public class FileUpload {
    private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);
    public static final String FILE_PATH = "/tmp/"; //图片虚拟目录
    public static final String realPath = ConfigUtils.UPLOAD_FILE_PATH(); //上传文件的目录

    //文件上传
    public static String uploadFile(HttpServletRequest request) throws IOException {

        logger.debug("图片上传根路径：{}", realPath);
        // 自定义的文件名称
        String trueFileName = "";
        String origName=""; //源文件名
        // 先实例化一个文件解析器
        CommonsMultipartResolver coMultipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());

        // 判断request请求中是否有文件上传
        if (coMultipartResolver.isMultipart(request)) {
            // 转换request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获得文件
            MultipartFile file = multiRequest.getFile("file");
            if (!file.isEmpty()) {

                origName = file.getOriginalFilename();
                logger.debug("上传的文件名：{}", origName);

                String ext = FilenameUtils.getExtension(origName).toLowerCase();
                logger.debug("上传文件后缀名：{}", ext);
                //判断上传文件后缀名
                if (!("gif".equals(ext) || "jpg".equals(ext) || "png".equals(ext))) {
                    //  return AjaxResultPo.failure("上传的图片格式不正确！");
                    logger.error("上传的文件格式不正确！文件名为：{}" + origName);
                    return "";
                }
                trueFileName =  DateConstants.DATE_FORMAT_NUM().format(new Date()) + origName;
                String path = realPath + trueFileName;

                logger.debug("上传文件完整的路径为：{}", path);
                // 转存文件到指定的路径
                File tempFile = new File(path);

                if (!tempFile.getParentFile().exists()) {
                    tempFile.getParentFile().mkdir();
                }
                if (!tempFile.exists()) {
                    tempFile.mkdir();
                }

                file.transferTo(tempFile);
                logger.debug("上传文件的路径：{}", tempFile.getAbsolutePath());
                logger.debug("上传文件成功！");
            } else {
                logger.error("找不到上传的文件！");
            }
        }
        return FILE_PATH+trueFileName;
    }
}