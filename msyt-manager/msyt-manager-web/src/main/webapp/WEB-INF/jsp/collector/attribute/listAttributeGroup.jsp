<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:attribute:gp:add">
                <input type="button" class="brandBtn" value="新增" onclick="editAttribute()"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:attribute:gp:del">
                <input type="button" class="brandBtn" value="删除" onclick="deleteGroup()"/>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">属性组名称：</label>
                        <input class="txt margin-r-20" type="text" id="groupName">
                    </div>
                    <span class="search" id="groupList-qry"><i></i>搜索</span>
                </div>
            </div>
            <div id="groupTable" class="yt-table">
                <table id="groupList" class="easyui-datagrid" style="width:98%;height: 464px;"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#groupList').datagrid({
                url: '/collect/attribute/gp/grid',
                method: 'post',
                singleSelect: false,
                collapsible: true,
                rownumbers: true,
                pagination: true,
                columns: [[
                    {
                        title: '序号',
                        field: '',
                        checkbox: 'true',
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'control',
                        align: 'center',
                        width: '10%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var array = [];
                            <shiro:hasPermission name="collect:attribute:gp:edit">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editAttribute(' + rowData.idx + ')"></a>');
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {
                        title: '属性组名称',
                        field: 'name',
                        width: '87%',
                        align: 'center'
                    }
                ]]
            });
        });

        $('#groupList-qry').on('click', function () {
            $('#groupList').datagrid('reload', {
                groupName: $.trim($('#groupName').val())
            });
        });

        // 编辑属性组
        function editAttribute(idx) {
            var title = (idx == null || idx == "") ? "新增属性组" : "修改属性组";
            idx = (idx == null || idx == "") ? "0" : idx;

            layer.open({
                type: 2,
                title: title,
                area: ['490px', '260px'],
                btn: ['保存', '取消'],
                content: ['/collect/attribute/gp/edit/' + idx, 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                },
                btn2: function () {
                    Ms.clearGridSelections('groupList');
                },
                cancel: function () {
                    Ms.clearGridSelections('groupList');
                }
            });
        }

        // 删除属性组
        function deleteGroup() {
            var row = $('#groupList').datagrid('getSelected');
            if (!row) {
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
                        Ms.clearGridSelections('groupList');
                    },
                    cancel: function () {
                        Ms.clearGridSelections('groupList');
                    }
                }, function (index) {
                    var ids = [];
                    var allCheckedRows = $('#groupList').datagrid('getChecked');
                    if (allCheckedRows.length > 0) {
                        var checkedRowLength = allCheckedRows.length;
                        for (var i = 0; i < checkedRowLength; i++) {
                            var checkedRow = allCheckedRows[i];
                            ids.push(checkedRow.idx);
                        }
                    }

                    $.ajax({
                        type: "post",
                        url: "/collect/attribute/gp/del",
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
                            $('#groupList').datagrid('reload', {});
                        },
                        error: function (d) {
                            layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            }
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>