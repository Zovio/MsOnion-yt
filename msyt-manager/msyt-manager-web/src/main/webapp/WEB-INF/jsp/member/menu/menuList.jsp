<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-菜单列表</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>
<div class="main" >
    <div class="table-container">
        <div class="tree-cont">
            <div class="treeMenu">
                <div class="easyui-panel">
                    <ul id="menuTree" class="easyui-tree">

                    </ul>
                </div>
            </div>

            <!-- 菜单列表 -->
            <div class="tree-right" id="menuRight" style="margin-top:20px;">
                <shiro:hasPermission name="menu:add">
                <div class="add-ccont" style="margin-top: 0">
                    <button type="button" onclick="editOrAdd()" class="addbtn">新增菜单</button>
                </div>
                </shiro:hasPermission>
                <div class="screen" style="margin-top: 10px">
                    <div class="screen-row">
                        <label class="name">菜单名称：</label>
                        <input class="txt" type="text" id="menuName">
                        <label class="name">状态：</label>
                        <p class="state">
                            <select class="state1" id="menuState">
                                <option value="">请选择</option>
                                <c:forEach items="${statusMap}" var="m">
                                    <option value="${m.key}">${m.value}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                        <span class="search" id="menulist-qry"><i></i>搜索</span>
                    </div>
                </div>
                <div class="yt-table" style="padding: 20px 20px;box-sizing: border-box;">
                    <table id="menulist" class="easyui-datagrid" style="width:98%">

                    </table>
                </div>
            </div>

            <!-- 按钮列表 -->
            <div class="tree-right" id="buttonRight" >
                <shiro:hasPermission name="menu:addButton">
                <div class="add-ccont">
                    <button type="button" onclick="editOrAddButton()"  class="addbtn">新增按钮</button>
                </div>
                </shiro:hasPermission>
                <div class="yt-table" style="padding: 20px 20px;box-sizing: border-box;">
                    <table id="buttonlist" class="easyui-datagrid" style="width:900px;height:400px">

                    </table>
                </div>
            </div>

        </div>
    </div>
    </div>
