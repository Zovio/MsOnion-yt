<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <title>洋桃供应链后台管理系统-角色操作相关</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>


<!-- start-->
<input type="hidden" id="idx" value="${role.idx}"/>
<div id="resourceCapacity">
    <div class="winCont">

        <div class="winRow">
            <label class="brandName"><i>*</i>角色名称：</label>
            <input id="name" class="brandTxt required" type="text" value="${role.name}">
            <p class="palce-hint hint1">*角色名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>编码：</label>
            <input id="code" class="brandTxt required" type="text" onkeyup="MS.onlyEnOrNumber(this);" onblur="MS.onlyEnOrNumber(this);"
                   value="${role.code}" ${type ne 'add' ? 'readonly="readonly"' :''} >
            <p class="palce-hint hint2">*编码必填</p>
        </div>

        <div class="winRow2" style="margin-bottom: 20px;">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark">${role.remark}</textarea>
        </div>
        <div class="row-set">
            <div class="row-set-lf">
                <label class="ms-userName">资源组设置：</label>
                <c:if test="${type ne 'view'}">
                <button class="ms-set" type="button" onclick="chooseResourceGroup(${role.idx});">设置</button>
                </c:if>
            </div>
            <div class="row-set-lr" id="resource-group-text">
                <c:forEach items="${resourceGroupList}" var="rg">
                    <span class="ms-institution" id="resource-${rg.idx}" onclick="closeResouceGroupName(this);"><i></i>${rg.name}</span>
                </c:forEach>
              <%--  <span class="ms-institution"><i></i> 广东省公司</span>--%>
            </div>
        </div>

    </div>
</div>



<script type="text/javascript">

    $(function () {
        var _type = '${type}';

        if(_type != 'view') {
            $('#resourceCapacity .required').on({
                'click': function () {
                    $(this).next().hide();
                    $(this).css("border", "1px solid #00d5b4");
                },
                'blur': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });
        }


        if(_type == 'view'){
            $('input,textarea').prop('readonly',true).on({
                'focus': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });
            $("span[id^='resource-']").each(function(index, element) {
                $(this).removeAttr("onclick");
            });
        }

    });

    /**
     *  选择资源组
     * @param idx 角色idx
     */
    function chooseResourceGroup(idx) {
        // 获取资源组idx
        var resourceGroupIdx = [];
        $("span[id^='resource-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            resourceGroupIdx.push(idxArr[1]);
        });

        var url = '/role/chooseResourceGroup/'+idx + '?resourceGroupIdx=' + resourceGroupIdx.join(',');
        var title ='选择资源组';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['605px', '665px'], //宽高
           /* offset: [        //随机坐标
                Math.random()*($(window).height()-300),
                Math.random()*($(window).width()+390)
            ],*/
            content: [url, 'no'],
            yes: function(index, layero){
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var result = iframeWin.chooseResult();
                // 回现
                var spanHtml = '',$resourceDiv = $('#resource-group-text');
                if (result){
                    var resultArr = result.split(",");
                    for (var i =0 ; i < resultArr.length ; i++){
                        var resourceArr = resultArr[i].split("|");
                        var idx = resourceArr[0], name = resourceArr[1];
                        if($('#resource-'+idx).length > 0 && !name){
                            name = $('#resource-'+idx).text();
                        }
                        spanHtml +='<span class="ms-institution" id="resource-'+idx+'" onclick="closeResouceGroupName(this)"><i></i>'+name+'</span>';
                    }
                }
                $resourceDiv.html(spanHtml);
                return false;
            }
        });
    }

    // 点击X 资源组
    function closeResouceGroupName(obj) {
        $(obj).remove();
    }

    // 提交表单
    function submitForm() {
        if (!$.trim($('#name').val())) {
            $('#resourceCapacity .hint1').show();
            $('#name').css("border", "1px solid #ff2c41");
            return false;
        }

        if (!$.trim($('#code').val())) {
            $('#resourceCapacity .hint2').show();
            $('#code').css("border", "1px solid #ff2c41");
            return false;
        }

        // 获取资源组idx
        var resourceGroupIdxArr = [];
        $("span[id^='resource-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            resourceGroupIdxArr.push(idxArr[1]);
        });
        var params = {};
        params.idx = $("#idx").val();
        params.name = $("#name").val();
        params.code = $("#code").val();
        params.remark = $("#remark").val();
        params.resourceGroupIdxs = resourceGroupIdxArr.join(",");
        parent.layer.load(2, {shade : 0.01});

        $.ajax({
            type: "POST",
            url: "/role/do-saveOrEdit",
            data: {
                idx: params.idx,
                name: params.name,
                code: params.code,
                remark: params.remark,
                resourceGroupIdxs : params.resourceGroupIdxs
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                parent.$('#rolelist').datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function (result) {
            parent.layer.msg(result.msg, {icon: 2});
        });
    }

</script>

</body>
</html>