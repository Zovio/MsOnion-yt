<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-用户选择属性组列表</title>
    <jsp:include page="../../header.jsp"></jsp:include>
</head>
<body>
<section class="main" >
    <div class="table-container" style="height: 480px;">
        <div class="main-content">
            <div class="screen" >
                <div class="screen-row">
                    <label class="name">名称：</label>
                    <input class="txt" type="text" id="groupName">
                    <span class="search" id="collector-attr-group-list2-qry"><i></i>搜索</span>
                </div>
            </div>

            <div class="yt-table" >

                <table id="collector-attr-group-list2" class="easyui-datagrid" style="width:98%;height:450px">

                </table>

            </div>
        </div>
    </div>

</section>


<script>
    var txtArr = [],defalutIdxArr = ${attributeGroupIds};
    if (defalutIdxArr) {
        for (var i=0; i < defalutIdxArr.length; i++) {
            defalutIdxArr[i] = defalutIdxArr[i] +"";
        }
    }
    $(function(){

        $('#collector-attr-group-list2').datagrid({
            url : '/collect/categy/getAttributeGroupGrid',
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
                title : '属性组名称'
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
                <%-- 将之前数据库中保存的默认选中 --%>
                if(defalutIdxArr.length >0 && data){
                    $.each(data.rows, function(index, item){
                        if($.inArray(item.idx+'', defalutIdxArr)>-1){
                            $('#collector-attr-group-list2').datagrid('checkRow', index);
                        }
                    });
                }
            }
        });

        $('#collector-attr-group-list2-qry').on('click',function () {
            $('#collector-attr-group-list2').datagrid('load',{
                name:$.trim($('#groupName').val())
            });
        });

    });

    <%-- 获取选中的资源组idx ， name --%>
    function chooseResult(){
        var tmpIdxArr = [];
        for(var i in txtArr){
            var id =  txtArr[i].split("|")[0];
            tmpIdxArr.push(id);
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

</body>
</html>

