<%@ page import="cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:br:add">
                <button type="button" id="addBrand" class="brandBtn">新增</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:br:del">
                <button type="button" id="deleteBrand" class="brandBtn">删除</button>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">品牌名称：</label>
                        <input class="txt" type="text" id="brandName">
                    </div>
                    <div class="screen-col">
                        <label class="name">状态：</label>
                        <p class="state">
                            <select class="state1" id="status">
                                <option value="">请选择</option>
                                <c:forEach items="${statusMap}" var="p">
                                    <option value="${p.key}">${p.value}</option>
                                </c:forEach>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <span class="search" id="brandTableList-qry"><i></i>搜索</span>
                </div>
            </div>
            <div class="yt-table">
                <table id="brandTableList" style="width: 96%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#brandTableList').datagrid({
                url: '/collect/br/grid',
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
                            <shiro:hasPermission name="collect:br:edit">
                                if (isDeleted) {
                                    array.push('<a class="handle editGray" href="javascript:void(0)" title="编辑"></a>');
                                } else {
                                    array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editBrand(' + rowData.idx + ');"></a>');
                                }
                            </shiro:hasPermission>
                            <%--<shiro:hasPermission name="collect:br:detail">
                            array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="detailBrand(' + rowData.idx + ');"></a>');
                            </shiro:hasPermission>--%>
                            return array.join('');
                        }
                    },
                    {title: 'ID', field: 'idx', width: '10%', align: 'center', sortable: true},
                    {title: '品牌编码', field: 'code', width: '15%', align: 'center', sortable: true},
                    {title: '品牌名称', field: 'brandName', width: '20%', align: 'center', sortable: true},
                    {title: '品牌所属国', field: 'state', width: '15%', align: 'center', sortable: true},
                    {
                        title: '品牌LOGO',
                        field: 'logoSmallDynamicS',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return value ? '<img src="' + value + '" />' : '';
                        }
                    },
                    {
                        title: '状态',
                        field: 'status',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return "<span class='state-" + value + "'>" + ${statusMapJson}[value] + "</span>";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    Ms.clearGridSelections('brandTableList');
                }
            });

            $('#brandTableList-qry').on('click', function () {
                $('#brandTableList').datagrid('load', {
                    brandName: $.trim($('#brandName').val()),
                    status: $('#status').val()
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

            // 新增品牌
            $('#addBrand').on('click', function () {
                editBrand();
            });

            // 删除品牌
            $('#deleteBrand').click(function () {
                if (!$('#brandTableList').datagrid('getSelected')) {
                    layer.confirm('请选中要删除的数据！', {
                        title: false,
                        btn: ['确定']
                    });
                } else {
                    layer.confirm('您确定要删除吗？', {
                        title: false,
                        btn: ['确定', '关闭'],
                        btn2: function () {
                            Ms.clearGridSelections('brandTableList');
                        },
                        cancel: function () {
                            Ms.clearGridSelections('brandTableList');
                        }
                    }, function (index) {
                        var ids = [];
                        var allCheckedRows = $('#brandTableList').datagrid('getChecked');
                        if (allCheckedRows.length > 0) {
                            var checkedRowLength = allCheckedRows.length;
                            for (var i = 0; i < checkedRowLength; i++) {
                                var checkedRow = allCheckedRows[i];
                                ids.push(checkedRow.idx);
                            }
                        }

                        $.ajax({
                            type: "post",
                            url: "/collect/br/delete",
                            data: {
                                ids: ids
                            },
                            dataType: 'json',
                            success: function (d) {
                                if (d.status == 200) {
                                    layer.msg('操作成功！', {icon: 1});
                                } else {
                                    layer.msg(d.msg, {icon: 2});
                                }
                                $('#brandTableList').datagrid('reload', {});
                            },
                            error: function (d) {
                                layer.msg(d.msg, {icon: 2});
                            }
                        });
                    });
                }
            });
        });

        // 修改品牌
        function editBrand(idx) {
            var title = "修改品牌";
            if (idx == undefined) {
                title = "新增品牌";
                idx = 0;
            }

            layer.open({
                type: 2,
                title: title,
                area: ['490px', '580px'],
                btn: ['保存', '取消'],
                content: ['/collect/br/edit/' + idx, 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                },
                btn2: function () {
                    Ms.clearGridSelections('brandTableList');
                },
                cancel: function () {
                    Ms.clearGridSelections('brandTableList');
                }
            });
        }

        // 查看品牌
        function detailBrand(idx) {
            layer.open({
                type: 2,
                title: "查看品牌",
                area: ['485px', '573px'],
                btn: ['关闭'],
                content: ['/collect/br/detail/' + idx, 'no']
            });
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>