<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <shiro:hasPermission name="collect:categy:add">
                <input type="button" class="brandBtn" value="新增类目" onclick="addCategory(this.value)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="collect:categy:edit">
                <input type="button" class="brandBtn" value="修改类目" onclick="editCategory(this.value)"/>
            </shiro:hasPermission>
        </div>
        <div class="main-content">
            <div class="screen">
                <div class="screen-row">
                    <div class="screen-col">
                        <label class="name">类目名称：</label>
                        <input class="txt" type="text" id="categoryName">
                    </div>
                    <div class="screen-col">
                        <label class="name">类目编码：</label>
                        <input class="txt" type="text" id="categoryCode">
                    </div>
                    <span class="search" id="categoryTableList-qry"><i></i>搜索</span>
                </div>
            </div>
            <div id="categoryTable" class="yt-table">
                <table id="categoryTree" class="easyui-treegrid" style="width:98%;height:464px"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            // 点击功能按钮的选中状态
            $('.yt-row .brandBtn').click(function () {
                $(this).addClass("active").siblings().removeClass("active");
            });

            // 新增类目弹框里的填写内容提示
            $('#categoryCapacity .required').on('keyup', function () {
                $(this).next().hide();
            });
            $('#categoryCapacity #TypeCoding').on('keyup', function () {
                $('#TypeCoding').css("border", "1px solid #dbdcde");
            });
            $('#categoryCapacity #categoryName').on('keyup', function () {
                $('#categoryName').css("border", "1px solid #dbdcde");
            });

            $('#categoryTree').treegrid({
                url: "/collect/categy/gridByParentId",
                method: 'post',          //请求方式
                idField: 'id',           //定义标识树节点的键名字段
                treeField: 'name',       //定义树节点的字段
                fit: false,               //网格自动撑满
                fitColumns: true,        //设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
                columns: [[
                    {field: 'name', title: '类目名称', width: '30%'},
                    {field: 'code', title: '类目编码', width: '20%'}
                ]],
                onClickCell:function(field,row){
                    $('#categoryTree').treegrid('toggle',row.id);
                },
                onBeforeExpand:function(row) {
                    var options = $('#categoryTree').treegrid('options');
                    options.url = '/collect/categy/gridByParentId';
                    $('#categoryTree').treegrid('options',options);
                }
            });

        $('#categoryTableList-qry').on('click', function () {
            var options = $('#categoryTree').treegrid('options');
            options.url = '/collect/categy/grid';
            $('#categoryTree').treegrid('options',options);
            $('#categoryTree').treegrid('reload', {
                code: $.trim($('#categoryCode').val()),
                name: $.trim($('#categoryName').val())
            });
        });
        });

        function addCategory(title) {
            layer.open({
                type: 2,
                title: title,
                area: ['490px', '550px'],
                btn: ['保存', '取消'],
                content: ['/collect/categy/edit/0', 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                }
            });
        }

        function isChecked() {
            var checkedCel = $('#categoryTree').treegrid('getChecked');
            if (null == checkedCel || checkedCel.length == 0 || checkedCel.length > 1) {
                return false;
            }
            return true;
        }

        function getChecked() {
            var checkedCel = $('#categoryTree').treegrid('getChecked');
            return checkedCel;
        }

        function editCategory(title) {
            if (!isChecked()) {
                layer.confirm('请选择一条类目', {
                    title: false,
                    btn: ['确定']
                });
                return false;
            }

            var checkedCel = getChecked();
            var id = checkedCel[0].id;

            layer.open({
                type: 2,
                title: '修改类目',
                area: ['490px', '560px'],
                btn: ['保存', '取消'],
                content: ['/collect/categy/edit/' + id, 'no'],
                yes: function (index, layero) {
                    Ms.saveLoading();
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.submitForm();
                    return false;
                }
            });
        }
    </script>
</section>

<jsp:include page="../../footer.jsp"/>