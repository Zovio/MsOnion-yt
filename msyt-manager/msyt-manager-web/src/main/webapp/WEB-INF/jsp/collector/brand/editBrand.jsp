<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="brandCapacity" style="overflow-y: scroll;overflow-x: hidden;height: 477px;">
    <input type="hidden" id="brandIdx" value="${itemBrand.idx}">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>品牌名称：</label>
            <input id="brandName" class="brandTxt required" type="text" value="${itemBrand.brandName}">
            <p class="hint">*品牌名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>品牌所属国：</label>
            <input class="brandTxt" type="text" id="state" value="${itemBrand.state}">
            <p class="hint">*品牌所属国必填</p>
        </div>

        <div class="winRow">
            <label class="brandName"><i>*</i>品牌状态：</label>
            <p class="relative">
                <select class="brandTxt" id="brandStatus">
                    <option value="">请选择</option>
                    <option value="1" ${itemBrand.status eq 1 ? 'selected' : ''}>有效</option>
                    <option value="2" ${itemBrand.status != 1 ? 'selected' : ''}>禁用</option>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="category-hint hint3">*品牌状态必填</p>
        </div>
        <div class="winRow2">
            <label class="brandName">品牌介绍：</label>
            <textarea class="brandTxt" id="brandIntroduce">${itemBrand.introduce}</textarea>
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
            <span class="img-title pd"><em class="left">品牌LOGO：</em><em class="ts">（支持jpg/png/gif，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">
                <div class="upload-col">
                    <div class="uploadBtn" onclick="uploadClick('collect','','upload-img3');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <p class="size-ts">120 x 320 / 60 x 160 px</p>
                    <div class="upload-img" id="upload-img3">
                        <p class="img-cont">
                            <c:if test="${not empty itemBrand.logoBigDynamicS}">
                                <img src="${itemBrand.logoBigDynamicS}" messageid='${itemBrand.logoBig}' alt="">
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
    $('#brandCapacity #brandName').on('keyup', function () {
        $(this).next().hide();
        $('#brandName').css("border", "1px solid #dbdcde");
    });

    $('#brandCapacity #state').on('keyup', function () {
        $(this).next().hide();
        $('#state').css("border", "1px solid #dbdcde");
    });

    $('#brandCapacity #brandStatus').on('change', function () {
        $(this).parent().next().hide();
        $('#brandStatus').css("border", "1px solid #dbdcde");
    });

    function submitForm() {
        var pass = true;
        var brandName = $('#brandName').val();
        if (brandName == "") {
            $('#brandCapacity .hint').show();
            $('#brandName').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var state = $('#state').val();
        if (state == "") {
            $('#state').next().show();
            $('#state').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var brandStatus = $('#brandStatus').val();
        if (brandStatus == "") {
            $('#brandCapacity .hint3').show();
            $('#brandStatus').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var brandIntroduce = $("#brandIntroduce").val();
        if (brandIntroduce != "" && brandIntroduce.length > 800) {
            layer.msg("品牌介绍不能超过800字", {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            $.ajax({
                type: "post",
                url: "/collect/br/save",
                data: {
                    idx: $("#brandIdx").val(),
                    brandName: brandName,
                    state: state,
                    logoBig: $("#upload-img3").find('.img-cont').find('img:first').attr("messageid") || null,
                    status: $("#brandStatus").val(),
                    introduce: brandIntroduce
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 6});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                    parent.$('#brandTableList').datagrid('reload', {});
                },
                error: function (d) {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            });
        }
    }
</script>

<jsp:include page="../../footer.jsp"/>