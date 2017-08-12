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
                <span class="search twiceEditTableList-qry"
                      style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">商品ID：</label>
                            <input class="txt margin-r-20" type="text" id="twice-idx">
                        </div>
                        <div class="screen-col">
                            <label class="name">中文品名：</label>
                            <input class="txt margin-r-20" type="text" id="twice-cnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">货号：</label>
                            <input class="txt margin-r-20" type="text" id="twice-itemNo">
                        </div>
                        <!--<div class="screen-col">
                            <label class="name">条形码：</label>
                            <input class="txt margin-r-20" type="text" id="twice-barcode">
                        </div>-->
                        <div class="screen-col">
                            <label class="name">品牌：</label>
                            <p class="state">
                                <select class="select-pl" id="twice-brandIdx">
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
                            <input class="txt margin-r-20" type="text" id="twice-batch" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)"/>
                        </div>
                        <div class="screen-col">
                            <label class="name">采集状态：</label>
                            <p class="state">
                                <select class="select-pl" id="twice-collectionStatus">
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
                                <select class="select-pl" id="twice-firstCategoryIdx"
                                        next-id="twice-secondCategoryIdx" onchange="changeSelect($(this));">
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
                                <select class="select-pl" id="twice-secondCategoryIdx"
                                        next-id="twice-threeCategoryIdx" onchange="changeSelect($(this));">
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
                                <select class="select-pl ld" id="twice-threeCategoryIdx">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${threeCategoryList}" var="p">
                                        <option value="${p.idx}">${p.name}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <!--<div class="screen-col">
                           <label class="name">发布状态：</label>
                           <p class="state">
                               <select class="select-pl" id="price-publishStatus">
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
                <table id="twiceEditTable" style="width:98%;height: 464px;"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#twiceEditTable').datagrid({
                url: '/collect/item/grid?purchaseStatus=${purchaseConfirmed}',
                idField: 'itemid',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: 'true',
                method: 'post',
                columns: [[
                    {
                        field: 'action',
                        align: 'center',
                        width: '10%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var array = [];
                            <shiro:hasPermission name="collect:item:twiceEdit:edit">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑预发布商品" onclick="twiceEdit(' + rowData.collectorItem.idx + ');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="collect:item:twiceEdit:view">
                            array.push('<a class="handle refer" href="javascript:void(0)" title="查看采集信息" onclick="viewCollectionInfo(' + rowData.collectorItem.idx + ');"></a>');
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
                    },
                    {
                        title: '发布状态', field: 'publishStatus', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.itemCollection) {
                                var pStatus = row.itemCollection.publishStatus;
                                return ${publishStatusJson}[pStatus];
                            }
                            return "未发布";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    $('#twiceEditTable').datagrid('clearSelections');
                }
            });

            $('.twiceEditTableList-qry').on('click', function () {
                $('#twiceEditTable').datagrid('load', {
                    idx: $.trim($('#twice-idx').val()),
                    cnName: $.trim($('#twice-cnName').val()),
                    itemNo: $.trim($('#twice-itemNo').val()),
                    barcode: $.trim($('#twice-barcode').val()),
                    brandIdx: $('#twice-brandIdx').val(),
                    batch: $('#twice-batch').val(),
                    firstCategoryIdx: $('#twice-firstCategoryIdx').val(),
                    secondCategoryIdx: $('#twice-secondCategoryIdx').val(),
                    threeCategoryIdx: $('#twice-threeCategoryIdx').val(),
                    collectionStatus: $('#twice-collectionStatus').val()
                });
            });

            setSelectData($("#twice-firstCategoryIdx"), 0);
        });

        function twiceEdit(idx) {
            layer.load(2, {shade: 0.01});

            var title = "编辑预发布商品";
            if ($('#tabs').tabs('exists', title)) {
                $('#tabs').tabs('close', title);
            }

            var url = "/collect/item/collection/edit2/" + idx;
            var height = $(window).height() - 136;
            var content = '<iframe onload="Ms.closeLoading()" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:' + height + 'px;"></iframe>';
            $('#tabs').tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }

        function viewCollectionInfo(idx) {
            layer.load(2, {shade: 0.01});

            var title = "查看采集信息";
            if ($('#tabs').tabs('exists', title)) {
                $('#tabs').tabs('close', title);
            }

            var url = "/collect/item/collection/detail/" + idx;
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