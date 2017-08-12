<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统-商品仓库信息</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="wareho:wareho:edit">
            <div class="yt-row">
                <input type="button" class="brandBtn active" value="同步ERP库存" id="warehouse-syncStock"/>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="screen-cont">
                <span class="screen-show"></span>
                <span class="screen-hide"></span>
                <span class="search customsTable-qry" style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">商品ID：</label>
                            <input class="txt margin-r-20" type="text" id="warehouse-idx">
                        </div>
                        <div class="screen-col">
                            <label class="name">中文品名：</label>
                            <input class="txt margin-r-20" type="text" id="warehouse-cnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">货号：</label>
                            <input class="txt margin-r-20" type="text" id="warehouse-itemNo">
                        </div>
                        <div class="screen-col">
                            <label class="name">权重：</label>
                            <input class="txt margin-r-20" type="text" onkeyup="MS.onlyNum(this);"
                                   id="warehouse-weight">
                        </div>
                        <!--<div class="screen-col">
                            <label class="name">条形码：</label>
                            <input class="txt margin-r-20" type="text" id="warehouse-barcode">
                        </div>
                        <div class="screen-col">
                            <label class="name">是否一键下单：</label>
                            <p class="state">
                                <select class="select-pl" id="warehouse-isKeyOrder">
                                    <option value="-1">全部</option>
                                    <c:forEach items="${isKeyOrderMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">是否包邮：</label>
                            <p class="state">
                                <select class="select-pl" id="warehouse-isFreeShipping">
                                    <option value="-1">全部</option>
                                    <c:forEach items="${isFreeShippingMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>-->
                        <!--<div class="screen-col">
                            <label class="name">发布状态：</label>
                            <p class="state">
                                <select class="select-pl" id="warehouse-publishStatus">
                                    <option value="-1">全部</option>
                                   <c:forEach items="${publishStatusMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>-->
                    </div>
                </div>
            </div>
            <div class="yt-table">
                <table id="warehouse-warehouseTable" style="width:98%;height: 464px;"></table>
            </div>
        </div>
    </div>

    <div id="warehouse-issueHint" class="issueHint"
         style="overflow-y: scroll;overflow-x: hidden;height: 187px;display: none;">
        <div class="issue-row" id="warehouse-issueHint-row">
        </div>
    </div>

    <script type="text/javascript">
        /* 报关资料 */
        $(function () {
            $('#warehouse-warehouseTable').datagrid({
                url: '/wareho/wareho/grid',
                idField: 'id',
                rownumbers: true,
                pagination: true,
                pageSize: 10,
                pageList: [10, 20, 30, 40, 50],
                method: 'post',
                columns: [[
                    {title: '', field: '', width: '10%', align: 'center', checkbox: true},
                    {
                        field: 'control',
                        align: 'center',
                        width: '10%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var array = [];
                            <shiro:hasPermission name="wareho:wareho:edit">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑商品仓库信息" onclick=editWarehouse("' + rowData.collectorItem.idx + '") ></a>');
                            </shiro:hasPermission>
                            array.push('<a class="handle refer" href="javascript:void(0)" title="查看商品仓库信息" onclick=showWarehouse("' + rowData.collectorItem.idx + '") ></a>');
                            return array.join('');
                        }
                    },
                    {
                        title: '商品ID', field: 'idx', width: '10%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItem.idx;
                        }
                    },
                    {
                        title: '货号', field: 'itemNo', width: '15%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItem.itemNo;
                        }
                    },
                    {
                        title: '条形码', field: 'barcode', width: '15%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItem.barcode;
                        }
                    },
                    {
                        title: '权重', field: 'weight', width: '15%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItem.weight;
                        }
                    },
                    {
                        title: '中文品名', field: 'cnName', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            return row.collectorItem.cnName;
                        }
                    },
                    {
                        title: '实时库存', field: 'realTimeInventory', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.warehouse) {
                                return row.warehouse.realTimeInventory;
                            }
                            return "";
                        }
                    },
                    {
                        title: '安全库存', field: 'safeInventory', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.warehouse) {
                                return row.warehouse.safeInventory;
                            }
                            return "";
                        }
                    },
                    {
                        title: '发货系数', field: 'deliveryCoefficient', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.warehouse) {
                                return row.warehouse.deliveryCoefficient;
                            }
                            return "";
                        }
                    },
                    {
                        title: '是否一键下单',
                        field: 'isKeyOrder',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.warehouse) {
                                var isKeyOrder = row.warehouse.isKeyOrder;
                                return ${isKeyOrderJson}[isKeyOrder];
                            }
                            return "";
                        }
                    },
                    {
                        title: '是否包邮',
                        field: 'isFreeShipping',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.warehouse) {
                                var isFreeShipping = row.warehouse.isFreeShipping;
                                return ${isFreeShippingJson}[isFreeShipping];
                            }
                            return "";
                        }
                    },
                    {
                        title: '发布状态',
                        field: 'publishStatus',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.warehouse) {
                                var pStatus = row.warehouse.publishStatus;
                                return ${publishStatusJson}[pStatus];
                            }
                            return "未发布";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    $('#warehouse-warehouseTable').datagrid('clearSelections');
                },
                loadFilter: function (result) {
                    if (result.status == 200) {
                        return result.data;
                    } else {
                        layer.msg(result.msg, {icon: 2});
                    }
                }
            });

            $('.customsTable-qry').on('click', function () {
                $('#warehouse-warehouseTable').datagrid('load', {
                    idx: $.trim($('#warehouse-idx').val()),
                    cnName: $.trim($('#warehouse-cnName').val()),
                    itemNo: $.trim($('#warehouse-itemNo').val()),
                    barcode: $.trim($('#warehouse-barcode').val()),
                    weight: $.trim($('#warehouse-weight').val()),
                    isKeyOrder: $.trim($('#warehouse-isKeyOrder').val()),
                    isFreeShipping: $.trim($('#warehouse-isFreeShipping').val()),
                    purchaseStatus: $('#warehouse-purchaseStatus').val()
                });
            });

            // 同步ERP库存
            $("#warehouse-syncStock").click(function () {
                var checkedCel = getChecked();
                if (!checkedCel || checkedCel.length == 0) {
                    layer.confirm('请至少选择一条数据', {
                        title: false,
                        btn: ['确定', '取消']
                    });
                    return false;
                }
                var idxArr = [];
                for (var i in checkedCel) {
                    var id = checkedCel[i].collectorItem.idx;
                    idxArr.push(id);
                }
                var msgIndex;
                $.ajax({
                    url: "/wareho/wareho/syncErpStock",
                    type: "post",
                    dataType: "json",
                    data: {itemIdxStr: idxArr.join(",")},
                    beforeSend: function (XHR) {
                        msgIndex = layer.msg('正在同步ERP库存...', {
                            icon: 16,
                            shade: [0.1, '#fff'],
                            time: 600000
                        });
                    },
                    success: function (result) {
                        if (result.status == 200) {
                            if (result.data && result.data.length > 0) {
                                var resultStr = [];
                                for (var i in result.data) {
                                    resultStr.push("<span class='issue-font'>" + result.data[i] + "</span>");
                                }
                                $("#warehouse-issueHint-row").html(resultStr.join(""));
                                layer.close(msgIndex);
                                layer.open({
                                    type: 1,
                                    title: '同步结果信息提示',
                                    area: ['350px', '300px'],
                                    btn: ['确定'],
                                    shadeClose: true, //点击遮罩关闭
                                    skin: 'hint-m',
                                    content: $('#warehouse-issueHint')
                                });
                            } else {
                                layer.msg("操作成功！", {icon: 1});
                            }
                            $('#warehouse-warehouseTable').datagrid('reload', {});
                        } else {
                            layer.msg("操作失败！", {icon: 2});
                        }
                    }
                });
            });
        });

        function editWarehouse(idx) {
            layer.open({
                type: 2,
                title: '修改商品仓库信息',
                area: ['655px', '600px'],
                btn: ['保存', '保存并发布'],
                skin: 'setPricepop',
                content: ['/wareho/wareho/edit/' + idx, 'no'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                },
                btn2: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm(1);
                    return false;
                },
                cancel: function () {
                    Ms.clearGridSelections('warehouse-warehouseTable');
                }
            });
        }

        function showWarehouse(idx) {
            layer.open({
                type: 2,
                title: '查看商品仓库信息',
                area: ['655px', '600px'],
                btn: ['关闭'],
                content: ['/wareho/wareho/edit/' + idx + "?type=view", 'no'],
                yes: function (index) {
                    Ms.clearGridSelections('warehouse-warehouseTable');
                    layer.close(index);
                },
                cancel: function () {
                    Ms.clearGridSelections('warehouse-warehouseTable');
                }
            });
        }

        function getChecked() {
            var checkedCel = $('#warehouse-warehouseTable').datagrid('getChecked');
            return checkedCel;
        }
    </script>
</section>
</body>
</html>
