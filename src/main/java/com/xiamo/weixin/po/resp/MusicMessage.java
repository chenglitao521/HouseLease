package com.xiamo.weixin.po.resp;

/**
 * <dl>
 * <dt>MusicMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 * 音乐响应消息
 * @author chenglitao
 */
public class MusicMessage extends BaseResponseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
