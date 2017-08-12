
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<head>

    <title>洋桃供应链后台管理系统-修改成员密码</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/member/member.css?_v=${css$version}" />
<!--修改密码 start-->
<div id="editPassWord" style="overflow-x: hidden;background-color: #FFFFFF">
    <input id="idx" type="hidden" value="${member.idx}">
    <div class="ms-container">
        <div class="row">
            <label class="ms-userName">成员名称：</label>
            <input class="coding" type="text" readonly="true" value="${member.name}" />
        </div>
        <div class="row">
            <label class="ms-userName">新密码：</label>
            <input class="ms-input" id="password" type="password" onblur="checkNewPassword()" />
            <span class="hints" id="hint4"></span>
            <p id="passHint" style="width: 380px;height: 24px;line-height: 24px;font-size: 12px;color: #00d5b4;position: absolute;top: 35px;padding-left: 50px;text-align: center;">建议使用字母，数字和符号两种以上组合，6-20位字符!</p>
        </div>
        <div class="row">
            <label class="ms-userName">确认新密码：</label>
            <input class="ms-input" id="password2" type="password" onblur="checkAgainPwd()" />
            <span class="hints" id="hint5"></span>
        </div>
        <div class="row">
            <label class="ms-userName">您的密码：</label>
            <input class="ms-input" id="password_old" type="password" onblur="checkOldPassword()"/>
            <span class="hints" id="hint_old"></span>
        </div>
    </div>
</div>
<!--修改密码 end-->

<script type="text/javascript">

    function editMemmberPassword(){
        // 验证密码相关
        if(!checkNewPassword() || !checkAgainPwd() ||  !checkOldPassword()){
            parent.layer.closeAll('loading');
            return false;
        }
        var idx = $('#idx').val();
        var curPassword = $.trim($('#password_old').val());
        var password = $.trim($('#password').val());

        $.post("/member/edit/do-password?v="+Math.random(), {
                idxStr: idx,
                curPassword: base64_encode(curPassword),
                newPassword : base64_encode(password)
            },
            function(data){
                if (data.status == 200) {
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.msg('修改成功！', {icon:6});
                    parent.layer.close(index);
                } else {
                    parent.layer.msg(data.msg, {icon:2});
                }
                parent.layer.closeAll('loading');
            }, "json");

    }
</script>

<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>
</body>
</html>
