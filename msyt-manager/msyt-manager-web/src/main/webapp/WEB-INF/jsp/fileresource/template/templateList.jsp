<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-文件模板列表</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="fileTemplate:add">
            <div class="yt-row">
                <button type="button" class="brandBtn active" onclick="editOrAddDict()">新增</button>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <label class="name" style="padding-left: 10px;">模板类别：</label>
                    <p class="state">
                        <select class="state1" id="fileTemplate-category">
                            <option value="">请选择</option>
                            <c:forEach items="${categoryMap}" var="m">
                                <option value="${m.key}">${m.value}</option>
                            </c:forEach>
                        </select>
                        <i class="downIcon"></i>
                    </p>

                    <label class="name" style="padding-left: 10px;">状态：</label>
                    <p class="state">
                        <select class="state1" id="fileTemplate-state">
                            <option value="">请选择</option>
                            <c:forEach items="${statusMap}" var="m">
                                <option value="${m.key}">${m.value}</option>
                            </c:forEach>
                        </select>
                        <i class="downIcon"></i>
                    </p>
                    <span class="search" id="fileTemplate-qry"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table">

                <table id="fileTemplate-list" class="easyui-datagrid" style="width:98%;height: 464px;">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    $(function () {
        $('#fileTemplate-list').datagrid({
            url: '/fileTemplate/paging',
            fit: true,
            nowrap: true,
            fitColumns: true,
            pagination: true,
            rownumbers: true,
            pageSize: 10,
            pageList: [10, 20, 30, 40, 50],
            idField: 'id',
            columns: [[
                {
                    field: 'control',
                    align: 'center',
                    width: "12%",
                    title: '操作',
                    formatter: function (value, rowData, rowIndex) {
                        var array = [];
                        array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForDict(' + rowData.idx + ')"></a>');
                        if (rowData.status == '1') {

                            <shiro:hasPermission name="fileTemplate:edit">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAddDict(' + rowData.idx + ')"></a>');
                            </shiro:hasPermission>

                            <shiro:hasPermission name="fileTemplate:del">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'fileTemplate/updateStatus/' + rowData.idx + '/0\', null, \'fileTemplate-list\');"></a>');
                            </shiro:hasPermission>
                        }
                        if (rowData.status == '2') {

                            <shiro:hasPermission name="fileTemplate:del">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'fileTemplate/updateStatus/' + rowData.idx + '/0\', null, \'fileTemplate-list\');"></a>');
                            </shiro:hasPermission>
                        }

                        return array.join('');
                    }
                },
                {
                    field: 'idx',
                    align: 'center',
                    width: 30,
                    sortable: true,
                    title: 'ID'
                }, {
                    field: 'title',
                    align: 'center',
                    width: 50,
                    sortable: false,
                    title: '模板标题'
                }, {
                    field: 'categoryId',
                    align: 'center',
                    width: 40,
                    sortable: true,
                    title: '模板类别',
                    formatter: function (value, rowData, rowIndex) {
                        return '<span >' + ${categoryMapJson}[value] + '</span>';
                    }
                }, {
                    field: 'appPlatform',
                    align: 'center',
                    width: 50,
                    sortable: true,
                    title: '应用平台',
                    formatter: function (value, rowData, rowIndex) {
                        return '<span >' + ${appPlatformMapJson}[value] + '</span>';
                    }
                }
                , {
                    field: 'fileName',
                    align: 'center',
                    width: 60,
                    sortable: false,
                    title: '文件',
                    formatter: function (value, rowData, rowIndex) {
                        return '<a href="'+fileResourceApi+'/cos/download/'+rowData.fileMessageIdDynamicS+'">' + value + '</a>';
                    }
                },{
                    field: 'updateByMemberIdxDynamicS',
                    align: 'center',
                    width: 30,
                    sortable: false,
                    title: '操作人'
                },{
                    field: 'updateTime',
                    align: 'center',
                    width: 50,
                    sortable: true,
                    title: '操作时间',
                    formatter: function (value, rowData, rowIndex) {
                        return  rowData.updateTimeEnYyyyMMddHHmmss;
                    }
                }

                , {
                    field: 'status',
                    align: 'center',
                    width: 20,
                    title: '状态',
                    sortable: true,
                    formatter: function (value, rowData, rowIndex) {
                        return '<span class="state-' + value + '">' + ${statusMapJson}[value] + '</span>';
                    }
                }]],
            onLoadSuccess: function (data) {
                $('#fileTemplate-list').datagrid('clearSelections');
            }
        });

        $('#fileTemplate-qry').on('click', function () {
            $('#fileTemplate-list').datagrid('load', {
                categoryIdx: $.trim($('#fileTemplate-category').val()),
                status: $.trim($('#fileTemplate-state').val())
            });
        });

    });


    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddDict(idx) {
        var url = idx ? '/fileTemplate/edit/' + idx : '/fileTemplate/add/1';
        var title = idx ? '修改文件模板' : '新增文件模板';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            area: ['600px', '530px'], //宽高
            content: [url, 'no'],
            yes: function (index, layero) {
                layer.load(2, {
                    shade: 0.01,
                    time: 5000
                });
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm();
                return false;
            }
        });
    }

    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function showForDict(idx) {
        var url = '/fileTemplate/view/' + idx;
        var title = '查看文件模板';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['600px', '530px'], //宽高
            content: [url, 'no']
        });
    }

</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

