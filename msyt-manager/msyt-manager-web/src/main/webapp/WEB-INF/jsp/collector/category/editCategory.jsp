<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="categoryCapacity" style="overflow-y: scroll;overflow-x: hidden;height: 447px;">
    <input type="hidden" id="categoryIdx" value="${itemCategory.idx}">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>类目编码：</label>
            <input id="categoryCode" class="brandTxt required" type="text" value="${itemCategory.code}"/>
            <p class="category-hint hint1">*类目编码必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>类目简称：</label>
            <input id="categoryName" class="brandTxt required" type="text" value="${itemCategory.name}"/>
            <p class="category-hint hint2">*类目简称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName">类目全称：</label>
            <input class="brandTxt" type="text" id="fullName" value="${itemCategory.fullName}"/>
        </div>

        <div class="winRow">
            <label class="brandName">上级目录：</label>
            <input type="text" id="upDirectory" class="brandTxt" value="${parentName == null ? "无": parentName}"
                   data-content-id="${itemCategory.pidx}"/>
            <span class="selectML cursor" onclick="selectClassify()">点击选择</span>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>限购数：</label>
            <input class="brandTxt" type="text" id="purchaseLimit" value="${itemCategory.purchaseLimit}"
                   onblur="Ms.onlyNum(this);" onkeyup="Ms.onlyNum(this);"/>
            <p class="hint">*限购数必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>类目排序：</label>
            <input class="brandTxt" type="text" id="categorySequence" value="${itemCategory.categorySequence}"
                   onblur="Ms.onlyNum(this);" onkeyup="Ms.onlyNum(this);"/>
            <p class="hint">*类目排序必填</p>
        </div>
        <!-- 图片上传 start -->
        <input id="_targerId" type="hidden"/>
        <form id="uploadForm">
            <input name="filePath" id="_filePath" type="hidden"/>
            <input name="remark" id="_remark" type="hidden"/>
            <input id="uploading" style="display: none" type="file" name="files"
                   onchange="uploadS('',1,'uploadForm',upCallback);"/>
        </form>
        <div class="uploadImg-col">
            <span class="img-title pd"><em class="left">类目图片：</em><em class="ts">（支持jpg/png/gif，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">
                <div class="upload-col">
                    <div class="uploadBtn" onclick="uploadClick('collect','','upload-img3');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <p class="size-ts">400 x 400</p>
                    <div class="upload-img" id="upload-img3">
                        <p class="img-cont">
                            <c:if test="${not empty itemCategory.imageBigDynamicS}">
                                <img src="${itemCategory.imageBigDynamicS}" messageid='${itemEditor.imageBig}'
                                     alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i> </span>
                        <span class="img-name"></span>
                    </div>
                </div>
            </div>
        </div>
        <!-- 图片上传 end -->
        <div class="winRow">
            <label class="brandName">发布渠道：</label>
            <p class="relative">
                <select class="brandTxt" id="publishChannel">
                    <c:forEach items="${publishChannelMap}" var="publishChannel">
                        <option value="${publishChannel.key}" <c:if test="${publishChannel.key eq itemCategory.publishChannel}">selected</c:if>>${publishChannel.value}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            <p class="relative">
        </div>

        <div class="row-set" style="padding:20px 20px 0 15px">
            <div class="row-set-lf">
                <label class="ms-userName"><i>*</i>类目属性：</label>
                <button class="ms-set" type="button" onclick="chooseAttributeGroup()">设置</button>
                <span class="category-hint hint3" style="line-height:38px;color:red;display:none;margin-left:20px">* 请设置属性组</span>
            </div>

            <div class="row-set-lr" id="collector-category-attrGroups">
                <c:forEach items="${attributeGroupList}" var="attributeGroup">
                    <span class="ms-institution" id="resource-${attributeGroup.id}" onclick="closeResouceGroupName(this)"><i></i>${attributeGroup.name}</span>
                </c:forEach>
            </div>
        </div>

        <div class="winRow" style="height: 35px;"></div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#categoryCapacity #categoryCode').on('keyup', function () {
            $(this).next().hide();
            $('#categoryCode').css("border", "1px solid #dbdcde");
        });

        $('#categoryCapacity #categoryName').on('keyup', function () {
            $(this).next().hide();
            $('#categoryName').css("border", "1px solid #dbdcde");
        });

        $('#categoryCapacity #attributeGroupIdx').on('change', function () {
            $(this).parent().next().hide();
            $('#attributeGroupIdx').css("border", "1px solid #dbdcde");
        });

        $('#categoryCapacity #purchaseLimit').on('keyup', function () {
            $(this).next().hide();
            $('#purchaseLimit').css("border", "1px solid #dbdcde");
        });

        $('#categoryCapacity #categorySequence').on('keyup', function () {
            $(this).next().hide();
            $('#categorySequence').css("border", "1px solid #dbdcde");
        });
    });

    function submitForm() {
        var pass = true;
        var categoryCode = $('#categoryCode').val();
        var categoryName = $('#categoryName').val();
//        var attributeGroupIdx = $('#attributeGroupIdx').val();
        var purchaseLimit = $('#purchaseLimit').val();
        var categorySequence = $('#categorySequence').val();
        var publishChannel = $("#publishChannel").val();

        if (categoryCode == "") {
            $('#categoryCapacity .hint1').show();
            $('#categoryCode').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }
        if (categoryName == "") {
            $('#categoryCapacity .hint2').show();
            $('#categoryName').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }
//        if (attributeGroupIdx == "") {
//            $('#categoryCapacity .hint3').show();
//            $('#attributeGroupIdx').css("border", "1px solid #ff2c41");
//            Ms.closeLoading();
//            pass = false;
//        }

        var $attributeGroupSpan = $("span[id^='resource-']");
        if ($attributeGroupSpan.length < 1) {
            $('#categoryCapacity .hint3').show();
            Ms.closeLoading();
            return false;
        }

        if (purchaseLimit == "") {
            $('#purchaseLimit').next().show();
            $('#purchaseLimit').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        if (categorySequence == "") {
            $('#categorySequence').next().show();
            $('#categorySequence').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var attributeGroupIdArr = [];
        $attributeGroupSpan.each(function(index, element) {
            var id = $(this).attr("id");
            var idArr = id.split("-");
            attributeGroupIdArr.push(idArr[1]);
        });

        if (pass) {
            $.ajax({
                type: "post",
                url: "/collect/categy/save",
                data: {
                    idx: $("#categoryIdx").val(),
                    code: categoryCode,
                    name: categoryName,
                    fullName: $("#fullName").val(),
                    purchaseLimit: purchaseLimit,
                    categorySequence: categorySequence,
                    publishChannel: publishChannel,
                    imageBig: $("#upload-img3").find('.img-cont').find('img:first').attr("messageid") || null,
                    pidx: $("#upDirectory").attr("data-content-id"),
                    attributeGroupIds: attributeGroupIdArr.join(",")
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 6});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                    parent.$('#categoryTree').treegrid('reload', {});
                },
                error: function (d) {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            });
        }
    }

    function selectClassify() {
        parent.layer.open({
            type: 2,
            title: '选择分类',
            area: ['610px', '520px'],
            btn: ['确定', '取消'],
            content: ['/collect/categy/sel/edit/${(itemCategory.idx==null)?0:itemCategory.idx}', 'no'],
            yes: function (index, layero) {
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var result = iframeWin.chooseCategoryResult();
                // 回显
                var spanHtml = '';
                var dataIds = '';
                var $resourceDiv = $('#upDirectory');
                if (result) {
                    var resourceArr = result.split("|");
                    spanHtml = resourceArr[1];
                    dataIds = resourceArr[0];
                }
                if (spanHtml != "") {
                    $resourceDiv.val(spanHtml);
                    $resourceDiv.attr("data-content-id", dataIds);
                } else {
                    $resourceDiv.val("无");
                }
                return false;
            }
        });
    }

    function chooseAttributeGroup() {
        <%-- 获取属性组idx --%>
        var attributeGroupIds = [];
        $("span[id^='resource-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            attributeGroupIds.push(idxArr[1]+"");
        });

        if (attributeGroupIds.length > 0) {
            attributeGroupIds = attributeGroupIds.join(',');
            attributeGroupIds = "["+attributeGroupIds+"]";
        } else {
            attributeGroupIds = "[0]";
        }

        var url = '/collect/categy/chooseAttributeGroup/' + attributeGroupIds;
        var title ='选择属性组';
        <%-- 页面层 --%>
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['605px', '670px'], //宽高
            /* offset: [        //随机坐标
             Math.random()*($(window).height()-300),
             Math.random()*($(window).width()+390)
             ],*/
            content: [url, 'no'],
            yes: function(index, layero){
                $('#categoryCapacity .hint3').hide();
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var result = iframeWin.chooseResult();
                // 回现
                var spanHtml = '',$resourceDiv = $('#collector-category-attrGroups');
                if (result){
                    var resultArr = result.split(",");
                    for (var i =0 ; i < resultArr.length ; i++){
                        var resourceArr = resultArr[i].split("|");
                        var idx = resourceArr[0], name = resourceArr[1];
                        if (idx!="0") {
                            if ($('#resource-' + idx).length > 0 && !name) {
                                name = $('#resource-' + idx).text();
                            }
                            spanHtml += '<span class="ms-institution" id="resource-' + idx + '" onclick="closeResouceGroupName(this)"><i></i>' + name + '</span>';
                        }
                    }
                }
                $resourceDiv.html(spanHtml);
                return false;
            }
        });
    }

    <%-- 点击X 去掉已选择的资源组 --%>
    function closeResouceGroupName(obj) {
        $(obj).remove();
    }
</script>
</body>
</html>