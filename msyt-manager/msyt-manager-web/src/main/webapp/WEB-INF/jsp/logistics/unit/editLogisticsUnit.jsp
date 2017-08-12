<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="logisticsUnitCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 477px;">
    <input type="hidden" id="logisticsUnitIdx" value="${logisticsUnit.idx}">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>编码：</label>
            <input id="logisticsUnitCode" class="brandTxt required" type="text" value="${logisticsUnit.code}">
            <p class="hint">*编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>名称：</label>
            <input id="logisticsUnitName" class="brandTxt required" type="text" value="${logisticsUnit.name}">
            <p class="hint">*名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>状态：</label>
            <p class="relative">
                <select class="brandTxt" id="logisticsUnitStatus">
                    <option value="">请选择</option>
                    <option value="1" ${logisticsUnit.status eq 1 ? 'selected' : ''}>有效</option>
                    <option value="2" ${logisticsUnit.status eq 2 ? 'selected' : ''}>禁用</option>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="category-hint hint3">*状态必填</p>
        </div>
        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="logisticsUnitRemark">${logisticsUnit.remark}</textarea>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#logisticsUnitCapacity #logisticsUnitCode').on('keyup', function () {
        $(this).next().hide();
        $('#logisticsUnitCode').css("border", "1px solid #dbdcde");
    });

    $('#logisticsUnitCapacity #logisticsUnitName').on('keyup', function () {
        $(this).next().hide();
        $('#logisticsUnitName').css("border", "1px solid #dbdcde");
    });

    $('#logisticsUnitCapacity #logisticsUnitStatus').on('change', function () {
        $(this).parent().next().hide();
        $('#logisticsUnitStatus').css("border", "1px solid #dbdcde");
    });

    function submitForm() {
        var pass = true;
        var logisticsUnitCode = $('#logisticsUnitCode').val();
        if (logisticsUnitCode == "") {
            $('#logisticsUnitCode').next().show();
            $('#logisticsUnitCode').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }
        if (logisticsUnitCode != "" && logisticsUnitCode.length > 20) {
            layer.msg("编码长度不能超过20", {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        var logisticsUnitName = $('#logisticsUnitName').val();
        if (logisticsUnitName == "") {
            $('#logisticsUnitName').next().show();
            $('#logisticsUnitName').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }
        if (logisticsUnitName != "" && logisticsUnitName.length > 20) {
            layer.msg("名称长度不能超过20", {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        var logisticsUnitStatus = $('#logisticsUnitStatus').val();
        if (logisticsUnitStatus == "") {
            $('#logisticsUnitStatus').parent().next().show();
            $('#logisticsUnitStatus').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var logisticsUnitRemark = $("#logisticsUnitRemark").val();
        if (logisticsUnitRemark != "" && logisticsUnitRemark.length > 100) {
            layer.msg("备注不能超过100字", {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            $.ajax({
                type: "post",
                url: "/logistics/unit/save",
                data: {
                    idx: $("#logisticsUnitIdx").val(),
                    code: logisticsUnitCode,
                    name: logisticsUnitName,
                    status: logisticsUnitStatus,
                    remark: logisticsUnitRemark
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 6});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                    parent.$('#logisticsUnitTableList').datagrid('reload', {});
                },
                error: function (d) {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            });
        }
    }
</script>

<jsp:include page="../../footer.jsp"/>