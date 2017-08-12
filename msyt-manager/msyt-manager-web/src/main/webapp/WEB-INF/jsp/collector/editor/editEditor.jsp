<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="editorCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 480px;">
    <input type="hidden" id="editorIdx" value="${itemEditor.idx}">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>名称：</label>
            <input id="name" class="brandTxt required" type="text" value="${itemEditor.name}" maxlength="20">
            <p class="hint">*名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>状态：</label>
            <p class="relative">
                <select class="brandTxt" id="status">
                    <option value="">请选择</option>
                    <option value="1" ${itemEditor.status eq 1 ? 'selected' : ''}>有效</option>
                    <option value="2" ${itemEditor.status eq 2 ? 'selected' : ''}>禁用</option>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="hint">*状态必填</p>
        </div>
        <!-- 图片上传 start -->
        <input id="_targerId" type="hidden"/>
        <form id="uploadForm">
            <input name="filePath" id="_filePath" type="hidden"/>
            <input name="remark" id="_remark" type="hidden"/>
            <input id="uploading" style="display: none" type="file" name="files"
                   onchange="uploadS('',1,'uploadForm',upCallback);"/>
        </form>
        <div class="uploadImg-col">
            <span class="img-title pd"><em class="left">头像：</em><em class="ts">（支持jpg/png/gif，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">
                <%--<div class="upload-col">
                    <div class="uploadBtn" onclick="uploadClick('collect/','','upload-img1');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传小图</span>
                    </div>
                    <p class="size-ts"></p>
                    <div class="upload-img" id="upload-img1">
                        <p class="img-cont">
                            <c:if test="${not empty itemEditor.headImageSmallIdDynamicS}">
                                <img src="${itemEditor.headImageSmallIdDynamicS}"
                                     messageid='${itemEditor.headImageSmallId}' alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i></span>
                        <span class="img-name"></span>
                    </div>
                </div>--%>
                <%--<div class="upload-col">
                    <div class="uploadBtn" onclick="uploadClick('collect/','','upload-img2')">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传中图</span>
                    </div>
                    <p class="size-ts"></p>
                    <div class="upload-img" id="upload-img2">
                        <p class="img-cont">
                            <c:if test="${not empty itemEditor.headImageMiddleIdDynamicS}">
                                <img src="${itemEditor.headImageMiddleIdDynamicS}"
                                     messageid='${itemEditor.headImageMiddleId}' alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i> </span>
                        <span class="img-name"></span>
                    </div>
                </div>--%>
                <div class="upload-col">
                    <div class="uploadBtn" onclick="uploadClick('collect/','','upload-img3');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <p class="size-ts"></p>
                    <div class="upload-img" id="upload-img3">
                        <p class="img-cont">
                            <c:if test="${not empty itemEditor.headImgBigDynamicS}">
                                <img src="${itemEditor.headImgBigDynamicS}" messageid='${itemEditor.headImgBig}'
                                     alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i> </span>
                        <span class="img-name"></span>
                    </div>
                </div>
            </div>
        </div>
        <!-- 图片上传 end -->
    </div>
</div>

<script type="text/javascript">
    $('#editorCapacity #name').on('keyup', function () {
        $(this).next().hide();
        $('#name').css("border", "1px solid #dbdcde");
    });

    $('#editorCapacity #status').on('change', function () {
        $(this).parent().next().hide();
        $('#status').css("border", "1px solid #dbdcde");
    });

    function submitForm() {
        var pass = true;
        var name = $('#name').val();
        if (name == "") {
            $('#name').next().show();
            $('#name').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var status = $('#status').val();
        if (status == "") {
            $('#status').parent().next().show();
            $('#status').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            $.ajax({
                type: "post",
                url: "/collector/editor/save",
                data: {
                    idx: $("#editorIdx").val(),
                    name: name,
                    status: status,
                    headImgBig: $("#upload-img3").find('.img-cont').find('img:first').attr("messageid") || null
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 1});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                    }
                    parent.$('#editorList').datagrid('reload', {});
                },
                error: function (d) {
                    parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                }
            });
        }
    }
</script>

<jsp:include page="../../footer.jsp"/>