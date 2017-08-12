<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统-商品仓库信息编辑</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>

<div id="editcustoms" class="addCommodity"
     style="overflow-y: scroll;height: 497px;overflow-x: hidden; display: block;background-color: #fff">
    <input type="hidden" id="itemIdx" value="${item.idx}">
    <div class="bigWinCont">
        <div class="bigRow">
            <label>货号：</label>
            <input type="text" value="${item.itemNo}" disabled>
            <label>中文品名：</label>
            <input type="text" value="${item.cnName}" disabled>
        </div>
        <div class="bigRow">
            <label>外文品名：</label>
            <input type="text" value="${item.enName}" disabled>
            <label><i>*</i>发货系数：</label>
            <input type="text" id="deliveryCoefficient" value="${warehouse.deliveryCoefficient}"
                   onkeyup="MS.onlyNum(this);" maxlength="3"/>
        </div>
        <div class="bigRow">
            <label><i>*</i>实时库存：</label>
            <input type="text" id="realTimeInventory" value="${warehouse.realTimeInventory}"
                   onkeyup="MS.onlyNum(this);" maxlength="7"/>
            <label><i>*</i>安全库存：</label>
            <input type="text" id="safeInventory" maxlength="7" value="${warehouse.safeInventory}"
                   onkeyup="MS.onlyNum(this);"/>
        </div>
        <div class="bigRow">

            <label>是否一键下单：</label>
            <p class="relative">
                <select id="isKeyOrder">
                    <option value="0" ${warehouse.isKeyOrder == 0?"selected":""}>否</option>
                    <option value="1" ${warehouse.isKeyOrder == 1?"selected":""}>是</option>
                </select>
                <i class="downIcon"></i>
            </p>
            <label>是否免邮：</label>
            <p class="relative">
                <select id="isFreeShipping">
                    <option value="0" ${warehouse.isFreeShipping == 0?"selected":""}>否</option>
                    <option value="1" ${warehouse.isFreeShipping == 1?"selected":""}>是</option>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>

        <div class="yt-row-txm txm">
            <label class="yt-label-f" for="ckbarBode"><i>*</i>条形码：</label>
            <input type="text" class="yt-input-f" id="ckbarBode" value="${item.barcode}" maxlength="20"
                   onkeyup="MS.onlyEnOrNumber(this);"/>
            <div id="add-element2" class="add-element"><span class="add-bq-icon"></span></div>
        </div>

        <c:forEach items="${itemBarcodeList}" var="bc">
            <div class="yt-row-txm txm">
                <label class="yt-label-f"></label>
                <input type="text" class="yt-input-f" value="${bc.barcode}" name="barBodejg" maxlength="20"
                       onkeyup="MS.onlyEnOrNumber(this);"/>
                <div class="add-element" onclick="removeNode(this)">
                    <span class="add-sc-icon"></span>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script type="text/javascript">
    function submitForm(syncFlag) {
        var message = "" ;
        var deliveryCoefficient = $.trim($('#deliveryCoefficient').val());
        if (!deliveryCoefficient) {
            message += "发货系数不能为空;\t";
        }
        var realTimeInventory = $.trim($('#realTimeInventory').val());
        if (!realTimeInventory) {
            message += "实时库存不能为空;\t";
        }
        var safeInventory = $.trim($('#safeInventory').val());
        if (!safeInventory) {
            message += "安全库存不能为空;\t";
        }
        var barBodeCode = $.trim($('#ckbarBode').val());
        if (!barBodeCode) {
            message += "第一个条形码不能为空;\t";
        }
        if(message){
            layer.msg(message, {icon: 2});
            return false;
        }

        var deliveryCoefficient = $.trim($('#deliveryCoefficient').val());
        if (deliveryCoefficient == "") {
            layer.msg('发货系数不能为空！', {icon: 2});
            return false;
        }

        var realTimeInventory = $.trim($('#realTimeInventory').val());
        if (realTimeInventory == "") {
            layer.msg('实时库存不能为空！', {icon: 2});
            return false;
        }

        var safeInventory = $.trim($('#safeInventory').val());
        if (safeInventory == "") {
            layer.msg('安全库存不能为空！', {icon: 2});
            return false;
        }

        // 获取条形码输入框的值
        var barCodeArr = $("input[name='barBodejg']");
        var arr = [];
        barCodeArr.length > 0 && barCodeArr.each(function () {
            if($(this).val()) {
                arr.push($(this).val());
            }
        });
        // 判断条形码重复
        var arr2 = [].concat(arr);
        arr2.push(barBodeCode);
        var nary=arr2.sort();
        var repeatBarcodeArray = [];
        for (var i=0;i < arr2.length; i++ ) {
            if (nary[i] == nary[i + 1]) {
                repeatBarcodeArray.push(nary[i]);
            }
        }
        if(repeatBarcodeArray.length > 0){
            layer.msg('条形码重复：' + $.unique(repeatBarcodeArray).join(" "), {icon: 2});
            return false;
        }

        parent.layer.load(2, {shade: 0.01});
        $.ajax({
            type: "post",
            cache: false,
            url: "/wareho/wareho/save",
            data: {
                itemIdx: $("#itemIdx").val(),
                realTimeInventory: $("#realTimeInventory").val(),
                safeInventory: $("#safeInventory").val(),
                deliveryCoefficient: $("#deliveryCoefficient").val(),
                isKeyOrder: $("#isKeyOrder").val(),
                isFreeShipping: $("#isFreeShipping").val(),
                barcode: barBodeCode,
                barcodeStrs: arr.join(','),
                syncFlag: syncFlag
            },
            dataType: 'json',
            success: function (d) {

                if (d.status == 200) {
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.msg('操作成功！', {icon: 6});
                    parent.layer.close(index);
                    parent.$('#warehouse-warehouseTable').datagrid('reload', {});
                } else {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            },
            error: function () {
                parent.layer.msg(Ms.ERROR_MSG, {icon: 2});
            }
        });
    }

    /* 点击加号icon添加条形码标签  */
    $('#add-element2').click(function () {
        var barCode = $(this).parent().children("input").val();
        if (barCode == "") {
            layer.msg('条形码必填！', {icon: 2});
        } else {
            if ($('.txm').length < 10) {
                $(".yt-row-txm:last").after('<div class="yt-row-txm txm"><label class="yt-label-f" for="barBodejg"></label><input type="text" class="yt-input-f"  name="barBodejg" maxlength="20" onkeyup="MS.onlyEnOrNumber(this);"/><div class="add-element" onclick="removeNode(this)"><span class="add-sc-icon"></span> </div></div>');
            } else {
                layer.msg('条形码最多能添加10个！', {icon: 2});
            }
        }
    });

    function removeNode(str) {
        $(str).parent().remove();
    }


</script>

</body>
</html>