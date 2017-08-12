<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-关键词列表</title>
    <jsp:include page="../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="content:keywd:add">
            <div class="yt-row">
                <button type="button"  class="brandBtn active" onclick="editOrAddKW()">新增</button>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">关键词：</label>
                        <input class="txt" type="text" id="keyword">
                    </div>
                     <div class="screen-col">
                        <label class="name">首字母：</label>
                        <input class="txt" type="text" id="firstWord">
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
                    <span class="search" id="keywordSearchBtn"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="keywordList" class="easyui-datagrid" style="width:98%;height: 464px;">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    var $keywordListGrid=$("#keywordList");
    $(function(){
        $keywordListGrid.datagrid({
            url : '/content/keywd/getKeywordPage',
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
                width : "15%",
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array=[];
                    <shiro:hasPermission name="content:keywd:edit">
                    if(rowData.status==1) {
                        array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAddKW(' + rowData.idx + ')"></a>');
                    }
                    </shiro:hasPermission>
                    <shiro:hasPermission name="content:keywd:add">
                    if(rowData.status==1) {
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="delKeywd(' + rowData.idx + ',1)"></a>');
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
                field : 'keyword',
                align : 'center',
                width : 50,
                title : '关键词'
            },{
                field : 'firstWord',
                align : 'center',
                width : 50,
                sortable : true,
                title : '首字母'
            } ] ],
            onLoadSuccess: function (data) {
                $keywordListGrid.datagrid('clearSelections');
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

        $('#keywordSearchBtn').on('click',function () {
            $keywordListGrid.datagrid('load',{
                keyword:$.trim($('#keyword').val()),
                firstWord:$.trim($('#firstWord').val()),
                isDelete:$('#isDelete').val()
            });
        });

    });

    /**
     * 删除关键词
     * @param idx
     */
    function delKeywd(idx){
        layer.confirm('您确定要删除吗？', {
            title: false,
            shade: 0,
            btn: ['确定', '关闭']
        }, function (index) {
            $.ajax({
                type: "post",
                url: "/content/keywd/removeKeyword",
                data: {
                    idxStr: idx
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        layer.msg('操作成功！', {icon: 6});
                        $keywordListGrid.datagrid('clearSelections');
                        $keywordListGrid.datagrid('reload', {});
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

    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddKW(idx) {
        var url = idx ? '/content/keywd/detail?idxStr='+idx : '/content/keywd/detail';
        var title =idx ? '修改关键词' : '新增关键词';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            //  skin: 'layui-layer-rim', //加上边框
            area: ['530px', '400px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm();
                return false;
            }
        });
    }


</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

