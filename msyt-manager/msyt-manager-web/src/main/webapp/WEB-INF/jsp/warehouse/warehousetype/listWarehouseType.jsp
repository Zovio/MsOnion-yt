<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统-商品仓库列表信息</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <div class="main-content" >
            <shiro:hasPermission name="wareho:waretype:add">
                <div class="yt-row">
                    <button type="button" class="brandBtn active" onclick="editOrAddWR()">新增</button>
                </div>
            </shiro:hasPermission>
            <div class="screen-cont">

                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">仓库名称：</label>
                            <input class="txt margin-r-20" type="text" id="wr-name">
                        </div>
                        <div class="screen-col">
                            <label class="name">状态：</label>
                            <p class="state">
                                <select class="select-pl" id="wr-status">
                                    <option value="">请选择</option>
                                    <c:forEach items="${statusMap}" var="m">
                                        <option value="${m.key}">${m.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>

                            <span class="search warehouseTypeTable-qry" style="margin: 10px 0 0 100px;"><i></i>搜索</span>

                    </div>
                </div>
            </div>
            <div class="yt-table">
                <table id="warehouseTypeTable" style="width:98%;height: 464px;"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        /* 报关资料 */
        $(function () {
            $('#warehouseTypeTable').datagrid({
                url: '/wareho/waretype/paging',
                idField: 'id',
                rownumbers: true,
                pagination: true,
                pageSize: 10,
                pageList: [10, 20, 30, 40, 50],
                method: 'post',
                columns: [[
                    {
                        field: 'control',
                        align: 'center',
                        width: '10%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var array = [];
                            if(rowData.status == '1') {
                                <shiro:hasPermission name="wareho:waretype:edit">
                                array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick=editOrAddWR("' + rowData.idx + '") ></a>');
                                </shiro:hasPermission>

                                <shiro:hasPermission name="wareho:waretype:del">
                                array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'wareho/waretype/updateStatus/' + rowData.idx + '/0\', null, \'warehouseTypeTable\');"></a>');
                                </shiro:hasPermission>
                                return array.join('');
                            }
                        }
                    },
                    {title: 'ID', field: 'idx', width: '10%', align: 'center', sortable: true},
                    {title: '仓库名称', field: 'warehouseName', width: '15%', align: 'center', sortable: true},
                    {title: '仓库编号', field: 'warehouseCode', width: '10%', align: 'center', sortable: true},
                    {title: '货号尾数', field: 'mantissa', width: '10%', align: 'center', sortable: true},
                    {title: '排序', field: 'zindex', width: '10%', align: 'center', sortable: true},
                    {title: '备注', field: 'remark', width: '25%', align: 'center'},
                    {
                        field : 'status',
                        align : 'center',
                        sortable : true,
                        width : '10%',
                        title : '状态',
                        formatter : function(value, rowData, rowIndex){
                            return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    $('#warehouseTypeTable').datagrid('clearSelections');
                }
            });

            $('.warehouseTypeTable-qry').on('click', function () {
                $('#warehouseTypeTable').datagrid('load', {
                    name: $.trim($('#wr-name').val()),
                    status: $.trim($('#wr-status').val())
                });
            });
        });

        /**
         *  编辑或者新增
         * @param idx 主键idx
         */
        function editOrAddWR(idx) {
            var url = idx ? '/wareho/waretype/edit/'+idx : '/wareho/waretype/add/1';
            var title =idx ? '修改仓库' : '新增仓库';
            //页面层
            layer.open({
                title: title,
                type: 2,
                btnAlign: 'c',
                btn: ['保存','关闭'],
                area: ['530px', '500px'], //宽高
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


    </script>
</section>
</body>
</html>
