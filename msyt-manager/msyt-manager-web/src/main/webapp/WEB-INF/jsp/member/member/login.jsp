<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<title>洋桃供应链后台管理系统-登录</title>

	<jsp:include page="../common/memberCommon.jsp"></jsp:include>
	<!--[if IE]>
	<style type="text/css">
		body{background: url(${static$domain}/images/login-bg.jpg?_v=${img$version}) center center no-repeat fixed;}
	</style>
	<![endif]-->
</head>
<body  style="background: url(${static$domain}/images/login-bg.jpg) center center no-repeat fixed;background-size:cover;height: 100%;margin: 0px;padding: 0px;align-items: center;">
<div class="wrapper">
	<div class="login-bg">
		<div class="login-container">
			<div class="cont">
				<%--<i class="user-icon"></i>--%>
				<i class="user-close"></i>
				<input id="userName" class="userName" type="text"  placeholder="成员名／手机号码／邮箱" />
				<%--<i class="pass-icon"></i>--%>
				<i class="pass-close"></i>
				<input id="passWord" class="passWord" type="password"  placeholder="密码"/>
				<%--<input id="verification" class="verification" type="text"  maxlength="5"  placeholder="验证码" />--%>
				<%--<img class="verification-img" id="vCode" src="${pageContext.request.contextPath }/validatecode"--%>
					 <%--onclick="javascript:$(this).attr('src',$(this).attr('src')+'?'+Math.random());"/>--%>

				<div id="_captcha_img_content" >

				</div>

			</div>
			<div class="login-row">
				<span class="checkbox" id="rememberme"></span>
				<span class="avoid">1天内免登录</span>
				<a class="forget" href="/sys/to-forget" target="_blank">忘记密码？</a>
			</div>
			<button class="login-btn-active" type="submit" id="login-btn">登录</button>
			<%-- 由于后台管理系统，只有一个节点，没有集群，避免受到攻击，因此先注释掉
			<a class="off-reg" href="/sys/to-register" target="_blank">还没有账户，马上注册</a>
			 --%>
			<div class="hint-d"><i></i><span class="hint"></span> </div>
		</div>
	</div>
</div>
<script>
	$(function () {
	    // 当登录页面在主页里面或者在弹层里面，需要重新加载一下...
	    if($('.win-nav').length > 0){
            window.location.reload();
		}

		if(self != top){
	        parent.window.location.reload()
		}


		/* enter绑定登录事件 */
        $(document).on("keydown", function(e){
            if (e.keyCode == 13) {
                doLogin();
            }
        });

    });
</script>
<link rel="stylesheet" href="${static$domain}/css/member/login.css?_v=${css$version}" />
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>
<script type="text/javascript" src="${static$domain}/plugins/picture-code/jquery-picture-code.js?_v=${js$version}" ></script>
<link rel="stylesheet" href="${static$domain}/plugins/picture-code/css/picture-code.css?_v=${css$version}"/>
<script type="text/javascript">
    var _captcha_img_content;
	$(function(){
        var verifyCodePictureSize="${verifyCodePictureSize}";
        var verifyCodePictureNum="${verifyCodePictureNum}";
        var verifyCodePictureRing="${verifyCodePictureRing}";

        _captcha_img_content = $("#_captcha_img_content").pictureCode({
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