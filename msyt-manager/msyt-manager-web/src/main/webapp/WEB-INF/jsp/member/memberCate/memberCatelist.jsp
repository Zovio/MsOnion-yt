<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-用户类别列表</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="memberCate:add">
        <div class="yt-row">
            <button type="button"  class="brandBtn active" onclick="editOrAddMemberCate()">新增</button>
        </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <label class="name">成员类别名称：</label>
                    <input class="txt" type="text"  id="memberCateName">
                    <label class="name" style="padding-left: 10px;">状态：</label>
                    <p class="state">
                        <select class="state1"  id="memberCateState">
                            <option value="">请选择</option>
                            <c:forEach items="${statusMap}" var="m">
                                <option value="${m.key}">${m.value}</option>
                            </c:forEach>
                        </select>
                        <i class="downIcon"></i>
                    </p>
                    <span class="search" id="memberCatelist-qry"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="memberCatelist" class="easyui-datagrid" style="width:98%;height: 464px;">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    $(function(){
        $('#memberCatelist').datagrid({
            url : '/memberCate/paging',
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
                        array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForMemberCate('+rowData.idx+')"></a>');
                        if(rowData.status == '1'){
                            <shiro:hasPermission name="memberCate:disabled">
                            array.push('<a class="handle forbidden" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'memberCate/updateStatus/'+rowData.idx+'/2\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="memberCate:edit">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAddMemberCate('+rowData.idx+')"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="memberCate:del">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'memberCate/updateStatus/'+rowData.idx+'/0\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                        }
                        if(rowData.status == '2'){
                            <shiro:hasPermission name="memberCate:recover">
                            array.push('<a class="handle putaway" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'memberCate/updateStatus/'+rowData.idx+'/1\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="memberCate:del">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'memberCate/updateStatus/'+rowData.idx+'/0\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                        }
                        return array.join('');
                    }
                },
                {
                field : 'idxCodeS',
                align : 'center',
                width : 30,
                sortable : true,
                title : 'ID'
            },
          /*  {
                 field : 'idxCodeS',
                 align : 'center',
                 width : 35,
                 sortable : true,
                 title : 'IdxCode'
            },*/
            {
                field : 'name',
                align : 'center',
                width : 80,
                sortable : true,
                title : '成员类别名称'
            },{
                field : 'status',
                align : 'center',
                width : 80,
                title : '状态',
                sortable : true,
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $('#memberCatelist').datagrid('clearSelections');
            }
        });

        $('#memberCatelist-qry').on('click',function () {
            $('#memberCatelist').datagrid('load',{
                name:$.trim($('#memberCateName').val()),
                status:$('#memberCateState').val()
            });
        });

    });



    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddMemberCate(idx) {
        var url = idx ? '/memberCate/edit/'+idx : '/memberCate/add/1';
        var title =idx ? '修改成员类别' : '新增成员类别';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['500px', '300px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm();
                return false;
            }
        });
    }

    function showForMemberCate(idx) {
        var url = '/memberCate/view/'+idx ;
        var title ='查看成员类别';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['500px', '300px'], //宽高
            content: [url, 'no']
        });
    }

</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

