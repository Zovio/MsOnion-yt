<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="propertyCapacity" style="overflow-y:auto;overflow-x:hidden;height: 327px;">
    <input type="hidden" id="attributeIdx" value="${attribute.idx}">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>属性值名称：</label>
            <input id="propertyCName" class="brandTxt" type="text" value="${attribute.name}" maxlength="10">
            <p class="hint">*属性值名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>是否选项：</label>
            <p class="relative">
                <select class="brandTxt" id="isSelect">
                    <option value="">请选择</option>
                    <option value="1" ${(attribute.isSelect != null) && (attribute.isSelect) ? 'selected' : ''}>是
                    </option>
                    <option value="0" ${(attribute.isSelect != null) && (!attribute.isSelect) ? 'selected' : ''}>否
                    </option>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="hint">*是否选项必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>是否显示：</label>
            <p class="relative">
                <select class="brandTxt" id="isShow">
                    <option value="">请选择</option>
                    <option value="1" ${(attribute.isShow != null) && (attribute.isShow) ? 'selected' : ''}>是</option>
                    <option value="0" ${(attribute.isShow != null) && (!attribute.isShow) ? 'selected' : ''}>否</option>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="hint">*是否显示必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>是否关键属性：</label>
            <p class="relative">
                <select class="brandTxt" id="isKey">
                    <option value="">请选择</option>
                    <option value="1" ${(attribute.isKey != null) && (attribute.isKey) ? 'selected' : ''}>是</option>
                    <option value="0" ${(attribute.isKey != null) && (!attribute.isKey) ? 'selected' : ''}>否</option>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="hint">*是否显示必填</p>
        </div>
        <div class="winRow2" style="margin-bottom:10px">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark">${attribute.remark}</textarea>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#propertyCName').on('keyup', function () {
            $('#propertyCName').css("border", "1px solid #dbdcde");
            $(this).next().hide();
        });

        $('#isSelect').on('change', function () {
            $('#isSelect').css("border", "1px solid #dbdcde");
            $(this).parent().next().hide();
        });

        $('#isShow').on('change', function () {
            $('#isShow').css("border", "1px solid #dbdcde");
            $(this).parent().next().hide();
        });

        $('#isKey').on('change', function () {
            $('#isKey').css("border", "1px solid #dbdcde");
            $(this).parent().next().hide();
        });
    });

    function submitForm() {
        var pass = true;
        var propertyCName = $('#propertyCName').val();
        if (propertyCName == "") {
            $('#propertyCName').css("border", "1px solid #ff2c41");
            $('#propertyCName').next().show();
            Ms.closeLoading();
            pass = false;
        }

        var isSelect = $('#isSelect').val();
        if (isSelect == "") {
            $('#isSelect').css("border", "1px solid #ff2c41");
            $('#isSelect').parent().next().show();
            Ms.closeLoading();
            pass = false;
        }

        var isShow = $('#isShow').val();
        if (isShow == "") {
            $('#isShow').css("border", "1px solid #ff2c41");
            $('#isShow').parent().next().show();
            Ms.closeLoading();
            pass = false;
        }

        var isKey = $("#isKey");
        if (!isKey.val()) {
            isKey.css("border", "1px solid #ff2c41");
            isKey.parent().next().show();
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            $.ajax({
                type: "post",
                url: "/collect/attribute/save",
                data: {
                    idx: $("#attributeIdx").val(),
                    name: propertyCName,
                    isSelect: isSelect,
                    isShow: isShow,
                    isKey: isKey.val(),
                    remark: $("#remark").val()
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 6});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                    parent.$('#propertyTree').treegrid('reload', {});
                },
                error: function (d) {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            });
        }
    }
</script>

<jsp:include page="../../footer.jsp"/>