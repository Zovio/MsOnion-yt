
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<head>

    <title>洋桃供应链后台管理系统-修改个人信息</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/member/member.css?_v=${css$version}" />
<section class="main" style="background-color: #fff">
    <input id="idx" type="hidden" value="${member.idx}">
    <!--编辑成员信息 start-->
    <div id="personalInformation" style="overflow-y: scroll;overflow-x: hidden;height: 400px;">
        <div class="ms-container">
            <div class="row">
                <label class="ms-userName">成员名称：</label>
                <input class="ms-input" id="name" type="text"  disabled="disabled" value="${member.name}"/>
                <span class="hints" id="hint1"></span>
            </div>
            <div class="row">
                <label class="ms-userName">全称：</label>
                <input class="ms-input" id="allName" type="text" onblur="checkAllName()" value="${member.fullName}"/>
                <span class="hints" id="hint2"></span>
            </div>
            <div class="row">
                <label class="ms-userName">成员编码：</label>
                <input class="ms-input-bm" id="coding" type="text" value="${member.code}" onblur="checkCode()"/>
                <span class="hints" id="hint3"></span>
            </div>
            <div class="row">
                <label class="ms-userName">性别：</label>
                <p class="checkbox-span reg-container">
                    <p class="gender-man">
                        <span class="sex-man ${member.sex eq 1 ? 'sex-manCur' : ''}"></span>
                        <label class="sex">男</label>
                    </p>
                    <p class="gender-woman">
                        <span class="sex-woman ${member.sex eq 2 ? 'sex-womanCur' : ''}"></span>
                        <label class="sex">女</label>
                    </p>
                </p>
            </div>
            <div class="row">
                <label class="ms-userName">手机号码：</label>
                <input class="ms-input" id="phone" type="text" onblur="checkPhone()" maxlength="11" value="${member.phone}"/>
                <span class="hints" id="hint6"></span>
            </div>
            <div class="row">
                <label class="ms-userName">电话号码：</label>
                <input class="ms-input" id="telephone" type="text" onblur="checkTelephone()" value="${member.tel}"/>
                <span class="hints" id="hint7"></span>
            </div>
            <div class="row">
                <label class="ms-userName">电子邮箱：</label>
                <input class="ms-input" id="email" type="text" onblur="checkEmail()" value="${member.email}"/>
                <span class="hints" id="hint8"></span>
            </div>


            <div class="row">
                <label class="ms-userName">最后登录时间：</label>
                <span class="ms-span">${member.lastLoginTimeEnYyyyMMddHHmmss}</span>
            </div>

            <div class="row">
                <label class="ms-userName">成员类型：</label>
                <p class="relative">
                    <select  class="ms-input" id="memberCate" >
                        <option value="0">请选择</option>
                        <c:forEach items="${memberCateList}" var="memberCate">
                            <option value="${memberCate.idx}" ${member.memberCateIdx eq memberCate.idx ? "selected='selected'" : ''}>${memberCate.name}</option>
                        </c:forEach>
                    </select>
                    <i class="downIcon"></i>
                </p>
            </div>

            <div class="row-bz">
                <label class="ms-userName">备注：</label>
                <textarea class="bz-textarea" id="remark">${member.remark}</textarea>
                <span class="hints" id="hint10"></span>
            </div>


        <%-- <div class="row-set">
                <div class="row-set-lf">
                    <label class="ms-userName">所属机构设置：</label>
                    <button class="ms-set" type="button" onclick="setOrganize(${member.idx});">设置</button>
                </div>
                <div class="row-set-lr" id="organize-div">
                    <span class="ms-institution" onclick="closeX(this);"><i></i> 洋葱集团</span>
                </div>
            </div>
--%>
            <div class="row-set">
                <div class="row-set-lf">
                    <label class="ms-userName">角色设置：</label>
                    <c:if test="${_type ne 'view'}">
                     <button class="ms-set" type="button" onclick="setRoles(${member.idx});">设置</button>
                    </c:if>
                </div>
                <div class="row-set-lr" id="role-div">
                    <c:forEach items="${roleList}" var="role">
                        <span class="ms-institution" onclick="closeX(this);" id="role-${role.idx}"><i></i>${role.name}</span>
                    </c:forEach>

