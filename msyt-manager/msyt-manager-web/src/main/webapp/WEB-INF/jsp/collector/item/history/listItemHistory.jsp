<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="main-content">
            <div class="historyContent">
                <div class="easyui-tabs" data-options="tabHeight:36" style="width: 100%;min-height:180px;">
                    <div title="基本信息" data-options="href:'/collect/item/history/basic/${idxStr}'"></div>
                    <div title="商品竞价" data-options="href:'/collect/item/history/bidding/${idxStr}'"></div>
                    <div title="商品价格" data-options="href:'/collect/item/history/price/${idxStr}'"></div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../../../footer.jsp"/>