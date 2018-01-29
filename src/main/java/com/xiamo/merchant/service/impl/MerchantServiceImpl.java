package com.xiamo.merchant.service.impl;

import com.xiamo.common.po.ServiceException;
import com.xiamo.common.utils.ConfigUtils;
import com.xiamo.common.utils.DateConstants;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.dao.IMerchantDao;
import com.xiamo.merchant.po.MerchantPo;
import com.xiamo.merchant.service.IMerchantService;
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
 * @date:2018/1/10 0010 15:27
 * @className:MerchantServiceImpl
 * @author:chenglitao
 * @description:商户接口
 */
public class MerchantServiceImpl implements IMerchantService {
    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    public static final String realPath = ConfigUtils.UPLOAD_FILE_PATH(); //上传文件的目录

    public static final String VIRTUAL_PATH = ConfigUtils.VIRTUAL_PATH(); //图片虚拟目录
    @Autowired
    IMerchantDao merchantDaoImpl;

    public int delete(Integer id) {
        try {
            return merchantDaoImpl.delete(id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<MerchantPo> query(MerchantPo po, PageInfo pageInfo) {
        List<MerchantPo> merchantPos = null;
        try {
            merchantPos = merchantDaoImpl.query(po, pageInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return merchantPos;
    }

    public int add(MerchantPo po, HttpServletRequest httprequest) {
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httprequest;
            Iterator<String> fileNames = request.getFileNames();

            //保存图片名和url对应关系
            List<String> imgUrls = new ArrayList<>();

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
                            imgUrls.add(VIRTUAL_PATH + trueFileName);

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

            for (int i=0;i<imgUrls.size();i++) {

                if(i==0){
                    po.setImageUrl1(imgUrls.get(i));
                }else if(i==1){
                    po.setImageUrl2(imgUrls.get(i));
                }else if(i==2){
                    po.setImageUrl3(imgUrls.get(i));
                }
            }

            return merchantDaoImpl.add(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    public int update(MerchantPo po) {
        try {
            return merchantDaoImpl.update(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
