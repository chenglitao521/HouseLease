package com.xiamo.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiamo.common.po.ServiceException;
import com.xiamo.common.utils.ConfigUtils;
import com.xiamo.common.utils.FileUpload;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.merchant.dao.IMerchantDao;
import com.xiamo.merchant.po.MerchantPo;
import com.xiamo.merchant.service.IMerchantService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    public int add(String param, HttpServletRequest httprequest) {
        try {
            JSONObject jo = JSON.parseObject(param);
            MerchantPo po = new MerchantPo();
            po.setName(jo.getString("name"));

            JSONArray jsa = jo.getJSONArray("recordFiles");
            if (jsa != null && jsa.size() >= 1) {
                JSONObject tmp = jsa.getJSONObject(0);
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(tmp.getString("src"), ","), tmp.getString("name"));
                po.setImageUrl1(iconUrl1);
            }
            if (jsa != null && jsa.size() >= 2) {
                JSONObject tmp = jsa.getJSONObject(1);
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(tmp.getString("src"), ","), tmp.getString("name"));
                po.setImageUrl2(iconUrl1);
            }
            if (jsa != null && jsa.size() >= 3) {
                JSONObject tmp = jsa.getJSONObject(2);
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(tmp.getString("src"), ","), tmp.getString("name"));
                po.setImageUrl3(iconUrl1);
            }
            po.setContactName(jo.getString("concats"));
            po.setNum(jo.getInteger("shopNum"));
            po.setAddress(jo.getString("addr"));
            po.setTelephone(jo.getString("tel"));
            po.setStatus(jo.getInteger("state"));
            return merchantDaoImpl.add(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    public int update(String param, HttpServletRequest httprequest) {
        try {

            JSONObject jo = JSON.parseObject(param);
            MerchantPo po = new MerchantPo();
            po.setId(jo.getInteger("id"));
            po.setName(jo.getString("name"));

            JSONArray jsa = jo.getJSONArray("recordFiles");
            if (jsa != null && jsa.size() >= 1) {
                JSONObject tmp = jsa.getJSONObject(0);
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(tmp.getString("src"), ","), tmp.getString("name"));
                po.setImageUrl1(iconUrl1);
            }
            if (jsa != null && jsa.size() >= 2) {
                JSONObject tmp = jsa.getJSONObject(1);
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(tmp.getString("src"), ","), tmp.getString("name"));
                po.setImageUrl2(iconUrl1);
            }
            if (jsa != null && jsa.size() >= 3) {
                JSONObject tmp = jsa.getJSONObject(2);
                String iconUrl1 = FileUpload.generateImage(StringUtils.substringAfter(tmp.getString("src"), ","), tmp.getString("name"));
                po.setImageUrl3(iconUrl1);
            }
            po.setContactName(jo.getString("concats"));
            po.setNum(jo.getInteger("shopNum"));
            po.setAddress(jo.getString("addr"));
            po.setTelephone(jo.getString("tel"));
            po.setStatus(jo.getInteger("state"));
            return merchantDaoImpl.update(po);

        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }
}
