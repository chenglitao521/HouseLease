package com.xiamo.common.utils;

import com.xiamo.common.po.ServiceException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @date:2018/1/14 0014 10:35
 * @className:FileUpload
 * @author:chenglitao
 * @description:上传文件
 */
public class FileUpload {
    private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);
    public static final String FILE_PATH = ConfigUtils.VIRTUAL_PATH(); //图片虚拟目录
    public static final String realPath = ConfigUtils.UPLOAD_FILE_PATH(); //上传文件的目录


    public static void main(String[] args) {

        String str = getImageStr(realPath + "20180312171536aaa.png");


        String path= generateImage(str,"测试");
        System.out.println(str);
    }

    /**
     * @param imgStr base64编码字符串
     * @param
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    public static String generateImage(String imgStr, String origName) {

        //保存到服务器的文件名
        String trueFileName = DateConstants.DATE_FORMAT_NUM().format(new Date()) +"_"+ origName ;
        //上传的路径
        String path = realPath + trueFileName;

        logger.debug("上传文件完整的路径为：{}", path);
        // 转存文件到指定的路径


        if (imgStr == null) {
            return "";
        }


        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return  trueFileName;
    }

    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    //文件上传
    public static String uploadFile(HttpServletRequest request) throws IOException {

        logger.debug("图片上传根路径：{}", realPath);
        // 自定义的文件名称
        String trueFileName = "";
        String origName = ""; //源文件名
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
                    throw new ServiceException("上传的文件格式不正确！");

                }
                trueFileName = DateConstants.DATE_FORMAT_NUM().format(new Date()) + origName;
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
        return FILE_PATH + trueFileName;
    }

    public static void uploadImages(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();

        while (fileNames.hasNext()) {

            //把fileNames集合中的值打出来
            String fileName = fileNames.next();
            logger.debug("fileName:{} ", fileName);

            /*
             * request.getFiles(fileName)方法即通过fileName这个Key, 得到对应的文件
             * 集合列表. 只是在这个Map中, 文件被包装成MultipartFile类型
             */
            List<MultipartFile> fileList = request.getFiles(fileName);

            String trueFileName = "";
            if (fileList.size() > 0) {

                //遍历文件列表
                Iterator<MultipartFile> fileIte = fileList.iterator();

                while (fileIte.hasNext()) {

                    //获得每一个文件
                    MultipartFile multipartFile = fileIte.next();

                    //获得原文件名
                    String origName = multipartFile.getOriginalFilename();
                    System.out.println("origName: " + origName);

                    trueFileName = DateConstants.DATE_FORMAT_NUM().format(new Date()) + origName;
                    String filePath = realPath + trueFileName;

                    logger.debug("上传文件完整的路径为：{}", filePath);

                    //检查该路径对应的目录是否存在. 如果不存在则创建目录
                    File tempFile = new File(filePath);

                    if (!tempFile.getParentFile().exists()) {
                        tempFile.getParentFile().mkdir();
                    }
                    if (!tempFile.exists()) {
                        tempFile.mkdir();
                    }


                    //保存文件
                    File dest = new File(filePath);
                    if (!(dest.exists())) {
                        /*
                         * MultipartFile提供了void transferTo(File dest)方法,
                         * 将获取到的文件以File形式传输至指定路径.
                         */
                        multipartFile.transferTo(dest);

                        /*
                         * 如果需对文件进行其他操作, MultipartFile也提供了
                         * InputStream getInputStream()方法获取文件的输入流
                         *
                         * 例如下面的语句即为通过
                         * org.apache.commons.io.FileUtils提供的
                         * void copyInputStreamToFile(InputStream source, File destination)
                         * 方法, 获取输入流后将其保存至指定路径
                         */
                        //FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dest);
                    }

                    //MultipartFile也提供了其他一些方法, 用来获取文件的部分属性

                    //获取文件contentType
                    String contentType = multipartFile.getContentType();
                    System.out.println("contentType: " + contentType);

                    /*
                     * 获取name
                     * 其实这个name跟上面提到的getFileName值是一样的,
                     * 就是Map中Key的值. 即前台页面<input>中name=""
                     * 属性. 但是上面的getFileName只是得到这个Map的Key,
                     * 而Spring在处理上传文件的时候会把这个值以name属性
                     * 记录到对应的每一个文件. 如果需要从文件层面获取这个
                     * 值, 则可以使用该方法
                     */
                    String name = multipartFile.getName();
                    System.out.println("name: " + name);

                    //获取文件大小, 单位为字节
                    long size = multipartFile.getSize();
                    System.out.println("size: " + size);

                }
            }
        }
    }

}