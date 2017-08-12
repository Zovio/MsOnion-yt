<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-内容列表</title>
    <jsp:include page="../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="content:list:add">
            <div class="yt-row">
                <button type="button"  class="brandBtn active" onclick="editOrAddContent()">新增</button>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">名称：</label>
                        <input class="txt" type="text" id="name">
                    </div>
                    <div class="screen-col">
                        <label class="name">类型：</label>
                        <p class="state">
                            <select class="state1" id="type">
                                <option value="0">请选择</option>
                                <option value="1">HTML</option>
                                <option value="2">图片</option>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <div class="screen-col">
                        <label class="name">状态：</label>
                        <p class="state">
                            <select class="state1" id="isDelete">
                                <option value="1">正常</option>
                                <option value="0">已删除</option>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <span class="search" id="contentPageSerch"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="contentlist" class="easyui-datagrid" style="width:98%;height: 464px;">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    var $contentGrid=$('#contentlist');
    $(function(){
        $contentGrid.datagrid({
            url : '/content/list/getContentPage',
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
                width : "10%",
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array = [];
                    <shiro:hasPermission name="content:list:edit">
                    if(rowData.status==1) {
                        array.push('<a class="handle edit" href="javascript:void(0)" title="修改" onclick="editOrAddContent(' + rowData.idx + ')"></a>');
                    }
                    </shiro:hasPermission>
                    <shiro:hasPermission name="content:list:remove">
                    if(rowData.status==1) {
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="delContent(' + rowData.idx + ',2)"></a>');
                    }
                    </shiro:hasPermission>

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
                title : '名称'
            },{
                field : 'code',
                align : 'center',
                width : 50,
                title : '编码'
            },{
                field : 'type',
                align : 'center',
                width : 30,
                title : '类型',
                formatter : function(value, rowData, rowIndex){
                    if(value==2){
                        return "图片";
                    }else{
                        return "HTML";
                    }

                }
            },{
                field : 'start_time',
                align : 'center',
                width : 50,
                title : '开始时间',
                sortable : true,
                formatter : function(value, rowData, rowIndex){
                    return rowData.startTimeEnYyyyMMddHHmmss;
                }
            },{
                field : 'end_time',
                align : 'center',
                width : 50,
                title : '结束时间',
                sortable : true,
                formatter : function(value, rowData, rowIndex){
                    return rowData.endTimeEnYyyyMMddHHmmss;
                }
            } ] ],
            onLoadSuccess: function (data) {
                $contentGrid.datagrid('clearSelections');
            },
            loadFilter: function(data){
                if (data.status == 200) {
                    return data.data;
                } else {
                    layer.msg(data.msg, {icon: 2});
                    return null;
                }
            }

        });

        $('#contentPageSerch').on('click',function () {
            $contentGrid.datagrid('load',{
                name:$.trim($('#name').val()),
                type:$('#type').val(),
                isDelete:$('#isDelete').val()
            });
        });

    });


    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddContent(idx) {
        var url = idx ? '/content/list/edit?idxStr='+idx : '/content/list/edit';
        var title =idx ? '修改内容' : '新增内容';
            var height = $(window).height()-136;
            if ($('#tabs').tabs('exists', title)&&!idx){
                $('#tabs').tabs('select', title);
            } else {
                var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:'+height+'px;"></iframe>';
                $('#tabs').tabs('add',{
                    title:title,
                    content:content,
                    closable:true
                });
            }
    }

    /**
     * 删除content
     * @param idx
     */
    function delContent(idx){
        layer.confirm('您确定要删除吗？', {
            title: false,
            shade: 0,
            btn: ['确定', '关闭']
        }, function (index) {
            $.ajax({
                type: "post",
                url: "/content/list/removeContent",
                data: {
                    idxStr: idx
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        layer.msg('操作成功！', {icon: 6});
                        $contentGrid.datagrid('clearSelections');
                        $contentGrid.datagrid('reload', {});
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

</body>
</html>

