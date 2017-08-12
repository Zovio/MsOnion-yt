<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="brandCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 500px;">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName">品牌名称：</label>
            <input id="brandCName" class="brandTxt required" type="text" value="${itemBrand.brandName}" readonly>
        </div>
        <div class="winRow">
            <label class="brandName">品牌所属国：</label>
            <input class="brandTxt" type="text" id="state" value="${itemBrand.state}" readonly>
        </div>

        <div class="winRow">
            <label class="brandName">品牌状态：</label>
            <p class="relative">
                <select class="brandTxt" id="brandStatus" disabled>
                    <option value="">请选择</option>
                    <c:forEach items="${statusMap}" var="p">
                        <option value="${p.key}" ${itemBrand.status eq p.key ? 'selected' : ''}>${p.value}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="winRow2">
            <label class="brandName">品牌介绍：</label>
            <textarea class="brandTxt" id="brandIntroduce" readonly>${itemBrand.introduce}</textarea>
        </div>
        <div class="uploadImg-col">
            <span class="img-title pd"><em class="left">品牌LOGO：</em><em class="ts">（支持jpg/png/gif，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">

                <div class="upload-col">
                    <div class="upload-img" id="upload-img3">
                        <p class="img-cont">
                            <c:if test="${not empty itemBrand.logoBigDynamicS}">
                                <img src="${itemBrand.logoBigDynamicS}"/>
                            </c:if>
                        </p>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../footer.jsp"/>