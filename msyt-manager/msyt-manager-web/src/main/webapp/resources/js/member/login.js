/**   
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.   
 *   
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。  
 *   
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1 
 *
 * 洋桃商城：http://www.yunyangtao.com
 * 
 * @Description: 杨桃后台管理系统 js
 * @Company: http://www.yunyangtao.com
 * @author lemon 
 * @e-mail address 2590586729@qq.com 
 * @date 2017年3月20日
 * @version V1.0
 * 
 */

/* 点击登录输入框的x */
$(function(){		
	/* 点击用户名框框内的X情况里面的内容 */	
	$(".user-close").click(function(){
		$(".userName").attr("value","");
	});
	
	$(".pass-close").click(function(){
		$(".passWord").attr("value","");
	});


    $(".login-row .checkbox, .avoid").click(function(){
        if($(".login-row .checkbox").hasClass("cur")){
            $(".login-row .checkbox").removeClass("cur");
        }else{
            $(".login-row .checkbox").addClass("cur");
        }
    });

    // 登录页面用户相关体验
    $('#userName,#passWord,#verification').on('blur',function(){
        hideLoginInfo();
    });
	


	/* 点击X清空用户名 */
	$(".user-close").click(function(){
		$("#userName").val("");
		$(".user-close").hide();
	});
	/* 点击X清空用密码 */
	$(".pass-close").click(function(){
		$("#passWord").val("");
		$(".pass-close").hide();
	});
	/* 注册重置 */
	$("#regidterReset").click(function(){
		$(".reg-container .reg-input").val("");
		$(".reg-container .bz-textarea").val("");
		$(".reg-container .yz-input").val("");
	});
	
	/* 找回密码重置 */
	$("#findReset").click(function(){
		$(".reg-container .reg-input").val("");
		$(".reg-container .yz-input").val("");
	});


    /* 注册选择性别 */
    $(".gender-man").click(function(){
        $(".sex-man").addClass("sex-manCur");
        $(".sex-woman").removeClass("sex-womanCur");
    });
    $(".gender-woman").click(function(){
        $(".sex-woman").addClass("sex-womanCur");
        $(".sex-man").removeClass("sex-manCur");
    });


    /* 点击登录事件 */
    $('#login-btn').on("click", doLogin);


    /* 点击注册事件 */
    $('#register-btn').on("click",submitForm);
	
});


/**
 *  登录错误提示语显示
 */
function showLoginInfo(txt) {
    $(".hint").html(txt);
    $(".hint-d").show();
}

/**
 *  登录错误提示语隐藏
 */
function hideLoginInfo() {
    $(".hint").html("");
    $(".hint-d").hide();
}


function doLogin() {
    if($('#login-btn').hasClass("login-btn")){
        return false;
    }
	var userName = $.trim($('#userName').val());
	if(!userName){
        showLoginInfo("账户不能为空");
        return false;
    }
	var passWord = $.trim($('#passWord').val());
    if(!passWord){
        showLoginInfo("密码不能为空");
        return false;
    }else if(passWord.length < 6 || passWord.length > 20){
        showLoginInfo("密码长度有误，请输入6-20位长度的密码");
        return false;
    }
    // var vCode = $.trim($('#verification').val());
    // if(!vCode){
    //     showLoginInfo("验证码不能为空");
    //     return false;
    // }
    var rememberme = $('#rememberme').hasClass("cur") ? "1" : "0";
	var param={};
	param.username =base64_encode(userName);
	param.password = base64_encode(passWord);
	param.vcode = base64_encode(_captcha_img_content.pictureCode("getCodeValue"));
    param.accessCode = _captcha_img_content.pictureCode("getAccessCodeValue");
	param.rememberme = base64_encode(rememberme);



    hideLoginInfo();
    $('#login-btn').html("登录中...").addClass("login-btn").removeClass("login-btn-active");
    $.ajax({
        type : "POST",
        url : "/sys/do-login",
        cache : false,
        data :param
    }).done(function (result) {
        if(parseInt(Ms.SUC_CODE) == parseInt(result.status)){
            window.location.href="/";  // /sys/index
        }else{
            showLoginInfo(result.msg);
            // 重置验证码
            _captcha_img_content.pictureCode("refresh");
            $("#verification").val("");
            $('#login-btn').html("登录").removeClass("login-btn").addClass("login-btn-active");
        }
    }).fail(function(result) {
        showLoginInfo(Ms.ERROR_MSG);
        // 重置验证码
        _captcha_img_content.pictureCode("refresh");
        $("#verification").val("");
        $('#login-btn').html("登录").removeClass("login-btn").addClass("login-btn-active");
    });


}


