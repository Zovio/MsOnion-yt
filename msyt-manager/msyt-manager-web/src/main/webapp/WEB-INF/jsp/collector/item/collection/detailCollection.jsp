<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="main-content">
            <div class="editor">
                <%-- 基本信息 --%>
                <p class="identification"><span class="identification-sp">基本信息</span></p>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">货号：</span>
                        <span class="right-value">${item.itemNo}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">亚马逊售价：</span>
                        <span class="right-value">${itemBidding.amazonPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">条形码：</span>
                        <span class="right-value">${item.barcode}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">天猫售价：</span>
                        <span class="right-value">${itemBidding.tmallPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">中文品名：</span>
                        <span class="right-value">${item.cnName}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">京东售价：</span>
                        <span class="right-value">${itemBidding.jdPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">外文品名：</span>
                        <span class="right-value">${item.enName}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">小红书售价：</span>
                        <span class="right-value">${itemBidding.redPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">品牌：</span>
                        <span class="right-value">${brandName}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">国外官网售价：</span>
                        <span class="right-value">${itemBidding.abroadPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">产地：</span>
                        <span class="right-value">${originCnName}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">中国官网售价：</span>
                        <span class="right-value">${itemBidding.domesticPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">商品规格：</span>
                        <span class="right-value">${item.specification}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">原产国售价：</span>
                        <span class="right-value">${itemBidding.originCountryPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">批次：</span>
                        <span class="right-value">${item.batch}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">考拉售价：</span>
                        <span class="right-value">${itemBidding.koalaPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">采集状态：</span>
                        <span class="right-value">${collectionStatus}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">聚美售价：</span>
                        <span class="right-value">${itemBidding.jumeiPriceDots2}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">采集备注：</span>
                        <span class="right-value">${item.collectionRemark}</span>
                    </div>
                </div>
                <%-- 采编编辑 --%>
                <p class="identification"><span class="identification-sp">采编编辑</span></p>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">采编名称：</span>
                        <span class="right-value">${editorName}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">卖点：</span>
                        <span class="right-value">${itemCollection.sellingPoint}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">广告语：</span>
                        <span class="right-value">${itemCollection.slogan}</span>
                    </div>
                </div>
                <div class="gather-row">
                    <div class="col-chunk">
                        <span class="left-key">搜索关键词：</span>
                        <span class="right-value">${itemCollection.searchKeywords}</span>
                    </div>
                    <div class="col-chunk">
                        <span class="left-key">导购语：</span>
                        <span class="right-value">${itemCollection.shoppingGuide}</span>
                    </div>
                </div>
                <%-- 属性值 --%>
                <p class="identification"><span class="identification-sp">属性值</span></p>
                <div class="gather-img-row">
                    <c:forEach items="${itemAttributeList}" var="p">
                        <c:if test="${!p.isKey}">
                            <div class="col-chunk">
                                <span class="left-key" style="width:130px">${p.attributeName}：</span>
                                <c:choose>
                                    <c:when test="${null != p.attributeOptions && fn:length(p.attributeOptions) > 0}">
                                        <span class="right-value" style="border:none">${p.selectedAttributeValue}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="right-value" style="border:none">${p.attributeValue}</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                    </c:forEach>

                    <%-- 关键属性--%>
                    <c:forEach items="${itemAttributeList}" var="p" varStatus="pStatus">
                        <c:if test="${p.isKey}">
                            <div class="col-chunk" style="margin-bottom: 5px;width:100%">
                                <label class="left-key" style="width:130px">${p.attributeName}：</label>
                                <c:choose>
                                    <c:when test="${null != p.attributeOptions && fn:length(p.attributeOptions) > 0}">
                                        <span class="right-value" style="border:none;line-height:20px;padding-top:5px;width:85%">${p.selectedAttributeValue}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="right-value" style="border:none;line-height:20px;padding-top:5px;width:85%">${p.attributeValue}</span>
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
                                                            <img class="upload-img"
                                                                 src="${attrImage.imageMiddleDynamicS}"
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

                <%-- 图片区域 --%>
                <p class="identification"><span class="identification-sp">图片区域</span></p>
                <div id="layer-photos-demo" class="picture layer-photos-demo">
                    <div class="photo-content">${itemContent.imagePasteArea}</div>
                </div>
                <%-- 附件区域 --%>
                <p class="identification"><span class="identification-sp">附件区域</span></p>
                <div class="video-content">
                    <c:if test="${not empty itemCollection.attachmentIdDynamicS}">
                        <a href="${downAttachUrl}">${itemCollection.attachmentIdDynamicS}</a> <span
                            style="padding-left: 10px;font-size: 12px;color:#9eadb6">可点击下载</span>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../../../footer.jsp"/>