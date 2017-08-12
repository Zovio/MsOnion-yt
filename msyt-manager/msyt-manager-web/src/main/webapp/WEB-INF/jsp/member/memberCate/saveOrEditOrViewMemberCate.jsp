<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-成员类别操作相关</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>


<!-- start-->
<input type="hidden" id="idx" value="${memberCate.idx}"/>
<div id="resourceCapacity">
    <div class="winCont">

        <div class="winRow">
            <label class="brandName"><i>*</i>成员类别名称：</label>
            <input id="name" class="brandTxt required" type="text" value="${memberCate.name}">
            <p class="palce-hint hint1">*名称必填</p>
        </div>

        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark">${memberCate.remark}</textarea>
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
            $('select').prop('disabled',true);
        }
    });

    // 提交表单
    function submitForm() {
        if (!$.trim($('#name').val())) {
            $('#resourceCapacity .hint1').show();
            $('#name').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        var params = {};
        params.idx = $("#idx").val();
        params.name = $("#name").val();
        params.remark = $("#remark").val();
        $.ajax({
            type: "POST",
            url: "/memberCate/do-saveOrEdit",
            data: {
                idx: params.idx,
                name: params.name,
                remark: params.remark
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                parent.$('#memberCatelist').datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function (result) {
            parent.layer.msg(result.msg, {icon: 2});
        });

    }

</script>

</body>
</html>