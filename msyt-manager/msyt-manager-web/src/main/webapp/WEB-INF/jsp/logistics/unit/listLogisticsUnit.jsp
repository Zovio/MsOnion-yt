<%@ page import="cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="logistics:unit:add">
                <button type="button" id="addLogisticsUnit" class="brandBtn">新增</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="logistics:unit:del">
                <button type="button" id="deleteLogisticsUnit" class="brandBtn">删除</button>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">编码：</label>
                        <input class="txt" type="text" id="logisticsUnitCode">
                    </div>
                    <div class="screen-col">
                        <label class="name">名称：</label>
                        <input class="txt" type="text" id="logisticsUnitName">
                    </div>
                    <div class="screen-col">
                        <label class="name">状态：</label>
                        <p class="state">
                            <select class="state1" id="logisticsUnitStatus">
                                <option value="">请选择</option>
                                <c:forEach items="${statusMap}" var="p">
                                    <option value="${p.key}">${p.value}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <span class="search" id="logisticsUnitTableList-qry"><i></i>搜索</span>
                </div>
            </div>
            <div class="yt-table">
                <table id="logisticsUnitTableList" style="width: 96%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#logisticsUnitTableList').datagrid({
                url: '/logistics/unit/grid',
                idField: 'id',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: 'true',
                method: 'post',
                columns: [[
                    {title: '', field: '', width: '5%', align: 'center', checkbox: 'true'},
                    {
                        field: 'control',
                        align: 'center',
                        width: '8%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var deletedStatus = "<%=MsOnionTableRecordStatus.DELETED.getValue()%>";
                            var isDeleted = (rowData.status == deletedStatus);

                            var array = [];
                            <shiro:hasPermission name="logistics:unit:edit">
                            if (isDeleted) {
                                array.push('<a class="handle editGray" href="javascript:void(0)" title="编辑"></a>');
                            } else {
                                array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editLogisticsUnit(' + rowData.idx + ');"></a>');
                            }
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {title: 'ID', field: 'idx', width: '10%', align: 'center', sortable: true},
                    {title: '编码', field: 'code', width: '15%', align: 'center', sortable: true},
                    {title: '名称', field: 'name', width: '20%', align: 'center', sortable: true},
                    {
                        title: '状态',
                        field: 'status',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return "<span class='state-" + value + "'>" + ${statusMapJson}[value] + "</span>";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    Ms.clearGridSelections('logisticsUnitTableList');
                }
            });

            $('#logisticsUnitTableList-qry').on('click', function () {
                gridReload();
            });
        });

        $(function () {
            // 新增计量单位
            $('#addLogisticsUnit').on('click', function () {
                editLogisticsUnit();
            });

            // 删除计量单位
            $('#deleteLogisticsUnit').click(function () {
                if (!$('#logisticsUnitTableList').datagrid('getSelected')) {
                    layer.confirm('请选中要删除的数据！', {
                        title: false,
                        btn: ['确定']
                    });
                } else {
                    layer.confirm('您确定要删除吗？', {
                        title: false,
                        btn: ['确定', '关闭'],
                        btn2: function () {
                            Ms.clearGridSelections('logisticsUnitTableList');
                        },
                        cancel: function () {
                            Ms.clearGridSelections('logisticsUnitTableList');
                        }
                    }, function (index) {
                        var ids = [];
                        var allCheckedRows = $('#logisticsUnitTableList').datagrid('getChecked');
                        if (allCheckedRows.length > 0) {
                            var checkedRowLength = allCheckedRows.length;
                            for (var i = 0; i < checkedRowLength; i++) {
                                var checkedRow = allCheckedRows[i];
                                ids.push(checkedRow.idx);
                            }
                        }

                        $.ajax({
                            type: "post",
                            url: "/logistics/unit/delete",
                            data: {
                                ids: ids
                            },
                            dataType: 'json',
                            success: function (d) {
                                if (d.status == 200) {
                                    layer.msg('操作成功！', {icon: 1});
                                } else {
                                    layer.msg(d.msg, {icon: 2});
                                }
                                gridReload();
                            },
                            error: function (d) {
                                layer.msg(d.msg, {icon: 2});
                            }
                        });
                    });
                }
            });
        });

        // 修改计量单位
        function editLogisticsUnit(idx) {
            var title = "修改计量单位";
            if (idx == undefined) {
                title = "新增计量单位";
                idx = 0;
            }

            layer.open({
                type: 2,
                title: title,
                area: ['490px', '415px'],
                btn: ['保存', '取消'],
                content: ['/logistics/unit/edit/' + idx, 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                },
                btn2: function () {
                    Ms.clearGridSelections('logisticsUnitTableList');
                },
                cancel: function () {
                    Ms.clearGridSelections('logisticsUnitTableList');
                }
            });
        }

        function gridReload() {
            $('#logisticsUnitTableList').datagrid('reload', {
                code: $.trim($('#logisticsUnitCode').val()),
                name: $.trim($('#logisticsUnitName').val()),
                status: $('#logisticsUnitStatus').val()
            });
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>