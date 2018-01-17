package com.xiamo.weixin.po.resp;

/**
 * <dl>
 * <dt>ImageMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/17 0017</dd>
 * </dl>
 * 响应消息-图片消息
 * @author chenglitao
 */
public class ImageMessage extends BaseResponseMessage {

    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}