<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="optionCapacity"
     style="overflow-y: hidden;overflow-x: hidden;overflow-y: scroll;height: 240px;background:#FFFFFF;">
    <input type="hidden" id="attributeIdx" value="${attribute.idx}">
    <div class="winCont">
        <c:forEach items="${attributeSelectList}" var="p" varStatus="i">
            <div class="winRow">
                <label class="brandName"><i>*</i>选项${i.index+1}：</label>
                <input name="attributeSelect" class="brandTxt" type="text" value="${p.name}">
                <p class="hint">*必填</p>
            </div>
        </c:forEach>
        <div class="winRow">
            <label class="brandName">新增选项：</label>
            <span id="addSelect" class="addSelect"><i></i></span>
        </div>
    </div>
</div>

<script type="text/javascript">
    function submitForm() {
        var pass = true;

        var attributeSelectList = [];
        $("input[name='attributeSelect']").each(function (i) {
            if (this.value == "") pass = false;
            attributeSelectList.push(this.value);
        });

        if (!pass) {
            layer.msg("属性选项值不能为空", {icon: 2});
            Ms.closeLoading();
            return false;
        }

        $.ajax({
            type: "post",
            url: "/collect/attribute/sel/save",
            data: {
                idxStr: $("#attributeIdx").val(),
                attributeSelectList: attributeSelectList
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
                parent.$('#propertyTree').treegrid('reload', {});
            },
            error: function (d) {
                parent.layer.msg(d.msg, {icon: 2});
            }
        });
    }

    $('#addSelect').click(function () {
        var index = $('#optionCapacity .winRow').length;
        $(this).parent().before('<div class="winRow"><label class="brandName"><i>*</i> 选项' + index + ' ：</label><input name="attributeSelect" class="brandTxt" type="text"><p class="hint">*必填</p></div>');
    });
</script>

<jsp:include page="../../footer.jsp"/>