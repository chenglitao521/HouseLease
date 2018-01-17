package com.xiamo.weixin.po.req;

/**
 * <dl>
 * <dt>TextMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 *请求消息-文本消息
 * @author chenglitao
 */
public class TextMessage extends BaseRequestMessage {

    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}