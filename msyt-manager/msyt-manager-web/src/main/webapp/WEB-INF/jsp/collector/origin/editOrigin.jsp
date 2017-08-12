<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="placeCapacity" style="display: block;">
    <input type="hidden" id="originIdx" value="${itemOrigin.idx}">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>产地编码：</label>
            <input id="placeCoding" class="brandTxt required" placeholder="请输入2-4位字符" type="text"
                   value="${itemOrigin.originCode}" maxlength="4">
            <p class="palce-hint hint1">*产地编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>海关编码：</label>
            <input id="customsCoding" class="brandTxt required" placeholder="请输入3位字符" type="text"
                   value="${itemOrigin.customsCode}" maxlength="3">
            <p class="palce-hint hint2">*海关编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>中文名称：</label>
            <input id="placeCnName" class="brandTxt required" placeholder="最多10位字符" type="text"
                   value="${itemOrigin.cnName}" maxlength="10">
            <p class="palce-hint hint3">*中文名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName">英文名称：</label>
            <input id="placeEnName" class="brandTxt" type="text" placeholder="最多30位字符" value="${itemOrigin.enName}">
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>产地状态：</label>
            <p class="relative">
                <select class="brandTxt" id="originStatus">
                    <option value="">请选择</option>
                    <option value="1" ${itemOrigin.status eq 1 ? 'selected' : ''}>有效</option>
                    <option value="2" ${itemOrigin.status eq 2 ? 'selected' : ''}>禁用</option>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="palce-hint hint4">*产地状态必填</p>
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
            <span class="img-title pd"><em class="left">产地图片：</em><em class="ts">（支持jpg/png/gif，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">

                <div class="upload-col">
                    <div class="uploadBtn" onclick="uploadClick('collect','','upload-img3');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <p class="size-ts">440 x 260</p>
                    <div class="upload-img" id="upload-img3">
                        <p class="img-cont">
                            <c:if test="${not empty itemOrigin.imageBigDynamicS}">
                                <img src="${itemOrigin.imageBigDynamicS}" messageid='${itemOrigin.imageBig}' alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i> </span>
                        <span class="img-name"></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="uploadImg-col">
            <span class="img-title pd"><em class="left">国旗图片：</em><em class="ts">（支持jpg/png/gif，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">
                <div class="upload-col">
                    <div class="uploadBtn" onclick="uploadClick('collect','','upload-img6');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <p class="size-ts">80 x 56</p>
                    <div class="upload-img" id="upload-img6">
                        <p class="img-cont">
                            <c:if test="${not empty itemOrigin.nationalFlagImageBigDynamicS}">
                                <img src="${itemOrigin.nationalFlagImageBigDynamicS}"
                                     messageid='${itemOrigin.nationalFlagImageBig}' alt="">
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
    $('#placeCapacity #placeCoding').on('keyup', function () {
        $(this).next().hide();
        $('#placeCoding').css("border", "1px solid #dbdcde");
    });

    $('#placeCapacity #customsCoding').on('keyup', function () {
        $(this).next().hide();
        $('#customsCoding').css("border", "1px solid #dbdcde");
    });

    $('#placeCapacity #placeCnName').on('keyup', function () {
        $(this).next().hide();
        $('#placeCnName').css("border", "1px solid #dbdcde");
    });

    $('#placeCapacity #originStatus').on('change', function () {
        $(this).parent().next().hide();
        $('#originStatus').css("border", "1px solid #dbdcde");
    });

    function submitForm() {
        var pass = true;

        var placeCoding = $('#placeCoding').val();
        if (placeCoding == "") {
            $('#placeCapacity .hint1').show();
            $('#placeCoding').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }
        if (placeCoding.length < 2 || placeCoding.length > 4) {
            layer.msg('产地编码必须2-4位！', {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        var customsCoding = $('#customsCoding').val();
        if (customsCoding == "") {
            $('#placeCapacity .hint2').show();
            $('#customsCoding').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }
        if (customsCoding.length != 3) {
            layer.msg('海关编码必须3位！', {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        var placeCnName = $('#placeCnName').val();
        if (placeCnName == "") {
            $('#placeCapacity .hint3').show();
            $('#placeCnName').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var originStatus = $('#originStatus').val();
        if (originStatus == "") {
            $('#placeCapacity .hint4').show();
            $('#originStatus').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            $.ajax({
                type: "post",
                url: "/collect/place/save",
                data: {
                    idx: $("#originIdx").val(),
                    originCode: placeCoding,
                    customsCode: customsCoding,
                    cnName: placeCnName,
                    enName: $("#placeEnName").val(),
                    nationalFlagImageBig: $("#upload-img6").find('.img-cont').find('img:first').attr("messageid") || null,
                    imageBig: $("#upload-img3").find('.img-cont').find('img:first').attr("messageid") || null,
                    status: originStatus
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
                    parent.$('#placeTableList').datagrid('reload', {});
                },
                error: function (d) {
                    parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                }
            });
        }
    }
</script>

<jsp:include page="../../footer.jsp"/>