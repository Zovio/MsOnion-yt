<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-角色列表</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="role:edit">
        <div class="yt-row">
             <button type="button"  class="brandBtn active" onclick="editOrAddRole()">新增</button>
        </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <label class="name">角色名称：</label>
                    <input class="txt" type="text" id="roleName">
                    <label class="name" style="padding-left: 10px;">状态：</label>
                    <p class="state">
                        <select class="state1" id="roleState">
                            <option value="">请选择</option>
                            <c:forEach items="${statusMap}" var="m">
                                <option value="${m.key}">${m.value}</option>
                            </c:forEach>
                        </select>
                        <i class="downIcon"></i>
                    </p>
                    <span class="search" id="rolelist-qry"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="rolelist" class="easyui-datagrid" style="width:98%;height: 464px;">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    $(function(){
        $('#rolelist').datagrid({
            url : '/role/paging',
            fit : true,
            nowrap : true,
            fitColumns : true,
            pagination : true,
            rownumbers : true,
            pageSize : 10,
            pageList : [10, 20, 30, 40, 50 ],
            idField : 'id',
            columns : [ [ /*{
                field : 'idx',
                checkbox:true
            },*/

            {
                field : 'control',
                align : 'center',
                width : "15%",
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array=[];
                    array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForRole('+rowData.idx+')"></a>');
                    if(rowData.status == '1'){
                        <shiro:hasPermission name="role:disabled">
                        array.push('<a class="handle forbidden" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'role/updateStatus/'+rowData.idx+'/2\', null, \'rolelist\');"></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="role:edit">
                        array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAddRole('+rowData.idx+')"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="role:del">
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'role/updateStatus/'+rowData.idx+'/0\', null, \'rolelist\');"></a>');
                        </shiro:hasPermission>
                    }
                    if(rowData.status == '2'){


                        <shiro:hasPermission name="role:recover">
                        array.push('<a class="handle putaway" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'role/updateStatus/'+rowData.idx+'/1\', null, \'rolelist\');"></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="role:del">
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'role/updateStatus/'+rowData.idx+'/0\', null, \'rolelist\');"></a>');
                        </shiro:hasPermission>
                    }
                    return array.join('');
                }
            },
            {
                field : 'idx',
                align : 'center',
                width : 30,
                sortable : true,
                title : 'ID'
            },{
                field : 'name',
                align : 'center',
                width : 50,
                sortable : true,
                title : '角色名称'
            },{
                field : 'code',
                align : 'center',
                width : 50,
                sortable : true,
                title : '编码'
            },{
                field : 'status',
                align : 'center',
                width : 50,
                title : '状态',
                sortable : true,
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $('#rolelist').datagrid('clearSelections');
            }
        });

        $('#rolelist-qry').on('click',function () {
            $('#rolelist').datagrid('load',{
                name:$.trim($('#roleName').val()),
                status:$('#roleState').val()
            });
        });

    });



    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddRole(idx) {
        var url = idx ? '/role/edit/'+idx : '/role/add/1';
        var title =idx ? '修改角色' : '新增角色';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['530px', '530px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
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
    function showForRole(idx) {
        var url = '/role/view/'+idx ;
        var title ='查看角色';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['530px', '530px'], //宽高
            content: [url, 'no']
        });
    }

</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

