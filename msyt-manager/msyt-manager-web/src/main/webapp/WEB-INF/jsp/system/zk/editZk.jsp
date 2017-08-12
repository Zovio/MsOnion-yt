<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../../header.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统_编辑ZK值</title>
</head>
<body>
<div id="brandCapacity" style="height: 700px;">
    <div class="winCont" style="height: 700px">
        <div class="winRow">
            <label class="brandName">path：</label>
            <span  class="brandTxt" type="text" readonly="readonly" style="border:none;padding:0;
                overflow-y:hidden;white-space:nowrap">${zk.fullPath}</span>
        </div>
        <div class="winRow">
            <label class="brandName">值：</label>
            <input id="value" class="brandTxt required" type="text" value="${zk.value}"
                   onkeyup="MS.isNumber(this)" onblur="MS.isNumber(this)" />
            <p class="hint">*必填</p>
        </div>
    </div>
</div>

<script>
    function submitForm() {
        var pass = true;
        var value = $('#value').val();
        if (!value) {
            $('#value .hint').show();
            $('#value').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            var fp = '${zk.fullPath}';
            fp = MS.base64_encode(fp);
            $.ajax({
                type: "post",
                url: "/system/zk/saveZKValue/" + fp + "/" + $("#value").val(),
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 6});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                    parent.getZKValue("${zk.fullPath}");
                },
                error: function (d) {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            });
        }

    }
</script>
</body>
</html>
