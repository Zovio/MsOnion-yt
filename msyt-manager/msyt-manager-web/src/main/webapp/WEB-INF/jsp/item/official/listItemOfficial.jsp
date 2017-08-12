<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="item:official:online">
                <input type="button" class="brandBtn" value="上架" onclick="onlineItem()"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="item:official:offline">
                <input type="button" class="brandBtn" value="下架" onclick="offlineItem()"/>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen-cont">
                <span class="screen-show"></span>
                <span class="screen-hide"></span>
                <span class="search itemOfficialTableList-qry"
                      style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">商品ID：</label>
                            <input class="txt margin-r-20" type="text" id="itemCollectorIdx">
                        </div>
                        <div class="screen-col">
                            <label class="name">品名：</label>
                            <input class="txt margin-r-20" type="text" id="itemOfficialCnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">货号：</label>
                            <input class="txt margin-r-20" type="text" id="itemOfficialItemNo">
                        </div>
                        <div class="screen-col">
                            <label class="name">品牌：</label>
                            <p class="state">
                                <select class="select-pl" id="itemOfficialBrandIdx">
                                    <option value="">请选择</option>
                                    <c:forEach items="${collectorItemBrandList}" var="p">
                                        <option value="${p.idx}">${p.brandName}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">批次：</label>
                            <input class="txt margin-r-20" type="text" id="itemOfficialBatch">
                        </div>
                        <div class="screen-col">
                            <label class="name">权重：</label>
                            <input class="txt margin-r-20" type="text" id="itemOfficialWeight">
                        </div>
                        <div class="screen-col">
                            <label class="name">第一品类：</label>
                            <p class="state">
                                <select class="select-pl" id="itemOfficialFirstCategoryIdx"
                                        next-id="itemOfficialSecondCategoryIdx"
                                        onchange="changeSelect($(this));">
                                    <option value="">请选择</option>
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
                                <select class="select-pl" id="itemOfficialSecondCategoryIdx"
                                        next-id="itemOfficialThreeCategoryIdx"
                                        onchange="changeSelect($(this));">
                                    <option value="">请选择</option>
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
                                <select class="select-pl" id="itemOfficialThreeCategoryIdx">
                                    <option value="">请选择</option>
                                    <c:forEach items="${threeCategoryList}" var="p">
                                        <option value="${p.idx}">${p.name}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">商品状态：</label>
                            <p class="state">
                                <select class="select-pl" id="itemOfficialOnlineStatus">
                                    <option value="">请选择</option>
                                    <c:forEach items="${itemOfficialStatusMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                        <div class="screen-col">
                            <label class="name">销售状态：</label>
                            <p class="state">
                                <select class="select-pl" id="itemOfficialItemStateIdx">
                                    <option value="">请选择</option>
                                    <c:forEach items="${itemSaleStatusMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="yt-table">
                <table id="itemOfficialTable" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#itemOfficialTable').datagrid({
                url: '/item/official/grid',
                idField: 'id',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: true,
                method: 'post',
                columns: [[
                    {title: '', field: '', width: '5%', align: 'center', checkbox: 'true'},
                    {
                        title: '操作',
                        field: 'action',
                        width: '5%',
                        align: 'center',
                        formatter: function (value, rowData, rowIndex) {
                            var array = [];
                            <shiro:hasPermission name="item:official:detail">
                            array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="detailItemOfficial(' + rowData.idx + ');"></a>');
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {title: '商品ID', field: 'itemIdx', width: '8%', align: 'center', sortable: true},
                    {title: '品名', field: 'cnName', width: '15%', align: 'center'},
                    {title: '货号', field: 'itemNo', width: '12%', align: 'center', sortable: true},
                    {title: '类目', field: 'categoryName', width: '10%', align: 'center'},
                    {title: '品牌', field: 'brandName', width: '10%', align: 'center'},
                    {title: '批次', field: 'batch', width: '8%', align: 'center', sortable: true},
                    {title: '权重', field: 'weight', width: '8%', align: 'center', sortable: true},
                    {title: '商品状态', field: 'onlineStatus', width: '10%', align: 'center'},
                    {title: '销售状态', field: 'saleStatus', width: '10%', align: 'center'},
                    {title: '初次上架人', field: 'firstOnlineByMemberName', width: '10%', align: 'center'},
                    {title: '初次上架时间', field: 'firstOnlineTime', width: '13%', align: 'center'},
                    {title: '上架人', field: 'onlineByMemberName', width: '10%', align: 'center'},
                    {title: '上架时间', field: 'onlineTime', width: '13%', align: 'center'},
                    {title: '下架人', field: 'offlineByMemberName', width: '10%', align: 'center'},
                    {title: '下架时间', field: 'offlineTime', width: '13%', align: 'center'}
                ]],
                onLoadSuccess: function (data) {
                    Ms.clearGridSelections('itemOfficialTable');
                }
            });

            $('.itemOfficialTableList-qry').on('click', function () {
                $('#itemOfficialTable').datagrid('load', {
                    itemCollectorIdx: $.trim($('#itemCollectorIdx').val()),
                    itemOfficialCnName: $.trim($('#itemOfficialCnName').val()),
                    itemOfficialItemNo: $.trim($('#itemOfficialItemNo').val()),
                    itemOfficialBrandIdx: $.trim($('#itemOfficialBrandIdx').val()),
                    itemOfficialBatch: $.trim($('#itemOfficialBatch').val()),
                    itemOfficialWeight: $.trim($('#itemOfficialWeight').val()),
                    itemOfficialFirstCategoryIdx: $.trim($('#itemOfficialFirstCategoryIdx').val()),
                    itemOfficialSecondCategoryIdx: $.trim($('#itemOfficialSecondCategoryIdx').val()),
                    itemOfficialThreeCategoryIdx: $.trim($('#itemOfficialThreeCategoryIdx').val()),
                    itemOfficialOnlineStatus: $('#itemOfficialOnlineStatus').val(),
                    itemOfficialItemStateIdx: $('#itemOfficialItemStateIdx').val()
                });
            });

            setSelectData($("#itemOfficialFirstCategoryIdx"), 0);
        });

        function detailItemOfficial(idx) {
            layer.load(2, {shade: 0.01});

            var title = "商品详情页";
            if ($('#tabs').tabs('exists', title)) {
                $('#tabs').tabs('close', title);
            }

            var url = '/item/official/detail/' + idx;
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

        // 上架
        function onlineItem() {
            if (!$('#itemOfficialTable').datagrid('getSelected')) {
                layer.confirm('请选中要上架的数据！', {
                    title: false,
                    btn: ['确定']
                });
            } else {
                layer.confirm('您确定要上架吗？', {
                    title: false,
                    btn: ['确定', '取消'],
                    btn2: function () {
                        Ms.clearGridSelections('itemOfficialTable');
                    },
                    cancel: function () {
                        Ms.clearGridSelections('itemOfficialTable');
                    }
                }, function (index) {
                    var ids = [];
                    var allCheckedRows = $('#itemOfficialTable').datagrid('getChecked');
                    if (allCheckedRows.length > 0) {
                        var checkedRowLength = allCheckedRows.length;
                        for (var i = 0; i < checkedRowLength; i++) {
                            var checkedRow = allCheckedRows[i];
                            ids.push(checkedRow.idx);
                        }
                    }

                    $.ajax({
                        type: "post",
                        url: "/item/official/online",
                        data: {
                            ids: ids
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                layer.msg('操作成功！', {icon: 1});
                            } else {
                                layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                            }
                            $('#itemOfficialTable').datagrid('reload', {});
                        },
                        error: function (d) {
                            alert(d.detailMessage);
                            layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                        }
                    });
                });
            }
        }

        // 下架
        function offlineItem() {
            if (!$('#itemOfficialTable').datagrid('getSelected')) {
                layer.confirm('请选中要下架的数据！', {
                    title: false,
                    btn: ['确定']
                });
            } else {
                layer.confirm('您确定要下架吗？', {
                    title: false,
                    btn: ['确定', '取消'],
                    btn2: function () {
                        Ms.clearGridSelections('itemOfficialTable');
                    },
                    cancel: function () {
                        Ms.clearGridSelections('itemOfficialTable');
                    }
                }, function (index) {
                    var ids = [];
                    var allCheckedRows = $('#itemOfficialTable').datagrid('getChecked');
                    if (allCheckedRows.length > 0) {
                        var checkedRowLength = allCheckedRows.length;
                        for (var i = 0; i < checkedRowLength; i++) {
                            var checkedRow = allCheckedRows[i];
                            ids.push(checkedRow.idx);
                        }
                    }

                    $.ajax({
                        type: "post",
                        url: "/item/official/offline",
                        data: {
                            ids: ids
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                layer.msg('操作成功！', {icon: 1});
                            } else {
                                layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                            }
                            $('#itemOfficialTable').datagrid('reload', {});
                        },
                        error: function (d) {
                            layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                        }
                    });
                });
            }
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>