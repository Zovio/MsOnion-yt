<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-用户选择角色列表</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main" >
    <div class="table-container" style="height: 480px;">
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <label class="name">名称：</label>
                    <input class="txt" type="text" id="roleName2">
                    <span class="search" id="rolelist2-qry"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="rolelist2" class="easyui-datagrid" style="width:98%;height:450px">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    var txtArr = [],defalutIdxArr = ${roleIdxListJson} ;
    $(function(){


        $('#rolelist2').datagrid({
            url : '/member/chooseRole/paging?status=1',
            fit : true,
            nowrap : true,
            fitColumns : true,
            pagination : true,
            rownumbers : true,
            pageSize : 10,
            pageList : [10, 20, 30, 40, 50 ],
            idField : 'id',
            columns : [ [ {
                field : 'id',
                checkbox:true
            }, {
                field : 'idx',
                align : 'center',
                width : 50,
                sortable : true,
                title : 'ID'
            }, {
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
            } ] ],
            onSelect:function(index,row){
                girdAddRow(row, defalutIdxArr, txtArr);
            },
            onUnselect:function(index,row){
                girdRemoveRow(row, defalutIdxArr, txtArr)
            },
            onSelectAll:function(rows){
                girdAddAll(rows, defalutIdxArr, txtArr);

            },
            onUnselectAll:function(rows){
                girdRemoveAll(rows, defalutIdxArr, txtArr);
            },
            onLoadSuccess: function (data) {
                // 将之前数据库中保存的默认选中
                if(defalutIdxArr.length >0 && data){
                    $.each(data.rows, function(index, item){
                        if($.inArray(item.idx+'', defalutIdxArr)>-1){
                            $('#rolelist2').datagrid('checkRow', index);
                        }
                    });
                }
            }
        });

        $('#rolelist2-qry').on('click',function () {
            $('#rolelist2').datagrid('load',{
                name:$.trim($('#roleName2').val())
            });
        });

    });



    /**
     *  选择角色结果
     * @param i
     */
    function chooseRoleResult() {
        // 获取idx|name 数组中idx
        var tmpIdxArr =[];
        for(var i in txtArr){
            var idx =  txtArr[i].split("|")[0];
            tmpIdxArr.push(idx);
        }
        for (var i in defalutIdxArr){
            if($.inArray(defalutIdxArr[i], tmpIdxArr) == -1){
                txtArr.push(defalutIdxArr[i]+"|"+"");
            }
        }
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
        return txtArr.join(",");
    }

</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