</div>
<script>
    $(function(){

        // 左边菜单树
        var $tree = $('#menuTree');
        $tree.html('拼命加载中...')
        $.ajax({
            timeout : Ms.AJAX_TIME_OUT,
            type : "POST",
            url : '/menu/getMenuData',
            cache : false,
        }).done(function (result) {
            // 初始化树菜单，
            $tree.tree({
                data : result,
                animate:true,
                onClick: function (node) {
                    $tree.tree('toggle', node.target).tree('select', node.target);
                    // 如果是最底层菜单查询按钮列表
                    if ($tree.tree("isLeaf", node.target)) {
                        var options = $('#buttonlist').datagrid('options');
                        options.url = '/menu/button/paging';
                        options.queryParams = {
                            menuIdx : node.id
                        };
                        $("#buttonlist").datagrid('reload');
                        $('#buttonRight').show();
                        $('#menuRight').hide();

                    }else {
                        // 查询菜单列表
                        $('#buttonRight').hide();
                        $('#menuRight').show();

                        $('#menulist').datagrid('load',{
                            name : $.trim($('#name').val()),
                            pid : node ? node.id : null,
                            status : $('#status').val()
                        });
                    }
                },
                onLoadSuccess:function(){
                    // remove menu
                    if('${hideMenuIdxs}'){
                        var menuIdxs = '${hideMenuIdxs}';
                        var menuIdxArr = menuIdxs.split(",");
                        for (x in menuIdxArr) {
                            var node = $tree.tree('find',menuIdxArr[x]);
                            $tree.tree('remove', node.target);
                        }
                    }

                }
            })
        }).fail(function(result) {
            layer.msg('加载菜单树失败,请重试...', {timeout:1500,icon: 2});
        });


        // 初始化菜单表格
        $('#menulist').datagrid({
            url : '/menu/paging',
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
                width : "17%",
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array=[];
                    array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForMenu('+rowData.idx+')"></a>');
                    if(rowData.status == '1'){
                        <shiro:hasPermission name="menu:disabled">
                        array.push('<a class="handle forbidden" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'menu/updateStatus/'+rowData.idx+'/2\', null, \'menulist\');"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="menu:edit">
                        array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAdd('+rowData.idx+')"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="menu:del">
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'menu/updateStatus/'+rowData.idx+'/0\', null, \'menulist\');"></a>');
                        </shiro:hasPermission>
                    }
                    if(rowData.status == '2'){
                        <shiro:hasPermission name="menu:recover">
                        array.push('<a class="handle putaway" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'menu/updateStatus/'+rowData.idx+'/1\', null, \'menulist\');"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="menu:del">
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'menu/updateStatus/'+rowData.idx+'/0\', null, \'menulist\');"></a>');
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
                field : 'name',
                align : 'center',
                sortable : true,
                width : 50,
                title : '菜单名称'
            }, {
                field : 'url',
                align : 'center',
                sortable : true,
                width : 70,
                title : '菜单url'
            },{
                field : 'zindex',
                align : 'center',
                sortable : true,
                width : 30,
                title : '顺序'
            },{
                field : 'code',
                align : 'center',
                sortable : true,
                width : 70,
                title : '编码'
            },{
                field : 'topShow',
                align : 'center',
                width : 50,
                sortable : true,
                title : '首页顶部显示',
                formatter : function(value, rowData, rowIndex){
                    if(value == '1'){
                        return '<span >是</span>';
                    }
                    return '<span >否</span>';

                }
            },{
                field : 'status',
                align : 'center',
                width : 40,
                sortable : true,
                title : '状态',
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $('#menulist').datagrid('clearSelections');
                // 不能直接将按钮列表隐藏，后面显示会出现问题，需要表格全部加载后再隐藏，希望后续有更好的方法
                $('#buttonRight').hide();
            }
        });


        $('#menulist-qry').on('click',function () {
            var node = $tree.tree('getSelected');
            $('#menulist').datagrid('load',{
                name : $.trim($('#menuName').val()),
                //pid : node ? node.id : null,
                status : $('#menuState').val()
            });
        });

        // 初始化按钮
        $('#buttonlist').datagrid({
           // url : '/button/paging',
            fit : true,
            nowrap : true,
            fitColumns : true,
            pagination : true,
            rownumbers : true,
            pageSize : 10,
            pageList : [10, 20, 30, 40, 50 ],
            idField : 'id',
            columns : [ [
                {
                field : 'control',
                align : 'center',
                width : 40,
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array=[];
                    array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForButton('+rowData.idx+')"></a>');
                    <shiro:hasPermission name="menu:editButton">
                    if(rowData.status == '1'){
                        array.push('<a class="handle forbidden" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/2\', null, \'buttonlist\');"></a>');
                        array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAddButton('+rowData.idx+')"></a>');
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/0\', null, \'buttonlist\');"></a>');
                    }
                    if(rowData.status == '2'){
                        array.push('<a class="handle putaway" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/1\', null, \'buttonlist\');"></a>');
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/0\', null, \'buttonlist\');"></a>');

                    }
                    </shiro:hasPermission>
                    return array.join('');
                }},
                {
                field : 'idx',
                align : 'center',
                width : 30,
                sortable : true,
                title : 'ID'
            }, {
                field : 'name',
                align : 'center',
                width : 40,
                sortable : true,
                title : '按钮名称'
            }, {
                field : 'code',
                align : 'center',
                width : 40,
                sortable : true,
                title : '编码'
            },{
                field : 'status',
                align : 'center',
                width : 20,
                sortable : true,
                title : '状态',
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            }
             ] ],
            onLoadSuccess: function (data) {
                $('#buttonlist').datagrid('clearSelections');
            }
        });

    });



    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAdd(idx) {
        var url = idx ? '/menu/edit/'+idx : '/menu/add/1';
        var title =idx ? '修改菜单' : '新增菜单';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            //skin: 'layui-layer-rim', //加上边框
            area: ['580px', '500px'], //宽高
            content: url,
            yes: function(index, layero){
                layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm();
                return false;
            }
        });
    }

    /**
     *  查看按钮
     * @param idx 主键idx
     */
    function showForMenu(idx) {
        var url ='/menu/view/'+idx ;
        var title ='查看菜单';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['580px', '500px'], //宽高
            content: url
        });
    }



    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddButton(idx) {
        var node = $('#menuTree').tree('getSelected');
        var url = idx ? '/menu/button/edit/'+idx : '/menu/button/add/1?menuIdx=' + node.id;
        var title =idx ? '修改按钮' : '新增按钮';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['525px', '500px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm();
                return false;
            }
        });
    }

    /**
     *  查看按钮
     * @param idx 主键idx
     */
    function showForButton(idx) {
        var node = $('#menuTree').tree('getSelected');
        var url = '/menu/button/view/'+idx;
        var title = '查看按钮';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['525px', '500px'], //宽高
            content: [url, 'no']
        });
    }


</script>
</body>
</html>
