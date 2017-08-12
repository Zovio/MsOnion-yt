<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<head>

<title>洋桃供应链后台管理系统-注册</title>
	<jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>

<div class="wrapper">
	<div class="reg-top">
		<div class="cont-top">
			<a class="reg-logo"><img src="${static$domain}/images/reg-logo.png" /> </a>
			<div class="logn-reg"><a class="font-logo" href="/sys/to-login">登录 </a><a class="font-reg" href="#">/ 注册</a> </div>
		</div>
	</div>
	<div class="reg-container">
		<div class="row">
			<label class="reg-userName">成员名称：</label>
			<input class="reg-input" id="name" type="text" onblur="checkName()" />
			<span class="hints" id="hint1"></span>
		</div>
		<div class="row">
			<label class="reg-userName">全称：</label>
			<input class="reg-input" id="allName" type="text" onblur="checkAllName()" />
			<span class="hints" id="hint2"></span>
		</div>
		<%--<div class="row">
			<label class="reg-userName">用户编码：</label>
			<input class="reg-input-bm coding" id="coding" type="text" />
			<span class="hints" id="hint3"></span>
		</div>--%>
		<div class="row">
			<label class="reg-userName">性别：</label>
			<p class="checkbox-span">
				<p class="gender-man">
					<span class="sex-man sex-manCur"></span>
					<label class="sex">男</label>
				</p>
				<p class="gender-woman">
					<span class="sex-woman"></span>
					<label class="sex">女</label>
				</p>
			</p>
			<span class="hints" id="hint_sex"></span>
		</div>
		<div class="row relative">
			<label class="reg-userName">密码：</label>
			<input class="reg-input" id="password" type="password" onblur="checkPassword()" />
			<span class="hints" id="hint4"></span>
			<p id="passHint">密码建议使用字母，数字和符号两种及以上的组合，6-20位字符!</p>
		</div>
		<div class="row">
			<label class="reg-userName">确认密码：</label>
			<input class="reg-input" id="password2" type="password" onblur="checkAgainPwd()" />
			<span class="hints" id="hint5"></span>
		</div>
		<div class="row">
			<label class="reg-userName">手机号码：</label>
			<input class="reg-input" id="phone" type="text" onblur="checkPhone()" maxlength="11"/>
			<span class="hints" id="hint6"></span>
		</div>
		<div class="row">
			<label class="reg-userName">电话号码：</label>
			<input class="reg-input" id="telephone" type="text" onblur="checkTelephone()" />
			<span class="hints" id="hint7"></span>
		</div>
		<div class="row">
			<label class="reg-userName">电子邮箱：</label>
			<input class="reg-input" id="email" type="text" onblur="checkEmail()" />
			<span class="hints" id="hint8"></span>
		</div>
		<div class="row" style="height: 110px;overflow:hidden;">
			<label class="reg-userName">验证码：</label>
			<%--<input class="yz-input" id="verification" onblur="checkVerification()" type="text" maxlength="5"/>--%>
			<%--<img class="yz-img" id="vCode" src="${pageContext.request.contextPath }/validatecode"--%>
				 <%--onclick="javascript:$(this).attr('src',$(this).attr('src')+'?'+Math.random());" />--%>
			<div id="_captcha_img_content" style="float: left;">

			</div>
			<span class="hints" id="hint9"></span>
		</div>
		<div class="row-bz">
			<label class="reg-userName">备注：</label>
			<textarea class="bz-textarea"  id="remark" onblur="checkRemark()" placeholder="50位字符（包含中英文）"></textarea>
			<span class="hints" id="hint10"></span>
		</div>
		<div class="row">
			<button class="submit" type="submit" id="register-btn" >提交申请</button>
			<button id="regidterReset" class="reset" type="reset">重置</button>
		</div>

	</div>
</div>
</body>
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
</html>