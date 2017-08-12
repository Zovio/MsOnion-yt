<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="main-content">
            <!-- 编辑采集信息 begin -->
            <div class="bigWinCont">
                <div class="gatherEdit">
                    <input type="hidden" id="itemIdx" value="${item.idx}">
                    <input type="hidden" id="biddingIdx" value="${itemBidding.idx}">
                    <input type="hidden" id="collectionIdx" value="${itemCollection.idx}">
                    <input type="hidden" id="contentIdx" value="${itemContent.idx}">
                    <%-- 基本信息 --%>
                    <div class="gather-row">
                        <p class="identification"><span class="identification-sp">基本信息</span></p>
                        <div class="col-chunk">
                            <label class="left-key">货号：</label>
                            <input type="text" class="readonly" value="${item.itemNo}" readonly="true"/>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key">中文品名：</label>
                            <input type="text" class="right-value" id="itemCnName" value="${item.cnName}"/>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key">外文品名：</label>
                            <input type="text" class="right-value" id="itemEnName" value="${item.enName}"/>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>品牌：</label>
                            <select id="itemBrandIdx" class="drop-down">
                                <option value="">请选择</option>
                                <c:forEach items="${itemBrandList}" var="p">
                                    <option value="${p.idx}" ${item.brandIdx == p.idx?"selected":""}>${p.brandName}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                            <p class="gather-bt">*品牌必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>产地：</label>
                            <select id="itemOriginIdx" class="drop-down">
                                <option value="">请选择</option>
                                <c:forEach items="${itemOriginList}" var="p">
                                    <option value="${p.idx}" ${item.originIdx == p.idx?"selected":""}>${p.cnName}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                            <p class="gather-bt">*产地必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key">商品规格：</label>
                            <input type="text" class="right-value" id="itemSpecification"
                                   value="${item.specification}"/>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>批次：</label>
                            <input type="text" class="right-value" id="itemBatch" value="${item.batch}" maxlength="5"/>
                            <p class="gather-bt">*批次必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key">权重：</label>
                            <input type="text" class="right-value" id="itemWeight" value="${item.weight}"/>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>采集状态：</label>
                            <select id="collectionStatus" class="drop-down">
                                <option value="">请选择</option>
                                <c:forEach items="${collectionStatusMap}" var="p">
                                    <option value="${p.key}" ${item.collectionStatus == p.key?"selected":""}>${p.value}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                            <p class="gather-bt">*采集状态必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key">销售状态：</label>
                            <select id="saleStatus" class="drop-down">
                                <option value="">请选择</option>
                                <c:forEach items="${saleStatusMap}" var="p">
                                    <option value="${p.key}" ${item.itemStateIdx == p.key?"selected":""}>${p.value}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                        </div>
                        <div class="col-chunk-h">
                            <label class="left-key">采集备注：</label>
                            <textarea class="right-value-area" id="collectionRemark">${item.collectionRemark}</textarea>
                        </div>

                        <c:if test="${! empty itemBarcodeList}">
                            <div class="col-chunk-h" style="margin-top:10px;clear:both">
                                <label class="left-key" style="margin-top: 15px;">条形码：</label>
                                <c:forEach items="${itemBarcodeList}" var="itemBarcode">
                                    <input class="yt-input-barcode" type="text" readonly="true"
                                           value="${itemBarcode.barcode}"/>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                    <%-- 商品竞价信息 --%>
                    <div class="gather-row">
                        <p class="identification"><span class="identification-sp">商品竞价</span></p>
                        <div class="col-chunk-price">
                            <label class="left-key">亚马逊售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="amazonPrice"
                                   value="${itemBidding.amazonPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">天猫售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="tmallPrice"
                                   value="${itemBidding.tmallPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">京东售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="jdPrice" value="${itemBidding.jdPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">小红书售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="redPrice" value="${itemBidding.redPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">国外官网售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="abroadPrice"
                                   value="${itemBidding.abroadPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">中国官网售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="domesticPrice"
                                   value="${itemBidding.domesticPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">原产国售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="originCountryPrice"
                                   value="${itemBidding.originCountryPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">考拉售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="koalaPrice"
                                   value="${itemBidding.koalaPriceDots2}"/>
                        </div>
                        <div class="col-chunk-price">
                            <label class="left-key">聚美售价：</label>
                            <input type="text" class="right-value-price" onkeyup="MS.priceNumber(this)"
                                   onblur="MS.priceNumber(this)" id="jumeiPrice"
                                   value="${itemBidding.jumeiPriceDots2}"/>
                        </div>
                    </div>
                    <%-- 采编编辑 --%>
                    <div class="gather-row">
                        <p class="identification"><span class="identification-sp">采集编辑</span></p>
                        <div class="col-chunk-r">
                            <label class="left-key">选择采编：</label>
                            <select id="editorIdx" class="drop-down">
                                <option value="">请选择采编</option>
                                <c:forEach items="${itemEditorList}" var="p">
                                    <option value="${p.idx}" ${itemCollection.editorIdx == p.idx?"selected":""}>${p.name}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                        </div>
                        <div class="col-chunk-r">
                            <label class="left-key">卖点：</label>
                            <textarea class="right-value-area"
                                      id="sellingPoint" maxlength="20">${itemCollection.sellingPoint}</textarea>
                            <label class="left-key">广告语：</label>
                            <textarea class="right-value-area" id="slogan"
                                      maxlength="50">${itemCollection.slogan}</textarea>
                        </div>
                        <div class="col-chunk-L">
                            <label class="left-key">搜索关键词：</label>
                            <textarea class="right-value-area"
                                      id="searchKeywords" maxlength="100">${itemCollection.searchKeywords}</textarea>
                        </div>
                        <div class="col-chunk-L">
                            <label class="left-key">导购语：</label>
                            <textarea class="right-value-area"
                                      id="shoppingGuide" maxlength="300">${itemCollection.shoppingGuide}</textarea>
                        </div>
                    </div>
                    <%-- 属性值 --%>
                    <div class="gather-row">
                        <p class="identification"><span class="identification-sp">属性值</span></p>

                        <c:forEach items="${itemAttributeList}" var="p">
                            <c:if test="${!p.isKey}">
                                    <c:choose>
                                        <c:when test="${null != p.attributeOptions && fn:length(p.attributeOptions) > 0}">
                                            <div class="col-chunk" style="padding-left:0px">
                                                <label class="left-key">${p.attributeName}：</label>
                                                <select class="drop-down" id="${p.attributeIdx}" name="attributeValue">
                                                    <option value="-1">请选择</option>
                                                    <c:forEach items="${p.attributeOptions}" var="attributeOption">
                                                        <option value="${attributeOption.idx}" <c:if test="${attributeOption.idx eq p.attributeValue}">selected</c:if> >${attributeOption.name}</option>
                                                    </c:forEach>
                                                </select>
                                                <i class="downIcon"></i>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="col-chunk-cl" style="padding-left:0px">
                                                <label class="left-key-cl">${p.attributeName}：</label>
                                                    <input type="text" class="right-value-cl" id="${p.attributeIdx}"
                                                           name="attributeValue"
                                                           value="${p.attributeValue}"/>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                            </c:if>
                        </c:forEach>

                        <%-- 关键属性--%>
                        <c:forEach items="${itemAttributeList}" var="p" varStatus="pStatus">
                            <c:if test="${p.isKey}">
                                <c:choose>
                                    <c:when test="${null != p.attributeOptions && fn:length(p.attributeOptions) > 0}">
                                    <div class="col-chunk" style="margin-top:10px">
                                        <label class="left-key">${p.attributeName}：</label>
                                            <select class="drop-down" id="${p.attributeIdx}" name="attributeValue">
                                                <option value="-1">请选择</option>
                                                <c:forEach items="${p.attributeOptions}" var="attributeOption">
                                                    <option value="${attributeOption.idx}" <c:if test="${attributeOption.idx eq p.attributeValue}">selected</c:if> >${attributeOption.name}</option>
                                                </c:forEach>
                                            </select>
                                            <i class="downIcon"></i>
                                    </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-specialProperty">
                                            <label class="left-key">${p.attributeName}：</label>
                                            <textarea
                                                    style="padding:6px;width:930px;height:100px;box-sizing:border-box;border:1px solid #dbdcde;border-radius:3px;-webkit-border-radius:3px;font-size:14px"
                                                    id="${p.attributeIdx}" name="attributeValue"
                                                    maxlength="500">${p.attributeValue}</textarea>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                                <div class="gather-img-row scroll-x">
                                    <div class="image-col-md">
                                        <div class="img-content" id="itemKey_upload-img${pStatus.index}">
                                            <c:if test="${ empty p.attributeImages}">
                                                <div class="attributeImg">
                                                    <img class="upload-img" src="" style="border: none">
                                                </div>
                                            </c:if>
                                            <div class="img-cont" attrId="${p.attributeIdx}">
                                                    <%-- 显示已上传的关键属性图片 --%>
                                                <c:forEach items="${p.attributeImages}" var="attrImage">
                                                    <c:if test="${attrImage.attributeIdx eq p.attributeIdx}">
                                                        <div class="attributeImg">
                                                            <img class="upload-img"
                                                                 src="${attrImage.imageMiddleDynamicS}"
                                                                 messageid="${attrImage.imageMiddle}"
                                                                 attrId="${p.attributeIdx}" />
                                                            <span class="close"
                                                                  onclick="removeAttributeImg(this)"><i></i> </span>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            <div class="uploadBtn"
                                                    <c:if test="${p.attributeImages != null && fn:length(p.attributeImages) >= 10}">
                                                        style="display: none;"
                                                    </c:if>
                                                 onclick="uploadClick('${imgurl}','','itemKey_upload-img${pStatus.index}');">
                                                <a href="javascript:;" class="upload-icon"></a>
                                                <span class="font-btn">上传图片</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                    </div>

                    <%-- 图片上传 --%>
                    <input id="_targerId" type="hidden"/>
                    <form id="uploadForm">
                        <input name="filePath" id="_filePath" type="hidden"/>
                        <input name="remark" id="_remark" type="hidden"/>
                        <input id="uploading" style="display: none" type="file" name="files"
                               onchange="uploadS('',1,'uploadForm',upCallback);"/>
                    </form>

                    <%-- 图片粘贴区 --%>
                    <div class="gather-row">
                        <p class="identification"><span class="identification-sp">图片粘贴区</span></p>
                        <div class="utf-editor">
                            <script id="image_paste_area" type="text/plain" class="editorContainer">
                                ${itemContent.imagePasteArea}</script>
                        </div>
                    </div>
                    <%-- 附件上传区 --%>
                    <div class="gather-row">
                        <p class="identification"><span class="identification-sp">附件上传区</span></p>
                        <div class="col-chunk-video">
                            <label class="left-key">视频/PPT/PDF：</label>
                            <form id="uploadAttachment">
                                <input name="filePath" value="collect/attach" type="hidden"/>
                                <input name="remark" value="这是附件" type="hidden"/>
                                <c:if test="${not empty itemCollection.attachmentIdDynamicS}">
                                    <div id="attachFileDiv" class="attachFileDiv">
                                        <span>
                                            <a href="${downAttachUrl}">${itemCollection.attachmentIdDynamicS}</a>
                                        </span>&nbsp;&nbsp;
                                        <span class="deleteFileName" onclick="delAttach();">删除</span></div>
                                </c:if>

                                <input class="selectFileDiv" type="file" name="file" id="attachFile"
                                       <c:if test="${not empty itemCollection.attachmentIdDynamicS}">style="display: none"</c:if> />

                                <input type="button" class="upload-btn" value="上传" onclick="uploadAttach();"/>
                            </form>
                            <input id="attachId" type="hidden" value="${itemCollection.attachmentId}"/>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 编辑采集信息 end -->
            <div class="fixed-save">
                <input type="button" id="sava-gather" class="save-chunk" value="保存" onclick="saveCollectionInfo();"/>
            </div>
        </div>
    </div>
