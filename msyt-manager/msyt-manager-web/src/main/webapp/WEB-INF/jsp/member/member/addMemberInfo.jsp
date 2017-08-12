
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<head>

    <title>洋桃供应链后台管理系统-新增个人信息</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<link rel="stylesheet" href="${static$domain}/css/member/member.css?_v=${css$version}" />
<!--新增成员信息 start-->
<div id="addInformation" style="overflow-y: scroll;overflow-x: hidden;width: 900px;height: 400px;display: block;background: #fff;">
    <div class="ms-container">
        <div class="row">
            <label class="ms-userName">成员名称：</label>
            <input class="ms-input" id="name" type="text" onblur="checkName()" />
            <span class="hints" id="hint1"></span>
        </div>
        <div class="row">
            <label class="ms-userName">全称：</label>
            <input class="ms-input" id="allName" type="text" onblur="checkAllName()" />
            <span class="hints" id="hint2"></span>
        </div>

        <div class="row">
            <label class="ms-userName">性别：</label>
            <p class="checkbox-span">
            <p class="gender-man">
                <span class="sex-man sex-manCur"></span>
                <label class="sex">男</label>
            </p>
            <p class="gender-woman">
                <span class="sex-woman"></span>
                <label class="sex">女</label>
            </p>
            </p>
        </div>
        <div class="row">
            <label class="ms-userName">密码：</label>
            <input class="ms-input" id="password" type="password" onblur="checkPassword()" />
            <span class="hints" id="hint4"></span>
            <p id="passHint" style="width: 380px;height: 24px;line-height: 24px;font-size: 12px;color: #00d5b4;position: absolute;top: 35px;padding-left: 50px;text-align: center;">建议使用字母，数字和符号两种以上组合，6-20位字符!</p>
        </div>
        <div class="row">
            <label class="ms-userName">确认密码：</label>
            <input class="ms-input" id="password2" type="password" onblur="checkAgainPwd()" />
            <span class="hints" id="hint5"></span>
        </div>
        <div class="row">
            <label class="ms-userName">手机号码：</label>
            <input class="ms-input" id="phone" type="text" onblur="checkPhone()" maxlength="11"/>
            <span class="hints" id="hint6"></span>
        </div>
        <div class="row">
            <label class="ms-userName">电话号码：</label>
            <input class="ms-input" id="telephone" type="text" onblur="checkTelephone()"  maxlength="20"/>
            <span class="hints" id="hint7"></span>
        </div>
        <div class="row">
            <label class="ms-userName">电子邮箱：</label>
            <input class="ms-input" id="email" type="text" onblur="checkEmail()" />
            <span class="hints" id="hint8"></span>
        </div>

        <div class="row">
            <label class="ms-userName">成员类型：</label>
            <p class="relative">
                <select class="ms-input" id="memberCate">
                    <option value="0">请选择</option>
                    <c:forEach items="${memberCateList}" var="memberCate">
                        <option value="${memberCate.idx}" ${member.memberCateIdx eq memberCate.idx ? "selected='selected'" : ''}>${memberCate.name}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="row">
            <label class="ms-userName">是否激活：</label>
            <p class="relative">
                <select class="ms-input" id="status">
                    <option value="1">是</option>
                    <option value="2">否</option>
                </select>
                <i class="downIcon"></i>
            </p>
        </div>
        <div class="row-bz">
            <label class="ms-userName">描述：</label>
            <textarea class="bz-textarea" id="remark" onblur="checkRemark()" placeholder="50位字符（包含中英文）"></textarea>
            <span class="hints" id="hint10"></span>
        </div>

        <div class="row-set">
            <div class="row-set-lf">
                <label class="ms-userName">角色设置：</label>
                <button class="ms-set" type="button" onclick="setRoles(${member.idx});">设置</button>
            </div>
            <div class="row-set-lr" id="role-div">
            </div>
        </div>
    </div>
</div>

<!--编辑成员信息 end-->
<script type="text/javascript">

    /* 成员列表 -->新增*/
    function addFormInfo(){
        if(!validateRegister('add')){
            parent.layer.closeAll('loading');
            return false;
        }

        // 获取资源组idx
        var roleIdxArr = [];
        $("span[id^='role-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            roleIdxArr.push(idxArr[1]);
        });

        var name = $.trim($('#name').val());
        var fullName = $.trim($('#allName').val());
        var sex = checkSex();
        var password = $.trim($('#password').val());
        var phone = $.trim($('#phone').val());
        var tel = $.trim($('#telephone').val());
        var email = $.trim($('#email').val());
        var remark = $.trim($('#remark').val());
        var memberCate = $('#memberCate').val();
        var status = $('#status').val();
        parent.layer.load(2, {shade : 0.01, time : 50000});
        $.ajax({
            type: "POST",
            url: "/member/do-save",
            data: {
                name: base64_encode(name),
                fullName: base64_encode(fullName),
                sex : sex,
                password : base64_encode(password),
                phone: base64_encode(phone),
                tel: base64_encode(tel),
                email: base64_encode(email),
                remark: base64_encode(remark),
                memberCateIdx: memberCate,
                status : status,
                roleIdxStr: base64_encode(roleIdxArr.join(","))
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('新增成功！', {time: 1000, icon:6});
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
            area: ['513px', '645px'], //宽高
            offset: [        //随机坐标
                Math.random()*($(window).height()-300),
                Math.random()*($(window).width()+300)
            ],
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
                        spanHtml +='<span class="ms-institution" id="role-'+resourceArr[0]+'" onclick="closeX(this)"><i></i>'+resourceArr[1]+'</span>';
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
