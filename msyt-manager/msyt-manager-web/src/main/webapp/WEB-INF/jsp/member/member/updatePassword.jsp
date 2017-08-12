<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE HTML>
<head>

<title>洋桃供应链后台管理系统-修改密码</title>
	<jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>

<div class="wrapper">

	<div class="reg-container" style="min-width: 500px;margin-top: 10px;">
		<input id="idx" type="hidden" value="${idx}">
		<div class="row relative">
			<label class="reg-userName">原密码：</label>
			<input class="reg-input" id="password_old" type="password" onblur="checkOldPassword()"/>
			<span class="set-hints" id="hint_old" ></span>
		</div>
		<div class="row relative">
			<label class="reg-userName">新密码：</label>
			<input class="reg-input" id="password" type="password" onblur="checkPassword()" />
			<span class="set-hints" id="hint4"  ></span>
			<p id="passHint">密码建议使用字母，数字和符号两种及以上的组合，6-20位字符!</p>
		</div>
		<div class="row">
			<label class="reg-userName">确认密码：</label>
			<input class="reg-input" id="password2" type="password" onblur="checkAgainPwd()" />
			<span class="set-hints" id="hint5" ></span>
		</div>
	</div>
</div>
</body>
<link rel="stylesheet" href="${static$domain}/css/member/login.css?_v=${css$version}" />
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>
</html>