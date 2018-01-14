package com.xiamo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

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
    public static final String FILE_PATH = "/upload/";

    //文件上传
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();

        String extendName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//equalsIgnoreCase()
        if (!".png".equalsIgnoreCase(extendName) && !".jpg".equalsIgnoreCase(extendName) && !".gif".equalsIgnoreCase(extendName)) {
            logger.error("不支持上传文件格式:{}", extendName);
        }

        String realPath = request.getSession().getServletContext().getRealPath("/");
        File tempFile = new File(realPath + FILE_PATH, new Date().getTime() + String.valueOf(fileName));
        logger.debug("上传文件的路径：{}", tempFile.getAbsolutePath());
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdir();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return tempFile.getAbsolutePath();
    }

    public static File getFile(String fileName) {
        return new File(FILE_PATH, fileName);
    }
}