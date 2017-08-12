<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统_ZK值配置</title>
</head>
<body>
<div class="main">
    <div class="table-container">
        <div class="tree-cont" style="margin-top: 20px;">
            <div class="treeMenu" >
                <div class="easyui-panel">
                    <ul id="zkTree" class="easyui-tree" style="scroll-y:hidden" >

                    </ul>
                </div>
            </div>
            <div class="tree-right">
                <shiro:hasPermission name="cache:key:disabled">
                    <div class="add-ccont" style="padding:0 10px">
                        <input type="text" id="zk_inp_value" placeholder="请输入整数" style="margin-right:20px;border: 1px solid #dbdcde;height: 35px;padding: 0 6px;" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" />
                        <button type="button" class="addbtn" onclick="updateMultiValue()" >一键设置</button>
                    </div>
                </shiro:hasPermission>
                <div class="yt-table" style="padding: 20px 20px;box-sizing: border-box;">
                    <table id="zkKeyList" class="easyui-datagrid" style="width:99%;">

                    </table>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        var $tree = $('#zkTree');
        var $dataGrid = $('#zkKeyList');
        var curPath = '/';
        $(function(){

            getZKValues(curPath);

            $.post("/system/zk/treeList", function (json) {
                $tree.tree({
                    data : json.data,
                    animate: true,
                    onClick: function (node) {
                        $tree.tree('toggle', node.target);

                        curPath = node.path;
                        if (!curPath) {
                            curPath = "/";
                        }

                        var hasLoaded = node.target.hasLoaded;
                        if (!hasLoaded) {
                            $.post("/system/zk/treeList", {
                                "path": node.path
                            }, function (json) {
                                if (json.data) {

                                    node.target.hasLoaded = true;

                                    $tree.tree('append', {
                                        parent: node.target,
                                        data: json.data[0].childList
                                    });

                                    $(node.target).next().css("display", "block");

                                    if (!json.data[0].childList || json.data[0].childList.length < 1) {
                                        node.target.fp = json.data[0].fullPath;
                                        node.target.isLeafNode = true;
                                    }
                                }
                            }, "json");
                        }
                        getZKValues(node.path);
                    }
                });
            });
        });

        function getZKValues(path) {
            if (!path) {
                path = "/";
            }
            $.post("/system/zk/getZKChildValues", {
                "path": path
            }, function (data) {
                showTable(data.data);
            }, "json");
        }

        function showTable(tableData) {
            // 初始化菜单表格
            $dataGrid.datagrid({
                data: tableData,
                fit : true,
                nowrap : true,
                fitColumns : true,
                pagination : false,
                rownumbers : true,
                pageSize : 10,
                pageList : [10, 20, 30, 40, 50 ],
                idField : 'path',
                columns : [[
                    {
                        title: '',
                        field: '',
                        width: "3%",
                        align: 'center',
                        checkbox: 'true'
                    },
                    {
                        field : 'control',
                        align : 'center',
                        width : "8%",
                        title : '操作',
                        formatter : function(value, rowData, rowIndex){
                            var path=rowData.fp;
                            var array=[];
                            <shiro:hasPermission name="cache:key:disabled">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick=editZKValue("'+path+'") ></a>');
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {
                        field : 'path',
                        align : 'left',
                        width : "74%",
                        title : 'ZK路径'
                    },
                    {
                        field : 'fp',
                        hidden: true,
                        title : ''
                    },
                    {
                    field : 'value',
                    align : 'center',
                    width : "15%",
                    title : '值'
                } ]],
                onLoadSuccess: function (data) {
                    $dataGrid.datagrid('clearSelections');
                }
            });
        }

        function editZKValue(fp){
            layer.open({
                type: 2,
                title: "编辑ZK值",
                area: ['550px', '300px'],
                btn: ['保存', '取消'],
                content: ['/system/zk/editZKValue?fp='+fp, 'no'],
                yes : function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                }
            });
        }

        function updateMultiValue(){
            if (!$dataGrid.datagrid('getSelected')) {
                layer.msg("请选中要修改的数据！", {icon: 2});
            } else {
                var zkInpValue = $("#zk_inp_value");
                if (!zkInpValue.val()) {
                    layer.msg("请输入整数", {icon: 2});
                    zkInpValue.focus();
                    return false;
                }

                var fps = [];
                var allCheckedRows = $dataGrid.datagrid('getChecked');
                if (allCheckedRows.length > 0) {
                    var checkedRowLength = allCheckedRows.length;
                    for (var i = 0; i < checkedRowLength; i++) {
                        var checkedRow = allCheckedRows[i];
                        fps.push(checkedRow.fp);
                    }
                }

                updateZKValue(fps, zkInpValue.val());
            }
        }

        function updateZKValue(fps, value){
            layer.confirm('您确定要一键设置吗？', {
                title: false,
                shade: 0.3,
                shadeClose: false,
                btn: ['确定', '取消']
            }, function (index) {
                $.ajax({
                    type: "post",
                    url: "/system/zk/batchSaveZKValue",
                    data: {
                        fps: fps,
                        value : value
                    },
                    dataType: 'json',
                    success: function (d) {
                        if (d.status == 200) {
                            layer.msg('操作成功！', {icon: 6});
                            $dataGrid.datagrid('clearSelections');
                            getZKValues(curPath);
                        } else {
                            layer.msg(d.msg, {icon: 2});
                        }
                    },
                    error: function (d) {
                        layer.msg(d.msg, {icon: 2});
                    }
                });
            });
        }
    </script>
</div>
</body>
</html>