<%--
                    <span class="ms-institution" onclick="closeX(this);"><i></i>超级管理员111</span>--%>

                </div>
            </div>
        </div>

    </div>
</section>
<!--编辑成员信息 end-->
<script type="text/javascript">
    $(function() {
        var _type = '${_type}';
        if(_type == 'view'){
            $('input,select,textarea').prop('readonly',true).on({
                'focus': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });

            $('select').prop('disabled',true).parent("p").parent("div").removeClass().addClass("row2");
            $("span[id^='role-']").each(function(index, element) {
                $(this).removeAttr("onclick");
            });

            if(!$(".sex-man").hasClass("sex-manCur")){
                $(".sex-man").parent('p').empty();
            }
            if(!$(".sex-woman").hasClass("sex-womanCur")){
                $(".sex-woman").parent('p').empty();
            }

            //

        }
    });

    /* 成员列表 -->编辑个人信息 */
    function editFormInfo(){
        if(!validateEdit()){
            return false;
        }

        // 获取资源组idx
        var roleIdxArr = [];
        $("span[id^='role-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            roleIdxArr.push(idxArr[1]);
        });

        parent.layer.load(2, {shade : 0.01, time : 10000});
        var idx = $('#idx').val();
        var fullName = $.trim($('#allName').val());
        var sex = checkSex();
        var code = $.trim($('#coding').val());
        var phone = $.trim($('#phone').val());
        var tel = $.trim($('#telephone').val());
        var email = $.trim($('#email').val());
        var remark = $.trim($('#remark').val());
        var memberCate = $('#memberCate').val();
        $.ajax({
            type: "POST",
            url: "/member/do-edit",
            data: {
                idx: idx,
                fullName: base64_encode(fullName),
                sex : sex,
                code: base64_encode(code),
                phone: base64_encode(phone),
                tel: base64_encode(tel),
                email: base64_encode(email),
                remark: base64_encode(remark),
                memberCateIdx: memberCate,
                roleIdxStr: base64_encode(roleIdxArr.join(","))
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('修改成功！', {time: 1000, icon:6});
                parent.layer.close(index);
                parent.$('#memberlist').datagrid('reload',{});
            } else {
                parent.layer.msg(data.msg, {icon:2});
            }
        }).fail(function (result) {
            parent.layer.msg(MS.ERROR_MSG, {icon: 2});
        });

    }


    // 点击X 资源组
    function closeX(obj) {
        $(obj).remove();
    }

    // 设置角色
    function setRoles(idx) {
        // 获取资源组idx
        var roleIdxArr = [];
        $("span[id^='role-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            roleIdxArr.push(idxArr[1]);
        });
        var url = '/member/chooseRole/'+idx + '?roleIdx=' + roleIdxArr.join(',');
        var title ='选择角色';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['515px', '675px'], //宽高
          /*  offset: [        //随机坐标
                Math.random()*($(window).height()-300),
                Math.random()*($(window).width()+300)
            ],*/
            content: [url,'no'],
            yes: function(index, layero){
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var result = iframeWin.chooseRoleResult();
                // 回现
                var spanHtml = '',$resourceDiv = $('#role-div');
                if (result){
                    var resultArr = result.split(",");
                    for (var i =0 ; i < resultArr.length ; i++){
                        var resourceArr = resultArr[i].split("|");
                        var idx = resourceArr[0], name = resourceArr[1];
                        if($('#role-'+idx).length > 0 && !name){
                            name = $('#role-'+idx).text();
                        }
                        spanHtml +='<span class="ms-institution" id="role-'+idx+'" onclick="closeX(this)"><i></i>'+name+'</span>';
                    }
                }
                $resourceDiv.html(spanHtml);
                return false;
            }
        });
    }
</script>
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>


</body>
</html>
