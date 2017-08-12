<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../../header.jsp"/>

<div id="editSetPrice" class="addCommodity" style="overflow-y: scroll;overflow-x: hidden;height: 300px;">
    <input type="hidden" id="itemIdx" value="${item.idx}">
    <div class="bigWinCont">
        <div class="bigRow">
            <label>货号：</label>
            <input type="text" value="${item.itemNo}" disabled class="yt-input-value-disabled"
                   style="margin-right:0px"/>
            <label>中文品名：</label>
            <input type="text" value="${item.cnName}" disabled class="yt-input-value-disabled"/>
        </div>
        <div class="bigRow">
            <label>外文品名：</label>
            <input type="text" value="${item.enName}" disabled class="yt-input-value-disabled" style="margin-right:0px">
            <label><i style="color:red">*</i>市场价：</label>
            <input type="text" id="marketPrice" value="${itemPrice.marketPriceDots2}" onkeyup="MS.priceNumber(this)"
                   onblur="MS.priceNumber(this)"/>
        </div>
        <div class="bigRow" style="clear:both">
            <label><i style="color:red">*</i>售价：</label>
            <input type="text" id="sellingPrice" value="${itemPrice.sellingPriceDots2}" onkeyup="MS.priceNumber(this)"
                   onblur="MS.priceNumber(this)"/>

            <label><i style="color:red">*</i>供货价：</label>
            <input type="text" id="supplyPrice" value="${itemPrice.supplyPriceDots2}" onkeyup="MS.priceNumber(this)"
                   onblur="MS.priceNumber(this)"/>
        </div>

        <c:if test="${! empty itemBarcodeList}">
            <div class="bigRow">
                <label>条形码：</label>
                <c:forEach items="${itemBarcodeList}" var="itemBarcode" end="1">
                    <input class="yt-input-value-disabled" type="text" disabled value="${itemBarcode.barcode}"
                           style="margin-right: 10px;"/>
                </c:forEach>
            </div>

            <c:if test="${fn:length(itemBarcodeList) > 1}">
                <c:forEach items="${itemBarcodeList}" begin="2" varStatus="status" step="2">
                    <div class="bigRow">
                        <label style="clear:both">&nbsp;</label>
                        <input class="yt-input-value-disabled" type="text" disabled
                               value="${itemBarcodeList[status.index].barcode}" style="margin-right: 10px;"/>
                        <c:if test="${fn:length(itemBarcodeList) > status.index + 1}">
                            <input class="yt-input-value-disabled" type="text" disabled
                                   value="${itemBarcodeList[status.index+1].barcode}" style="margin-right: 10px;"/>
                        </c:if>
                    </div>
                </c:forEach>
            </c:if>
        </c:if>
    </div>
</div>

<script>
    function submitForm(st) {
        var marketPrice = $('#marketPrice');
        var sellingPrice = $('#sellingPrice');
        var supplyPrice = $('#supplyPrice');

        marketPrice.removeClass("curs");
        sellingPrice.removeClass("curs");
        supplyPrice.removeClass("curs");

        if (!marketPrice.val()) {
            marketPrice.addClass("curs");
            marketPrice.focus();
            layer.msg("请录入市场价", {icon: 2, time: 1000, offset: '200px'});
            return false;
        }

        if (!sellingPrice.val()) {
            sellingPrice.addClass("curs");
            sellingPrice.focus();
            layer.msg("请录入售价", {icon: 2, time: 1000, offset: '200px'});
            return false;
        }

        if (!supplyPrice.val()) {
            supplyPrice.addClass("curs");
            supplyPrice.focus();
            layer.msg("请录入供货价", {icon: 2, time: 1000, offset: '200px'});
            return false;
        }

        var itemIdx = $("#itemIdx").val();

        $.ajax({
            type: "post",
            url: "/collect/item/price/save",
            data: {
                'itemIdx': itemIdx,
                'marketPriceStr': marketPrice.val(),
                'sellingPriceStr': sellingPrice.val(),
                'supplyPriceStr': supplyPrice.val(),
                'st': !st ? null : st
            },
            dataType: 'json',
            success: function (d) {
                if (d.status == 200) {
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.msg('操作成功！', {icon: 1});
                    parent.layer.close(index);
                } else {
                    parent.layer.msg(d.msg, {icon: 2});
                }
                parent.$('#collector-setPriceTable').datagrid('reload', {});
            },
            error: function (d) {
                parent.layer.msg(d.msg, {icon: 2});
            }
        });
    }
</script>

<jsp:include page="../../../footer.jsp"/>