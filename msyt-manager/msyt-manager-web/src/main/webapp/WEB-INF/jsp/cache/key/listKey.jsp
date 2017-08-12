<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统_缓存中心</title>
</head>
<body>
<div class="main">
    <div class="table-container">
        <shiro:hasPermission name="cache:key:disabled">
            <div class="yt-row">
                <button type="button"  class="brandBtn active" onclick="delMultiNode()">删除节点</button>
            </div>
        </shiro:hasPermission>
        <div class="tree-cont" style="margin-top: 20px;">
            <div class="treeMenu" >
                <div class="easyui-panel">
                    <ul id="cacheTree" class="easyui-tree" >

                    </ul>
                </div>
            </div>
            <div class="tree-right">
            <shiro:hasPermission name="cache:key:disabled">
                <div class="add-ccont">
                    <button type="button" class="addbtn" id="delMuitiBtn" >批量删除</button>
                </div>
            </shiro:hasPermission>
                <div class="yt-table" style="padding: 20px 20px;box-sizing: border-box;">
                    <table id="cacheKeyList" class="easyui-datagrid" style="width:99%;">

                    </table>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        var $tree = $('#cacheTree');
        var $dataGrid = $('#cacheKeyList');
        var tableData = '';
        $(function(){
            showTreeData();
            showTableData();
            $('#delMuitiBtn').on("click",delMultiKey);

        });
        function showTreeData(){
            $tree.tree({
                url : "/cache/key/treeList",
                animate:true,
                checkbox:true,
                onClick:function(node){
                    $tree.tree('toggle',node.target);
                },
                onBeforeExpand:function(node){
                    $.ajax({
                        type: "post",
                        url: "/cache/key/keyList",
                        data: {
                            id: node.attributes[0].url
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                var jsonData=d['data'];
                                tableData=jsonData;
                                showTableData();
                            }
                        },
                        error: function (d) {
                            layer.msg("加载数据失败", {icon: 2});
                        }
                    });
                },
                loadFilter : function(data) {
                    var jsonData=data['data'];
                    if(data.status==200){
                        return jsonData;
                    }else{
                        return null;
                    }
                }
            });

        }


        function showTableData() {
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
                        width: 80,
                        align: 'center',
                        checkbox: 'true'
                    },
                    {
                        field : 'control',
                        align : 'center',
                        width : 20,
                        title : '操作',
                        formatter : function(value, rowData, rowIndex){
                            var path=rowData.path;
                            var array=[];
                            array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick=showKeyDetail("'+path+'") ></a>');
                            <shiro:hasPermission name="cache:key:disabled">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick=delSingleKey("'+path+'") ></a>');
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {
                    field : 'path',
                    align : 'center',
                    width : 80,
                    sortable : true,
                    title : 'Key'
                }  ] ],
                onLoadSuccess: function (data) {
                    $dataGrid.datagrid('clearSelections');
                }
            });
        }
        function showKeyDetail(key){
            layer.open({
                type: 2,
                title: "查看缓存详情",
                area: ['700px', '700px'],
                content: ['/cache/key/detail?keyPath='+key, 'no'],
                cancel: function () {
                    Ms.clearGridSelections('cacheKeyList');
                }
            });
        }

        function delMultiKey(){
                if (!$dataGrid.datagrid('getSelected')) {
                    layer.confirm('请选中要删除的数据！', {
                        title: false, /* 不显示标题 */
                        shade: 0.3,
                        shadeClose: false,
                        btn: ['确定', '取消']
                    });
                } else {
                    var ids = [];
                    var allCheckedRows = $dataGrid.datagrid('getChecked');
                    if (allCheckedRows.length > 0) {
                        var checkedRowLength = allCheckedRows.length;
                        for (var i = 0; i < checkedRowLength; i++) {
                            var checkedRow = allCheckedRows[i];
                            ids.push(checkedRow.path);
                        }
                    }
                    delKey(ids);
                }
        }
        function delSingleKey(ids){
            var idsArr=[];
            idsArr.push(ids);
            delKey(idsArr);
        }
        function delKey(ids){
            layer.confirm('您确定要删除吗？', {
                title: false,
                shade: 0.3,
                shadeClose: false,
                btn: ['确定', '关闭']
            }, function (index) {
                $.ajax({
                    type: "post",
                    url: "/cache/key/deleteKey",
                    data: {
                        ids: ids
                    },
                    dataType: 'json',
                    success: function (d) {
                        if (d.status == 200) {
                            var newCacheKeyList=d.data.newCacheKeyList;
                            layer.msg('操作成功！', {icon: 6});
                            $dataGrid.datagrid('clearSelections');
                            $dataGrid.datagrid('loadData',newCacheKeyList);
                            var failMsg=d.data.deleteTips;
                            if (failMsg.length > 0 && failMsg != "") {
                                var failMsghtml = failMsg.join("<br/>");
                                layer.open({
                                    type: 1,
                                    title: "删除提示",
                                    area: ['400px', '400px'],
                                    content: failMsghtml //这里content是一个普通的String
                                });
                            }
                            showTreeData();
                        } else {
                            layer.msg(d.msg, {icon: 2});
                        }
                    },
                    error: function (d) {
                        layer.msg("请求数据失败", {icon: 2});
                    }
                });
            });
        }



        function delMultiNode(){
            var nodes = $tree.tree('getChecked');
            if (!nodes||nodes.length==0) {
                layer.confirm('请选中要删除的数据！', {
                    title: false, /* 不显示标题 */
                    shade: 0.3,
                    shadeClose: false,
                    btn: ['确定', '取消']
                });
            } else {
                layer.confirm('您确定要删除吗？', {
                    title: false,
                    shade: 0.3,
                    shadeClose: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    var ids = [];
                    for (var x in nodes) {
                        var node=nodes[x];
                        ids.push(node.attributes[0].url);
                    }

                    $.ajax({
                        type: "post",
                        url: "/cache/key/deleteNode",
                        data: {
                            ids: ids
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                layer.msg('操作成功！', {icon: 6});
                                $dataGrid.datagrid('clearSelections');
                                var failMsg=d.data.deleteTips;
                                if (failMsg.length > 0 && failMsg != "") {
                                    var failMsghtml = failMsg.join("<br/>");
                                    layer.open({
                                        type: 1,
                                        title: "删除提示",
                                        area: ['400px', '400px'],
                                        content: failMsghtml //这里content是一个普通的String
                                    });
                                }
                                showTreeData();
                            } else {
                                layer.msg(d.msg, {icon: 2});
                            }
                        },
                        error: function (d) {
                            layer.msg("请求数据失败", {icon: 2});
                        }
                    });
                });
            }
        }
    </script>

</div>

</body>
</html>

