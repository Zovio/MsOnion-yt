<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="selectGAbility" style="overflow-y: hidden;overflow-x: hidden;height: 460px;">
    <div class="winCont">
        <div class="winRow" style="height: 20px;"></div>
        <div class="selectProperty">
            <table id="selectProperty" class="easyui-datagrid" style="margin: 0 auto;width:400px;height:307px">
            </table>
        </div>
        <div class="winRow" style="height: 20px;"></div>
    </div>
</div>

<script type="text/javascript">
    function chooseAttributeResult() {
        var resultArr = [];
        var selRows = $('#selectProperty').datagrid('getChecked');
        for (var i = 0; i < selRows.length; i++) {
            var result = selRows[i].idx + "|" + selRows[i].name;
            resultArr.push(result);
        }
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
        return resultArr.join(",");
    }

    $(function () {
        var defaultIdxArr = ${attributeIdxListJson};

        $('#selectProperty').datagrid({
            url: '/collect/attribute/all',
            method: 'post',
            singleSelect: false,
            collapsible: true,
            columns: [[
                {
                    title: '序号',
                    field: '',
                    width: 80,
                    checkbox: 'true',
                    align: 'center',
                    sortable: true
                },
                {
                    title: '属性值',
                    field: 'name',
                    width: 370,
                    align: 'center',
                    sortable: true
                }
            ]],
            onLoadSuccess: function (data) {
                $('#selectProperty').datagrid('clearSelections');

                // 将之前数据库中保存的默认选中
                if (defaultIdxArr.length > 0 && data) {
                    $.each(data.rows, function (index, item) {
                        if ($.inArray(item.idx, defaultIdxArr) > -1) {
                            $('#selectProperty').datagrid('checkRow', index);
                        }
                    });
                }
            }
        });
    });
</script>

<jsp:include page="../../footer.jsp"/>