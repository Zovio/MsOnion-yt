<%@ page import="cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:edit:add">
                <button type="button" id="editEditor" class="brandBtn">新增</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:edit:del">
                <button type="button" id="deleteEditor" class="brandBtn">删除</button>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">名称：</label>
                        <input class="txt" type="text" id="editorName">
                    </div>
                    <div class="screen-col">
                        <label class="name">状态：</label>
                        <p class="state">
                            <select class="state1" id="editorStatus">
                                <option value="">请选择</option>
                                <c:forEach items="${statusMap}" var="p">
                                    <option value="${p.key}">${p.value}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <span class="search" id="editorList-qry"><i></i>搜索</span>
                </div>
            </div>
            <div class="yt-table">
                <table id="editorList" style="width: auto;height:464px;"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#editorList').datagrid({
                url: '/collector/editor/grid',
                idField: 'id',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: 'true',
                method: 'post',
                columns: [[
                    {title: '', field: '', width: '5%', align: 'center', checkbox: 'true'},
                    {
                        field: 'control',
                        align: 'center',
                        width: '8%',
                        title: '操作',
                        formatter: function (value, rowData, rowIndex) {
                            var deletedStatus = "<%=MsOnionTableRecordStatus.DELETED.getValue()%>";
                            var isDeleted = (rowData.status == deletedStatus);

                            var array = [];
                            <shiro:hasPermission name="collect:edit:edit">
                            if (isDeleted) {
                                array.push('<a class="handle editGray" href="javascript:void(0)" title="编辑"></a>');
                            } else {
                                array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editEditor(' + rowData.idx + ');"></a>');
                            }
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {title: 'ID', field: 'idx', width: '10%', align: 'center', sortable: true},
                    {title: '名称', field: 'name', width: '30%', align: 'center', sortable: true},
                    {
                        title: '头像',
                        field: 'headImgSmallDynamicS',
                        width: '15%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return value ? '<img src="' + value + '" />' : '';
                        }
                    },
                    {
                        title: '状态', field: 'status', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            return "<span class='state-" + value + "'>" + ${statusMapJson}[value] + "</span>";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    Ms.clearGridSelections('editorList');
                }
            });

            $('#editorList-qry').on('click', function () {
                $('#editorList').datagrid('load', {
                    name: $.trim($('#editorName').val()),
                    status: $('#editorStatus').val()
                });
            });
        });

        $(function () {
            /* 更改表格的高度 */
            $('.datagrid-header').height(40);
            $('.datagrid-htable').height(40);
            $('.datagrid-header-row').height(40);

            /* 点击功能按钮的选中状态  */
            $('.yt-row .brandBtn').click(function () {
                $(this).addClass("active").siblings().removeClass("active");
            });

            /* 输入框获取焦点时的样式变化 */
            $(".winCont .brandTxt").focus(function () {
                $(this).css("border", "1px solid #00d5b4");
            });
            $(".winCont .brandTxt").blur(function () {
                $(this).css("border", "1px solid #dbdcde");
            });

            // 新增采编
            $('#editEditor').on('click', function () {
                editEditor();
            });

            // 删除采编
            $('#deleteEditor').click(function () {
                if (!$('#editorList').datagrid('getSelected')) {
                    layer.confirm('请选中要删除的数据！', {
                        title: false,
                        btn: ['确定']
                    });
                } else {
                    layer.confirm('您确定要删除吗？', {
                        title: false,
                        btn: ['确定', '取消'],
                        btn2: function () {
                            Ms.clearGridSelections('editorList');
                        },
                        cancel: function () {
                            Ms.clearGridSelections('editorList');
                        }
                    }, function (index) {
                        var ids = [];
                        var allCheckedRows = $('#editorList').datagrid('getChecked');
                        if (allCheckedRows.length > 0) {
                            var checkedRowLength = allCheckedRows.length;
                            for (var i = 0; i < checkedRowLength; i++) {
                                var checkedRow = allCheckedRows[i];
                                ids.push(checkedRow.idx);
                            }
                        }

                        $.ajax({
                            type: "post",
                            url: "/collector/editor/delete",
                            data: {
                                ids: ids
                            },
                            dataType: 'json',
                            success: function (d) {
                                if (d.status == 200) {
                                    layer.msg('操作成功！', {icon: 1});
                                    $('#editorList').datagrid('reload', {});
                                } else {
                                    layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                                }
                            },
                            error: function (d) {
                                layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                            }
                        });
                    });
                }
            });
        });

        function editEditor(idx) {
            var title = "修改采编";
            if (idx == undefined) {
                title = "新增采编";
                idx = 0;
            }

            layer.open({
                type: 2,
                title: title,
                area: ['490px', '472px'],
                btn: ['保存', '取消'],
                content: ['/collector/editor/edit/' + idx, 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                },
                btn2: function () {
                    Ms.clearGridSelections('editorList');
                },
                cancel: function () {
                    Ms.clearGridSelections('editorList');
                }
            });
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>