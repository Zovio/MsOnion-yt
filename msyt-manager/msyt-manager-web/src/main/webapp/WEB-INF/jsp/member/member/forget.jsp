
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>洋桃供应链后台管理系统-找回密码</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>

</head>
<body>
<div class="wrapper">
    <div class="reg-top">
        <div class="cont-top">
            <a class="reg-logo"><img src="${static$domain}/images/reg-logo.png" /> </a>
            <div class="logn-reg"><a class="font-logo" href="/sys/to-login">登录 </a>
            <%-- 由于后台管理系统，只有一个节点，没有集群，避免受到攻击，因此先注释掉
            <a class="font-reg" href="/sys/to-register">/ 注册</a>
            --%>
            </div>
            <span class="reg-find"><img src="${static$domain}/images/findPass.png" /></span>
        </div>
    </div>

    <div class="reg-container">
        <div class="fp-item-step">
            <ul class="fp-step-list clearfix">
                <li class="step on">
                    <div class="line"></div>
                    <div class="circle">1</div>
                    <span>填写资料</span>
                </li>
                <li class="step">
                    <div class="line"></div>
                    <div class="circle">√</div>
                    <span>完成</span>
                </li>
            </ul>
        </div>

        <div class="row">
            <label class="reg-userName">电子邮箱：</label>
            <input class="reg-input" id="email" type="text" onblur="checkEmail2()" />
            <span class="hints" id="hint8"></span>
        </div>
        <div class="row relative">
            <label class="reg-userName">新密码：</label>
            <input class="reg-input" id="password" type="password" onblur="checkPassword()" />
            <span class="hints" id="hint4"></span>
        </div>
        <div class="row" style="height: 110px;overflow:hidden;">
            <label class="reg-userName">验证码：</label>
            <%--<input class="yz-input" id="verification" onblur="checkVerification()" type="text"  maxlength="5"/>--%>
            <%--<img class="yz-img" id="vCode" src="${pageContext.request.contextPath }/validatecode"--%>
                 <%--onclick="javascript:$(this).attr('src',$(this).attr('src')+'?'+Math.random());"/>--%>
            <div id="_captcha_img_content" style="float: left;">

            </div>
            <span class="hints" id="hint9"></span>
        </div>
        <div class="row">
            <button class="submit" type="submit" onclick="replyForm()">下一步</button>
            <button id="findReset" class="reset" type="reset">重置</button>
        </div>
    </div>
</div>
<link rel="stylesheet" href="${static$domain}/css/member/login.css?_v=${css$version}" />
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>
<script type="text/javascript" src="${static$domain}/plugins/picture-code/jquery-picture-code.js?_v=${js$version}" ></script>
<link rel="stylesheet" href="${static$domain}/plugins/picture-code/css/picture-code.css?_v=${css$version}"/>
<script type="text/javascript">
    var _captcha_img_content_reg;
    $(function(){
        var verifyCodePictureSize="${verifyCodePictureSize}";
        var verifyCodePictureNum="${verifyCodePictureNum}";
        var verifyCodePictureRing="${verifyCodePictureRing}";

        _captcha_img_content_reg = $("#_captcha_img_content").pictureCode({
            url: verifyCodeDomin + "/validateCode?version=${js$version}",
            accessCodeUrl: verifyCodeDomin + "/getValidateAccessCode?version=${js$version}",
            picSize: verifyCodePictureSize,
            picNum: verifyCodePictureNum,
            ring:verifyCodePictureRing
        });
    });
</script>
</body>
</html>
