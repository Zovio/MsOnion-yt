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
                        <span class="left-key">亚马逊售价：</span>
                        <span class="right-value">${itemBidding.amazonPriceDots2}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">天猫售价：</span>
                        <span class="right-value">${itemBidding.tmallPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">京东售价：</span>
                        <span class="right-value">${itemBidding.jdPriceDots2}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">小红书售价：</span>
                        <span class="right-value">${itemBidding.redPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">国外官网售价：</span>
                        <span class="right-value">${itemBidding.abroadPriceDots2}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">国内官网售价：</span>
                        <span class="right-value">${itemBidding.domesticPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">原产国售价：</span>
                        <span class="right-value">${itemBidding.originCountryPriceDots2}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">考拉售价：</span>
                        <span class="right-value">${itemBidding.koalaPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">聚美售价：</span>
                        <span class="right-value">${itemBidding.jumeiPriceDots2}</span>
                    </div>
                </div>
            </div>
            <div class="editor" style="min-height: 100px;margin-bottom: 0;margin-top: 0;padding-bottom: 0;">
                <p class="identification"><span class="identification-sp">历史记录</span></p>
                <div class="yt-table" style="min-height: 220px;height: 220px;padding: 10px 0px 10px 0px;">
                    <table id="historyBidding" style="width:100%;height: 200px;"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#historyBidding').datagrid({
            url: '/collect/item/history/bidding/grid?itemIdx=${itemIdx}',
            idField: 'id',
            rownumbers: true,
            singleSelect: false,
            iconCls: 'icon-save',
            pagination: true,
            method: 'post',
            columns: [[
                {title: '亚马逊售价', field: 'amazonPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '天猫售价', field: 'tmallPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '京东售价', field: 'jdPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '小红书售价', field: 'redPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '国外官网售价', field: 'abroadPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '国内官网售价', field: 'domesticPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '原产国售价', field: 'originCountryPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '考拉售价', field: 'koalaPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '聚美售价', field: 'jumeiPriceDots2', width: '8%', align: 'center', sortable: true},
                {title: '操作人', field: 'createByMemberIdxDynamicS', width: '10%', align: 'center', sortable: true},
                {title: '操作时间', field: 'createTimeEnYyyyMMddHHmmss', width: '20%', align: 'center', sortable: true}
            ]]
        });
    });
</script>

<jsp:include page="../../../footer.jsp"/>