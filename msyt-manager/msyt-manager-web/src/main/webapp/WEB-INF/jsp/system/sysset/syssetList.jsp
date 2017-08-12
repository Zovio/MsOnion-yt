<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-系统设置列表</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="sysset:add">
            <div class="yt-row">
                 <button type="button"  class="brandBtn active" onclick="editOrAddDict()">新增</button>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <label class="name" >设置编码：</label>
                    <input class="txt" type="text" id="setkey" style="width: 180px;">
                    <label class="name" style="padding-left: 10px;">状态：</label>
                    <p class="state">
                        <select class="state1" id="syssetState">
                            <option value="">请选择</option>
                            <c:forEach items="${statusMap}" var="m">
                                <option value="${m.key}">${m.value}</option>
                            </c:forEach>
                        </select>
                        <i class="downIcon"></i>
                    </p>
                    <span class="search" id="sysset-qry"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="syssetlist" class="easyui-datagrid" style="width:98%;height: 464px;">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    $(function(){
        $('#syssetlist').datagrid({
            url : '/sysset/paging',
            fit : true,
            nowrap : true,
            fitColumns : true,
            pagination : true,
            rownumbers : true,
            pageSize : 10,
            pageList : [10, 20, 30, 40, 50 ],
            idField : 'id',
            columns : [ [/* {
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
                    array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForDict('+rowData.idx+')"></a>');
                    if(rowData.status == '1'){

                        <shiro:hasPermission name="sysset:edit">
                        array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAddDict('+rowData.idx+')"></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="sysset:disabled">
                        array.push('<a class="handle forbidden" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'sysset/updateStatus/'+rowData.idx+'/2\', null, \'syssetlist\');"></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="sysset:del">
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'sysset/updateStatus/'+rowData.idx+'/0\', null, \'syssetlist\');"></a>');
                        </shiro:hasPermission>
                    }
                    if(rowData.status == '2'){
                        <shiro:hasPermission name="sysset:recover">
                        array.push('<a class="handle putaway" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'sysset/updateStatus/'+rowData.idx+'/1\', null, \'syssetlist\');"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysset:del">
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'sysset/updateStatus/'+rowData.idx+'/0\', null, \'syssetlist\');"></a>');
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
            }, {
                field : 'settingKey',
                align : 'center',
                width : 50,
                sortable : true,
                title : '设置编码'
            },{
                field : 'settingValue',
                align : 'center',
                width : 80,
                sortable : true,
                title : '设置值'
            },{
                field : 'remark',
                align : 'center',
                width : 70,
                sortable : true,
                title : '备注'
            }
            ,{
                field : 'status',
                align : 'center',
                width : 20,
                title : '状态',
                    sortable : true,
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $('#syssetlist').datagrid('clearSelections');
            }
        });

        $('#sysset-qry').on('click',function () {
            $('#syssetlist').datagrid('load',{
                setKey:$.trim($('#setkey').val()),
                status:$.trim($('#syssetState').val())
            });
        });

    });


    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddDict(idx) {
        var url = idx ? '/sysset/edit/'+idx : '/sysset/add/1';
        var title =idx ? '修改系统设置' : '新增系统设置';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['530px', '350px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                layer.load(2,{
                    shade : 0.01,
                    time : 5000
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
        var url = '/sysset/view/'+idx ;
        var title ='查看系统设置';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['530px', '350px'], //宽高
            content: [url, 'no']
        });
    }

</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

