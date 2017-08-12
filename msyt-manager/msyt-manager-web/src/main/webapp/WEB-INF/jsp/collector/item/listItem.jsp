<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:item:add">
                <input type="button" class="brandBtn" value="新增商品" onclick="addCommodity(this.value)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:item:upload">
                <input type="button" class="brandBtn" value="下载模板" id="item-template"/>
                <input type="button" class="brandBtn" value="上传表格" onclick="onClickUpBtn();"/>
                <input id="citem-fileupload" type="file" name="file" style="display: none;"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:item:purchase">
                <input type="button" class="brandBtn" value="确认采购" onclick="Purchase('确认采购编辑信息')"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:item:categy">
                <input type="button" class="brandBtn" value="修改分类" onclick="editCommondity()"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:item:warehouse">
                <input type="button" class="brandBtn" value="修改仓库" onclick="editWarehouse()"/>
            </shiro:hasPermission>
            <%--<shiro:hasPermission name="collect:item:history">
                <input type="button" class="brandBtn" value="历史记录" onclick="showHistory()"/>
            </shiro:hasPermission>--%>
        </div>
        <div class="main-content">
            <div class="screen-cont">
                <span class="screen-show"></span>
                <span class="screen-hide"></span>
                <span class="search itemTableList-qry" style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">商品ID：</label>
                            <input class="txt margin-r-20" type="text" id="citem-idx">
                        </div>
                        <div class="screen-col">
                            <label class="name">中文品名：</label>
                            <input class="txt margin-r-20" type="text" id="citem-cnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">货号：</label>
                            <input class="txt margin-r-20" type="text" id="citem-itemNo">
                        </div>
                        <!--<div class="screen-col">
                            <label class="name">条形码：</label>
                            <input class="txt margin-r-20" type="text" id="citem-barcode">
                        </div>-->
                        <div class="screen-col">
                            <label class="name">品牌：</label>
                            <p class="state">
                                <select class="select-pl" id="citem-brandIdx">
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
                            <input class="txt margin-r-20" type="text" id="citem-batch">
                        </div>
                        <div class="screen-col">
                            <label class="name">采购状态：</label>
                            <p class="state">
                                <select class="select-pl" id="citem-purchaseStatus">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${purchaseStatusMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">第一品类：</label>
                            <p class="state">
                                <select class="select-pl" id="citem-firstCategoryIdx" next-id="citem-secondCategoryIdx"
                                        onchange="changeSelect($(this));">
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
                                <select class="select-pl" id="citem-secondCategoryIdx" next-id="citem-threeCategoryIdx"
                                        onchange="changeSelect($(this));">
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
                                <select class="select-pl ld" id="citem-threeCategoryIdx">
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
                <table id="itemManageList" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <!-- 上传表格弹框 start -->
    <div id="citem-upTableCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 114px;display: none;">
        <div class="promptCont">
            <div class="winRow">
                <label>上传文件：</label>
                <div class="brandLogo">
                    选择文件进行上传...
                    <a href="javascript:;" class="upfile">
                        <input class="uploading" type="file" id="citem-itemFile">
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- 上传表格弹框 end -->

    <!-- 选择仓库 start -->
    <div id="citem-selectWarehouse" style="overflow-y: hidden;overflow-x: hidden;height: 80px;display: none;">
        <div class="promptCont">
            <div class="winRow">
                <label class="lb-name">选择仓库：</label>
                <p class="relative select-re">
                    <select class="select-Ware" id="citem-warehouseTypeIdx">
                        <option value="">请选择</option>

                    </select>
                    <i class="downIcon"></i>
                </p>
            </div>
        </div>
    </div>
    <!-- 选择仓库 end -->

    <script src="/resources/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
    <script src="/resources/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <script src="/resources/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#itemManageList').datagrid({
                url: '/collect/item/grid',
                idField: 'id',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: true,
                method: 'post',
                columns: [[
                    {title: '', field: 'id', width: '10%', align: 'center', checkbox: true},
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
                        title: '品牌名称', width: '10%', align: 'center', field: 'brandName',
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
                            if (row.collectorItem) {
                                var pStatus = row.collectorItem.publishStatus;
                                return ${publishStatusJson}[pStatus];
                            }
                            return "";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    Ms.clearGridSelections('itemManageList');
                }
            });

            $('.itemTableList-qry').on('click', function () {
                $('#itemManageList').datagrid('load', {
                    idx: $.trim($('#citem-idx').val()),
                    cnName: $.trim($('#citem-cnName').val()),
                    itemNo: $.trim($('#citem-itemNo').val()),
                    barcode: $.trim($('#citem-barcode').val()),
                    brandIdx: $('#citem-brandIdx').val(),
                    batch: $('#citem-batch').val(),
                    firstCategoryIdx: $('#citem-firstCategoryIdx').val(),
                    secondCategoryIdx: $('#citem-secondCategoryIdx').val(),
                    threeCategoryIdx: $('#citem-threeCategoryIdx').val(),
                    purchaseStatus: $('#citem-purchaseStatus').val(),
                    publishStatus: $('#price-publishStatus').val()
                });
            });
            // 下载模板
            $('#item-template').on('click', function () {
                var templateApi = '${templateApi}';
                if(!templateApi){
                    layer.msg('暂无模板，请联系管理员配置！', {icon: 2});
                    return false;
                }
                window.location.href = templateApi;
            })

            // 上传表格
            $("#citem-fileupload").click(function () {
                $(this).fileupload({
                    url: '/collect/item/importNewItemData',
                    type: "post",
                    dataType: 'json',
                    start: function (e) {
                        layer.msg('正在上传数据...', {
                            icon: 16,
                            shade: [0.1, '#fff'],
                            time: 6000000
                        });
                    },
                    stop: function (e) {
                    },
                    done: function (e, data) {
                        var dataResult = data.result;
                        // 处理上传成功后处理
                        if (dataResult.status == 200) {
                            layer.msg('上传成功！', {icon: 6});
                            location.href = "/collect/item/downExcel?filePath=" + dataResult.data;
                            Ms.reloadDataGrid('itemManageList');
                        } else {
                            layer.msg(dataResult.msg, {icon: 2});
                        }
                    },
                    fail: function (e, data) {
                        // 处理上传失败后处理
                        layer.msg('上传失败！', {icon: 2});
                    }
                });
            });
            setSelectData($("#citem-firstCategoryIdx"), 0);
        });

        // 新增商品
        function addCommodity(title) {
            layer.open({
                type: 2,
                title: title,
                area: ['800px', '580px'],
                btn: ['保存', '取消'],
                content: ['/collect/item/edit/0', 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                }
            });
        }

        <shiro:hasPermission name="collect:item:edit">
        // 双击修改
        $('#itemManageList').datagrid({
            onDblClickCell: function (index, field, value) {
                var rows = $(this).datagrid('getRows');
                var row = rows[index];

                debugger;

                var hasOnline = row["hasOnline"];
                if (!hasOnline) {
                    layer.open({
                        type: 2,
                        title: '修改商品',
                        area: ['800px', '580px'],
                        btn: ['保存', '取消'],
                        content: ['/collect/item/edit/' + row.collectorItem.idx, 'no'],
                        yes: function (index, layero) {
                            Ms.saveLoading();
                            var iframeWin = window[layero.find('iframe')[0]['name']];
                            iframeWin.submitForm();
                            return false;
                        },
                        btn2: function () {
                            Ms.clearGridSelections('itemManageList');
                        },
                        cancel: function () {
                            Ms.clearGridSelections('itemManageList');
                        }
                    });
                } else {
                    layer.open({
                        type: 2,
                        title: '修改商品',
                        area: ['800px', '580px'],
                        btn: ['关闭'],
                        content: ['/collect/item/edit/' + row.collectorItem.idx, 'no'],
                        yes: function (index, layero) {
                            layer.close(index);
                        }
                    });
                }
            }
        });
        </shiro:hasPermission>

        // 修改分类
        function editCommondity() {
            if (!isChecked()) {
                layer.confirm('请选择一条数据', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }

            var checkedCel = getChecked();
            var id = checkedCel[0].collectorItem.id;
            var purchaseStatus = checkedCel[0].collectorItem.purchaseStatus;
            if (purchaseStatus != ${purchaseConfirmed}) {
                layer.confirm('请先确认采购', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }

            layer.open({
                type: 2,
                title: '选择商品分类',
                area: ['610px', '520px'],
                btn: ['保存', '保存并发布'],
                skin: 'setPricepop',
                content: ['/collect/item/categy/sel/edit/' + id, 'no'],
                yes: function (index, layero) {
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var result = iframeWin.chooseCategoryResult();

                    var categoryIdx;
                    if (result) {
                        var resourceArr = result.split("|");
                        categoryIdx = resourceArr[0];
                    } else {
                        return false;
                    }

                    $.ajax({
                        type: "post",
                        url: "/collect/item/category/save",
                        data: {
                            idx: id,
                            categoryIdx: categoryIdx
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 6});
                                Ms.reloadDataGrid('itemManageList');
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                        },
                        error: function (d) {
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                },
                btn2: function (index, layero) {
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var result = iframeWin.chooseCategoryResult();

                    var categoryIdx;
                    if (result) {
                        var resourceArr = result.split("|");
                        categoryIdx = resourceArr[0];
                    } else {
                        return false;
                    }

                    $.ajax({
                        type: "post",
                        url: "/collect/item/category/save",
                        data: {
                            idx: id,
                            categoryIdx: categoryIdx,
                            syncFlag: "1"
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 6});
                                Ms.reloadDataGrid('itemManageList');
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                        },
                        error: function (d) {
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                },
                cancel: function () {
                    Ms.clearGridSelections('itemManageList');
                }
            });
        }

        // 修改仓库
        function editWarehouse() {
            if (!isChecked()) {
                layer.confirm('请选择一条数据', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }

            var checkedCel = getChecked();
            var id = checkedCel[0].collectorItem.id;
            var purchaseStatus = checkedCel[0].collectorItem.purchaseStatus;
            if (purchaseStatus != ${purchaseConfirmed}) {
                layer.confirm('请先确认采购', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }

            var warehouseType = checkedCel[0].collectorItem.warehouseTypeIdx;
            initWarehouse(warehouseType);
            var inx = layer.open({
                type: 1,
                title: '选择仓库',
                area: ['350px', '200px'],
                btn: ['保存', '保存并发布'],
                skin: 'setPricepop',
                content: $('#citem-selectWarehouse'),
                yes: function (index) {
                    var warehouseTypeIdx = $("#citem-warehouseTypeIdx").val();
                    if (warehouseTypeIdx == "") {
                        layer.confirm('请选择仓库！', {
                            title: false,
                            btn: ['确定']
                        });
                        return false;
                    }

                    if (warehouseType != warehouseTypeIdx) {
                        $.ajax({
                            type: "post",
                            url: "/collect/item/editWarehouse",
                            data: {
                                idx: id,
                                warehouseTypeIdx: warehouseTypeIdx
                            },
                            dataType: 'json',
                            success: function (d) {
                                if (d.status == 200) {
                                    layer.close(inx);
                                    layer.msg(MSYT.SUC_MSG, {icon: 6});
                                    Ms.reloadDataGrid('itemManageList');
                                } else {
                                    layer.msg(d.msg, {icon: 2});
                                }
                            },
                            error: function (d) {
                                layer.msg(d.msg, {icon: 2});
                            }
                        });
                    }
                    layer.close(inx);
                },
                btn2: function (index) {
                    var warehouseTypeIdx = $("#citem-warehouseTypeIdx").val();
                    if (warehouseTypeIdx == "") {
                        layer.confirm('请选择仓库！', {
                            title: false,
                            btn: ['确定']
                        });
                        return false;
                    }

                    $.ajax({
                        type: "post",
                        url: "/collect/item/editWarehouse",
                        data: {
                            idx: id,
                            warehouseTypeIdx: warehouseTypeIdx,
                            syncFlag: "1"
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                layer.close(inx);
                                layer.msg(MSYT.SUC_MSG, {icon: 6});
                                Ms.reloadDataGrid('itemManageList');
                            } else {
                                layer.msg(d.msg, {icon: 2});
                            }
                        },
                        error: function (d) {
                            layer.msg(d.msg, {icon: 2});
                        }
                    });
                    layer.close(inx);
                },
                cancel: function () {
                    Ms.clearGridSelections('itemManageList');
                }
            });
        }

        function isChecked() {
            var checkedCel = $('#itemManageList').datagrid('getChecked');
            if (null == checkedCel || checkedCel.length == 0 || checkedCel.length > 1) {
                return false;
            }
            return true;
        }

        function getChecked() {
            var checkedCel = $('#itemManageList').datagrid('getChecked');
            return checkedCel;
        }

        // 确认采购
        function Purchase(title) {
            var checkedCel = $('#itemManageList').datagrid('getChecked');
            if (null == checkedCel || checkedCel.length == 0 || checkedCel.length > 1) {
                layer.confirm('请选择一条数据', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }

            var id = checkedCel[0].collectorItem.id;
            var purchaseStatus = checkedCel[0].collectorItem.purchaseStatus;
            if (purchaseStatus == ${purchaseConfirmed}) {
                layer.confirm('该商品已确认采购', {
                    title: false,
                    btn: ['确定'],
                    yes: function (index) {
                        Ms.clearGridSelections('itemManageList');
                        layer.close(index);
                    },
                    cancel: function () {
                        Ms.clearGridSelections('itemManageList');
                    }
                });
                return false;
            }

            layer.confirm('确认采购该商品吗？', {
                title: false,
                btn: ['确定', '取消'],
                btn2: function () {
                    Ms.clearGridSelections('itemManageList');
                },
                cancel: function () {
                    Ms.clearGridSelections('itemManageList');
                }
            }, function (index) {
                layer.open({
                    type: 2,
                    title: '选择商品分类',
                    area: ['610px', '520px'],
                    btn: ['保存', '取消'],
                    content: ['/collect/item/categy/sel/edit/' + id, 'no'],
                    yes: function (index, layero) {
                        var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                        var result = iframeWin.chooseCategoryResult();

                        var categoryIdx;
                        if (result) {
                            var resourceArr = result.split("|");
                            categoryIdx = resourceArr[0];
                        } else {
                            return false;
                        }

                        initWarehouse();
                        layer.open({
                            type: 1,
                            title: '选择仓库',
                            area: ['350px', '200px'],
                            btn: ['保存', '取消'],
                            content: $('#citem-selectWarehouse'),
                            yes: function (index) {
                                var warehouseTypeIdx = $("#citem-warehouseTypeIdx").val();
                                if (warehouseTypeIdx == "") {
                                    layer.confirm('请选择仓库！', {
                                        title: false,
                                        btn: ['确定']
                                    });
                                    return false;
                                }

                                $.ajax({
                                    type: "post",
                                    url: "/collect/item/purchase",
                                    data: {
                                        idx: id,
                                        categoryIdx: categoryIdx,
                                        warehouseTypeIdx: warehouseTypeIdx
                                    },
                                    dataType: 'json',
                                    success: function (d) {
                                        if (d.status == 200) {
                                            layer.open({
                                                type: 2,
                                                title: '修改商品',
                                                area: ['800px', '580px'],
                                                btn: ['保存', '取消'],
                                                content: ['/collect/item/edit/' + id, 'no'],
                                                yes: function (index, layero) {
                                                    var iframeWin = window[layero.find('iframe')[0]['name']];
                                                    iframeWin.submitForm();
                                                    return false;
                                                },
                                                btn2: function () {
                                                    Ms.reloadDataGrid('itemManageList');
                                                },
                                                cancel: function () {
                                                    Ms.reloadDataGrid('itemManageList');
                                                }
                                            });
                                        } else {
                                            parent.layer.msg(d.msg, {icon: 2});
                                        }
                                    },
                                    error: function (d) {
                                        parent.layer.msg(d.msg, {icon: 2});
                                    }
                                });
                                layer.close(index);
                            },
                            btn2: function () {
                                Ms.clearGridSelections('itemManageList');
                            },
                            cancel: function () {
                                Ms.clearGridSelections('itemManageList');
                            }
                        });
                        layer.close(index);
                    },
                    btn2: function () {
                        Ms.clearGridSelections('itemManageList');
                    },
                    cancel: function () {
                        Ms.clearGridSelections('itemManageList');
                    }
                });
                layer.close(index);
            });
        }

        /*// 历史记录
         function showHistory() {
         if (!isChecked()) {
         layer.confirm('请选择一条数据', {
         title: false,
         btn: ['确定']
         });
         return false;
         }

         var checkedCel = getChecked();
         var id = checkedCel[0].collectorItem.id;
         var title = "商品历史记录";

         var height = $(window).height() - 136;
         if ($('#tabs').tabs('exists', title)) {
         $('#tabs').tabs('close', title);
         }

         var content = '<iframe scrolling="auto" frameborder="0"  src="/collect/item/history/' + id + '" style="width:100%;height:' + height + 'px;"></iframe>';
         $('#tabs').tabs('add', {
         title: title,
         content: content,
         closable: true
         });
         }*/

        // 上传触发
        function onClickUpBtn() {
            $("#citem-fileupload").trigger("click");
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

        // 初始化仓库
        function initWarehouse(idx) {
            var $wr = $('#citem-warehouseTypeIdx');
            $wr.find('option:gt(0)').remove();
            $.ajax({
                type: "POST",
                url: "/collect/item/initWarehouse",
                dataType: 'json'
            }).done(function (data) {
                if (data.status == 200) {
                    $.each(data.data, function (index, item) {
                        var $option = $("<option>").val(item.idx).text(item.warehouseName);
                        if (idx && idx == item.idx) {
                            $option.attr("selected", true);
                        }
                        $wr.append($option);
                    });
                }
            });
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>