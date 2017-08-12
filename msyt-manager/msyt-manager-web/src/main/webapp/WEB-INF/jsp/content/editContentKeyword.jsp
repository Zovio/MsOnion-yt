<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-编辑关键词</title>
    <jsp:include page="../member/common/memberCommon.jsp"></jsp:include>

</head>
<body>

<!-- start-->
<form id="dataForm">
    <input type="hidden" id="idx" name="idx" value="${contentKeyword.data.idx}"/>
    <div id="resourceCapacity">
        <div class="winCont">

            <div class="winRow">
                <label class="brandName"><i>*</i>名称：</label>
                <input id="keyword" class="brandTxt required" name="keyword" type="text" value="${contentKeyword.data.keyword}">
                <p class="palce-hint hint1">*名称必填</p>
            </div>
            <div class="winRow">
                <label class="brandName"><i>*</i>首字母：</label>
                <input id="firstWord" class="brandTxt required" name="firstWord" size="1" type="text" value="${contentKeyword.data.firstWord}">
                <p class="palce-hint hint2">*首字母必填</p>
            </div>
            <div class="winRow2">
                <label class="brandName">备注：</label>
                <textarea class="brandTxt" id="remark">${contentKeyword.data.remark}</textarea>
            </div>
            <div class="winRow">
                <p class="palce-hint hint3">*备注必填</p>
            </div>
        </div>
    </div>
</form>


<script type="text/javascript">


    // 提交表单
    function submitForm() {
        var keyword=$.trim($('#keyword').val());
        var idx=$.trim($('#idx').val());
        if (!keyword) {
            $('#resourceCapacity .hint1').show();
            $('#keyword').css("border", "1px solid #ff2c41");
            return false;
        }else{
            $('#resourceCapacity .hint1').hide();
            $('#keyword').css("border", "1px solid #dbdcde");
        }
        var firstWord=$.trim($('#firstWord').val());
        if (!firstWord) {
            $('#resourceCapacity .hint2').show();
            $('#firstWord').css("border", "1px solid #ff2c41");
            return false;
        }else{
            $('#resourceCapacity .hint2').hide();
            $('#firstWord').css("border", "1px solid #dbdcde");
        }
//        var remark=$.trim($('#remark').val());
//        if (!remark) {
//            $('#resourceCapacity .hint3').show();
//            $('#remark').css("border", "1px solid #ff2c41");
//            return false;
//        }else{
//            $('#resourceCapacity .hint3').hide();
//            $('#remark').css("border", "1px solid #dbdcde");
//        }
        var remark=$.trim($('#remark').val());
        var params = {};
        params.idx = idx;
        params.keyword = keyword;
        params.firstWord = firstWord;
        params.remark = remark;
        $.ajax({
            type: "POST",
            url: "/content/keywd/addKeyword",
            data: params,
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                parent.$keywordListGrid.datagrid('reload', {});
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