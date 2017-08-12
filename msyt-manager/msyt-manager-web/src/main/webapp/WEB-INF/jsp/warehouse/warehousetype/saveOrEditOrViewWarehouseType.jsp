<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-仓库操作相关</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>


<!-- start-->
<input type="hidden" id="idx" value="${warehouseType.idx}"/>
<div id="resourceCapacity">
    <div class="winCont">

        <div class="winRow">
            <label class="brandName"><i>*</i>仓库名称：</label>
            <input id="warehouseName" class="brandTxt required"   type="text" value="${warehouseType.warehouseName}" >
            <p class="palce-hint hint1">*仓库名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>仓库编码：</label>
            <input id="warehouseCode" class="brandTxt required" type="text" onkeyup="MS.onlyNum(this);" value="${warehouseType.warehouseCode}">
            <p class="palce-hint hint2">*仓库编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>货号尾数：</label>
            <input id="mantissa" class="brandTxt required" type="text" onkeyup="MS.onlyNum(this);" maxlength="2"
                   value="${warehouseType.mantissa}" ${type eq 'edit' ? 'disabled="disabled"' :''} >
            <p class="palce-hint hint3">*货号尾数必填</p>
        </div>
        <div class="winRow">
            <label class="brandName">排序：</label>
            <input id="zindex" class="brandTxt required" onkeyup="MS.onlyNum(this);" onblur="MS.onlyNum(this);" type="text" value="${warehouseType.zindex}" >
        </div>
        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark" >${warehouseType.remark}</textarea>
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
        var warehouseName = $.trim($('#warehouseName').val());
        var warehouseCode = $.trim($('#warehouseCode').val());
        var mantissa = $.trim($('#mantissa').val());
        var zindex = $.trim($('#zindex').val());
        var remark = $.trim($('#remark').val());

        if (!warehouseName) {
            $('#resourceCapacity .hint1').show();
            $('#warehouseName').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        if (!warehouseCode) {
            $('#resourceCapacity .hint2').show();
            $('#warehouseCode').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }

        if (!mantissa) {
            $('#resourceCapacity .hint3').show();
            $('#mantissa').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }

        var params = {};
        params.idx = $("#idx").val();
        params.warehouseName = warehouseName;
        params.warehouseCode = warehouseCode;
        params.mantissa = mantissa;
        params.zindex = zindex;
        params.remark = remark;

        $.ajax({
            type: "POST",
            url: "/wareho/waretype/do-saveOrEdit",
            data: params,
            cache: false,
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg(MSYT.SUC_MSG, {icon: 6});
                parent.layer.close(index);
                parent.$('#warehouseTypeTable').datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function () {
            parent.layer.msg(Ms.ERROR_MSG, {icon: 2});
        });
    }


</script>

</body>
</html>