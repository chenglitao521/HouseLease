package com.xiamo.weixin.po.resp;

/**
 * <dl>
 * <dt>VideoMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 * 视频响应消息
 *
 * @author chenglitao
 */
public class VideoMessage extends BaseResponseMessage {
    // 视频
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
