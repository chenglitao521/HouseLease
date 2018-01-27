package com.xiamo.weixin.service.impl;

import com.xiamo.weixin.dao.IWeinXinDao;
import com.xiamo.weixin.po.Token;
import com.xiamo.weixin.po.resp.TextMessage;
import com.xiamo.weixin.service.IWeiXinService;
import com.xiamo.weixin.utils.MessageUtil;
import com.xiamo.weixin.utils.WeiXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * <dl>
 * <dt>WeinXinServiceImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class WeinXinServiceImpl implements IWeiXinService {
    private static final Logger logger = LoggerFactory.getLogger(WeinXinServiceImpl.class);
    @Autowired
    IWeinXinDao weiXinDaoImpl;


    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return xml
     */
    public String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            String eventKey = requestMap.get("EventKey");

            logger.debug("处理微信发来的消息，发送方账号：{}", fromUserName);

            logger.debug("处理微信发来的消息，发送方微信号：{}", toUserName);
            logger.debug("处理微信发来的消息，消息类型：{}", msgType);


            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

                    eventKey = requestMap.get("EventKey"); //事件KEY值
                    respContent = "谢谢您的关注！" + eventKey;
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件

                    eventKey = requestMap.get("EventKey"); //事件KEY值
                    respContent = "您扫描的二维码事件！" + eventKey;

                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                    String Latitude = requestMap.get("Latitude");
                    String Longitude = requestMap.get("Longitude");
                    String Precision = requestMap.get("Precision");


                    respContent = "您上报的地理位置！" + Latitude + "_" + Longitude + "_" + Precision;

                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                    eventKey = requestMap.get("EventKey"); //事件KEY值
                    respContent = "您点击的自定义菜单事件！" + eventKey;
                }
            }
            // 设置文本消息的内容
            textMessage.setContent(respContent);
            // 将文本消息对象转换成xml
            respXml = MessageUtil.messageToXml(textMessage);

            logger.debug("处理微信发来的消息，转化后的XML：{}", respXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    @Override
    public int createMenu() {
        int result = 0;
        Token token= weiXinDaoImpl.getAccessToken();
        result = WeiXinUtil.createMenu(token.getAccessToken());
        logger.debug("创建菜单返回的结果：{}", result);
        return result;
    }

}
