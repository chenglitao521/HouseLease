package com.xiamo.classify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiamo.classify.dao.IClassifyDao;
import com.xiamo.classify.po.ClassifyPo;
import com.xiamo.classify.service.IClassifyService;
import com.xiamo.common.po.ServiceException;
import com.xiamo.common.utils.ConfigUtils;
import com.xiamo.common.utils.FileUpload;
import com.xiamo.shops.service.IShopsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    public List<ClassifyPo> query(ClassifyPo classifyPo) {

        List<ClassifyPo> classifyPos = null;
        try {
            classifyPos = classifyDaoImpl.query(classifyPo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return classifyPos;
    }

    public int add(String param, HttpServletRequest httprequest) {
        try {
            ClassifyPo classifyPo = new ClassifyPo();
            JSONObject jo = JSON.parseObject(param);

            JSONObject icon = jo.getJSONObject("icon");
            JSONArray subName = jo.getJSONArray("subName");
            classifyPo.setCatalog(jo.getString("name"));

            if(null!=icon ){
                String iconUrl = FileUpload.generateImage(StringUtils.substringAfter(icon.getString("src"),","), icon.getString("name"));
                classifyPo.setIconUrl(iconUrl);
            }

            if(subName.size()>=1){
                JSONObject jb1= subName.getJSONObject(0);
                classifyPo.setCatalog1(jb1.getString("name"));
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(jb1.getJSONObject("files").getString("src"),","), jb1.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl1(iconUrl1);
            }


            if(subName.size()>=2){
                JSONObject jb2= subName.getJSONObject(1);
                classifyPo.setCatalog2(jb2.getString("name"));
                String iconUrl2 = FileUpload.generateImage(StringUtils.substringAfter(jb2.getJSONObject("files").getString("src"),","), jb2.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl2(iconUrl2);
            }


            if(subName.size()>=3){
                JSONObject jb3= subName.getJSONObject(2);
                classifyPo.setCatalog3(jb3.getString("name"));
                String iconUrl3 = FileUpload.generateImage(StringUtils.substringAfter(jb3.getJSONObject("files").getString("src"),","), jb3.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl3(iconUrl3);
            }


            if(subName.size()>=4){
                JSONObject jb4= subName.getJSONObject(3);
                classifyPo.setCatalog4(jb4.getString("name"));
                String iconUrl4 = FileUpload.generateImage(StringUtils.substringAfter(jb4.getJSONObject("files").getString("src"),","), jb4.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl4(iconUrl4);
            }


            if(subName.size()>=5){
                JSONObject jb5= subName.getJSONObject(4);
                classifyPo.setCatalog5(jb5.getString("name"));
                String iconUrl5 = FileUpload.generateImage(StringUtils.substringAfter(jb5.getJSONObject("files").getString("src"),","), jb5.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl1(iconUrl5);
            }
            return classifyDaoImpl.add(classifyPo);

        } catch (DataAccessException e) {
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

    public int update(String param, HttpServletRequest httprequest) {
        try {
            ClassifyPo classifyPo = new ClassifyPo();
            JSONObject jo = JSON.parseObject(param);

            JSONObject icon = jo.getJSONObject("icon");
            JSONArray subName = jo.getJSONArray("subName");
            classifyPo.setCatalog(jo.getString("name"));
            classifyPo.setSort(jo.getIntValue("sort"));
            classifyPo.setId(jo.getIntValue("id"));

            if(null!=icon ){
                String iconUrl = FileUpload.generateImage(StringUtils.substringAfter(icon.getString("src"),","), icon.getString("name"));
                classifyPo.setIconUrl(iconUrl);
            }

            if(subName.size()>=1){
                JSONObject jb1= subName.getJSONObject(0);
                classifyPo.setCatalog1(jb1.getString("name"));
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(jb1.getJSONObject("files").getString("src"),","), jb1.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl1(iconUrl1);
            }


            if(subName.size()>=2){
                JSONObject jb2= subName.getJSONObject(1);
                classifyPo.setCatalog2(jb2.getString("name"));
                String iconUrl2 = FileUpload.generateImage(StringUtils.substringAfter(jb2.getJSONObject("files").getString("src"),","), jb2.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl2(iconUrl2);
            }


            if(subName.size()>=3){
                JSONObject jb3= subName.getJSONObject(2);
                classifyPo.setCatalog3(jb3.getString("name"));
                String iconUrl3 = FileUpload.generateImage(StringUtils.substringAfter(jb3.getJSONObject("files").getString("src"),","), jb3.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl3(iconUrl3);
            }


            if(subName.size()>=4){
                JSONObject jb4= subName.getJSONObject(3);
                classifyPo.setCatalog4(jb4.getString("name"));
                String iconUrl4 = FileUpload.generateImage(StringUtils.substringAfter(jb4.getJSONObject("files").getString("src"),","), jb4.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl4(iconUrl4);
            }


            if(subName.size()>=5){
                JSONObject jb5= subName.getJSONObject(4);
                classifyPo.setCatalog5(jb5.getString("name"));
                String iconUrl5 = FileUpload.generateImage(StringUtils.substringAfter(jb5.getJSONObject("files").getString("src"),","), jb5.getJSONObject("files").getString("name"));
                classifyPo.setIconUrl1(iconUrl5);
            }
            return classifyDaoImpl.update(classifyPo);

        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
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
