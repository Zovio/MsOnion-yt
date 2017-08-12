<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-系统设置操作相关</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>


<!-- start-->
<input type="hidden" id="idx" value="${sysSetting.idx}"/>
<div id="resourceCapacity">
    <div class="winCont">

        <div class="winRow">
            <label class="brandName"><i>*</i>设置编码：</label>
            <input id="settingKey" class="brandTxt required" onkeyup="MS.onlyEnOrNumber(this);" onblur="MS.onlyEnOrNumber(this);" type="text" value="${sysSetting.settingKey}" ${(type eq 'view' or type eq 'edit') ? 'readonly="readonly"' :''}>
            <p class="palce-hint hint1">*设置编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>设置值：</label>
            <input id="settingValue" class="brandTxt required" type="text"
                   value="${sysSetting.settingValue}" ${type eq 'view' ? 'readonly="readonly"' :''} >
            <p class="palce-hint hint2">*设置值必填</p>
        </div>

        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark" ${type eq 'view' ? 'readonly="readonly"' :''}>${sysSetting.remark}</textarea>
        </div>

    </div>
</div>



<script type="text/javascript">

    $(function () {
        var _type = '${type}';

        if(_type != 'view') {
            $('#resourceCapacity .required').on({
                'click': function () {
                    $(this).next().hide();
                    $(this).css("border", "1px solid #00d5b4");
                },
                'blur': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });
        }

        if(_type == 'view'){
            $('input,textarea').prop('readonly',true).on({
                'focus': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });

        }

    });



    // 提交表单
    function submitForm() {
        var settingKey = $.trim($('#settingKey').val());
        var settingValue = $.trim($('#settingValue').val());
        var remark = $.trim($('#remark').val());

        if (!settingKey) {
            $('#resourceCapacity .hint1').show();
            $('#settingKey').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        if (!settingValue) {
            $('#resourceCapacity .hint2').show();
            $('#settingValue').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }

        var params = {};
        params.idx = $("#idx").val();
        params.settingKey = settingKey;
        params.settingValue = settingValue;
        params.remark = remark;

        $.ajax({
            type: "POST",
            url: "/sysset/do-saveOrEdit",
            data: params,
            cache: false,
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                parent.$('#syssetlist').datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function (result) {
            parent.layer.msg(Ms.ERROR_MSG, {icon: 2});
        });
    }


</script>

</body>
</html>