<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统_缓存详情</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<div id="brandCapacity" style="height: 700px;">
    <div class="winCont" style="height: 700px">
        <div class="winRow">
            <label class="brandName">key：</label>
            <input  class="brandTxt required" type="text" readonly="readonly" value="${cacheObject.key}">
        </div>
        <div class="winRow">
            <label class="brandName">ttl：</label>
            <input  class="brandTxt required" type="text" readonly="readonly" value="${cacheObject.ttl}">
        </div>
        <div class="winRow-cache">
            <label class="brandName">加密文本：</label>
            <textarea class="brandTxt" readonly="readonly" style="width:520px;height: 200px" >${cacheObject.value}</textarea>
        </div>
        <div class="winRow-cache">
            <label class="brandName">解密文本：</label>
            <textarea class="brandTxt" readonly="readonly" style="width:520px;height: 200px" >${cacheObject.decodeValue}</textarea>
        </div>
    </div>
</div>
</body>
</html>
