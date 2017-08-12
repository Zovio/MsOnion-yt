<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../header.jsp"/>

<div class="main" style="margin-bottom: 0;padding-left: 10px;padding-right: 10px;padding-top: 0;">
    <div class="table-container">
        <div class="main-content">
            <div class="editor" style="min-height: 100px;margin-bottom: 0;margin-top: 0;padding-bottom: 0;">
                <p class="identification"><span class="identification-sp">当前信息</span></p>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">市场价：</span>
                        <span class="right-value">${itemPrice.marketPriceDots2}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">售价：</span>
                        <span class="right-value">${itemPrice.sellingPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">供货价：</span>
                        <span class="right-value">${itemPrice.supplyPriceDots2}</span>
                    </div>
                </div>
            </div>
            <div class="editor" style="min-height: 100px;margin-bottom: 0;margin-top: 0;padding-bottom: 0;">
                <p class="identification"><span class="identification-sp">历史记录</span></p>
                <div class="yt-table" style="min-height: 310px;height: 310px;padding: 10px 0px 10px 0px;">
                    <table id="historyPrice" style="width:100%;height: 290px;"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#historyPrice').datagrid({
            url: '/collect/item/history/price/grid?itemIdx=${itemIdx}',
            idField: 'id',
            rownumbers: true,
            singleSelect: false,
            iconCls: 'icon-save',
            pagination: true,
            method: 'post',
            columns: [[
                {title: '市场价', field: 'marketPriceDots2', width: '20%', align: 'center', sortable: true},
                {title: '售价', field: 'sellingPriceDots2', width: '20%', align: 'center', sortable: true},
                {title: '供货价', field: 'supplyPriceDots2', width: '20%', align: 'center', sortable: true},
                {title: '操作人', field: 'createByMemberIdxDynamicS', width: '10%', align: 'center', sortable: true},
                {title: '操作时间', field: 'createTimeEnYyyyMMddHHmmss', width: '20%', align: 'center', sortable: true}
            ]]
        });
    });
</script>

<jsp:include page="../../../footer.jsp"/>