<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统-报关信息编辑</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<div id="editcustoms" class="addCommodity" style="overflow-y: scroll;overflow-x: hidden;height: 440px;">
    <input type="hidden" id="itemIdx" value="${item.idx}">
    <div class="bigWinCont">
        <div class="bigRow">
            <label>货号：</label>
            <input type="text" value="${item.itemNo}" disabled>
        </div>
        <div class="bigRow">
            <label>中文品名：</label>
            <input type="text" value="${item.cnName}" disabled>
            <label>外文品名：</label>
            <input type="text" value="${item.enName}" disabled>
        </div>
        <div class="bigRow">
            <label>报关品名：</label>
            <input type="text" id="customName" value="${itemCustom.customName}"/>
            <label>第一数量：</label>
            <input type="text" id="firstQuantity" value="${itemCustom.firstQuantity}" onkeyup="MS.onlyNum(this);"/>
        </div>
        <div class="bigRow">
            <label>第二数量：</label>
            <input type="text" id="secondQuantity" value="${itemCustom.secondQuantity}" onkeyup="MS.onlyNum(this);"/>
            <label>第一单位：</label>
            <p class="relative">
                <select id="firstUnitIdx">
                    <option value="">请选择</option>
                    <c:forEach items="${logisticsUnitList}" var="p">
                        <option value="${p.idx}" ${itemCustom.firstUnitIdx == p.idx?"selected":""}>${p.name}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="bigRow">
            <label>第二单位：</label>
            <p class="relative">
                <select id="secondUnitIdx">
                    <option value="">请选择</option>
                    <c:forEach items="${logisticsUnitList}" var="p">
                        <option value="${p.idx}" ${itemCustom.secondUnitIdx == p.idx?"selected":""}>${p.name}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
            <label><i>*</i>行邮税率：</label>
            <input type="text" id="postalArticlesTaxRate" value="${itemCustom.postalArticlesTaxRateDots5}"/>
        </div>
        <div class="bigRow">
            <label><i>*</i>跨境税率：</label>
            <input type="text" id="crossBorderTaxRate" value="${itemCustom.crossBorderTaxRateDots5}"/>
            <label><i>*</i>BC价：</label>
            <input type="text" id="bcPrice" value="${itemCustom.bcPriceDots2}" onkeyup="MS.priceNumber(this)"
                   onblur="MS.priceNumber(this)"/>
        </div>
        <div class="bigRow">
            <label><i>*</i>HS编码：</label>
            <input type="text" id="hsCode" value="${itemCustom.hsCode}" onkeyup="MS.onlyNum(this);" maxlength="9"/>
            <label>毛重：</label>
            <input type="text" id="grossWeight" value="${itemCustom.grossWeight}"/>
        </div>
        <div class="bigRow">
            <label><i>*</i>行邮税号：</label>
            <input type="text" id="postalArticlesTaxNumber" onkeyup="MS.onlyNum(this);"
                   value="${itemCustom.postalArticlesTaxNumber}" maxlength="10"/>
            <label>是否含有消费税：</label>
            <p class="relative">
                <select id="isContainExcise">
                    <option value="0" ${itemCustom.isContainExcise == 0?"selected":""}>否</option>
                    <option value="1" ${itemCustom.isContainExcise == 1?"selected":""}>是</option>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
    </div>
</div>

<script type="text/javascript">
    function submitForm(flag) {
        var postalArticlesTaxRate = $.trim($('#postalArticlesTaxRate').val());
        if (postalArticlesTaxRate == "") {
            layer.msg('行邮税率不能为空！', {icon: 2});
            return false;
        }
        var crossBorderTaxRate = $.trim($('#crossBorderTaxRate').val());
        if (crossBorderTaxRate == "") {
            layer.msg('跨境税率不能为空！', {icon: 2});
            return false;
        }
        var bcPrice = $.trim($('#bcPrice').val());
        if (bcPrice == "") {
            layer.msg('BC价不能为空！', {icon: 2});
            return false;
        }
        var hsCode = $.trim($('#hsCode').val());
        if (hsCode == "") {
            layer.msg('HS编码不能为空！', {icon: 2});
            return false;
        }
        var postalArticlesTaxNumber = $.trim($('#postalArticlesTaxNumber').val());
        if (postalArticlesTaxNumber == "") {
            layer.msg('行邮税号不能为空！', {icon: 2});
            return false;
        }

        $.ajax({
            type: "post",
            cache: false,
            url: "/logistics/customs/save",
            data: {
                itemIdx: $("#itemIdx").val(),
                customName: $("#customName").val(),
                firstQuantity: $("#firstQuantity").val(),
                secondQuantity: $("#secondQuantity").val(),
                firstUnitIdx: $("#firstUnitIdx").val(),
                secondUnitIdx: $("#secondUnitIdx").val(),
                postalArticlesTaxRateStr: $("#postalArticlesTaxRate").val(),
                crossBorderTaxRateStr: $("#crossBorderTaxRate").val(),
                bcPriceStr: $("#bcPrice").val(),
                hsCode: $("#hsCode").val(),
                grossWeight: $("#grossWeight").val(),
                postalArticlesTaxNumber: $("#postalArticlesTaxNumber").val(),
                isContainExcise: $("#isContainExcise").val(),
                syncFlag: flag
            },
            dataType: 'json',
            success: function (d) {
                if (d.status == 200) {
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.msg('操作成功！', {icon: 6});
                    parent.layer.close(index);
                    parent.$('#custom-customsTable').datagrid('reload', {});
                } else {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            },
            error: function (d) {
                parent.layer.msg(Ms.ERROR_MSG, {icon: 2});
            }
        });
    }
</script>

</body>
</html>