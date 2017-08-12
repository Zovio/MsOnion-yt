<%@ page import="cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-验证码图片列表</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="sysVerifyCode:add">
            <div class="yt-row">
                <button type="button"  class="brandBtn active" onclick="save()">上传</button>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <label class="name" >备注：</label>
                    <input class="txt" type="text" id="verifyCode-remark" style="width: 180px;">
                    <label class="name" style="padding-left: 10px;">类型：</label>
                    <p class="state">
                        <select class="state1" id="verifyCode-type">
                            <option value="">请选择</option>
                            <c:forEach items="${verifyCodePictureTypeMap}" var="p">
                                <option value="${p.key}">${p.value}</option>
                            </c:forEach>
                        </select>
                        <i class="downIcon"></i>
                    </p>
                    <label class="name" style="padding-left: 10px;">状态：</label>
                    <p class="state">
                        <select class="state1" id="verifyCode-status">
                            <option value="">请选择</option>
                            <c:forEach items="${statusMap}" var="m">
                                <option value="${m.key}">${m.value}</option>
                            </c:forEach>
                        </select>
                        <i class="downIcon"></i>
                    </p>
                    <span class="search" id="verifyCode-qry"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="verifyCode-tableList" class="easyui-datagrid" style="width:98%;height: 380px;">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    $(function(){
        $('#verifyCode-tableList').datagrid({
            url : '/system/verifyCode/list/getPage',
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
                        var deletedStatus = "<%=MsOnionTableRecordStatus.DELETED.getValue()%>";
                        var isDeleted = (rowData.status == deletedStatus);
                        var array = [];
                        if(!isDeleted){
                            array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showDetail(' + rowData.idx + ')"></a>');
                            <shiro:hasPermission name="sysVerifyCode:del">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="delContent(' + rowData.idx + ')"></a>');
                            </shiro:hasPermission>
                        }
                        return array.join('');
                    }
                },
                {
                    field : 'idx',
                    align : 'center',
                    width : "10%",
                    sortable : true,
                    title : 'ID'
                }, {
                    field : 'type',
                    align : 'center',
                    width : "20%",
                    sortable : true,
                    title : '类型',
                    formatter : function(value, rowData, rowIndex){
                        return ${verifyCodePictureTypeJson}[value] ;
                    }
                }, {
                    field : 'imagePath',
                    align : 'center',
                    width : "45%",
                    sortable : true,
                    title : '图片路径'
                },{
                    field : 'status',
                    align : 'center',
                    width : "10%",
                    title : '状态',
                    sortable : true,
                    formatter : function(value, rowData, rowIndex){
                        return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                    }
                } ] ],
            onLoadSuccess: function (data) {
                $('#verifyCode-tableList').datagrid('clearSelections');
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

        $('#verifyCode-qry').on('click',function () {
            $('#verifyCode-tableList').datagrid('load',{
                remark:$.trim($('#verifyCode-remark').val()),
                type:$.trim($('#verifyCode-type').val()),
                status:$.trim($('#verifyCode-status').val())
            });
        });

    });


    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function showDetail(idx) {
        var url = '/system/verifyCode/list/detail?idxStr='+idx ;
        var title ='查看验证码图片';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['490px', '550px'], //宽高
            content: [url, 'no']
        });
    }

    /**
     * 删除
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
                url: "/system/verifyCode/list/delete",
                data: {
                    idxStr: idx
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        layer.msg('操作成功！', {icon: 6});
                        $('#verifyCode-tableList').datagrid('clearSelections');
                        $('#verifyCode-tableList').datagrid('reload', {});
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
     * 上传页面
     */
    function save(idx) {
        var title = "新增验证码图片";
        layer.open({
            type: 2,
            title: title,
            area: ['490px', '520px'],
            btn: ['确定', '取消'],
            content: ['/system/verifyCode/list/save', 'no'],
            yes: function (index, layero) {
                Ms.saveLoading();
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm();
                return false;
            },
            btn2: function () {
                Ms.clearGridSelections('verifyCode-tableList');
            },
            cancel: function () {
                Ms.clearGridSelections('verifyCode-tableList');
            }
        });
    }
</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

