<%@ page import="cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:igin:add">
                <input type="button" class="brandBtn" value="新增" onclick="editPlace()"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:igin:del">
                <input type="button" class="brandBtn" value="删除" onclick="deletePlace()"/>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">中文名称：</label>
                        <input class="txt" type="text" id="originCnName">
                    </div>
                    <div class="screen-col">
                        <label class="name">英文名称：</label>
                        <input class="txt" type="text" id="originEnName">
                    </div>
                    <div class="screen-col">
                        <label class="name">状态：</label>
                        <p class="state">
                            <select class="state1" id="originStatus">
                                <option value="">请选择</option>
                                <c:forEach items="${statusMap}" var="p">
                                    <option value="${p.key}">${p.value}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <span class="search" id="placeTableList-qry"><i></i>搜索</span>
                </div>
            </div>
            <div class="yt-table">
                <table id="placeTableList" class="easyui-datagrid" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#placeTableList').datagrid({
                url: '/collect/place/grid',
                idField: 'id',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: true,
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
                            <shiro:hasPermission name="collect:igin:edit">
                            if (isDeleted) {
                                array.push('<a class="handle editGray" href="javascript:void(0)" title="编辑"></a>');
                            } else {
                                array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editPlace(' + rowData.idx + ');"></a>');
                            }
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {title: 'ID', field: 'idx', width: '10%', align: 'center', sortable: true},
                    {title: '产地编码', field: 'originCode', width: '10%', align: 'center', sortable: true},
                    {title: '海关编码', field: 'customsCode', width: '10%', align: 'center', sortable: true},
                    {title: '中文名称', field: 'cnName', width: '15%', align: 'center', sortable: true},
                    {title: '英文名称', field: 'enName', width: '15%', align: 'center', sortable: true},
                    {
                        title: '国旗',
                        field: 'nationalFlagImageSmallDynamicS',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return value ? '<img src="' + value + '" />' : '';
                        }
                    },
                    {
                        title: '状态',
                        field: 'status',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, rowData, rowIndex) {
                            return "<span class='state-" + value + "'>" + ${statusMapJson}[value] + "</span>";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    Ms.clearGridSelections('placeTableList');
                }
            });

            $('#placeTableList-qry').on('click', function () {
                $('#placeTableList').datagrid('load', {
                    cnName: $.trim($('#originCnName').val()),
                    enName: $.trim($('#originEnName').val()),
                    status: $('#originStatus').val()
                });
            });
        });

        // 修改产地
        function editPlace(idx) {
            var title = "修改产地";
            if (idx == undefined) {
                title = "新增产地";
                idx = 0;
            }

            layer.open({
                type: 2,
                title: title,
                area: ['490px', '540px'],
                btn: ['确定', '取消'],
                content: ['/collect/place/edit/' + idx, 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                },
                btn2: function () {
                    Ms.clearGridSelections('placeTableList');
                },
                cancel: function () {
                    Ms.clearGridSelections('placeTableList');
                }
            });
        }

        // 删除产地
        function deletePlace() {
            var rows = $('#placeTableList').datagrid('getSelected');
            if (!rows) {
                layer.confirm('请选中要删除的数据！', {
                    title: false,
                    btn: ['确定']
                });
            } else {
                layer.confirm('您确定要删除吗？', {
                    title: false,
                    shadeClose: true,
                    btn: ['确定', '取消'],
                    btn2: function () {
                        Ms.clearGridSelections('placeTableList');
                    },
                    cancel: function () {
                        Ms.clearGridSelections('placeTableList');
                    }
                }, function (index) {
                    var ids = [];
                    var allCheckedRows = $('#placeTableList').datagrid('getChecked');
                    if (allCheckedRows.length > 0) {
                        var checkedRowLength = allCheckedRows.length;
                        for (var i = 0; i < checkedRowLength; i++) {
                            var checkedRow = allCheckedRows[i];
                            ids.push(checkedRow.idx);
                        }
                    }

                    $.ajax({
                        type: "post",
                        url: "/collect/place/delete",
                        data: {
                            ids: ids
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                layer.msg('操作成功！', {icon: 1});
                                $('#placeTableList').datagrid('reload', {});
                            } else {
                                layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                            }
                        },
                        error: function (d) {
                            layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                        }
                    });
                    layer.close(index);
                });
            }
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>