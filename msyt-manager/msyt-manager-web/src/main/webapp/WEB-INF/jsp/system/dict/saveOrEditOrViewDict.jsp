<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-角色操作相关</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>


<!-- start-->
<input type="hidden" id="idx" value="${sysDataDictionary.idx}"/>
<div id="resourceCapacity">
    <div class="winCont">

        <div class="winRow">
            <label class="brandName"><i>*</i>字典编码：</label>
            <input id="dictCode" onkeyup="MS.onlyEnOrNumber(this);" onblur="MS.onlyEnOrNumber(this);" class="brandTxt required" type="text" value="${sysDataDictionary.dictCode}" ${(type eq 'view' or type eq 'edit') ? 'readonly="readonly"' :''} />
            <p class="palce-hint hint1">*字典编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>字典值：</label>
            <input id="dictKey" class="brandTxt required" type="text" onkeyup="MS.onlyEnOrNumber(this);" onblur="MS.onlyEnOrNumber(this);"
                   value="${sysDataDictionary.dictKey}" ${type eq 'view' ? 'readonly="readonly"' :''} >
            <p class="palce-hint hint2">*字典值必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>字典名称：</label>
            <input id="dictValue" class="brandTxt required" type="text"
                   value="${sysDataDictionary.dictValue}" ${type eq 'view' ? 'readonly="readonly"' :''} >
            <p class="palce-hint hint3">*字典名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>排序：</label>
            <input id="zIndex" class="brandTxt required" type="text" onblur="Ms.onlyNum(this);"  onkeyup="Ms.onlyNum(this);"
                    value="${sysDataDictionary.zindex}" ${type eq 'view' ? 'readonly="readonly"' :''} >
            <p class="palce-hint hint4">*排序必填</p>
        </div>
        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark" ${type eq 'view' ? 'readonly="readonly"' :''}>${sysDataDictionary.remark}</textarea>
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
        var dictCode = $.trim($('#dictCode').val());
        var dictKey = $.trim($('#dictKey').val());
        var dictValue = $.trim($('#dictValue').val());
        var zIndex = $.trim($('#zIndex').val());
        var remark = $.trim($('#remark').val());

        if (!dictCode) {
            $('#resourceCapacity .hint1').show();
            $('#dictCode').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        if (!dictKey) {
            $('#resourceCapacity .hint2').show();
            $('#dictKey').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        if (!dictValue) {
            $('#resourceCapacity .hint3').show();
            $('#dictValue').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        if (!zIndex) {
            $('#resourceCapacity .hint4').show();
            $('#zIndex').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        var params = {};
        params.idx = $("#idx").val();
        params.dictCode = dictCode;
        params.dictKey = dictKey;
        params.dictValue = dictValue;
        params.zindex = zIndex;
        params.remark = remark;

        $.ajax({
            type: "POST",
            url: "/sysDict/do-saveOrEdit",
            data: params,
            cache: false,
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                parent.$('#sysDictlist').datagrid('reload', {});
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