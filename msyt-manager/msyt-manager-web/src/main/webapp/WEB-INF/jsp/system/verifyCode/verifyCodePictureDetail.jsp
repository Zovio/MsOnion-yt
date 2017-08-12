<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="verifyCodef-div" style="display: block;">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName">类型：</label>
            <p class="relative">
                ${verifyCodePictureTypeName}
            </p>
        </div>
        <div class="winRow">
            <label class="brandName">状态：</label>
            <p class="relative">
                ${statusName}
            </p>
        </div>
        <div class="uploadImg-col">
            <span class="img-title pd"><em class="left">验证码图片：</em></span>
            <div class="upload-cont wtdm fr">
                <div class="upload-col">
                    <div class="upload-img" id="verifyCodef-upload-img">
                        <p class="img-cont">
                                <img src="${picimgurl}${sysVerifyCodePicture.imagePath}" >
                        </p>
                        <span class="img-name"></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="winRow2">
            <label class="brandName">备注：</label>
            <p class="relative">
                ${sysVerifyCodePicture.remark}
            </p>
        </div>
        <div class="winRow">
            <label class="brandName">创建者：</label>
            <p class="relative">
                ${createMember.name}
            </p>
        </div>
        <div class="winRow">
            <label class="brandName">创建时间：</label>
            <p class="relative">
                ${createTime}
            </p>
        </div>
        <!-- 图片上传 end -->
    </div>
</div>

<jsp:include page="../../footer.jsp"/>