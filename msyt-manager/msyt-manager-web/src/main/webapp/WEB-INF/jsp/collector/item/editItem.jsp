<%@ page import="cc.msonion.carambola.commons.common.utils.MsOnionSaleStatusUtils" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>
<% Map saleStatusMap = MsOnionSaleStatusUtils.getMap(); %>

<div id="commodityCapacity" class="addCommodity" style="overflow-y: scroll;overflow-x: hidden;height: 467px;">
    <input type="hidden" id="itemIdx" value="${item.idx}">
    <input type="hidden" id="biddingIdx" value="${itemBidding.idx}">
    <div class="bigWinCont">
        <div class="forbiddenTips" style="display:none; font-size:11px;text-align:center">
            <span style="color:red">该商品上架过不允许修改</span>
        </div>
        <c:if test="${item.purchaseStatus == purchaseConfirmed}">
            <div class="bigRow">
                <label>货号：</label>
                <span class="artNo">${item.itemNo}</span>
            </div>
        </c:if>
        <div class="bigRow">
            <div class="required">
                <label><i>*</i>条形码：</label>
                <input type="text" id="barcode" value="${item.barcode}">
                <p class="requiredHint">*条形码必填</p>
            </div>
            <div class="required">
                <label><i>*</i>中文品名：</label>
                <input type="text" id="cnName" value="${item.cnName}">
                <p class="requiredHint">*中文品名必填</p>
            </div>
        </div>
        <div class="bigRow">
            <label>外文品名：</label>
            <input type="text" id="enName" value="${item.enName}">
            <label>商品规格：</label>
            <input type="text" id="specification" value="${item.specification}">
        </div>
        <div class="bigRow">
            <div class="required">
                <label><i>*</i>品牌：</label>
                <p class="relative">
                    <select id="brandIdx">
                        <option value="">请选择</option>
                        <c:forEach items="${itemBrandList}" var="p">
                            <option value="${p.id}" ${p.idx eq item.brandIdx?"selected":""}>
                                    ${p.brandName}
                            </option>
                        </c:forEach>
                    </select>
                    <i class="downIcon"></i>
                </p>
                <p class="requiredHint">*品牌必填</p>
            </div>
            <div class="required">
                <label><i>*</i>产地：</label>
                <p class="relative">
                    <select id="originIdx">
                        <option value="">请选择</option>
                        <c:forEach items="${itemOriginList}" var="p">
                            <option value="${p.id}" ${p.idx eq item.originIdx?"selected":""}>${p.cnName}</option>
                        </c:forEach>
                    </select>
                    <i class="downIcon"></i>
                </p>
                <p class="requiredHint">*品牌必填</p>
            </div>
        </div>
        <div class="bigRow">
            <label>亚马逊售价：</label>
            <input type="text" id="amazonPrice" value="${itemBidding.amazonPriceDots2}">
            <label>天猫售价：</label>
            <input type="text" id="tmallPrice" value="${itemBidding.tmallPriceDots2}">
        </div>
        <div class="bigRow">
            <label>京东售价：</label>
            <input type="text" id="jdPrice" value="${itemBidding.jdPriceDots2}">
            <label>小红书售价：</label>
            <input type="text" id="redPrice" value="${itemBidding.redPriceDots2}">
        </div>
        <div class="bigRow">
            <label>国外官网价：</label>
            <input type="text" id="abroadPrice" value="${itemBidding.abroadPriceDots2}">
            <label>中国官网价：</label>
            <input type="text" id="domesticPrice" value="${itemBidding.domesticPriceDots2}">
        </div>
        <div class="bigRow">
            <label>原产国售价：</label>
            <input type="text" id="originCountryPrice"
                   value="${itemBidding.originCountryPriceDots2}">
            <label>考拉售价：</label>
            <input type="text" id="koalaPrice" value="${itemBidding.koalaPriceDots2}">
        </div>
        <div class="bigRow">
            <label>聚美售价：</label>
            <input type="text" id="jumeiPrice" value="${itemBidding.jumeiPriceDots2}">
            <label>销售状态：</label>
            <p class="relative">
                <select id="itemStateIdx">
                    <option value="">请选择</option>
                    <c:forEach items="<%=saleStatusMap%>" var="p">
                        <option value="${p.key}" ${p.key eq item.itemStateIdx?"selected":""}>${p.value}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="bigRow">
            <div class="required">
                <label><i>*</i>批次：</label>
                <input type="text" id="batch" placeholder="请输入不超过5位数字" value="${item.batch}" maxlength="5">
                <p class="requiredHint">*批次必填</p>
            </div>
            <label>权重：</label>
            <input type="text" id="weight" value="${item.weight}">
        </div>
        <div class="bigRow">
            <label>采集备注：</label>
            <textarea style="width: 590px;height: 50px;" id="collectionRemark">${item.collectionRemark}</textarea>
        </div>

        <input id="_targerId" type="hidden"/>
        <form id="uploadForm">
            <input name="filePath" id="_filePath" type="hidden"/>
            <input name="remark" id="_remark" type="hidden"/>
            <input id="uploading" style="display: none" type="file" name="files"
                   onchange="uploadS('',1,'uploadForm',upCallback);"/>
        </form>

        <div class="uploadImg-col">
            <span class="img-title"><em class="left">提供图片：</em><em class="ts">（支持jpg/png/gif，宽 x 高)</em></span>
            <div class="upload-cont wtdl fr">
                <div class="upload-col" style="margin-left: 0">
                    <div class="uploadBtn" onclick="uploadClick('${collectImgurl}','','upload-img3');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <p class="size-ts">200 x 400</p>
                    <div class="upload-img" id="upload-img3">
                        <p class="img-cont">
                            <c:if test="${not empty item.imageBigDynamicS}">
                                <img src="${item.imageBigDynamicS}" messageid='${item.imageBig}' alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i> </span>
                        <span class="img-name"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        <%-- 是否上架过 --%>
        var ol = '${hasOnline}';
        if (ol && '1' === ol) {
            $(":input").attr("disabled", "disabled");
            $(".upload-img .close").css("display", "none");
            $(".forbiddenTips").css("display", "block");
        }
    });

    $('#commodityCapacity #barcode').on('keyup', function () {
        $(this).next().hide();
        $('#barcode').css("border", "1px solid #dbdcde");
    });

    $('#commodityCapacity #cnName').on('keyup', function () {
        $(this).next().hide();
        $('#cnName').css("border", "1px solid #dbdcde");
    });

    $('#commodityCapacity #brandIdx').on('change', function () {
        $(this).parent().next().hide();
        $('#brandIdx').css("border", "1px solid #dbdcde");
    });

    $('#commodityCapacity #originIdx').on('change', function () {
        $(this).parent().next().hide();
        $('#originIdx').css("border", "1px solid #dbdcde");
    });

    $('#commodityCapacity #batch').on('keyup', function () {
        $(this).next().hide();
        $('#batch').css("border", "1px solid #dbdcde");
    });

    function submitForm() {
        var barcode = $('#barcode').val();
        var cnName = $('#cnName').val();
        var enName = $('#enName').val();
        var specification = $('#specification').val();
        var brandIdx = $('#brandIdx').val();
        var originIdx = $('#originIdx').val();
        // 竞价 begin
        var amazonPrice = $('#amazonPrice').val();
        var tmallPrice = $('#tmallPrice').val();
        var jdPrice = $('#jdPrice').val();
        var redPrice = $('#redPrice').val();
        var abroadPrice = $('#abroadPrice').val();
        var domesticPrice = $('#domesticPrice').val();
        var originCountryPrice = $('#originCountryPrice').val();
        var koalaPrice = $('#koalaPrice').val();
        var jumeiPrice = $('#jumeiPrice').val();
        // 竞价 end
        var collectionRemark = $('#collectionRemark').val();
        var batch = $('#batch').val();
        var weight = $('#weight').val();
        var itemStateIdx = $('#itemStateIdx').val();

        var pass = true;
        if (barcode == "") {
            $('#barcode').css("border", "1px solid #ff2c41");
            $('#barcode').next().show();
            Ms.closeLoading();
            pass = false;
        }

        if (cnName == "") {
            $('#cnName').css("border", "1px solid #ff2c41");
            $('#cnName').next().show();
            Ms.closeLoading();
            pass = false;
        }

        if (brandIdx == "") {
            $('#brandIdx').css("border", "1px solid #ff2c41");
            $('#brandIdx').parent().next().show();
            Ms.closeLoading();
            pass = false;
        }

        if (originIdx == "") {
            $('#originIdx').css("border", "1px solid #ff2c41");
            $('#originIdx').parent().next().show();
            Ms.closeLoading();
            pass = false;
        }

        if (batch == "") {
            $('#batch').css("border", "1px solid #ff2c41");
            $('#batch').next().show();
            Ms.closeLoading();
            pass = false;
        }

        if (!Ms.isNumber(batch)) {
            layer.msg("批次必须是数字", {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        if (weight != "" & !Ms.isNumber(weight)) {
            layer.msg("权重必须是数字", {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            $.ajax({
                type: "post",
                url: "/collect/item/save",
                data: {
                    idx: $("#itemIdx").val(),
                    biddingIdx: $("#biddingIdx").val(),
                    barcode: $("#barcode").val(),
                    cnName: cnName,
                    enName: $("#enName").val(),
                    specification: $("#specification").val(),
                    brandIdx: $("#brandIdx").val(),
                    originIdx: $("#originIdx").val(),
                    amazonPriceStr: $("#amazonPrice").val(),
                    tmallPriceStr: $("#tmallPrice").val(),
                    jdPriceStr: $("#jdPrice").val(),
                    redPriceStr: $("#redPrice").val(),
                    abroadPriceStr: $("#abroadPrice").val(),
                    domesticPriceStr: $("#domesticPrice").val(),
                    originCountryPriceStr: $("#originCountryPrice").val(),
                    koalaPriceStr: $("#koalaPrice").val(),
                    jumeiPriceStr: $("#jumeiPrice").val(),
                    imageBig: $("#upload-img3").find('.img-cont').find('img:first').attr("messageid") || null,
                    collectionRemark: $("#collectionRemark").val(),
                    batch: $("#batch").val(),
                    weight: $("#weight").val(),
                    itemStateIdx: $("#itemStateIdx").val()
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 6});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                    Ms.reloadParentDataGrid('itemManageList');
                },
                error: function (d) {
                    parent.layer.msg(MS.ERROR_MSG, {icon: 2});
                }
            });
        }
    }
</script>

<jsp:include page="../../footer.jsp"/>