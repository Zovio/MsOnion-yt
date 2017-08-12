<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>洋桃供应链后台管理系统-完成找回密码</title>
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
                <li class="step on">
                    <div class="line"></div>
                    <div class="circle">√</div>
                    <span>完成</span>
                </li>
            </ul>
        </div>

        <div class="row">
            <span class="succeed"><img src="${static$domain}/images/no-result.png" /> </span>
            <c:choose>
                <c:when test="${msOnionResult.status eq 200}">
                    <a class="return" href="/member/to-login"><h3>修改成功，回到登录</h3></a>
                </c:when>
                <c:otherwise>
                    <a class="return" style="color: red" href="/member/to-forget"><h3>${msOnionResult.msg}，重新申请</h3></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<link rel="stylesheet" href="${static$domain}/css/member/login.css?_v=${css$version}" />
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>
</body>
</html>
