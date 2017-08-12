<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统-报关信息查看</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>

<!--查看报关信息 start-->
<div id="selectCustoms" class="selectCustoms" style="overflow-y: scroll;overflow-x: hidden;height: 440px;">
    <input type="hidden" id="itemIdx" value="${item.idx}">
    <div class="selectRow">
        <div class="select-col">
            <label class="key">货号：</label>
            <span class="value">${item.itemNo}</span>

        </div>
        <div class="select-col">
            <label class="key">中文品名：</label>
            <span class="value" title="${item.cnName}">${item.cnName}</span>
            <label class="key">外文品名：</label>
            <span class="value">${item.enName}</span>
        </div>
        <div class="select-col">
            <label class="key">报关品名：</label>
            <span class="value" title="${itemCustom.customName}">${itemCustom.customName}</span>
            <label class="key">第一数量：</label>
            <span class="value">${itemCustom.firstQuantity}</span>
        </div>
        <div class="select-col">
            <label class="key">第二数量：</label>
            <span class="value">${itemCustom.secondQuantity}</span>
            <label class="key">第一单位：</label>
            <span class="value">${itemCustom.firstUnitIdxDynamicS}</span>
        </div>
        <div class="select-col">
            <label class="key">第二单位：</label>
            <span class="value">${itemCustom.secondUnitIdxDynamicS}</span>
            <label class="key">行邮税率：</label>
            <span class="value">${itemCustom.postalArticlesTaxRateDots5}</span>
        </div>
        <div class="select-col">
            <label class="key">跨境税率：</label>
            <span class="value">${itemCustom.crossBorderTaxRateDots5}</span>
            <label class="key">BC价：</label>
            <span class="value">${itemCustom.bcPriceDots2}</span>
        </div>
        <div class="select-col">
            <label class="key">HS编码：</label>
            <span class="value">${itemCustom.hsCode}</span>
            <label class="key">毛重：</label>
            <span class="value">${itemCustom.grossWeight}</span>
        </div>
        <div class="select-col">
            <label class="key">是否含有消费税：</label>
            <span class="value">
                <c:if test="${itemCustom.isContainExcise == 1}">是</c:if>
                <c:if test="${itemCustom.isContainExcise == 0}">否</c:if>
            </span>
            <label class="key">行邮税号：</label>
            <span class="value">${itemCustom.postalArticlesTaxNumber}</span>
        </div>
    </div>
</div>
<!--查看报关信息 end-->
</body>
</html>