package com.xiamo.weixin.po.req;

/**
 * <dl>
 * <dt>VideoMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 * 请求消息-视频消息
 *
 * @author chenglitao
 */
public class VideoMessage extends BaseRequestMessage {

    // 媒体ID
    private String MediaId;
    // 语音格式
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }


}