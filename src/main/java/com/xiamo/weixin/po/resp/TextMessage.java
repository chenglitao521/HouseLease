package com.xiamo.weixin.po.resp;

/**
 * <dl>
 * <dt>TextMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 * 响应消息-文本
 * @author chenglitao
 */
public class TextMessage extends BaseResponseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}