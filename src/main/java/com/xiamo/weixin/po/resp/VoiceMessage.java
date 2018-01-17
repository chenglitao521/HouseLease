package com.xiamo.weixin.po.resp;

/**
 * <dl>
 * <dt>VoiceMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 * 语音响应消息
 * @author chenglitao
 */
public class VoiceMessage extends BaseResponseMessage{
    // 语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
