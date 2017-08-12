<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>洋桃供应链后台管理系统-报关信息</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="logistics:customs:upload">
            <div class="yt-row">
                <input type="button" class="brandBtn" value="下载模板" id="custom-template"/>
                <input type="button" class="brandBtn active" value="上传覆盖" onclick="onClickUpBtn();"/>
                <input id="custom-fileupload" type="file" name="file" style="display: none;"/>
                <input type="button" class="brandBtn active" value="保存并发布" id="custom-saveAndPublish"/>
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
                            <input class="txt margin-r-20" type="text" id="custom-idx">
                        </div>
                        <div class="screen-col">
                            <label class="name">中文品名：</label>
                            <input class="txt margin-r-20" type="text" id="custom-cnName">
                        </div>
                        <div class="screen-col">
                            <label class="name">货号：</label>
                            <input class="txt margin-r-20" type="text" id="custom-itemNo">
                        </div>
                        <!--<div class="screen-col">
                            <label class="name">是否含有消费税：</label>
                            <p class="state">
                                <select class="select-pl" id="custom-isContainExcise">
                                    <option value="-1">全部</option>
                                    <c:forEach items="${isContainExciseMap}" var="p">
                                        <option value="${p.key}">${p.value}</option>
                                    </c:forEach>
                                </select>
                                <i class="downIcon"></i>
                            </p>
                        </div>-->
                        <div class="screen-col">
                            <label class="name">第一品类：</label>
                            <p class="state">
                                <select class="select-pl" id="custom-firstCategoryIdx"
                                        next-id="custom-secondCategoryIdx" onchange="changeSelect($(this));">
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
                                <select class="select-pl" id="custom-secondCategoryIdx"
                                        next-id="custom-threeCategoryIdx" onchange="changeSelect($(this));">
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
                                <select class="select-pl ld" id="custom-threeCategoryIdx">
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
                                <select class="select-pl" id="custom-publishStatus">
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
                <table id="custom-customsTable" style="width:98%;height: 464px;"></table>
            </div>
        </div>
    </div>

    <!--上传表格弹框 strat-->
    <div id="custom-upTableCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 114px;display: none;">
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
    <!--上传表格弹框 end-->

    <div id="custom-issueHint" class="issueHint"
         style="overflow-y: scroll;overflow-x: hidden;height: 187px;display: none;">
        <div class="issue-row" id="custom-issueHint-row">
        </div>
    </div>


    <jsp:include page="../../include/fileUpload.jsp"/>
    <script type="text/javascript">

        /* 报关资料 */
        $(function () {

            $('#custom-customsTable').datagrid({
                url: '/logistics/customs/grid',
                idField: 'id',
                rownumbers: true,
                pagination: true,
                pageSize: 10,
                pageList: [10, 20, 30, 40, 50],
                method: 'post',
                singleSelect: false,
                columns: [[
                    {title: '', field: '', width: '10%', align: 'center', checkbox: true},
                    {
                        field: 'control',
                        align: 'center',
                        width: '10%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var array = [];
                            <shiro:hasPermission name="logistics:customs:edit">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick=editCustomsDeclare("' + rowData.collectorItem.idx + '") ></a>');
                            </shiro:hasPermission>
                            array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick=showCustomsDeclare("' + rowData.collectorItem.idx + '") ></a>');
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
                        title: '中文品名', field: 'cnName', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            return row.collectorItem.cnName;
                        }
                    },
                    {
                        title: '类目', field: 'categoryName', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.collectorItemCategory) {
                                return row.collectorItemCategory.name;
                            }
                            return "";
                        }
                    },
                    {
                        title: '报关品名', field: 'customName', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.customName;
                            }
                            return "";
                        }
                    },
                    {
                        title: '第一数量',
                        field: 'firstQuantity',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.firstQuantity;
                            }
                            return "";
                        }
                    },
                    {
                        title: '第二数量',
                        field: 'secondQuantity',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.secondQuantity;
                            }
                            return "";
                        }
                    }, {
                        title: '第一单位',
                        field: 'firstUnit',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.firstUnitIdxDynamicS;
                            }
                            return "";
                        }
                    }, {
                        title: '第二单位',
                        field: 'secondUnit',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.secondUnitIdxDynamicS;
                            }
                            return "";
                        }
                    },
                    {
                        title: '行邮税率', field: 'postalArticlesTaxRate', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.postalArticlesTaxRateDots5;
                            }
                            return "";
                        }
                    }, {
                        title: '跨境税率', field: 'crossBorderTaxRate', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.crossBorderTaxRateDots5;
                            }
                            return "";
                        }
                    }, {
                        title: 'BC价', field: 'bcPrice', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.bcPriceDots2;
                            }
                            return "";
                        }
                    }, {
                        title: 'HS编码', field: 'hsCode', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.hsCode;
                            }
                            return "";
                        }
                    }, {
                        title: '毛重', field: 'grossWeight', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.grossWeight;
                            }
                            return "";
                        }
                    }, {
                        title: '行邮税号', field: 'postalArticlesTaxNumber', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                return row.logisticsCustomsDeclare.postalArticlesTaxNumber;
                            }
                            return "";
                        }
                    }, {
                        title: '是否含有消费税', field: 'isContainExcise', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                var isContainExcise = row.logisticsCustomsDeclare.isContainExcise;
                                return ${isContainExciseJson}[isContainExcise];
                            }
                            return "";
                        }
                    }, {
                        title: '发布状态', field: 'publishStatus', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.logisticsCustomsDeclare) {
                                var pStatus = row.logisticsCustomsDeclare.publishStatus;
                                return ${publishStatusJson}[pStatus];
                            }
                            return "未发布";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    $('#custom-customsTable').datagrid('clearSelections');
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
                $('#custom-customsTable').datagrid('load', {
                    idx: $.trim($('#custom-idx').val()),
                    cnName: $.trim($('#custom-cnName').val()),
                    itemNo: $.trim($('#custom-itemNo').val()),
                    isContainExcise: $.trim($('#custom-isContainExcise').val()),
                    firstCategoryIdx: $('#custom-firstCategoryIdx').val(),
                    secondCategoryIdx: $('#custom-secondCategoryIdx').val(),
                    threeCategoryIdx: $('#custom-threeCategoryIdx').val(),
                    purchaseStatus: $('#custom-purchaseStatus').val()
                });
            });
            
            $('#custom-template').on('click', function () {
                var templateApi = '${templateApi}';
                if(!templateApi){
                    layer.msg('暂无模板，请联系管理员配置！', {icon: 2});
                    return false;
                }
                window.location.href = templateApi;
            })

            // 上传表格
            $("#custom-fileupload").click(function () {
                $(this).fileupload({
                    url: '/logistics/customs/importCustomData',
                    type: "post",
                    dataType: 'json',
                    start: function (e) {
                        msgIndex = layer.msg('正在上传数据...', {
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
                            $('#custom-customsTable').datagrid('reload', {});
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

            setSelectData($("#custom-firstCategoryIdx"), 0);


            // 保存和发布
            $("#custom-saveAndPublish").click(function () {
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
                    url: "/logistics/customs/saveAndPublish",
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
                                $("#custom-issueHint-row").html(resultStr.join(""));
                                layer.close(msgIndex);
                                layer.open({
                                    type: 1,
                                    title: '发布结果信息提示',
                                    area: ['350px', '300px'],
                                    btn: ['确定'],
                                    shadeClose: true, //点击遮罩关闭
                                    skin: 'hint-m',
                                    content: $('#custom-issueHint')
                                });
                            } else {
                                layer.msg("发布成功！", {icon: 1});
                            }
                            $('#custom-customsTable').datagrid('reload', {});
                        } else {
                            layer.msg("发布失败！", {icon: 2});
                        }
                    }
                });


            });
        });


        /* 点击功能按钮的选中状态
         $('.yt-row .brandBtn').click(function () {
         $(this).addClass("active").siblings().removeClass("active");

         });*/


        /* 上传表格 */
        function Upload(title) {
            layer.open({
                type: 1,
                title: title,
                area: ['490px', '230px'],
                btn: ['保存', '取消'],
                shadeClose: true, //点击遮罩关闭
                content: $('#custom-upTableCapacity')
            });
        }

        function editCustomsDeclare(idx) {
            layer.open({
                type: 2,
                title: '修改报关资料',
                area: ['660px', '550px'],
                btn: ['保存', '保存并发布'],
                skin: 'setPricepop',
                content: ['/logistics/customs/edit/' + idx, 'no'],
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
                    Ms.clearGridSelections('custom-customsTable');
                }
            });
        }

        function showCustomsDeclare(idx) {
            layer.open({
                type: 2,
                title: '查看报关资料',
                area: ['660px', '550px'],
                btn: ['关闭'],
                content: ['/logistics/customs/edit/' + idx + "?type=view", 'no'],
                yes: function (index) {
                    Ms.clearGridSelections('custom-customsTable');
                    layer.close(index);
                },
                cancel: function () {
                    Ms.clearGridSelections('custom-customsTable');
                }
            });
        }

        function onClickUpBtn() {
            $("#custom-fileupload").trigger("click");
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
            var checkedCel = $('#custom-customsTable').datagrid('getChecked');
            return checkedCel;
        }
    </script>
</section>
</body>
</html>
