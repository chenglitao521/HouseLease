package com.xiamo.shops.service.impl;

import com.xiamo.common.po.ImgPo;
import com.xiamo.common.po.ServiceException;
import com.xiamo.common.utils.ConfigUtils;
import com.xiamo.common.utils.DateConstants;
import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.shops.dao.IShopsDao;
import com.xiamo.shops.po.LeasePo;
import com.xiamo.shops.po.ShopsPo;
import com.xiamo.shops.service.IShopsService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @date:2018/1/11 0011 20:10
 * @className:ShopsServiceImpl
 * @author:chenglitao
 * @description:商铺接口
 */


public class ShopsServiceImpl implements IShopsService {
    private static final Logger logger = LoggerFactory.getLogger(ShopsServiceImpl.class);

    public static final String realPath = ConfigUtils.UPLOAD_FILE_PATH(); //上传文件的目录

    public static final String VIRTUAL_PATH = ConfigUtils.VIRTUAL_PATH(); //图片虚拟目录

    @Autowired
    IShopsDao shopsDaoImpl;

    @Override
    public List<ShopsPo> query(ShopsPo po, PageInfo pageInfo) {
        List<ShopsPo> shopsPos = null;
        try {
            shopsPos = shopsDaoImpl.query(po, pageInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return shopsPos;
    }

    public int update(ShopsPo po, HttpServletRequest httprequest) {


        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httprequest;
            Iterator<String> fileNames = request.getFileNames();

            //拼接多个图片Url

            StringBuffer stringBuffer = new StringBuffer();

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

                            if (StringUtils.isBlank(stringBuffer.toString())) {
                                stringBuffer.append(VIRTUAL_PATH + trueFileName);
                            } else {
                                stringBuffer.append(",").append(VIRTUAL_PATH + trueFileName);
                            }


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

            logger.debug("商铺图片地址拼接：{}", stringBuffer.toString());
            return shopsDaoImpl.update(po);

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

    public int add(ShopsPo po, HttpServletRequest httprequest) {
        try {


            List<LeasePo> leasePos = po.getRentType();
            //处理租赁时间
            StringBuffer leaseTimes = new StringBuffer();
            StringBuffer leaseMoneys = new StringBuffer();
            if (leasePos != null && leasePos.size() > 0) {
                for (int i = 0; i < leasePos.size(); i++) {
                    leaseMoneys.append(leasePos.get(i).getMoney());
                    leaseTimes.append(leasePos.get(i).getTime());
                    if ((i + 1) != leasePos.size()) {
                        leaseMoneys.append(",");
                    }
                }
            }
            po.setLeaseMoney(leaseMoneys.toString());
            po.setLeaseTime(leaseTimes.toString());
            //处理商铺图片
            List<ImgPo> imgPos = po.getRecordFiles();

            StringBuffer imgStr = new StringBuffer();
            if (imgPos != null && imgPos.size() > 0) {
                for (int i = 0; i < imgPos.size(); i++) {
                    ImgPo temIm = imgPos.get(i);
                    String trueFileName = FileUpload.generateImage(StringUtils.substringAfter(temIm.getSrc(), ","), temIm.getName());
                    imgStr.append(trueFileName);
                    if ((i + 1) != imgPos.size()) {
                        imgStr.append(",");
                    }
                }
            }

            po.setPhotoUrl(imgStr.toString());
            return shopsDaoImpl.add(po);
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(Integer id) {
        try {
            return shopsDaoImpl.delete(id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteByClassifyId(Integer id) {
        try {
            return shopsDaoImpl.deleteByClassifyId(id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
