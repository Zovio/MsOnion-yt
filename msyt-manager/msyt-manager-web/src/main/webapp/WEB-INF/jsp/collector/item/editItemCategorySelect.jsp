<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="selectClassify" style="overflow-y: scroll;overflow-x: hidden;height: 437px;">
    <div class="winCont">
        <ul id="checkTree" class="easyui-tree"></ul>
    </div>
</div>

<script type="text/javascript">
    function chooseCategoryResult() {
        var selRows = $('#checkTree').tree('getSelected');
        var result = selRows.id + "|" + selRows.text;
        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
        parent.layer.close(index);
        return result;
    }

    $(function () {
        $('#checkTree').tree({
            url: '/collect/categy/grid',
            method: 'post',
            animate: true
        });
    });
</script>

<jsp:include page="../../footer.jsp"/>