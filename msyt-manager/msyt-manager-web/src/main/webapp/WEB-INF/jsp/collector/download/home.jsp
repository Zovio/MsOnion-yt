<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<div class="main">
    <div class="table-container">
        <div class="main-content">
            <div class="screen multi-row">
                <div class="screen-cont">
                    <span class="screen-show"></span>
                    <span class="screen-hide"></span>
                    <span class="search gatherTableList-qry"
                          style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                    <form id="searchForm">
                        <div class="screen multi-row">
                            <div class="screen-row">
                                <div class="screen-col">
                                    <label class="name">商品ID：</label>
                                    <input class="txt margin-r-20" type="text" name="s_id" id="download-idx" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)"/>
                                </div>
                                <div class="screen-col">
                                    <label class="name">品名：</label>
                                    <input class="txt margin-r-20" type="text" name="s_cnName" id="download-cnName" onkeyup="MS.noSpecialCharacter(this)" onblur="MS.noSpecialCharacter(this)" />
                                </div>
                                <div class="screen-col">
                                    <label class="name">货号：</label>
                                    <input class="txt margin-r-20" type="text" name="s_itemNo" id="download-itemNo" onkeyup="MS.noSpecialCharacter(this)" onblur="MS.noSpecialCharacter(this)"/>
                                </div>
                                <div class="screen-col">
                                    <label class="name">品牌：</label>
                                    <p class="state">
                                        <select class="select-pl" id="download-brandIdx" name="s_brandId">
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
                                    <input class="txt margin-r-20" type="text" id="download-batch"
                                           name="s_batch" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)"/>
                                </div>
                                <div class="screen-col">
                                    <label class="name">商品状态：</label>
                                    <p class="state">
                                        <select class="select-pl" id="download-onLineStatus" name="s_status">
                                            <option value="">请选择</option>
                                            <c:forEach items="${itemOfficialStatusMap}" var="p">
                                                <option value="${p.key}">${p.value}</option>
                                            </c:forEach>
                                        </select>
                                        <i class="downIcon"></i>
                                    </p>
                                </div>
                                <div class="screen-col">
                                    <label class="name">第一品类：</label>
                                    <p class="state">
                                        <select class="select-pl" id="download-firstCategoryIdx"
                                                next-id="download-secondCategoryIdx" onchange="changeSelect($(this));"
                                                name="s_category1">
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
                                        <select class="select-pl" id="download-secondCategoryIdx"
                                                next-id="download-threeCategoryIdx" onchange="changeSelect($(this));"
                                                name="s_category2">
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
                                        <select class="select-pl" id="download-threeCategoryIdx" name="s_category3">
                                            <option value="">请选择</option>
                                            <c:forEach items="${threeCategoryList}" var="p">
                                                <option value="${p.idx}">${p.name}</option>
                                            </c:forEach>
                                        </select>
                                        <i class="downIcon"></i>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="markedWords" style="display:none">搜索结果共 <span class="totalCount"></span> 个商品！<span style="font-size: 14px;padding-left: 5px;">（温馨提示：因数据量较大，导出属性值时请先筛选具体品类）</span></div>

            <form id="reasource-frm" method="post" action="/collect/download/exportDate">
                <input type="hidden" value="" name="keyArr"/>
                <input type="hidden" value="" name="nameArr"/>
                <input type="hidden" value="" name="itemAttribute" id="down-itemAttribute"/>
                <input type="hidden" value="" name="categorIdx" id="down-categorIdx"/>

                <input type="hidden" name="s_id" id="down-sid"/>
                <input type="hidden" name="s_cnName" id="down-cnName"/>
                <input type="hidden" name="s_itemNo" id="down-itemNo"/>

                <input type="hidden" name="s_brandId" id="down-brandId"/>
                <input type="hidden" name="s_batch" id="down-batch"/>
                <input type="hidden" name="s_status" id="down-status"/>
                <input type="hidden" name="s_category1" id="down-category1"/>
                <input type="hidden" name="s_category2" id="down-category2"/>
                <input type="hidden" name="s_category3" id="down-category3"/>
            </form>

            <div class="yt-table" style="height: 100%;">
                <shiro:hasPermission name="collect:download:expt">
                    <div class="derive">
                        <input type="button" id="expt" value="导出" class="deriveBtn margin-r-20"/>
                        <input type="button" id="checkCancel" value="取消" class="deriveBtn"/>
                    </div>
                </shiro:hasPermission>
                <div class="downloadContent">
                    <c:forEach items="${JSONArray}" var="s" varStatus="status">
                        <div class="downloadModule">
                            <div class="classHeading">
                                <p class="zy-check">
                                    <span class="checkbox"></span>
                                    <span class="className">${s.name}</span>
                                </p>
                                <i class="icon <c:if test="${status.index == 0}" >active</c:if>"></i>
                            </div>
                            <ul <c:if test="${status.index == 0}">style="display: block;"</c:if>>
                                <c:forEach items="${s.detail}" var="d">
                                    <li data-pojo="${s.pojo}">
                                        <span class="checkbox" data-val="${d.key}" data-pojo="${d.pojo}"></span>
                                        <span class="className">${d.name}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            <%-- 初始化分类 --%>
            setSelectData($("#download-firstCategoryIdx"), 0);

            // 页面标题点击相关js
            $('.classHeading .zy-check').click(function () {
                if ($(this).children(".checkbox").hasClass("active")) {
                    $(this).children(".checkbox").removeClass("active");
                    $(this).parent().next().children().children(".checkbox").removeClass("active");
                } else {
                    $(this).children(".checkbox").addClass("active");
                    $(this).parent().next().children().children(".checkbox").addClass("active");
                }
            });

            $('.downloadModule li').click(function () {
                var checkLi = $(this).siblings().children(".checkbox");
                var checkHeading = $(this).parent().prev().children().children(".checkbox");
                var checkNum = checkLi.length;
                var count = 0;
                if ($(this).children(".checkbox").hasClass("active")) {
                    $(this).children(".checkbox").removeClass("active");
                    checkHeading.removeClass("active");
                } else {
                    $(this).children(".checkbox").addClass("active");
                    for (var i = 0; i < checkNum; i++) {
                        if (checkLi.eq(i).hasClass("active")) {
                            count++;
                        }
                    }
                    if (checkNum == count) {
                        checkHeading.addClass("active");
                    }
                }
            });

            $('.classHeading .icon').click(function () {
                if ($(this).hasClass("active")) {
                    $(this).removeClass("active");
                    $(this).parent().next().slideUp();
                } else {
                    $(this).addClass("active");
                    $(this).parent().next().slideDown();
                }
            });

            $('#checkCancel').click(function () {
                $('.checkbox').removeClass("active");
            });
            // ======

            // 导出事件
            var keyArr = [], nameArr = [], itemAttribute = 0, categoryIdx = null, params = {};
            $('#expt').on('click', function () {
                keyArr = [], nameArr = [], params = {}, itemAttribute = 0;
                $('li span.checkbox').each(function () {
                    if ($(this).hasClass('active')) {
                        if ($(this).data('val') == 'itemAttribute') {
                            itemAttribute = 1;
                        } else {
                            var pojo = $(this).data('pojo') || $(this).parent('li').data('pojo');
                            keyArr.push($(this).data('val') + "|" + pojo);
                            nameArr.push($(this).siblings('span').text());
                        }

                    }
                });

                if (itemAttribute === 1 && !$("#download-threeCategoryIdx").val()) {
                    layer.msg("属性值导出请先筛选商品分类到第三品类", {icon: 6});
                    return false;
                }

                $("input[name='keyArr']").val(MS.base64_encode(keyArr.join(',')));
                $("input[name='nameArr']").val(MS.base64_encode(nameArr.join(',')));
                $("#down-itemAttribute").val(itemAttribute);
                $("#down-categorIdx").val(categoryIdx);

                $("#down-sid").val($("#download-idx").val());
                $("#down-cnName").val($("#download-cnName").val());
                $("#down-itemNo").val($("#download-itemNo").val());
                $("#down-brandId").val($("#download-brandIdx").val());
                $("#down-batch").val($("#download-batch").val());
                $("#down-status").val($("#download-onLineStatus").val());
                $("#down-category1").val($("#download-firstCategoryIdx").val());
                $("#down-category2").val($("#download-secondCategoryIdx").val());
                $("#down-category3").val($("#download-threeCategoryIdx").val());

                $('#reasource-frm').submit();
            });
            // ======

            $(".gatherTableList-qry").click(function () {
                layer.load(2, {shade: 0.01, offset: '200px'});
                $.ajax({
                    url: "/collect/download/getItemCount",
                    data: {
                        s_id: $.trim($('#download-idx').val()),
                        s_cnName: encodeURIComponent($('#download-cnName').val()),
                        s_itemNo: $.trim($('#download-itemNo').val()),
                        s_brandId: getSelectValidValue($('#download-brandIdx').val()),
                        s_batch: $('#download-batch').val(),
                        s_status: getSelectValidValue($('#download-onLineStatus').val()),
                        s_category1: getSelectValidValue($('#download-firstCategoryIdx').val()),
                        s_category2: getSelectValidValue($('#download-secondCategoryIdx').val()),
                        s_category3: getSelectValidValue($('#download-threeCategoryIdx').val())
                    },
                    type: "post",
                    async: false,
                    dataType: "json",
                    success: function (result) {
                        if (result.status == 200) {
                            $(".markedWords .totalCount").text(result.data['totalCount']);
                            $(".markedWords").css("display", "block");
                        }
                        layer.closeAll("loading");
                    },
                    error : function (d) {
                        layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2, time: 2000, offset: '200px'});
                    }
                });
            });

            // 初始化调用
            $(".gatherTableList-qry").click();
        });

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

        function getSelectValidValue(selectObj) {
            return selectObj === "-1" ? null : selectObj;
        }
    </script>
</div>

<jsp:include page="../../footer.jsp"/>
