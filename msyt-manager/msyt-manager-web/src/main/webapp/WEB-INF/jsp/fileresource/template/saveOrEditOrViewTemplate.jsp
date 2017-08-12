<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-文件模板操作相关</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>


<style>
    .filetemplate-btn{background: #00d5b4;
        color: #FFFFFF;
        font-size: 14px;
        border: none;
        width: 58px;
        height: 30px;
        display: inline-block;
        position: absolute;
        display: none;
    }
    .filename-span{width: 160px; overflow: hidden; text-overflow:ellipsis; white-space: nowrap;}

</style>
<!-- start-->
<input type="hidden" id="idx" value="${template.idx}"/>
<div id="resourceCapacity">
    <div class="winCont">

        <div class="winRow">
            <label class="brandName"><i>*</i>模板标题：</label>
            <input id="title" class="brandTxt required" type="text"
                   value="${template.title}" ${(type eq 'view') ? 'readonly="readonly"' :''}>
        </div>
        <div class="winRow">
            <label class="brandName">模板类别：</label>
            <p class="relative">
                <select class="brandTxt" id="categoryId" ${type ne 'add' ? 'disabled="disabled"' :''}>
                    <c:forEach items="${categoryMap}" var="m">
                        <option value="${m.key}" ${template.categoryId eq m.key ? "selected=selected" : ""}>${m.value}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="winRow">
            <label class="brandName">应用平台：</label>
            <p class="relative">
                <select class="brandTxt" id="appPlatform" ${type ne 'add' ? 'disabled="disabled"' :''}>
                    <c:forEach items="${appPlatformMap}" var="m">
                        <option value="${m.key}" ${template.appPlatform eq m.key ? "selected=selected" : ""}>${m.value}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>文件：</label>
            <form id="uploadAttachment">
                <input name="filePath" value="filetemplate" type="hidden"/>

                <c:if test="${not empty template.fileName}">
                    <div id="attachFileDiv" >
                        <span class="filename-span">${template.fileName}</span>
                        <a onclick="delTemplate();" style="color:#00d5b4;padding-left: 15px;cursor: pointer" title="删除">X</a>
                    </div>
                </c:if>

                <input  type="file" name="file" id="attachFile" <c:if test="${not empty template.fileMessageId}">style="display: none"</c:if> />
                <input type="button" id="filetemplate-btn" class="filetemplate-btn" value="上传" onclick="uploadAttach();"/>
            </form>
            <input id="fileMessageId" type="hidden" value="${template.fileMessageId}"/>
            <input id="fileName" type="hidden" value="${template.fileName}"/>
        </div>

        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt"
                      id="remark" ${type eq 'view' ? 'readonly="readonly"' :''}>${template.remark}</textarea>
        </div>

    </div>
</div>


<script type="text/javascript">

    $(function () {
        var _type = '${type}';

        if (_type != 'view') {
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

        if (_type == 'view') {
            $('input,textarea').prop('readonly', true).on({
                'focus': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });

            $('#attachFileDiv a').remove();
        }

        $('#attachFile').on('change',function () {
            $('#filetemplate-btn').show();
        })

    });


    // 提交表单
    function submitForm() {
        var title = $.trim($('#title').val());
        var categoryId = $.trim($('#categoryId').val());
        var appPlatform = $.trim($('#appPlatform').val());
        var remark = $.trim($('#remark').val());
        var fileMessageId = $.trim($('#fileMessageId').val());
        var fileName = $('#fileName').val();
        var message = "";
        if (!title) {
            message += "模板标题不能为空;";
        }
        if (!fileMessageId) {
            message += "文件不能为空;";
        }
        if(message){
            layer.msg(message, {icon: 2});
            parent.layer.closeAll('loading');
            return false;
        }

        var params = {};
        params.idx = $("#idx").val();
        params.title = title;
        params.categoryId = categoryId;
        params.appPlatform = appPlatform;
        params.fileMessageId = fileMessageId;
        params.fileName = fileName;
        params.remark = remark;

        $.ajax({
            type: "POST",
            url: "/fileTemplate/do-saveOrEdit",
            data: params,
            cache: false,
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                parent.$('#fileTemplate-list').datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function (result) {
            parent.layer.msg(Ms.ERROR_MSG, {icon: 2});
        });
    }

    function delTemplate() {
        $('#attachFileDiv').remove();
        $('#fileMessageId').val('');
        $('#fileName').val('');
        $('#attachFile').show();
       // $('#filetemplate-btn').show();
    }

    // 上传附件
    function uploadAttach() {
        var file = document.getElementById("attachFile");
        var f = file.value;
        if (!f) {
            layer.msg('请选择文件', {icon: 2, offset: '100px'});
            return false;
        }
        var suffix = '${templateSuffix}';
        var suffixArr =[];
        suffixArr = suffix ? suffix.split("|"): MS.ATTACHMENT;
        if ($.inArray(MS.getFileExtension(f),suffixArr) < 0) {
            document.getElementById('uploadAttachment').reset();
            layer.msg('文件格式不对，目前只支持' + suffixArr.join(','), {icon: 2, offset: '100px'});
            return false;
        }
        var maxsize =${templateSize} || MSYT.PIC_UPLOAD_MAX_SIZE;
        if (parseInt(maxsize) < MS.getFileSize(file)) {
            layer.msg('文件过大，目前最大只支持' + maxsize +"B", {icon: 2, offset: '100px'});
            document.getElementById('uploadAttachment').reset();
            return false;
        }

        var formData = new FormData($('#uploadAttachment')[0]);
        var inx = layer.msg('上传中', {icon: 16, offset: '100px', shade: 0.01, time: MSYT.UPLOAD_TIME});
        $.ajax({
            url: fileResourceApi + '/cos/uploadFiles',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false
        }).done(function (result) {
            layer.close(inx);
            if (result && result.status == 200) {
                if (result.data.length == 0) {
                    layer.msg('上传失败', {icon: 2, offset: '100px'});
                    return;
                }
                if (result.data[0].uploadStatus == 1) {
                    layer.msg('上传成功,保存后生效', {icon: 6, offset: '100px'});
                    $('#fileMessageId').val(result.data[0].messageId);
                    $('#fileName').val(result.data[0].name);
                } else {
                    layer.msg('上传文件失败', {icon: 2, offset: '100px'});
                }
            } else {
                layer.msg('上传文件失败', {icon: 2, offset: '100px'});
            }
        }).fail(function () {
            layer.close(inx);
            layer.msg('上传文件失败', {icon: 2, offset: '100px'});
            document.getElementById('uploadAttachment').reset();
        });
    }
</script>

</body>
</html>