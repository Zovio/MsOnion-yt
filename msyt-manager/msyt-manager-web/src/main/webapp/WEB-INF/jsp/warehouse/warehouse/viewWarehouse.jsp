<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统-商品仓库信息查看</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>

<!--查看仓库信息 start-->
<div  class="selectCustoms" style="overflow-y: scroll;height: 497px;overflow-x: hidden; display: block;background-color: #fff">
    <input type="hidden" id="itemIdx" value="${item.idx}">
    <div class="selectRow">
        <div class="select-col">
            <label class="key">货号：</label>
            <span class="value">${item.itemNo}</span>
            <label class="key">发货系数：</label>
            <span class="value">${warehouse.deliveryCoefficient}</span>
        </div>
        <div class="select-col">
            <label class="key">中文品名：</label>
            <span class="value" title="${item.cnName}">${item.cnName}</span>
            <label class="key">外文品名：</label>
            <span class="value">${item.enName}</span>
        </div>
        <div class="select-col">
            <label class="key">实时库存：</label>
            <span class="value" >${warehouse.realTimeInventory}</span>
            <label class="key">安全库存：</label>
            <span class="value">${warehouse.safeInventory}</span>
        </div>
        <div class="select-col">

            <label class="key">是否一键下单：</label>
            <span class="value">
                <c:if test="${warehouse.isKeyOrder == 1}">是</c:if>
                <c:if test="${warehouse.isKeyOrder == 0}">否</c:if>
            </span>
            <label class="key">是否免邮：</label>
            <span class="value">
                <c:if test="${warehouse.isFreeShipping == 1}">是</c:if>
                <c:if test="${warehouse.isFreeShipping == 0}">否</c:if>
            </span>
        </div>

        <div class="yt-row-txm txm" style="color:#666;">
            <label class="yt-label-f" for="ckbarBode">条形码：</label>
            <input type="text" class="yt-input-f" style="color:#666;" value="${item.barcode}" readonly />
        </div>
        <c:forEach items="${itemBarcodeList}" var="bc">
            <div class="yt-row-txm txm"  style="color:#666;">
                <label class="yt-label-f" for="ckbarBode"></label>
                <input type="text" class="yt-input-f" style="color:#666;" id="ckbarBode"  value="${bc.barcode}" readonly/>
            </div>
        </c:forEach>

    </div>
</div>
<!--查看仓库信息 end-->





</body>
</html>