<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:price:edit">
                <input type="button" class="brandBtn" value="修改价格" onclick="editPrice(this.value)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:price:upload">
                <input type="button" class="brandBtn" value="下载模板" id="price-template"/>
                <input type="button" class="brandBtn" value="上传覆盖" onclick="onClickUpBtn();"/>
                <input type="button" class="brandBtn" value="保存并发布" id="price-saveAndPublish"/>
            </shiro:hasPermission>
            <input id="price-fileupload" type="file" name="file" style="display: none;"/>
        </div>
        <div class="main-content">
            <div class="screen-cont">
                <span class="screen-show"></span>
                <span class="screen-hide"></span>
                <span class="search setPriceTable-qry" style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                <div class="screen multi-row">
                    <div class="screen-row">
                        <div class="screen-col">
                            <label class="name">商品ID：</label>
                            <input class="txt margin-r-20" type="text" id="price-idx">
                        </div>
                        <div class="screen-col">
                            <label class="name">中文品名：</label>
                            <input class="txt margin-r-20" type="text" id="price-cnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">货号：</label>
                            <input class="txt margin-r-20" type="text" id="price-itemNo">
                        </div>
                        <!--<div class="screen-col">
                            <label class="name">条形码：</label>
                            <input class="txt margin-r-20" type="text" id="price-barcode">
                        </div>-->
                        <div class="screen-col">
                            <label class="name">第一品类：</label>
                            <p class="state">
                                <select class="select-pl" id="price-firstCategoryIdx" next-id="price-secondCategoryIdx"
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
                                <select class="select-pl" id="price-secondCategoryIdx" next-id="price-threeCategoryIdx"
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
                                <select class="select-pl ld" id="price-threeCategoryIdx">
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
                <table id="collector-setPriceTable" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <!-- 上传表格弹框 start -->
    <div id="price-upTableCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 114px;display: none;">
        <div class="promptCont">
            <div class="winRow">
                <label>上传文件：</label>
                <div class="brandLogo">
                    选择文件进行上传...
                    <a href="javascript:;" class="upfile">
                        <input class="uploading" type="file">
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- 上传表格弹框 end -->
    <div id="price-issueHint" class="issueHint"
         style="overflow-y: scroll;overflow-x: hidden;height: 187px;display: none;">
        <div class="issue-row" id="price-issueHint-row">
        </div>
    </div>
    <script src="/resources/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
    <script src="/resources/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <script src="/resources/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
    <script type="text/javascript">
        // 设置价格
        $(function () {
            $('#collector-setPriceTable').datagrid({
                url: '/collect/item/price/grid',
                idField: 'id',
                rownumbers: true,
                iconCls: 'icon-save',
                pagination: true,
                dataType: "json",
                method: 'post',
                columns: [[
                    {title: '', field: '', width: '10%', align: 'center', checkbox: true},
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
                        title: '中文品名', field: 'cnName', width: '25%', align: 'center', sortable: true,
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
                        title: '售价', field: 'sellingPrice', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.collectorItemPrice) {
                                var sellingPrice = row.collectorItemPrice.sellingPrice;
                                sellingPrice = sellingPrice / 100;
                                return sellingPrice.toFixed(2);
                            }
                            return "";
                        }
                    },
                    {
                        title: '供货价', field: 'supplyPrice', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.collectorItemPrice) {
                                var supplyPrice = row.collectorItemPrice.supplyPrice;
                                supplyPrice = supplyPrice / 100;
                                return supplyPrice.toFixed(2);
                            }
                            return "";
                        }
                    },
                    {
                        title: '市场价', field: 'marketPrice', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.collectorItemPrice) {
                                var marketPrice = row.collectorItemPrice.marketPrice;
                                marketPrice = marketPrice / 100;
                                return marketPrice.toFixed(2);
                            }
                            return "";
                        }
                    },
                    {
                        title: '发布状态', field: 'publishStatus', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.collectorItemPrice) {
                                var pStatus = row.collectorItemPrice.publishStatus;
                                return ${publishStatusJson}[pStatus];
                            }
                            return "未发布";
                        }
                    }
                ]],
                loadFilter: function (result) {
                    if (result.status == 200) {
                        return result.data;
                    } else {
                        layer.msg(result.msg, {icon: 2});
                    }
                },
                onLoadSuccess: function (data) {
                    Ms.clearGridSelections('collector-setPriceTable');
                }
            });

            $('.setPriceTable-qry').on('click', function () {
                $('#collector-setPriceTable').datagrid('load', {
                    idx: $.trim($('#price-idx').val()),
                    cnName: $.trim($('#price-cnName').val()),
                    itemNo: $.trim($('#price-itemNo').val()),
                    barcode: $.trim($('#price-barcode').val()),
                    brandIdx: $('#price-brandIdx').val(),
                    batch: $('#price-batch').val(),
                    firstCategoryIdx: $('#price-firstCategoryIdx').val(),
                    secondCategoryIdx: $('#price-secondCategoryIdx').val(),
                    threeCategoryIdx: $('#price-threeCategoryIdx').val(),
                    publishStatus: $('#price-publishStatus').val()
                });
            });

            $('#price-template').on('click', function () {
                var templateApi = '${templateApi}';
                if(!templateApi){
                    layer.msg('暂无模板，请联系管理员配置！', {icon: 2});
                    return false;
                }
                window.location.href = templateApi;
            })

            // 上传表格
            $("#price-fileupload").click(function () {
                $(this).fileupload({
                    url: '/collect/item/price/importItemPriceData',
                    type: "post",
                    dataType: 'json',
                    start: function (e) {
                        layer.msg('正在上传数据...', {
                            icon: 16,
                            shade: [0.1, '#fff'],
                            time: 600000
                        });
                    },
                    stop: function (e) {
                    },
                    done: function (e, data) {
                        var dataResult = data.result;
                        // 处理上传成功后处理
                        if (dataResult.status == 200) {
                            layer.msg('上传成功！', {icon: 1});
                            location.href = "/collect/item/downExcel?filePath=" + dataResult.data;
                            $('#collector-setPriceTable').datagrid('reload', {});
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

            setSelectData($("#price-firstCategoryIdx"), 0);

            // 保存和发布
            $("#price-saveAndPublish").click(function () {
                var checkedCel = getChecked();
                if (!checkedCel || checkedCel.length == 0) {
                    layer.confirm('请至少选择一条数据', {
                        title: false,
                        btn: ['确定']
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
                    url: "/collect/item/price/saveAndPublish",
                    type: "post",
                    dataType: "json",
                    data: {itemIdxStr: idxArr.join(",")},
                    beforeSend: function (XHR) {
                        msgIndex = layer.msg('正在发布...', {
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
                                $("#price-issueHint-row").html(resultStr.join(""));
                                layer.close(msgIndex);
                                layer.open({
                                    type: 1,
                                    title: '发布结果信息提示',
                                    area: ['350px', '300px'],
                                    btn: ['确定'],
                                    shadeClose: true, //点击遮罩关闭
                                    skin: 'hint-m',
                                    content: $('#price-issueHint')
                                });
                            } else {
                                layer.msg("发布成功！", {icon: 1});
                            }
                            $('#collector-setPriceTable').datagrid('reload', {});
                        } else {
                            layer.msg("发布失败！", {icon: 2});
                        }
                    }
                });
            });
        });

        // 修改价格
        function editPrice(title) {
            var checkedCel = $('#collector-setPriceTable').datagrid('getChecked');
            if (null == checkedCel || checkedCel.length == 0) {
                layer.confirm('请选择一条数据', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }
            if (checkedCel.length > 1) {
                layer.confirm('请选择一条数据', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }

            var idx = checkedCel[0].collectorItem.idx;
            layer.open({
                type: 2,
                title: title,
                area: ['618px', '400px'],
                btn: ['保存', '保存并发布'],
                skin: 'setPricepop',
                content: ['/collect/item/price/edit/' + idx, 'no'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                },
                btn2: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm(1);
                    <%-- 保存并发布 --%>
                    return false;
                },
                cancel: function () {
                    Ms.clearGridSelections('collector-setPriceTable');
                }
            });
        }

        // 上传表格
        function upload(title) {
            layer.open({
                type: 2,
                title: title,
                area: ['490px', '230px'],
                btn: ['保存', '取消'],
                shadeClose: true,
                content: $('#price-upTableCapacity')
            });
        }

        function onClickUpBtn() {
            $("#price-fileupload").trigger("click");
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

        function getChecked() {
            var checkedCel = $('#collector-setPriceTable').datagrid('getChecked');
            return checkedCel;
        }
    </script>
</section>

<jsp:include page="../../../footer.jsp"/>