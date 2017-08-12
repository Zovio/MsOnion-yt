<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="classifyCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 410px;background:#fff;padding:0 20px;">
    <div class="classifyRow">
        <label class="lb-name">分类名称：</label>
        <input type="text" class="txt-name" id="categoryName"/>
        <label class="lb-name">分类编码：</label>
        <input type="text" class="txt-name" id="categoryCode"/>
        <span class="search" id="checkTree-qry"><i></i>搜索</span>
    </div>
    <%--<c:if test="${!empty selectedCategoryIdx and selectedCategoryIdx gt 0}">--%>
        <%--<div class="classifyRow">--%>
            <%--商品类目：--%>
            <%--<c:if test="${!empty selectedCategoryOne}"> ${selectedCategoryOne.name} > </c:if>--%>
            <%--<c:if test="${!empty selectedCategoryTwo}"> ${selectedCategoryTwo.name} > </c:if>--%>
            <%--<c:if test="${!empty selectedCategoryThree}"> ${selectedCategoryThree.name}</c:if>--%>
        <%--</div>--%>

    <%--</c:if>--%>

    <div class="classifyRow2">
        <div class="classifyCont">
            <div class="left-Menu">
                <ul id="checkTree" class="easyui-tree"></ul>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function chooseCategoryResult() {
        var selRows = $('#checkTree').tree('getSelected');
        if (!selRows) {
            parent.layer.msg("请选择类目");
            return false;
        }
        var result = selRows.id + "|" + selRows.text;
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
        return result;
    }

    $(function () {
        <%--var selectedCategoryIdx = "${selectedCategoryIdx}";--%>
//        var checkTree = $('#checkTree').tree({
//            url: '/collect/categy/grid',
//            method: 'post',
//            animate: true,
//            onLoadSuccess: function (node, data) {
//                if (selectedCategoryIdx != null && selectedCategoryIdx != "") {
//                    var node = $('#checkTree').tree("find", selectedCategoryIdx);
//                    $('#checkTree').tree("select", node.target);
//                }
//            }
//        });
        showTreeData();
        $('#checkTree-qry').on('click', function () {
            $('#checkTree').children().remove();
            $('#checkTree').tree({
                url: '/collect/categy/grid',
                method: 'post',
                animate: true,
                onBeforeLoad: function (node, param) {
                    param.code = $.trim($('#categoryCode').val());
                    param.name = $.trim($('#categoryName').val());
                },
                onClick:function(node){
                    $('#checkTree').tree('toggle',node.target);
                },
                onBeforeExpand:function(node) {
                    var options = $('#checkTree').tree('options');
                    options.url = '/collect/categy/gridByParentId';
                    $('#checkTree').tree('options',options);
                }

            });
        });


    });

    function showTreeData(){

        $('#checkTree').tree({
            url : "/collect/categy/gridByParentId",
            animate:true,
            onClick:function(node){
                $('#checkTree').tree('toggle',node.target);
            },
            loadFilter : function(data) {
                return data;
            },
            onLoadSuccess : function(node, data){
                <c:if test="${!empty selectedCategoryIdx and selectedCategoryIdx gt 0}">
                <c:if test="${!empty selectedCategoryOne and !empty selectedCategoryOne.idx and selectedCategoryOne.idx gt 0}">
                var nodeOne = $('#checkTree').tree('find', ${selectedCategoryOne.idx});
                if (nodeOne) {
                    $('#checkTree').tree('expand', nodeOne.target);
                }
                </c:if>
                <c:if test="${!empty selectedCategoryTwo and !empty selectedCategoryTwo.idx and selectedCategoryTwo.idx gt 0}">
                var nodeTwo = $('#checkTree').tree('find', ${selectedCategoryTwo.idx});
                if (nodeTwo) {
                    $('#checkTree').tree('expand', nodeTwo.target);
                }
                </c:if>
                var nodeThree = $('#checkTree').tree('find', ${selectedCategoryThree.idx});
                if (nodeThree) {
                    $('#checkTree').tree('select', nodeThree.target);
                }
                </c:if>
            }
        });

    }



</script>

<jsp:include page="../../footer.jsp"/>