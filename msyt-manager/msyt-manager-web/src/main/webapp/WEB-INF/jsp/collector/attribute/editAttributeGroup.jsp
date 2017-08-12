<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="groupCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 187px;">
    <input type="hidden" id="attributeGroupIdx" value="${attributeGroup.idx}">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>属性组名称：</label>
            <input id="attributeGroupName" class="brandTxt" type="text" value="${attributeGroup.name}" maxlength="10">
            <p class="hint">*属性组名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>属性值：</label>
            <input id="attributes" class="getProperty" type="button"
                   value="${(attributeValues == null || attributeValues == '')?'点击选择属性值':attributeValues}"
                   readonly="true"
                   onclick="selectGAbility()" data-content-id="${attributeIds}"/>
            <p class="hint">*属性值必填</p>
        </div>
        <div class="winRow"></div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#groupCapacity #attributeGroupName').on('keyup', function () {
            $(this).next().hide();
            $('#attributeGroupName').css("border", "1px solid #dbdcde");
        });
    });

    function submitForm() {
        var pass = true;
        var attributeGroupName = $('#attributeGroupName').val();
        if (attributeGroupName == "") {
            $('#attributeGroupName').next().show();
            $('#attributeGroupName').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        var attributes = $('#attributes').val();
        if (attributes == "点击选择属性值") {
            $('#attributes').next().show();
            $('#attributes').css("border", "1px solid #ff2c41");
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            layer.load(2, {shade: 0.01, time: 5000});
            $.ajax({
                type: "post",
                url: "/collect/attribute/gp/save",
                data: {
                    idx: $("#attributeGroupIdx").val(),
                    name: $("#attributeGroupName").val(),
                    attributes: $("#attributes").attr("data-content-id")
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 1});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                    }
                    layer.closeAll("loading");
                    parent.$('#groupList').datagrid('reload', {});
                },
                error: function (d) {
                    layer.closeAll("loading");
                    parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                }
            });
        }
    }

    // 选择属性值
    function selectGAbility() {
        parent.layer.open({
            type: 2,
            title: '选择属性值',
            area: ['500px', '460px'],
            btn: ['确定', '取消'],
            content: ['/collect/attribute/gp/sel/edit/${(attributeGroup.idx==null)?0:attributeGroup.idx}', 'no'],
            yes: function (index, layero) {
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var result = iframeWin.chooseAttributeResult();
                // 回现
                var spanHtml = '';
                var dataIds = '';
                var $resourceDiv = $('#attributes');
                if (result) {
                    var resultArr = result.split(",");
                    for (var i = 0; i < resultArr.length; i++) {
                        var resourceArr = resultArr[i].split("|");

                        if (spanHtml == "") {
                            spanHtml += resourceArr[1];
                            dataIds += resourceArr[0];
                        } else {
                            spanHtml += "，" + resourceArr[1];
                            dataIds += "," + resourceArr[0];
                        }
                    }
                }
                if (spanHtml != "") {
                    $resourceDiv.val(spanHtml);
                    $resourceDiv.attr("data-content-id", dataIds);
                    $resourceDiv.next().hide();
                    $resourceDiv.css("border", "1px solid #dbdcde");
                } else {
                    $resourceDiv.val("点击选择属性值");
                }
                return false;
            }
        });
    }
</script>

<jsp:include page="../../footer.jsp"/>