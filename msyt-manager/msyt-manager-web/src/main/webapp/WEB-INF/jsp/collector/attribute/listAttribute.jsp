<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:attribute:add">
                <input type="button" class="brandBtn" value="新增属性值" onclick="addAbility(this.value)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:attribute:addSel">
                <input type="button" class="brandBtn" value="新增属性选项" onclick="addProperty(this.value)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:attribute:up">
                <input type="button" class="brandBtn" value="修改" onclick="editProperty(this.value)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:attribute:del">
                <input type="button" class="brandBtn" value="删除" onclick="removeRole()"/>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">属性值名称：</label>
                        <input class="txt margin-r-20" type="text" id="attributeName">
                    </div>
                    <div class="screen-col">
                        <label class="name">选项框：</label>
                        <p class="state">
                            <select class="state1" id="attributeIsSelect">
                                <option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <div class="screen-col">
                        <label class="name">显示状态：</label>
                        <p class="state">
                            <select class="state1" id="attributeIsShow">
                                <option value="">请选择</option>
                                <option value="1">显示</option>
                                <option value="0">不显示</option>
                            </select>
                            <i class="downIcon"></i>
                        </p>
                    </div>
                    <span class="search" id="propertyTree-qry"><i></i>搜索</span>
                </div>
            </div>
            <div id="propertyTable" class="yt-table">
                <table id="propertyTree" class="easyui-treegrid" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            var $propertyTree = $('#propertyTree');
            $propertyTree.treegrid({
                url: "/collect/attribute/getAttributeGridByParentId",
                method: 'post',          //请求方式
                idField: 'id',           //定义标识树节点的键名字段
                treeField: 'name',       //定义树节点的字段
                fit: false,               //网格自动撑满
                fitColumns: true,        //设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
                columns: [[
                    {field: 'name', title: '属性值名称', width: '30%'},
                    {
                        field: 'isSelect', title: '选项框', width: '10%',
                        formatter: function (value, rowData, rowIndex) {
                            return value ? "是" : "否";
                        }
                    },
                    {
                        field: 'isShow', title: '显示状态', width: '10%',
                        formatter: function (value, rowData, rowIndex) {
                            return value ? "显示" : "不显示";
                        }
                    },
                    {
                        field: 'isKey', title: '是否关键属性', width: '10%',
                        formatter: function (value, rowData, rowIndex) {
                            return value ? "是" : "否";
                        }
                    }
                ]],
                onBeforeExpand:function(node) {
                    var options = $propertyTree.treegrid('options');
                    options.url = '/collect/attribute/getAttributeGridByParentId';
                    $propertyTree.treegrid('options',options);
                },
                onClickCell : function(field, row) {
                    $propertyTree.treegrid('toggle',row.id);
                }
            });
            $propertyTree.treegrid("collapseAll");

            $('#propertyTree-qry').on('click', function () {
                $propertyTree.treegrid('reload', {
                    name: $.trim($('#attributeName').val()),
                    isSelect: $('#attributeIsSelect').val(),
                    isShow: $('#attributeIsShow').val()
                });
            });

            /* 点击功能按钮的选中状态  */
            $('.yt-row .brandBtn').click(function () {
                $(this).addClass("active").siblings().removeClass("active");
            });
        })

        // 新增属性值
        function addAbility(title) {
            layer.open({
                type: 2,
                title: title,
                area: ['490px', '415px'],
                btn: ['保存', '取消'],
                content: ['/collect/attribute/edit/0', 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                }
            });
        }

        // 新增属性选项
        function addProperty(title) {
            var node = $("#propertyTree").treegrid('getSelected');
            if (node) {
                var isSelect = node.isSelect;
                if (!isSelect) {
                    layer.confirm('请选择是选项框的属性值才可新增属性选项！', {
                        title: false,
                        btn: ['确定']
                    });
                    return false;
                }

                layer.open({
                    type: 2,
                    title: title,
                    area: ['490px', '330px'],
                    btn: ['保存', '取消'],
                    content: ['/collect/attribute/sel/edit/' + node.id, 'no'],
                    yes: function (index, layero) {
                        Ms.saveLoading();
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        iframeWin.submitForm();
                        return false;
                    }
                });
            } else {
                layer.confirm('请先选择属性值哦！', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }
        }

        // 打开修改属性值的弹框
        function editProperty(title) {
            var node = $("#propertyTree").treegrid('getSelected');
            if (node) {
                layer.open({
                    type: 2,
                    title: title,
                    area: ['490px', '415px'],
                    btn: ['保存', '取消'],
                    content: ['/collect/attribute/edit/' + node.id, 'no'],
                    yes: function (index, layero) {
                        Ms.saveLoading();
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        iframeWin.submitForm();
                        return false;
                    }
                });
            } else {
                layer.confirm('请先选择属性值哦！', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }
        }

        function removeRole() {
            var node = $("#propertyTree").treegrid('getSelected');
            if (!node) {
                layer.confirm('请选中要删除的数据！', {
                    title: false,
                    btn: ['确定']
                });
            } else {
                layer.confirm('您确定要删除吗？', {
                    title: false,
                    shadeClose: true,
                    btn: ['确定', '取消']
                }, function (index) {
                    $.ajax({
                        type: "post",
                        url: "/collect/attribute/del",
                        data: {
                            idxStr: node.id
                        },
                        dataType: 'json',
                        success: function (d) {
                            if (d.status == 200) {
                                layer.msg('操作成功！', {icon: 1});
                            } else {
                                layer.msg(d.msg, {icon: 2});
                            }
                            $('#propertyTree').treegrid('reload', {});
                        },
                        error: function (d) {
                            layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            }
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>