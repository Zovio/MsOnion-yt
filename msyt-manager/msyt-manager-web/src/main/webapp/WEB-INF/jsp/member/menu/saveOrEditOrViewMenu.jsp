<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-菜单操作相关</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>

<body>

<!--新增菜单弹框 start-->
<input type="hidden" id="idx" value="${menu.idx}"/>
<div id="resourceCapacity" style="height: 700px;">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>菜单名称：</label>
            <input id="name" class="brandTxt required" type="text" value="${menu.name}">
            <p class="palce-hint hint1">*菜单名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>编码：</label>
            <input id="code" class="brandTxt required" type="text"
                   value="${menu.code}" ${type ne 'add' ? 'readonly="readonly"' :''} >
            <p class="palce-hint hint2">*编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName">菜单url：</label>
            <input id="url" class="brandTxt" type="text" value="${menu.url}">
            <p class="palce-hint hint3">*url必填</p>
        </div>

        <div class="winRow">
            <label class="brandName">菜单级别：</label>
            <p class="relative">
                <select class="brandTxt" id="level">
                    <option value="0" ${menu.level eq 0 ? 'selected="selected"' : ''}>请选择</option>
                    <option value="1" ${menu.level eq 1 ? 'selected="selected"' : ''}>一级菜单</option>
                    <option value="2" ${menu.level eq 2 ? 'selected="selected"' : ''}>二级菜单</option>
                    <option value="3" ${menu.level eq 3 ? 'selected="selected"' : ''}>三级菜单</option>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="winRow">
            <label class="brandName">首页顶部显示：</label>
            <p class="relative">
            <select class="brandTxt" id="topshow" disabled="disabled">
                <option value="0" ${menu.topShow eq 0 ? 'selected="selected"' : ''}>否</option>
                <option value="1" ${menu.topShow eq 1 ? 'selected="selected"' : ''}>是</option>
            </select>
            <i class="downIcon"></i>
            </p>
        </div>
        <div class="winRow">
            <label class="brandName">父级菜单：</label>
            <p class="relative">
                <select class="brandTxt" id="pidx">
                    <option value="0">无</option>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>排序：</label>
            <input id="zindex" class="brandTxt required" type="text" value="${menu.zindex}" onblur="Ms.onlyNum(this);"  onkeyup="Ms.onlyNum(this);">
            <p class="palce-hint hint4">*排序不能为空或者数字格式不对</p>
        </div>


        <div class="winRow">
            <label class="brandName">生成默认按钮：</label>
            <p class="relative">
            <select class="brandTxt" id="markButton">
                <option value="0" >否</option>
                <option value="1" ${menu.markButton eq 1 ? 'selected="selected"' : ''}>是</option>
            </select>
            <i class="downIcon"></i>
            </p>
        </div>

        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark">${menu.remark}</textarea>
        </div>
    </div>
</div>
<!--新增菜单弹框 end-->
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

            $('#level').val()==1 ? $('#topshow').prop('disabled', false) : $('#topshow').prop('disabled', true).val(0);

            $('#level').on('change',function () {
                var val = $(this).val();
                var level = parseInt(val)-1;
                initNextMenus(level,null);
                if(val == 1){
                    $('#topshow').prop('disabled', false);
                    return false;
                }
                $('#topshow').prop('disabled', true).val(0);

            });

            // 初始化
            var level = parseInt($('#level').val())-1;
            initNextMenus(level, '${menu.pidx}');
        }


        if(_type == 'view'){
            $('input,textarea').prop('readonly',true).on({
                'focus': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });
            $('select').prop('disabled',true).parent("p").parent("div").removeClass("winRow").addClass("winRow1");
        }
    });

    // 初始化菜单
    function initNextMenus(level,defaultIdx) {
        var url ='/menu/getMenusByLevel/'+level;
        var $wr = $('#pidx');
        $wr.find('option:gt(0)').remove();
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                $.each(data.data, function (index, item) {
                    var $option = $("<option>").val(item.idx).text(item.name);
                    if (defaultIdx && defaultIdx == item.idx) {
                        $option.attr("selected", true);
                    }
                    $wr.append($option);
                });
            }
        });
    }

    // 提交表单
    function submitForm() {
        if (!$.trim($('#name').val())) {
            $('#resourceCapacity .hint1').show();
            $('#name').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }

        if (!$.trim($('#code').val())) {
            $('#resourceCapacity .hint2').show();
            $('#code').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }

        if (!$.trim($('#zindex').val()) || !Ms.isNumber($.trim($('#zindex').val()))) {
            $('#resourceCapacity .hint4').show();
            $('#zindex').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        var params = {};
        params.idx = $("#idx").val();
        params.name = $("#name").val();
        params.code = $("#code").val();
        params.url = $("#url").val();
        params.pidx = $("#pidx").val();
        //params.iconUrl = $("#iconUrl").val();
        params.zindex = $("#zindex").val();
        params.level = $("#level").val();
        params.topShow = $("#topshow").val();
        params.remark = $("#remark").val();
        params.markButton = $("#markButton").val();
        $.ajax({
            type: "POST",
            url: "/menu/do-saveOrEdit",
            data: {
                idx: params.idx,
                name: params.name,
                code: params.code,
                url: params.url,
                pidx: params.pidx,
                //iconUrl:params.iconUrl,
                zindex: params.zindex,
                topShow: params.topShow,
                level: params.level,
                remark: params.remark,
                markButton: params.markButton
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {time: 3000, icon: 6});
                parent.layer.close(index);
                parent.$('#menulist').datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function (result) {
            parent.layer.msg(MS.ERROR_MSG, {icon: 2});
        });
    }
</script>

</body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>
</html>