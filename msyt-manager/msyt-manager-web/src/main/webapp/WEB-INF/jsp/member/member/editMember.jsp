<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE HTML>
<head>

<title>洋桃供应链后台管理系统-个人设置</title>
	<jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>

<div class="wrapper">

	<div class="reg-container" style="min-width: 500px;margin-top: 10px;">
		<input id="idx" type="hidden" value="${member.idx}">
		<div class="row">
			<label class="reg-userName">成员名称：</label>
			<input class="reg-input" id="name" type="text"  value="${member.name}" disabled/>
			<span class="set-hints" id="hint1"></span>
		</div>
		<div class="row">
			<label class="reg-userName">全称：</label>
			<input class="reg-input" id="allName" type="text" onblur="checkAllName()" value="${member.fullName}" />
			<span class="set-hints" id="hint2"></span>
		</div>
		<div class="row">
			<label class="reg-userName">成员编码：</label>
			<input class="reg-input" id="coding" type="text" value="${member.code}" onblur="checkCode()"/>
			<span class="set-hints" id="hint3"></span>
		</div>
		<div class="row">
			<label class="reg-userName">性别：</label>
			<p class="checkbox-span">
				<p class="gender-man">
					<span class="sex-man ${member.sex eq 1 ? 'sex-manCur' : ''}"></span>
					<label class="sex">男</label>
				</p>
				<p class="gender-woman">
					<span class="sex-woman ${member.sex eq 2 ? 'sex-womanCur' : ''}"></span>
					<label class="sex">女</label>
				</p>
			</p>
			<span class="set-hints" id="hint_sex"></span>
		</div>

		<div class="row">
			<label class="reg-userName">手机号码：</label>
			<input class="reg-input" id="phone" type="text" onblur="checkPhone()" maxlength="11" value="${member.phone}"/>
			<span class="set-hints" id="hint6"></span>
		</div>
		<div class="row">
			<label class="reg-userName">电话号码：</label>
			<input class="reg-input" id="telephone" type="text" onblur="checkTelephone()" value="${member.tel}"/>
			<span class="set-hints" id="hint7"></span>
		</div>
		<div class="row">
			<label class="reg-userName">电子邮箱：</label>
			<input class="reg-input" id="email" type="text" onblur="checkEmail()" value="${member.email}"/>
			<span class="set-hints" id="hint8"></span>
		</div>

		<div class="row-bz">
			<label class="reg-userName">备注：</label>
			<textarea class="bz-textarea"  style="height: 120px;width: 240px;" id="remark" onblur="checkRemark()" >${member.remark}</textarea>
			<span class="set-hints" id="hint10"></span>
		</div>


	</div>
</div>
</body>

<link rel="stylesheet" href="${static$domain}/css/member/login.css?_v=${css$version}" />
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>

</html>