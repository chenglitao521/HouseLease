<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

<script>

    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${appId}', // 必填，公众号的唯一标识
        timestamp: '${timestamp}', // 必填，生成签名的时间戳
        nonceStr: '${nonceStr}', // 必填，生成签名的随机串
        signature: '${signature}',// 必填，签名，见附录1
        jsApiList: [
            'onMenuShareTimeline',
            'onMenuShareAppMessage', 'onMenuShareQQ',
            'onMenuShareWeibo', 'onMenuShareQZone', 'startRecord', 'stopRecord',
            'onVoiceRecordEnd', 'playVoice',
            'pauseVoice', 'stopVoice', 'onVoicePlayEnd',
            'uploadVoice', 'downloadVoice',
            'chooseImage', 'previewImage', 'uploadImage',
            'downloadImage', 'translateVoice', 'getNetworkType', 'openLocation', 'getLocation', 'hideOptionMenu', 'showOptionMenu',
            'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem',
            'closeWindow', 'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
            'addCard', 'chooseCard', 'openCard'
        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {
        // 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
        wx.onMenuShareTimeline({
            title: '第八篇 ：微信公众平台开发实战Java版之如何网页授权获取用户基本信息', // 分享标题
            link: "http://www.cnblogs.com/liuhongfeng/p/5099149.html",
            imgUrl: "http://images.cnblogs.com/cnblogs_com/liuhongfeng/737147/o_1442809977405.jpg" // 分享图标
        });
        // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
        wx.onMenuShareAppMessage({
            title: '第七篇 ：微信公众平台开发实战Java版之如何获取微信用户基本信息', // 分享标题
            desc: "第七篇 ：微信公众平台开发实战Java版之如何获取微信用户基本信息", // 分享描述
            link: "http://www.cnblogs.com/liuhongfeng/p/5057167.html",
            imgUrl: "http://images.cnblogs.com/cnblogs_com/liuhongfeng/737147/o_QQ%E5%9B%BE%E7%89%8720151118180508.png", // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
        });

        //获取“分享到QQ”按钮点击状态及自定义分享内容接口
        wx.onMenuShareQQ({
            title: '第六篇 ：微信公众平台开发实战Java版之如何自定义微信公众号菜单', // 分享标题
            desc: '第六篇 ：微信公众平台开发实战Java版之如何自定义微信公众号菜单', // 分享描述
            link: 'http://www.cnblogs.com/liuhongfeng/p/4857312.html', // 分享链接
            imgUrl: 'http://images.cnblogs.com/cnblogs_com/liuhongfeng/737147/o_qrcode_for_gh_228cd30523bc_258.jpg', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        //获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
        wx.onMenuShareWeibo({
            title: '分享到腾讯微博标题', // 分享标题
            desc: '分享到腾讯微博描述', // 分享描述
            link: 'http://www.cnblogs.com/liuhongfeng/p/4857312.html', // 分享链接
            imgUrl: 'http://images.cnblogs.com/cnblogs_com/liuhongfeng/737147/o_qrcode_for_gh_228cd30523bc_258.jpg', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        //获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
        wx.onMenuShareQZone({
            title: '分享到QQ空间标题', // 分享标题
            desc: '分享到QQ空间描述', // 分享描述
            link: 'http://www.cnblogs.com/liuhongfeng/p/4857312.html', // 分享链接
            imgUrl: 'http://images.cnblogs.com/cnblogs_com/liuhongfeng/737147/o_qrcode_for_gh_228cd30523bc_258.jpg', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

    });
</script>
<body>
<h2>Hello World!</h2>

<div class="panel-body">
    <h2>请核对你的订单信息</h2>
    <h2>${snsUserInfo}</h2>
</div>
</body>
</html>