</section>

<script type="text/javascript">

    function delAttach() {
        $('#attachFileDiv').hide();
        $('#attachId').val('');
        $('#attachFile').show();
    }

    // 上传附件
    function uploadAttach() {
        var file = document.getElementById("attachFile");
        var f = file.value;
        if (!f) {
            layer.msg('请选择附件', {icon: 2, offset: '100px'});
            return false;
        }
        if ($.inArray(MS.getFileExtension(f), MS.ATTACHMENT) < 0) {
            document.getElementById('uploadAttachment').reset();
            layer.msg('附件格式不对，目前只支持' + MS.ATTACHMENT.join(','), {icon: 2, offset: '100px'});
            return false;
        }
        var maxsize = '${maxAttachmentSize}' || MSYT.ATTACH_UPLOAD_MAX_SIZE;
        if (parseInt(maxsize) < MS.getFileSize(file)) {
            layer.msg('附件过大，目前最大只支持2GB', {icon: 2, offset: '100px'});
            document.getElementById('uploadAttachment').reset();
            return false;
        }

        var formData = new FormData($('#uploadAttachment')[0]);
        var inx = layer.msg('上传中', {icon: 16, offset: '100px', shade: 0.01, time: MSYT.UPLOAD_TIME});
        $.ajax({
            url: fileResourceApi + '/cos/uploadFiles',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false
        }).done(function (result) {
            layer.close(inx);
            if (result && result.status == 200) {
                if (result.data.length == 0) {
                    layer.msg('上传失败', {icon: 2, offset: '100px'});
                    return;
                }
                if (result.data[0].uploadStatus == 1) {
                    layer.msg('上传成功,保存后生效', {icon: 6, offset: '100px'});
                    $('#attachId').val(result.data[0].messageId);
                } else {
                    layer.msg('上传附件失败', {icon: 2, offset: '100px'});
                }
            } else {
                layer.msg('上传附件失败', {icon: 2, offset: '100px'});
            }
        }).fail(function () {
            layer.close(inx);
            layer.msg('上传附件失败', {icon: 2, offset: '100px'});
            document.getElementById('uploadAttachment').reset();
        });
    }

    var image_paste_area;
    $(function () {
        image_paste_area = app.ueditorHelper.initEditor({
            editorContainerId: "image_paste_area",
            initialFrameWidth: 900,
            initialFrameHeight: 300,
            allowDivTransToP: false
        });

        $('#itemBrandIdx').on('change', function () {
            $('#itemBrandIdx').removeClass("curs");
            $('#itemBrandIdx').next().next().hide();
        });

        $('#itemOriginIdx').on('change', function () {
            $('#itemOriginIdx').removeClass("curs");
            $('#itemOriginIdx').next().next().hide();
        });

        $('#itemBatch').on('keyup', function () {
            $('#itemBatch').removeClass("curs");
            $('#itemBatch').next().hide();
        });

        $('#collectionStatus').on('change', function () {
            $('#collectionStatus').removeClass("curs");
            $('#collectionStatus').next().next().hide();
        });
    });

    function saveCollectionInfo() {
        var pass = true;

        var itemBrandIdx = $('#itemBrandIdx').val();
        if (itemBrandIdx == "") {
            $('#itemBrandIdx').addClass("curs");
            $('#itemBrandIdx').next().next().show();
            layer.msg("请选择品牌", {icon: 2, time: 1000, offset: '200px'});
            pass = false;
        }

        var itemOriginIdx = $('#itemOriginIdx').val();
        if (itemOriginIdx == "") {
            $('#itemOriginIdx').addClass("curs");
            $('#itemOriginIdx').next().next().show();
            layer.msg("请选择产地", {icon: 2, time: 1000, offset: '200px'});
            pass = false;
        }

        var itemBatch = $('#itemBatch').val();
        if (itemBatch == "") {
            $('#itemBatch').addClass("curs");
            $('#itemBatch').next().show();
            layer.msg("请录入批次", {icon: 2, time: 1000, offset: '200px'});
            pass = false;
        }
        if (!Ms.isNumber(itemBatch)) {
            layer.msg("批次必须是数字", {icon: 2, offset: '200px'});
            pass = false;
        }

        var collectionStatus = $('#collectionStatus').val();
        if (collectionStatus == "") {
            $('#collectionStatus').addClass("curs");
            $('#collectionStatus').next().next().show();
            layer.msg("请选择采集状态", {icon: 2, time: 1000, offset: '200px'});
            pass = false;
        }

        if (pass) {
            var attributeValueList = new Array();
            var attributeValue = $("input[name='attributeValue']");
            if (null != attributeValue && attributeValue.length > 0) {
                for (var i = 0; i < attributeValue.length; i++) {
                    var val = attributeValue[i].value;
                    if (val != "") {
                        var idx = attributeValue[i].id;
                        attributeValueList.push({"attributeIdx": idx, "attributeValue": val});
                    }
                }
            }
            var attributeAreaValue = $("textarea[name='attributeValue']");
            if (null != attributeAreaValue && attributeAreaValue.length > 0) {
                for (var i = 0; i < attributeAreaValue.length; i++) {
                    var val = attributeAreaValue[i].value;
                    if (val != "") {
                        var idx = attributeAreaValue[i].id;
                        attributeValueList.push({"attributeIdx": idx, "attributeValue": val});
                    }
                }
            }

            var attributeSelectValue = $("select[name='attributeValue']");
            if (null != attributeSelectValue && attributeSelectValue.length > 0) {
                for (var i = 0; i < attributeSelectValue.length; i++) {
                    var val = attributeSelectValue[i].value;
                    if (val != "") {
                        var idx = attributeSelectValue[i].id;
                        attributeValueList.push({"attributeIdx": idx, "attributeValue": val});
                    }
                }
            }

            var itemAttributeValues = MS.base64_encode(JSON.stringify(attributeValueList));

            var attributeImageList = new Array();
            var attributeImageValue = $(".img-content .img-cont .upload-img:not(0)");
            if (null != attributeImageValue && attributeImageValue.length > 0) {
                for (var i = 0; i < attributeImageValue.length; i++) {
                    var val = $(attributeImageValue.get(i)).attr("messageid");
                    if (val) {
                        var attrId = $(attributeImageValue.get(i)).parent().parent().attr("attrId");
                        attributeImageList.push({"imageBig": val, "attributeIdx": attrId});
                    }
                }
            }

            layer.load(2, {shade: 0.01, offset: '200px'});

            $.ajax({
                type: "post",
                url: "/collect/item/collection/save",
                data: {
                    // 基本信息
                    idx: $("#itemIdx").val(),
                    itemIdx: $("#itemIdx").val(),
                    biddingIdx: $("#biddingIdx").val(),
                    collectionIdx: $("#collectionIdx").val(),
                    contentIdx: $("#contentIdx").val(),
                    cnName: $("#itemCnName").val(),
                    enName: $("#itemEnName").val(),
                    brandIdx: itemBrandIdx,
                    originIdx: itemOriginIdx,
                    specification: $("#itemSpecification").val(),
                    batch: $("#itemBatch").val(),
                    weight: $("#itemWeight").val(),
                    collectionStatus: $("#collectionStatus").val(),
                    itemStateIdx: $("#saleStatus").val(),
                    collectionRemark: $("#collectionRemark").val(),
                    imageBig: $("#upload-img3").find('.img-cont').find('img:first').attr("messageid") || null,
                    // 商品竞价
                    amazonPriceStr: $("#amazonPrice").val(),
                    tmallPriceStr: $("#tmallPrice").val(),
                    jdPriceStr: $("#jdPrice").val(),
                    redPriceStr: $("#redPrice").val(),
                    abroadPriceStr: $("#abroadPrice").val(),
                    domesticPriceStr: $("#domesticPrice").val(),
                    originCountryPriceStr: $("#originCountryPrice").val(),
                    koalaPriceStr: $("#koalaPrice").val(),
                    jumeiPriceStr: $("#jumeiPrice").val(),
                    // 采编编辑
                    editorIdx: $("#editorIdx").val(),
                    slogan: $("#slogan").val(),
                    sellingPoint: $("#sellingPoint").val(),
                    searchKeywords: $("#searchKeywords").val(),
                    shoppingGuide: $("#shoppingGuide").val(),
                    imagePasteArea: image_paste_area.getContent(),
                    // 属性值
                    itemAttributeValues: itemAttributeValues,
                    attachmentId: $('#attachId').val() || 0,
                    itemAttributeImages: MS.base64_encode(JSON.stringify(attributeImageList))
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        layer.msg("保存成功", {icon: 1, time: 1000, offset: '200px'});
                    } else {
                        layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2, time: 1000, offset: '200px'});
                    }
                    layer.closeAll("loading");
                },
                error: function (d) {
                    layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2, time: 1000, offset: '200px'});
                }
            });
        }
    }

    <%-- 关键属性上传图片后可删除图片 --%>
    function removeAttributeImg(str) {
        var parent = $(str).parent();
        parent.parent().parent().find(".uploadBtn").css("display", "block");
        parent.remove();
    }

    <%-- 重写文件上传回调方法 --%>
    function upCallback(result) {
        if (result && result.status == 200) {
            if (result.data.length == 0) {
                layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time: 1500, offset: '100px'});
                return;
            }
            if (result.data[0].uploadStatus == 1) {
                var $targerId = $('#' + $('#_targerId').val());
                var path = imgurl + result.data[0].path;
                if ($targerId.get(0) && $targerId.get(0).id.indexOf("itemKey") > -1) {
                    var imgCont = $targerId.find('.img-cont');
                    if (imgCont.children().length < 1) {
                        var a = $targerId.find(".attributeImg:first");
                        a.css("display", "none");
                    } else if (imgCont.children().length >= 9) {
                        $targerId.find(".uploadBtn").css("display", "none");
                    }
                    imgCont.append('<div class="attributeImg"><img class="upload-img" src="' + path + '" messageid=' + result.data[0].path + '><span class="close" onclick="removeAttributeImg(this)"><i></i> </span></div>');
                } else {
                    var dataurl = result.data[0].messageId + "||" + result.data[0].path;
                    $targerId.find('.img-cont').html('<img src="' + path + '" messageid=' + dataurl + ' />');
                }
            } else {
                layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time: 1500, offset: '100px'});
            }
        } else {
            layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time: 1500, offset: '100px'});
        }
    }

</script>

<jsp:include page="../../ueditor.jsp"/>
<jsp:include page="../../../footer.jsp"/>