/* 验证密码的合法性  */
var str1=new RegExp(/\d/);
var str2=new RegExp(/[A-Za-z]/);
var str3=new RegExp(/[~`!@#\$%\^&\-=+\\|'";:/\.,\*\(\)_]/);
function checkRegular(str){
    var count=0;
    if(str1.exec(str)){
        count+=1;
    }
    if(str2.exec(str)){
        count+=1;
    }
    if(str3.exec(str)){
        count+=1;
    }
    return count>=2;
}


/* 验证登录页面的用户名是否为空  */	
function checkUserName(){
	var userName = $(".userName").val();
	if($.trim(userName)){
        hideLoginInfo();
	}else{
	    showLoginInfo("账号不能为空");
    }
}
/* 验证登录页面的密码是否为空  */	
function checkPassWord(){
	var passWord = $(".passWord").val();
	if(!$.trim(passWord)){
        showLoginInfo('密码不能为空');
	}else if($.trim(passWord).toString().length < 6 || $.trim(passWord).toString().length > 20){
        showLoginInfo('密码长度有误');
	}else{
        hideLoginInfo();
    }
}

/* 验证验证码的合法性  */
function checkVeryCode(){
    var strVerification = $.trim($("#verification").val());
    if(!strVerification){
        showLoginInfo('验证码不能为空');
    }else if(strVerification.toString().length != 5){
        showLoginInfo('验证码长度有误');
    }else{
        hideLoginInfo();
    }
}

/* 注册验证 start */

/* 验证用户名的合法性 */
function checkName(){
    var name = $.trim($("#name").val());
    if(name == "" || name == undefined || name == null){
    	$("#hint1").html(" * 成员名称不能为空！");
    	$("#name").addClass("border-red").removeClass("border-gray");
        return false;
    }else if(name != null || name != "" || name != undefined){
    	if(Ms.strLength(name) < 4 || Ms.strLength(name) > 20){
    		$("#hint1").html(" * 成员名称长度为4-20字符，请重新输入！");
    		$("#name").addClass("border-red").removeClass("border-gray");
            return false;
    	}else{
            var info=checkUnique(1,name);
            if(info){
                $("#hint1").html(" * "+info);
                $("#name").addClass("border-red").removeClass("border-gray");
                return false;
            }
            $("#hint1").html("");
            $("#name").addClass("border-gray").removeClass("border-red");
            return true;
    	}
    }    
}

/* 验证全称的合法性  */
function checkAllName(){
    var allName = $.trim($("#allName").val());
    if(allName == "" || allName == null || allName == undefined){
    	$("#hint2").html(" * 全称不能为空！");
    	$("#allName").addClass("border-red").removeClass("border-gray");
        return false;
    }else if(allName != null || allName != "" || allName != undefined){
    	if(Ms.strLength(allName) < 2 || Ms.strLength(allName) > 20){
    		$("#hint2").html(" * 全称长度为2-20字符，请重新输入！");
    		$("#allName").addClass("border-red").removeClass("border-gray");
            return false;
    	}else{
            $("#hint2").html("");
            $("#allName").addClass("border-gray").removeClass("border-red");
            return true;
    	}
    }    
}	

/* 验证密码的合法性  */
function checkPassword(){	
    var pwd = $.trim($("#password").val());
    var name = $.trim($("#name").val());   /*获取用户名*/
    var strEmail = $("#email").val();  /* 获取邮箱 */
    if(pwd == "" || pwd == null || pwd == undefined){
    	$("#hint4").html(" * 密码不能为空！");
    	$("#password").addClass("border-red").removeClass("border-gray");
        return false;
    }else if(pwd != null || pwd != ""){
    	if(pwd.toString().length < 6 || pwd.toString().length > 20){
    		$("#hint4").html(" * 密码长度为6-20字符，请重新输入！");
    		$("#password").addClass("border-red").removeClass("border-gray");
            return false;
    	}else{
    		$("#hint4").html("");
    		$("#password").addClass("border-gray").removeClass("border-red");
    		if(pwd.toString().length >= 6 && pwd.toString().length <= 20){
                if(checkRegular(pwd)){
                    if(pwd == name){
                        $("#hint4").html("密码不能和用户名重合！");
                        $("#password").addClass("border-red").removeClass("border-gray");
                        $("#passHint").html("密码建议使用字母，数字和符号两种及以上的组合，6-20个字符!");
                        return false;
                    }else if(pwd == strEmail){
                        $("#hint4").html("密码不能和邮箱重合！");
                        $("#password").addClass("border-red").removeClass("border-gray");
                        $("#passHint").html("密码建议使用字母，数字和符号两种及以上的组合，6-20个字符!");
                        return false;
                    }else{
                        $("#hint4").html("");
                        $("#password").addClass("border-gray").removeClass("border-red");
                        $("#passHint").html("你的密码很安全！");
                        return true;
                    }
                }else{
                    $("#hint4").html("密码必须是字母或特殊符号和数字结合。");
                    $("#password").addClass("border-red").removeClass("border-gray");
                    $("#passHint").html("密码建议使用字母，数字和符号两种及以上的组合，6-20个字符!");
                    return false;
                }
            }
    	}
    }    
}

/* 验证是否一致  */
function checkAgainPwd(){
    var pwd2 = $.trim($("#password2").val());
    var pwd = $.trim($("#password").val());
    if(pwd2 == "" || pwd2 == null || pwd2 == undefined || pwd2 != pwd){
    	$("#hint5").html(" * 密码不一致！");
    	$("#password2").addClass("border-red").removeClass("border-gray");
        return false;
    }else{
    	$("#hint5").html("");
    	$("#password2").addClass("border-gray").removeClass("border-red");
        return true;
    }
}

/* 验证手机号码的合法性  */
function checkPhone(){
    var strPhone = $.trim($("#phone").val());
    if(strPhone == "" || strPhone == null || strPhone == undefined){
    	$("#hint6").html(" * 手机号码不能为空！");
    	$("#phone").addClass("border-red").removeClass("border-gray");
    	return false;
    }else if(strPhone != null || strPhone != "" || strPhone != undefined){
    	if(Ms.checkMobileNo(strPhone) == true){
            var info=checkUnique(2,strPhone);
            if(info){
                $("#hint6").html(" * "+info);
                $("#phone").addClass("border-red").removeClass("border-gray");
                return false;
            }
    		$("#hint6").html("");
    		$("#phone").addClass("border-gray").removeClass("border-red");
            return true;
    	}else{
    		$("#hint6").html(" * 手机号码不合法");
    		$("#phone").addClass("border-red").removeClass("border-gray");
            return false;
    	}
    }    
}

/* 验证电话号码的合法性  */
function checkTelephone(){
    var strTelephone = $.trim($("#telephone").val());
    if(strTelephone){
    	if(Ms.checkTelephone(strTelephone) == true){
    		$("#hint7").html("");
    		$("#telephone").addClass("border-gray").removeClass("border-red");
            return true;
    	}else{
    		$("#hint7").html(" * 电话号码不合法");
    		$("#telephone").addClass("border-red").removeClass("border-gray");
            return false;
    	}
    }
    $("#hint7").html("");
    $("#telephone").addClass("border-gray").removeClass("border-red");
    return true;
}

/* 验证邮箱地址的合法性  */
function checkEmail(){
    var strEmail = $.trim($("#email").val());
    var pwd = $.trim($("#password").val());
    if(strEmail == ""){
    	$("#hint8").html(" * 电子邮箱地址不能为空！");
    	$("#email").addClass("border-red").removeClass("border-gray");
        return false;
    }else if(strEmail != null || strEmail != ""){
    	if(strEmail.toString().length < 6 || strEmail.toString().length > 30){
    		$("#hint8").html(" * 电子邮箱地址长度为6-30字符，请重新输入！");
    		$("#email").addClass("border-red").removeClass("border-gray");
            return false;
    	}else{
    	    if(strEmail == pwd){
                $("#hint8").html(" * 电子邮箱不能和密码重合！");
                $("#email").addClass("border-red").removeClass("border-gray");
                return false;
            }
    		if(Ms.checkMail(strEmail)){
                var info=checkUnique(3,strEmail);
                if(info){
                    $("#hint8").html(" * "+info);
                    $("#email").addClass("border-red").removeClass("border-gray");
                    return false;
                }
    			$("#hint8").html("");
    			$("#email").addClass("border-gray").removeClass("border-red");
                return true;
	    	}else{
	    		$("#hint8").html(" * 电子邮箱地址不合法");
	    		$("#email").addClass("border-red").removeClass("border-gray");
                return false;
	    	}
    	}    	
    }    
}

/* 验证验证码的合法性  */
function checkVerification(){
    var strVerification = _captcha_img_content_reg.pictureCode("getCodeValue");
    if(strVerification == "" || strVerification == null || strVerification == undefined){
    	$("#hint9").html(" * 验证码不能为空！");
    	$("#verification").addClass("border-red").removeClass("border-gray");
        return false;
    }else if(strVerification != null || strVerification != "" || strVerification != undefined){
    	if(strVerification.toString().length != 5){
    		$("#hint9").html(" * 验证码有误！");
    		$("#verification").addClass("border-red").removeClass("border-gray");
            return false;
    	}  	
    }
	$("#hint9").html("");
	$("#verification").addClass("border-gray").removeClass("border-red");
	return true;

}

/* 验证备注的合法性  */
function checkRemark(){
    var strRemark = $.trim($("#remark").val());
    if(strRemark != null || strRemark != ""){
    	if(Ms.strLength(strRemark) > 100){
    		$("#hint10").html(" * 备注不能超出100个字符！");
    		$("#remark").addClass("border-red").removeClass("border-gray");
            return false;
    	}else{
    		$("#hint10").html("");
    		$("#remark").addClass("border-gray").removeClass("border-red");
            return true;
    	}
    }    
}

/* 验证性别合法性 返回1:男，2：女，false:没有选择性别 */
function checkSex(){
    var $hint = $('#hint_sex');
    if($(".sex-man").hasClass("sex-manCur")){
        $hint.html('');
        return 1;
	}
    if($(".sex-woman").hasClass("sex-womanCur")){
        $hint.html('');
        return 2;
    }
    $hint.html(' * 请选择性别！');
	return false ;
}

/**
 *  校验唯一性
 * @param type 类型
 * @param val 值
 */
function checkUnique(type,val) {
	var param={val : val, type : type}, info=null, targetIdx=0;
    targetIdx = $('#idx').val() || 0;
    param.targetIdx = targetIdx;
    $.ajax({
        timeout : Ms.AJAX_TIME_OUT ,
        type : "POST",
        url : "/sys/check-register",
        cache : false,
        async : false, // 同步
        data :param
    }).done(function (result) {
        if(Ms.SUC_CODE != result.status){
            info = result.msg;
        }
    }).fail(function(result) {
        info = Ms.ERROR_MSG;
    });
    return info;
}

/* 点击注册时验证数据的合法性 */
function validateRegister(arg){
	// 验证名称相关
    if(!checkName() || !checkAllName()){
        return false;
    }
    // 验证性别
    if(!checkSex()){
        return false;
    }
    // 验证密码相关
    if(!checkPassword() || !checkAgainPwd()){
        return false;
    }

    // 验证手机号码、电话号码、邮件相关
    if(!checkPhone() || !checkTelephone() || !checkEmail()){
        return false;
    }
    // 备注
    if(!checkRemark()){
        return false;
    }
    // 验证码
    if(arg != 'add'){
        if(!checkVerification()){
            return false;
        }
    }
    return true;
}

/* 提交注册表单 */
function submitForm(){
    if(!validateRegister()){
        return false;
    }
    var name = $.trim($('#name').val());
    var fullName = $.trim($('#allName').val());
    var sex = checkSex();
    var password = $.trim($('#password').val());
    var password2 = $.trim($('#password2').val());
    var phone = $.trim($('#phone').val());
    var tel = $.trim($('#telephone').val());
    var email = $.trim($('#email').val());
    var remark = $.trim($('#remark').val());
    var vcode =  _captcha_img_content_reg.pictureCode("getCodeValue");
    var accessCode = _captcha_img_content_reg.pictureCode("getAccessCodeValue");
    layer.load(2, {shade : 0.01, time : 5000});
    $.post("/sys/do-register?v="+Math.random(), {
            name: base64_encode(name),
            fullName: base64_encode(fullName),
            sex : sex,
            password: base64_encode(password),
            phone: base64_encode(phone),
            tel: base64_encode(tel),
            email: base64_encode(email),
            vcode: base64_encode(vcode),
        accessCode:accessCode,
            remark: base64_encode(remark)
        },
        function(data){
            if (data.status == 200) {
                layer.alert('注册成功!', {
                    title:'注册',
                    icon: 1,
                    btnAlign: 'c',
                    btn: ['马上登录','继续注册'],
                    closeBtn: 0,
                    yes: function(){
                        window.location="/sys/to-login";
                    },btn2: function(index, layero){
                        $("#regidterReset").click();
                    }
                });
            } else {
                layer.alert(data.msg, {
                    title:'注册',
                    icon: 2,
                    closeBtn: 0
                });
            }
            layer.closeAll('loading');
            _captcha_img_content_reg.pictureCode("refresh");
            $('#verification').val('');
        }, "json");
}


/* 注册验证 end */

/* 忘记密码  start  */
function checkNewPassword(){	
    var pwd = $.trim($("#password").val());
    if(pwd == "" || pwd == null || pwd == undefined){
    	$("#hint4").html(" * 密码不能为空！");
        return false;
    }else if(pwd != null || pwd != ""){
    	if(pwd.toString().length < 6 || pwd.toString().length > 20){
    		$("#hint4").html(" * 密码长度为6-20字符，请重新输入！");
            return false;
    	}else{
            if(checkRegular(pwd)){
                $("#hint4").html("");
                return true;
            }
            $("#hint4").html("* 密码必须是字母或特殊符号和数字结合。");
            return false;
        }
    }    
}

/* 验证邮箱地址的合法性  */
function checkEmail2(){
    var strEmail = $.trim($("#email").val());
    if(strEmail == ""){
        $("#hint8").html(" * 电子邮箱地址不能为空！");
        $("#email").addClass("border-red").removeClass("border-gray");
        return false;
    }else if(strEmail != null || strEmail != ""){
        if(strEmail.toString().length < 6 || strEmail.toString().length > 30){
            $("#hint8").html(" * 电子邮箱地址长度为6-30字符，请重新输入！");
            $("#email").addClass("border-red").removeClass("border-gray");
            return false;
        }else{
            if(Ms.checkMail(strEmail)){
                $("#hint8").html("");
                $("#email").addClass("border-gray").removeClass("border-red");
                return true;
            }else{
                $("#hint8").html(" * 电子邮箱地址不合法");
                $("#email").addClass("border-red").removeClass("border-gray");
                return false;
            }
        }
    }
}



/* 提交申请 */
function replyForm(){
    if(!checkEmail2() || !checkNewPassword() || !checkVerification() ){
        return false;
    }
    var password = $.trim($('#password').val());
    var email = $.trim($('#email').val());
    var vCode = $.trim( _captcha_img_content_reg.pictureCode("getCodeValue"));
    var accessCode = _captcha_img_content_reg.pictureCode("getAccessCodeValue");
    layer.load(2, {shade : 0.01, time : 50000});
    $.post("/sys/apply-forget?v="+Math.random(), {
            password: base64_encode(password),
            email: base64_encode(email),
            vCode : base64_encode(vCode),
            accessCode:accessCode
        },
        function(data){
            if (data.status == 200) {
                $("#findReset").click();
                layer.msg('提交成功，请前往邮箱确认修改！', {time: 3000, icon:6});
            } else {

                layer.msg(data.msg, {icon:2});
                _captcha_img_content_reg.pictureCode("refresh");
            }

            layer.closeAll('loading');
            $('#vCode').click();
            $('#verification').val('');
        }, "json");

}

/* 忘记密码  end  */


/* 个人设置  start  */

/* 验证用户编码的合法性  */
function checkCode(){
    var coding = $.trim($("#coding").val());
    if(!coding){
        $("#hint3").html(" * 用户编码不能为空！");
        $("#coding").addClass("border-red").removeClass("border-gray");
        return false;
    }else{
        var info=checkUnique(4,coding);
        if(info){
            $("#hint3").html(" * "+info);
            $("#coding").addClass("border-red").removeClass("border-gray");
            return false;
        }
        $("#hint3").html("");
        $("#coding").addClass("border-gray").removeClass("border-red");
        return true;
    }
}

/* 校验编辑 */
function validateEdit(){
    // 验证名称相关
    if(!checkAllName()){
        return false;
    }
    // 验证性别
    if(!checkSex()){
        return false;
    }
    // 验证用户编码
    if(!checkCode()){
        return false;
    }

    // 验证手机号码、电话号码、邮件相关
    if(!checkPhone() || !checkTelephone() || !checkEmail()){
        return false;
    }
    // 备注
    if(!checkRemark()){
        return false;
    }

    return true;
}

/* 修改个人信息 */
function editForm(){
    if(!validateEdit()){
        parent.layer.closeAll('loading');
        return false;
    }
    var idx = $('#idx').val();
    var fullName = $.trim($('#allName').val());
    var sex = checkSex();
    var code = $.trim($('#coding').val());
    var phone = $.trim($('#phone').val());
    var tel = $.trim($('#telephone').val());
    var email = $.trim($('#email').val());
    var remark = $.trim($('#remark').val());
    parent.layer.load(2, {shade : 0.01, time : 5000});
    $.post("/sys/do-edit?v="+Math.random(), {
            idx: idx,
            fullName: base64_encode(fullName),
            sex : sex,
            code: base64_encode(code),
            phone: base64_encode(phone),
            tel: base64_encode(tel),
            email: base64_encode(email),
            remark: base64_encode(remark)
        },
        function(data){
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('修改成功！', {time: 1000, icon:6});
                parent.layer.close(index);
            } else {
                parent.layer.msg(data.msg, {icon:2});
            }
            parent.layer.closeAll('loading');
        }, "json");

}
/* 个人设置  end  */

/* 修改密码  start  */

/* 验证原密码的合法性  */
function checkOldPassword(){
    var $oldPwd = $("#password_old");
    var $hintOld =  $("#hint_old");
    var pwd = $.trim($oldPwd.val());
    if(!pwd){
        $hintOld.html(" * 密码不能为空！");
        $oldPwd.addClass("border-red").removeClass("border-gray");
        return false;
    }else {
        if(pwd.length < 6 || pwd.length > 20){
            $hintOld.html(" * 密码长度为6-20字符，请重新输入！");
            $oldPwd.addClass("border-red").removeClass("border-gray");
            return false;
        }
    }
    $hintOld.html("");
    $oldPwd.addClass("border-gray").removeClass("border-red");
    return true;
}

function editPassword(){
    // 验证密码相关
    if(!checkOldPassword() || !checkPassword() || !checkAgainPwd()){
        parent.layer.closeAll('loading');
        return false;
    }
    var idx = $('#idx').val();
    var passwordOld = $.trim($('#password_old').val());
    var password = $.trim($('#password').val());

    $.post("/sys/update/do-password?v="+Math.random(), {
            idx: idx,
            oldPassword: base64_encode(passwordOld),
            newPassword : base64_encode(password)
        },
        function(data){
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('修改成功！', {time: 1000, icon:6});
                parent.layer.close(index);
                parent.window.location.href = '/sys/logout';
            } else {
                parent.layer.msg(data.msg, {icon:2});
            }
            parent.layer.closeAll('loading');
        }, "json");

}
/* 修改密码  end  */


/**
 *  BASE64加密
 * @param str 字符串
 * @return 加密后字符串
 */
function base64_encode(str){
    var words = CryptoJS.enc.Utf8.parse(str);
    return CryptoJS.enc.Base64.stringify(words);

}

/**
 *  BASE64解密
 * @param str 字符串
 * @return 解密后字符串
 */
function base64_decode(str){
    var words = CryptoJS.enc.Base64.parse(str);
    return words.toString(CryptoJS.enc.Utf8);
}



