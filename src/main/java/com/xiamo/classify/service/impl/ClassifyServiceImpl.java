package com.xiamo.classify.service.impl;

import com.xiamo.classify.dao.IClassifyDao;
import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.classify.service.IClassifyService;
import com.xiamo.common.po.ServiceException;
import com.xiamo.common.utils.ConfigUtils;
import com.xiamo.common.utils.DateConstants;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.service.IShopsService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <dl>
 * <dt>ClassifyServiceImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 *
 * @author CLT
 */
public class ClassifyServiceImpl implements IClassifyService {

    private static final Logger logger = LoggerFactory.getLogger(ClassifyServiceImpl.class);

    public static final String realPath = ConfigUtils.UPLOAD_FILE_PATH(); //上传文件的目录

    public static final String VIRTUAL_PATH = ConfigUtils.VIRTUAL_PATH(); //图片虚拟目录
    @Autowired
    IShopsService shopsServiceImpl;

    @Autowired
    IClassifyDao classifyDaoImpl;

    public List<ClassifyPo> query(ClassifyPo classifyPo, PageInfo pageInfo) {

        List<ClassifyPo> classifyPos = null;
        try {
            classifyPos = classifyDaoImpl.query(classifyPo, pageInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return classifyPos;
    }

    public int add(ClassifyPo classifyPo, HttpServletRequest httprequest) {
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httprequest;
            Iterator<String> fileNames = request.getFileNames();

            //保存图片名和url对应关系
            Map<String, String> imgUrls = new HashMap<String, String>();

            long totalSum = 0;
            while (fileNames.hasNext()) {

                //把fileNames集合中的值打出来
                String fileName = fileNames.next();
                logger.debug("fileName:{} ", fileName);

            /*
             * request.getFiles(fileName)方法即通过fileName这个Key, 得到对应的文件
             * 集合列表. 只是在这个Map中, 文件被包装成MultipartFile类型
             */
                List<MultipartFile> fileList = request.getFiles(fileName);


                if (fileList.size() > 0) {

                    //遍历文件列表
                    Iterator<MultipartFile> fileIte = fileList.iterator();


                    while (fileIte.hasNext()) {

                        //获得每一个文件
                        MultipartFile multipartFile = fileIte.next();
                        //判断文件是否为空
                        if (!multipartFile.isEmpty()) {
                            //获取文件大小, 单位为字节
                            long size = multipartFile.getSize();
                            logger.debug("size: ", size);
                            totalSum += size;
                            if (totalSum > 10485760) {

                                throw new ServiceException("上传的文件总大小，不能超过10M");
                            }
                            //获得原文件名
                            String origName = multipartFile.getOriginalFilename();
                            logger.debug("origName: " + origName);

                            String ext = FilenameUtils.getExtension(origName).toLowerCase();

                            String removeExtName = FilenameUtils.removeExtension(origName).toLowerCase();
                            logger.debug("上传文件后缀名：{}", ext);
                            //判断上传文件后缀名
                            if (!("gif".equals(ext) || "jpg".equals(ext) || "png".equals(ext))) {
                                //  return AjaxResultPo.failure("上传的图片格式不正确！");
                                logger.error("上传的文件格式不正确！文件名为：{}" + origName);
                                throw new ServiceException("上传的文件格式不正确！");

                            }
                            String trueFileName = DateConstants.DATE_FORMAT_NUM().format(new Date()) + origName;

                            imgUrls.put(fileName, VIRTUAL_PATH + trueFileName);
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

                        /*
                         * MultipartFile提供了void transferTo(File dest)方法,
                         * 将获取到的文件以File形式传输至指定路径.
                         */
                            multipartFile.transferTo(tempFile);

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


                            //MultipartFile也提供了其他一些方法, 用来获取文件的部分属性

                            //获取文件contentType
                            String contentType = multipartFile.getContentType();
                            logger.debug("contentType: ", contentType);

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
                            logger.debug("name: ", name);
                        }


                    }
                }
            }
            //保存每个图片的相对地址
            Set<String> keys = imgUrls.keySet();
            for (String key : keys) {

                switch (key) {
                    case "imageUrl":
                        classifyPo.setIconUrl(imgUrls.get(key));
                        break;
                    case "imageUrl1":
                        classifyPo.setIconUrl1(imgUrls.get(key));
                        break;
                    case "imageUrl2":
                        classifyPo.setIconUrl2(imgUrls.get(key));
                        break;
                    case "imageUrl3":
                        classifyPo.setIconUrl3(imgUrls.get(key));
                        break;
                    case "imageUrl4":
                        classifyPo.setIconUrl4(imgUrls.get(key));
                        break;
                    case "imageUrl5":
                        classifyPo.setIconUrl5(imgUrls.get(key));
                        break;
                    default:
                        break;

                }

            }

            return classifyDaoImpl.add(classifyPo);

        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加分类失败！", e.getMessage());
        }
        return 0;
    }

    public int update(ClassifyPo classifyPo) {
        try {
            return classifyDaoImpl.update(classifyPo);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(Integer id) {
        try {
            int j = shopsServiceImpl.deleteByClassifyId(id);
            return classifyDaoImpl.delete(id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
