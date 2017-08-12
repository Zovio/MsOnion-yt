<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-成员列表</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>
<section class="main">
    <div class="table-container">
        <div class="main-content">
            <shiro:hasPermission name="member:add">
                <div class="yt-row">
                    <button type="button" class="brandBtn active" onclick="addInformation()">新增</button>
                </div>
            </shiro:hasPermission>
            <div class="main-content">
                <div class="screen-cont">
                    <span class="screen-show"></span>
                    <span class="screen-hide"></span>
                    <span id="memberlist-qry1" class="search" style="position: absolute;top: 0px;right: 80px;"><i></i>搜索</span>
                    <div class="screen multi-row">
                        <div class="screen-row">
                            <div class="screen-col">
                                <label class="name">成员名称：</label>
                                <input class="txt" type="text" id="m-name"/>
                            </div>
                            <div class="screen-col">
                                <label class="name">手机号码：</label>
                                <input class="txt" type="text" id="m-telphone">
                            </div>
                            <div class="screen-col">
                                <label class="name">电子邮箱：</label>
                                <input class="txt" type="text" id="m-email">
                            </div>
                            <div class="screen-col">
                                <label class="name">成员编码：</label>
                                <input class="txt" type="text" id="m-userCode">
                            </div>
                            <div class="screen-col">
                                <label class="name">全称：</label>
                                <input class="txt" type="text" id="m-userFullName">
                            </div>
                            <div class="screen-col">
                                <label class="name">状态：</label>
                                <p class="state">
                                    <select class="select-pl" id="m-status">
                                        <option value="">请选择</option>
                                        <c:forEach items="${statusMap}" var="m">
                                            <option value="${m.key}">${m.value}</option>
                                        </c:forEach>
                                    </select>
                                    <i class="downIcon"></i>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="yt-table">
                    <table id="memberlist" class="easyui-datagrid" style="width:98%;height: 454px;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    $(function () {
        $('#memberlist').datagrid({
            url: '/member/paging',
            method: 'post',
            fit: true,
            nowrap: true,
            fitColumns: true,
            pagination: true,
            rownumbers: true,
            pageSize: 10,
            pageList: [10, 20, 30, 40, 50],
            idField: 'id',
            columns: [[/*{
             field : 'id',
             checkbox:true
             },*/
                {
                    field: 'control',
                    align: 'center',
                    width: "15%",
                    title: '操作',
                    formatter: function (value, rowData, rowIndex) {
                        var array = [];
                        array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForMember(' + rowData.idx + ')"></a>');
                        if (rowData.status == '1') {
                            <shiro:hasPermission name="member:edit">
                            array.push('<a class="handle forbidden" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'member/updateStatus/' + rowData.idx + '/2\', null, \'memberlist\');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="member:disabled">
                            array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editForMember(' + rowData.idx + ');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="member:editpwd">
                            array.push('<a class="handle encrypt" href="javascript:void(0)" title="修改密码" onclick="editPassWord(' + rowData.idx + ')"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="member:del">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'member/updateStatus/' + rowData.idx + '/0\', null, \'memberlist\');"></a>');
                            </shiro:hasPermission>
                        }
                        if (rowData.status == '2') {
                            <shiro:hasPermission name="member:recover">
                            array.push('<a class="handle putaway" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'member/updateStatus/' + rowData.idx + '/1\', null, \'memberlist\');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="member:del">
                            array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'member/updateStatus/' + rowData.idx + '/0\', null, \'memberlist\');"></a>');
                            </shiro:hasPermission>
                        }
                        return array.join('');
                    }
                },
                {
                    field: 'idxCodeS',
                    align: 'center',
                    width: 30,
                    sortable: true,
                    title: 'ID'
                },
                {
                    field: 'name',
                    align: 'center',
                    width: 50,
                    sortable: true,
                    title: '成员名称'
                }, {
                    field: 'sex',
                    align: 'center',
                    sortable: true,
                    width: 20,
                    title: '性别',
                    formatter: function (value, rowData, rowIndex) {
                        return ${sexMapJson}[value];
                    }
                }, {
                    field: 'fullName',
                    align: 'center',
                    sortable: true,
                    width: 50,
                    title: '全称'
                }, {
                    field: 'code',
                    align: 'center',
                    sortable: true,
                    width: 50,
                    title: '成员编码'
                }, {
                    field: 'phone',
                    align: 'center',
                    sortable: true,
                    width: 40,
                    title: '手机号码'
                }, {
                    field: 'email',
                    align: 'center',
                    sortable: true,
                    width: 60,
                    title: '电子邮箱'
                }, {
                    field: 'lastLoginTimeEnYyyyMMddHHmmss',
                    align: 'center',
                    sortable: false,
                    width: 55,
                    title: '最后登录时间'
                }, {
                    field: 'status',
                    align: 'center',
                    sortable: true,
                    width: 25,
                    title: '状态',
                    formatter: function (value, rowData, rowIndex) {
                        return '<span class="state-' + value + '">' + ${statusMapJson}[value] + '</span>';
                    }
                }]],
            onLoadSuccess: function (data) {
                $('#memberlist').datagrid('clearSelections');
            }
        });

        $('[id^=memberlist-qry]').on('click', function () {
            $('#memberlist').datagrid('load', {
                name: $.trim($('#m-name').val()),
                telphone: $.trim($('#m-telphone').val()),
                userCode: $.trim($('#m-userCode').val()),
                email: $.trim($('#m-email').val()),
                userFullName: $.trim($('#m-userFullName').val()),
                status: $('#m-status').val()
            });
        });
    });

    /**
     *  编辑成员 赋权
     * @param idx
     */
    function editForMember(idx) {
        var url = '/member/edit/' + idx;
        var title = '修改个人信息';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            area: ['700px', '512px'], //宽高
            content: [url, 'no'],
            yes: function (index, layero) {
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.editFormInfo();
                return false;
            }
        });
    }

    /**
     *  编辑成员 赋权
     * @param idx
     */
    function showForMember(idx) {
        var url = '/member/edit/' + idx + '?_type=view';
        var title = '查看个人信息';
        //页面层
        layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            area: ['700px', '512px'], //宽高
            content: [url, 'no']
        });
    }

    /* 修改密码 */
    function editPassWord(idx) {
        layer.open({
            type: 2,
            title: '修改密码',
            area: ['650px', '380px'],
            btn: ['保存', '取消'],
            shadeClose: true,
            content: ['/member/edit/memberPwd/' + idx, 'no'],
            yes: function (index, layero) {
                layer.load(2, {shade: 0.01, time: 5000});
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.editMemmberPassword();
                return false;
            }
        });
    };

    /* 新增成员信息 */
    function addInformation() {
        layer.open({
            type: 2,
            title: '新增成员信息',
            area: ['900px', '513px'],
            btn: ['保存', '取消'],
            shadeClose: true,
            content: ['/member/toAddMember', 'no'],
            yes: function (index, layero) {
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.addFormInfo();
                return false;
            }
        });
    }
</script>
</body>
</html>

