<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<section class="main">
    <div class="table-container">
        <div class="main-content">
            <div class="screen-cont">
                <span class="screen-show"></span>
                <span class="screen-hide"></span>
                <span class="search itemTableList-qry" style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">商品ID：</label>
                            <input class="txt margin-r-20" type="text" id="itemLog-idx">
                        </div>
                        <div class="screen-col">
                            <label class="name">中文品名：</label>
                            <input class="txt margin-r-20" type="text" id="itemLog-cnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">操作人：</label>
                            <input class="txt margin-r-20" type="text" id="itemLog-userName">
                        </div>
                        <div class="screen-col">
                            <label class="name">开始时间：</label>
                            <input id="itemLog-startTime" type="text" class="txt margin-r-20" value="" readonly
                                   onclick="WdatePicker({maxDate:'#F{$dp.$D(\'itemLog-endTime\')}',lang:'zh-cn', skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                   style="width: 150px;"/>
                        </div>
                        <div class="screen-col">
                            <label class="name">结束时间：</label>
                            <input id="itemLog-endTime" type="text" class="txt margin-r-20" value="" readonly
                                   onclick="WdatePicker({minDate:'#F{$dp.$D(\'itemLog-startTime\')}',lang:'zh-cn', skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                   style="width: 150px;"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="yt-table">
                <table id="itemLog-commodityTableList" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#itemLog-commodityTableList').datagrid({
                url: '/log/itemLog/grid',
                idField: 'id',
                rownumbers: true,
                singleSelect: true,
                iconCls: 'icon-save',
                pagination: true,
                method: 'post',
                columns: [[
                    {
                        title: '商品ID', field: 'itemIdx', width: '10%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItemLog.itemIdx;
                        }
                    },
                    {
                        title: '中文品名', field: 'cnName', width: '25%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.item) {
                                return row.item.cnName;
                            }
                            return "";
                        }
                    },
                    {
                        title: '修改位置', field: 'modifyLocation', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            return ${itemModifyLocationJson}[row.collectorItemLog.modifyLocation];
                        }
                    },
                    {
                        title: '修改前', field: 'originValue', width: '15%', align: 'center',
                        formatter: function (value, row, index) {
                            return row.collectorItemLog.originValue;
                        }
                    },
                    {
                        title: '修改后', field: 'newValue', width: '15%', align: 'center',
                        formatter: function (value, row, index) {
                            return row.collectorItemLog.newValue;
                        }
                    },
                    {
                        title: '操作人', field: 'userName', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.member) {
                                return row.member.name;
                            }
                            return "";
                        }
                    },
                    {
                        title: '操作时间', field: 'createTime', width: '15%', align: 'center',
                        formatter: function (value, row, index) {
                            return row.collectorItemLog.createTimeEnYyyyMMddHHmmss;
                        }
                    }
                ]],
                loadFilter: function (result) {
                    if (result.status == 200) {
                        return result.data;
                    } else {
                        layer.msg(result.msg, {icon: 2});
                    }
                },
                onLoadSuccess: function (data) {
                    $('#itemLog-commodityTableList').datagrid('clearSelections');
                }
            });

            $('.itemTableList-qry').on('click', function () {
                $('#itemLog-commodityTableList').datagrid('load', {
                    itemIdx: $.trim($('#itemLog-idx').val()),
                    cnName: $.trim($('#itemLog-cnName').val()),
                    userName: $.trim($('#itemLog-userName').val()),
                    startTime: $.trim($('#itemLog-startTime').val()),
                    endTime: $.trim($('#itemLog-endTime').val())
                });
            });
        });
    </script>
</section>

<jsp:include page="../footer.jsp"/>