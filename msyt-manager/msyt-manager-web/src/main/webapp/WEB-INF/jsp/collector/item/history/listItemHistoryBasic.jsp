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
                        <span class="left-key">中文品名：</span>
                        <span class="right-value">${item.cnName}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">英文品名：</span>
                        <span class="right-value">${item.enName}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">品牌：</span>
                        <span class="right-value">${brandName}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">产地：</span>
                        <span class="right-value">${originCnName}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">商品规格：</span>
                        <span class="right-value">${item.specification}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">批次：</span>
                        <span class="right-value">${item.batch}</span>
                    </div>
                </div>
            </div>
            <div class="editor" style="min-height: 100px;margin-bottom: 0;margin-top: 0;padding-bottom: 0;">
                <p class="identification"><span class="identification-sp">历史记录</span></p>
                <div class="yt-table" style="min-height: 280px;height: 280px;padding: 10px 0px 10px 0px;">
                    <table id="historyBasic" style="width:100%;height: 260px;"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#historyBasic').datagrid({
            url: '/collect/item/history/basic/grid?itemIdx=${itemIdx}',
            idField: 'id',
            rownumbers: true,
            singleSelect: false,
            iconCls: 'icon-save',
            pagination: true,
            method: 'post',
            columns: [[
                {title: '中文品名', field: 'cnName', width: '15%', align: 'center', sortable: true},
                {title: '英文品名', field: 'enName', width: '15%', align: 'center', sortable: true},
                {title: '品牌', field: 'brandName', width: '10%', align: 'center'},
                {title: '产地', field: 'originCnName', width: '10%', align: 'center'},
                {title: '商品规格', field: 'specification', width: '10%', align: 'center'},
                {title: '批次', field: 'batch', width: '10%', align: 'center', sortable: true},
                {title: '操作人', field: 'createByMemberName', width: '10%', align: 'center', sortable: true},
                {title: '操作时间', field: 'createTime', width: '20%', align: 'center', sortable: true}
            ]]
        });
    });
</script>

<jsp:include page="../../../footer.jsp"/>