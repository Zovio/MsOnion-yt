<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="main-content">
            <div class="screen-cont">
                <span class="screen-show"></span>
                <span class="screen-hide"></span>
                <span class="search gatherTableList-qry"
                      style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">商品ID：</label>
                            <input class="txt margin-r-20" type="text" id="collector-idx">
                        </div>
                        <div class="screen-col">
                            <label class="name">中文品名：</label>
                            <input class="txt margin-r-20" type="text" id="collector-cnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">货号：</label>
                            <input class="txt margin-r-20" type="text" id="collector-itemNo">
                        </div>
                        <!--<div class="screen-col">
                            <label class="name">条形码：</label>
                            <input class="txt margin-r-20" type="text" id="collector-barcode">
                        </div>-->
                        <div class="screen-col">
                            <label class="name">品牌：</label>
                            <p class="state">
                                <select class="select-pl" id="collector-brandIdx">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${collectorItemBrandList}" var="p">
                                        <option value="${p.idx}">${p.brandName}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">批次：</label>
                            <input class="txt margin-r-20" type="text" id="collector-batch">
                        </div>
                        <div class="screen-col">
                            <label class="name">采集状态：</label>
                            <p class="state">
                                <select class="select-pl" id="collector-collectionStatus">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${collectionStatusMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">第一品类：</label>
                            <p class="state">
                                <select class="select-pl" id="collector-firstCategoryIdx"
                                        next-id="collector-secondCategoryIdx" onchange="changeSelect($(this));">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${firstCategoryList}" var="p">
                                        <option value="${p.idx}">${p.name}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">第二品类：</label>
                            <p class="state">
                                <select class="select-pl" id="collector-secondCategoryIdx"
                                        next-id="collector-threeCategoryIdx" onchange="changeSelect($(this));">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${secondCategoryList}" var="p">
                                        <option value="${p.idx}">${p.name}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">第三品类：</label>
                            <p class="state">
                                <select class="select-pl" id="collector-threeCategoryIdx">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${threeCategoryList}" var="p">
                                        <option value="${p.idx}">${p.name}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="yt-table">
                <table id="collector-gatherTable" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#collector-gatherTable').datagrid({
                url: '/collect/item/grid?purchaseStatus=${purchaseConfirmed}',
                idField: 'id',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: true,
                method: 'post',
                columns: [[
                    {
                        field: 'action',
                        align: 'center',
                        width: '5%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var array = [];
                            <shiro:hasPermission name="collect:item:collection:edit">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑采集信息" onclick="editCollectionInfo(' + rowData.collectorItem.idx + ');" style="background-position: -295px -15px;"></a>');
                            </shiro:hasPermission>
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
                        title: '条形码', field: 'barcode', width: '10%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItem.barcode;
                        }
                    },
                    {
                        title: '中文品名', field: 'cnName', width: '15%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItem.cnName;
                        }
                    },
                    {
                        title: '类目', field: 'name', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.collectorItemCategory) {
                                return row.collectorItemCategory.name;
                            }
                            return "";
                        }
                    },
                    {
                        title: '仓库',
                        field: 'setWarehouseTypeIdxDynamicS',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.collectorItem) {
                                return row.collectorItem.warehouseTypeIdxDynamicS;
                            }
                            return "";
                        }
                    },
                    {
                        title: '品牌名称', width: '18%', align: 'center', field: 'brandName',
                        formatter: function (value, row, index) {
                            if (row.collectorItemBrand) {
                                return row.collectorItemBrand.brandName;
                            }
                            return "";
                        }
                    },
                    {
                        title: '采购状态',
                        field: 'purchaseStatus',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return ${purchaseStatusMapJson}[row.collectorItem.purchaseStatus];
                        }
                    },
                    {
                        title: '采集状态',
                        field: 'collectionStatus',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return ${collectionStatusMapJson}[row.collectorItem.collectionStatus];
                        }
                    },
                    {
                        title: '批次', field: 'batch', width: '10%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.collectorItem.batch;
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    $('#collector-gatherTable').datagrid('clearSelections');
                }
            });

            $('.gatherTableList-qry').on('click', function () {
                $('#collector-gatherTable').datagrid('load', {
                    idx: $.trim($('#collector-idx').val()),
                    cnName: $.trim($('#collector-cnName').val()),
                    itemNo: $.trim($('#collector-itemNo').val()),
                    barcode: $.trim($('#collector-barcode').val()),
                    brandIdx: $('#collector-brandIdx').val(),
                    batch: $('#collector-batch').val(),
                    firstCategoryIdx: $('#collector-firstCategoryIdx').val(),
                    secondCategoryIdx: $('#collector-secondCategoryIdx').val(),
                    threeCategoryIdx: $('#collector-threeCategoryIdx').val(),
                    collectionStatus: $('#collector-collectionStatus').val(),
                    publishStatus: $('#price-publishStatus').val()
                });
            });

            setSelectData($("#collector-firstCategoryIdx"), 0);
        });

        function editCollectionInfo(idx) {

            layer.load(2, {shade: 0.01});

            var title = "编辑采集信息";
            if ($('#tabs').tabs('exists', title)) {
                $('#tabs').tabs('close', title);
            }

            var url = '/collect/item/collection/edit/' + idx;
            var height = $(window).height() - 136;
            var content = '<iframe onload="Ms.closeLoading()" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:' + height + 'px;"></iframe>';
            $('#tabs').tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }
        /**
         * 设置类目数据
         * @param obj DOM元素
         * @param pid 父类的ID
         */
        function setSelectData(obj, pid) {
            if (pid == null || pid < 0) {
                $(obj).html("");
                $(obj).append("<option value=\"-1\">请选择</option>");
                return false;
            }
            $.ajax({
                url: "/collect/item/getSubList",
                data: {parentIdStr: pid},
                type: "get",
                async: false,
                dataType: "json",
                success: function (result) {
                    if (result.status == 200) {
                        $(obj).html("");
                        $(obj).append("<option value=\"-1\">请选择</option>");
                        var josnDataList = result.data;
                        if (josnDataList && josnDataList.length > 0) {
                            for (var i in josnDataList) {
                                var josnData = josnDataList[i];
                                var htmlObj = $("<option value=\"" + josnData.idx + "\">" + josnData.name + "</option>");

                                $(obj).append(htmlObj);
                            }
                        }
                    }
                }
            });

        }
        function changeSelect(obj) {
            // 多级联动
            var nextIdName = $(obj).attr("next-id");
            var idx = $(obj).val();

            if (nextIdName != undefined) {
                var subObj = $("#" + nextIdName);
                if (idx < 0) {
                    $(subObj).html("");
                    $(subObj).append("<option value=\"-1\">请选择</option>");
                } else {
                    setSelectData(subObj, idx);
                }
                $(subObj).trigger("change");
            }
        }
    </script>
</section>

<jsp:include page="../../../footer.jsp"/>