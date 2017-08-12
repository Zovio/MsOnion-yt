<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../header.jsp"/>

<form class="yt-main">
    <!-- 基本信息 start -->
    <div class="yt-module">
        <div class="yt-title">基本信息</div>
        <div class="yt-row">
            <div class="yt-col-md">
                <label class="yt-label">货号：</label>
                <input class="yt-input" type="text" disabled value="${itemBasicOfficial.itemNo}"/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">中文品名：</label>
                <input class="yt-input" type="text" disabled value="${itemBasicOfficial.cnName}"/>
            </div>
            <div class="yt-col-md width-auto">
                <label class="yt-label">外文品名：</label>
                <input class="yt-input" type="text" disabled value="${itemBasicOfficial.enName}"/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">品牌：</label>
                <div class="sel-cont">
                    <input class="yt-input" type="text" disabled value="${brandName}"/>
                </div>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">产地：</label>
                <div class="sel-cont">
                    <input class="yt-input" type="text" disabled value="${originCnName}"/>
                </div>
            </div>
            <div class="yt-col-md width-auto">
                <label class="yt-label">商品规格：</label>
                <input class="yt-input" type="text" disabled value="${itemBasicOfficial.specification}"/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">批次：</label>
                <input class="yt-input" type="text" disabled value="${itemBasicOfficial.batch}"/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">权重：</label>
                <input class="yt-input" type="text" disabled value="${itemBasicOfficial.weight}"/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">销售状态：</label>
                <input class="yt-input" type="text" disabled value="${saleStatus}"/>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">备注：</label>
                <textarea class="yt-textarea" disabled>${itemBasicOfficial.collectionRemark}</textarea>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">条形码：</label>
                <p class="txm-cont">
                    <input class="yt-input" type="text" disabled value="${itemBasicOfficial.barcode}"
                           style="margin: 0 20px 20px 0;"/>
                    <c:forEach items="${barcodeList}" var="m">
                        <input class="yt-input" type="text" disabled value="${m.barcode}"
                               style="margin: 0 20px 20px 0;"/>
                    </c:forEach>

                </p>
            </div>
        </div>
    </div>
    <!-- 基本信息 end -->

    <!-- 采集编辑 start -->
    <div class="yt-module">
        <div class="yt-title">采集编辑</div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">采编：</label>
                <input class="yt-input" type="text" disabled value="${editorName}">
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">搜索关键词：</label>
                <textarea class="yt-textarea" disabled>${itemCollectionOfficial.searchKeywords}</textarea>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">广告语：</label>
                <textarea class="yt-textarea" disabled>${itemCollectionOfficial.slogan}</textarea>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">卖点：</label>
                <textarea class="yt-textarea" disabled>${itemCollectionOfficial.sellingPoint}</textarea>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">导购语：</label>
                <textarea class="yt-textarea" disabled>${itemCollectionOfficial.shoppingGuide}</textarea>
            </div>
        </div>
    </div>
    <!-- 采集编辑 end -->

    <!--属性值 start-->
    <div class="yt-module" style="padding: 10px 0 0 0;">
        <div class="yt-title">属性值</div>
        <div class="gather-row">
            <c:forEach items="${itemAttributeList}" var="p">
                <c:if test="${!p.isKey}">
                    <div class="col-specialProperty">
                        <span class="left-key">${p.attributeName}：</span>
                        <c:choose>
                            <c:when test="${! empty p.selectedAttributeValue}">
                                <input type="text" style="padding:10px;border:1px solid #d4d4d4" value="${p.selectedAttributeValue}"
                                       disabled/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" style="padding:10px;border:1px solid #d4d4d4" value="${p.attributeValue}"
                                       disabled/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </c:forEach>

            <%-- 关键属性--%>
            <c:forEach items="${itemAttributeList}" var="p" varStatus="pStatus">
                <c:if test="${p.isKey}">
                    <div class="col-specialProperty" style="margin:10px 0">
                        <label class="left-key">${p.attributeName}：</label>
                        <c:choose>
                            <c:when test="${! empty p.selectedAttributeValue}">
                                <textarea style="padding:6px;width:480px;height:90px;box-sizing:border-box;border:1px solid #dbdcde;border-radius:3px;-webkit-border-radius:3px;font-size:14px"
                                          id="${p.attributeIdx}" name="attributeValue" disabled>${p.selectedAttributeValue}</textarea>
                            </c:when>
                            <c:otherwise>
                                <textarea style="padding:6px;width:480px;height:90px;box-sizing:border-box;border:1px solid #dbdcde;border-radius:3px;-webkit-border-radius:3px;font-size:14px"
                                          id="${p.attributeIdx}" name="attributeValue" disabled>${p.attributeValue}</textarea>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="gather-img-row" style="overflow-x: auto">
                        <c:if test="${fn:length(p.attributeImages) > 0}">
                            <div class="image-col-md">
                                <div class="img-content">
                                    <div class="img-cont">
                                            <%-- 显示已上传的关键属性图片 --%>
                                        <c:forEach items="${p.attributeImages}" var="attrImage">
                                            <c:if test="${attrImage.attributeIdx eq p.attributeIdx}">
                                                <div class="attributeImg">
                                                    <img class="upload-img" src="${attrImage.imageMiddleDynamicS}"
                                                         messageid="${attrImage.imageMiddle}">
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <!--属性值 end-->

    <!--报关类信息 start-->
    <div class="yt-module">
        <div class="yt-title">报关类信息</div>
        <div class="yt-row">
            <div class="yt-col-md width-auto">
                <label class="yt-label">报关品名：</label>
                <input class="yt-input-long" style="width: 480px;" type="text"
                       value="${logisticsCustomsDeclareOfficial.customName}" disabled/>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md">
                <label class="yt-label">第一数量：</label>
                <input class="yt-input " type="text" value="${logisticsCustomsDeclareOfficial.firstQuantity}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">第二数量：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.secondQuantity}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">行邮税率：</label>
                <input class="yt-input" type="text"
                       value="${logisticsCustomsDeclareOfficial.postalArticlesTaxRateDots5}" disabled/>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md">
                <label class="yt-label">第一单位：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.firstUnitIdxDynamicS}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">第二单位：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.secondUnitIdxDynamicS}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">跨境税率：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.crossBorderTaxRateDots5}"
                       disabled/>
            </div>
        </div>
        <div class="yt-row">
            <div class="yt-col-md">
                <label class="yt-label">BC价：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.bcPriceDots2}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">HS编码：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.hsCode}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">毛重：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.grossWeight}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">行邮税号：</label>
                <input class="yt-input" type="text" value="${logisticsCustomsDeclareOfficial.postalArticlesTaxNumber}"
                       disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label line-hei">是否含有消费税：</label>
                <div class="sel-cont">
                    <input class="yt-input" type="text"
                           value="${logisticsCustomsDeclareOfficial.isContainExcise == 1?"是":"否"}" disabled/>
                </div>
            </div>
        </div>
    </div>
    <!-- 报关类信息 end -->

    <!-- 库存信息 start -->
    <div class="yt-module">
        <div class="yt-title">库存信息</div>
        <div class="yt-row">
            <div class="yt-col-md">
                <label class="yt-label">发货系数：</label>
                <input class="yt-input " type="text" value="${warehouseOfficial.deliveryCoefficient}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">实时库存：</label>
                <input class="yt-input " type="text" value="${warehouseOfficial.realTimeInventory}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">安全库存：</label>
                <input class="yt-input" type="text" value="${warehouseOfficial.safeInventory}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">是否一键发货：</label>
                <div class="sel-cont">
                    <input class="yt-input" type="text" value="${warehouseOfficial.isKeyOrder == 1?"是":"否"}" disabled/>
                </div>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">是否免邮：</label>
                <div class="sel-cont">
                    <input class="yt-input" type="text" value="${warehouseOfficial.isFreeShipping == 1?"是":"否"}"
                           disabled/>
                </div>
            </div>
        </div>
    </div>
    <!-- 库存信息 end -->

    <!-- 设置价格 start -->
    <div class="yt-module">
        <div class="yt-title">商品价格</div>
        <div class="yt-row">
            <div class="yt-col-md">
                <label class="yt-label">售价：</label>
                <input class="yt-input" type="text" value="${itemPriceOfficial.sellingPriceDots2}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">供货价：</label>
                <input class="yt-input" type="text" value="${itemPriceOfficial.supplyPriceDots2}" disabled/>
            </div>
            <div class="yt-col-md">
                <label class="yt-label">市场价：</label>
                <input class="yt-input" type="text" value="${itemPriceOfficial.marketPriceDots2}" disabled/>
            </div>
        </div>
    </div>
    <!-- 设置价格 end -->

    <!-- 商品图片 start -->
    <div class="yt-module" style="padding: 10px 0 0 0;">
        <div class="yt-title">商品图片</div>
        <div class="yt-row ">
            <div class="image-col-md">
                <div class="img-content">
                    <div class="attributeImg">
                        <c:if test="${not empty itemFormalImageLib.whiteBackgroundImageBigDynamicS}">
                            <img class="upload-img" src="${itemFormalImageLib.whiteBackgroundImageBigDynamicS}">
                        </c:if>
                        <c:if test="${not empty itemFormalImageLib.detailPageMainImageBigDynamicS}">
                            <img class="upload-img" src="${itemFormalImageLib.detailPageMainImageBigDynamicS}">
                        </c:if>
                        <c:if test="${not empty itemFormalImageLib.mainPushImageBigDynamicS}">
                            <img class="upload-img" src="${itemFormalImageLib.mainPushImageBigDynamicS}">
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 商品图片 end -->

    <!-- 商品详情图片 start -->
    <div class="yt-module">
        <div class="yt-title">商品详情图片</div>
        <div class="details-editor">${itemFormalImageLib.contentInfo}</div>
    </div>
    <!-- 商品图片 end -->

    <!-- 视频链接 start -->
    <div class="yt-module">
        <div class="yt-title">视频链接区域</div>
        <div class="yt-row">
            <div class="yt-col-md">
                <input class="yt-input-video" style="width: 540px;" type="text"
                       value="${itemCollectionOfficial.videoLink}" disabled/>
            </div>
        </div>
    </div>
</form>

<jsp:include page="../../footer.jsp"/>