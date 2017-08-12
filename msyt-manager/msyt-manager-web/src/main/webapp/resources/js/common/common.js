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
 * @Description: 登录注册js验证
 * @Company: http://www.yunyangtao.com
 * @author lemon
 * @e-mail address 2590586729@qq.com
 * @date 2017年3月10日
 * @version V1.0
 *
 */
var Ms = MS =  {
	// 返回成功码
	SUC_CODE : 200,
    // 请求后台返回错误友好提示语
	ERROR_MSG : '服务器忙，请稍后重试 ...',

    // AJAX请求超时时间
    AJAX_TIME_OUT : 6000,

    // 图片格式
    PIC : new Array('jpg', 'jpeg', 'gif', 'png'),
    // 验证码图片格式
    VERIFYPIC : new Array('jpg', 'jpeg', 'png'),
    // 附件格式
	ATTACHMENT : new Array('mp4', 'avi', 'pdf', 'ppt', 'pptx'),

	saveLoading: function () {
        layer.load(2, {shade : 0.01, time : 5000});
    },
	closeLoading: function () {
        parent.layer.closeAll('loading');
    },
    clearGridSelections: function (tableId) {
        $('#' + tableId).datagrid('clearSelections');
    },
	reloadDataGrid: function (tableId) {
        $('#' + tableId).datagrid('reload');
    },
	reloadParentDataGrid: function (tableId) {
        parent.$('#' + tableId).datagrid('reload');
    },

    // 只能输入大于0的正数字
    onlyNum: function onlyNum(obj){
   		 obj.value = obj.value.replace(/\D/g,'');
         obj.value = parseInt(obj.value) == 0 ? 1 : obj.value;
	},
	// 只能输入英文和数字
	onlyEnOrNumber: function onlyEnOrNumber(obj) {
        obj.value=obj.value.replace(/[^\a-\z\A-\Z0-9\_\.\:\#\*]/g,'');
    },

	/* 去掉左右两边的空格  str为要去掉空格的字符串  */
	trim : function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g,'');
	},

	/* 将某一字符串去所有空格处理  str为要去掉所以空格的字符串  */
	AllTrim : function AllTrim(str){
		var Finds=/ /g;
		var str = str.replace(Finds,"");
		return str;
	},

	/* 判断是否只有只包括字母，数字  函数  str参数为要判断字符串  */
	isChar : function isChar(str){
		if(str.length == 0){
			return false;
		}
		var regu = "^[0-9A-Za-z]*$";
		var re = new RegExp(regu);
		if(str.search(re) != -1){
			return true
		}else{
			return false;
		}
	},

	/* 判断中英文，数字的正则  str：传入的字符串  */
	isStringAndNull : function isStringAndNull(str){
		if(str.length == 0){
			return false;
		}
		var regu = /^(\w|[\u4E00-\u9FA5])*$/;
		var re = new RegExp(regu);
		if(str.search(re) != -1){
			return true
		}else{
			return false;
		}
	},

	/* 判断是否包含数字字母和符号  str：传入的字符串  */
	isStringNullSymbol : function isStringNullSymbol(str){
		if(str.length == 0){
			return false;
		}
		var regu = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*])[\da-zA-Z~!@#$%^&*]{8,}$/;
		var re = new RegExp(regu);
		if(str.search(re) != -1){
			return true
		}else{
			return false;
		}
	},

    /* 验证价格,只能为数字且只能保留2为小数 */
    priceNumber : function priceNumber(obj) {
        obj.value = obj.value.replace(/[^\d.]/g,"");
        obj.value = obj.value.replace(/^\./g,"");
        obj.value = obj.value.replace(/\.{2,}/g,".");
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
    },

    /* 判断是否只有只包括数字  num参数为要判断的数字  */
	isNumber : function isNumber(num){
		if(num.toString().length == 0){
			return false;
		}
		var regu = "^[0-9]*$";
		var re = new RegExp(regu);
		if(num.toString().search(re) != -1){
			return true;
		}else{
			return false;
		}
	},


	/* 判断是否为数字或小数  num参数为要判断的数字  */
	isNumberOrFloat : function isNumberOrFloat(num){
		if(num.toString().length == 0){
			return false;
		}
		var regu = "^[0-9]*$";
		var re = new RegExp(regu);
		if(num.toString().search(re) != -1){
			return true;
		}else{
			/* 判断是否为小数  */
			var regu1 = "^[0-9]*[\.][0-9]*$";
			var re1 = new RegExp(regu1);
			if(num.toString().search(re1) != -1){
				return true;
			}else{
				return false;
			}
		}
	},

    /* 验证密码  */
    _checkPassword: function _checkPassword(str) {
//		return (new RegExp(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/).test(str));
        return (new RegExp(/((?=.*[a-z])(?=.*\d)|(?=[a-z])(?=.*[#@!~%^&*])|(?=.*\d)(?=.*[#@!~%^&*]))[a-z\d#@!~%^&*]{8,16}/i).test(str));
    },

	/* 判断是否有中文函数  str:传入你需要判断的字符串  */
	isChinese : function isChinese(str){
		var bool = false;
		for(var i = 0; i < str.length; i++){
			if(str.charCodeAt(i)>=256){
				bool = true;
				break;
			}
		}
		return bool;
	},

	/* 检查E-mail的输入是否合法  mail:输入的邮箱地址  */
	checkMail : function checkMail(mail){
		return(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(mail));
	},

	/* 检查手机号码的合法性  num:输入的手机号码   */
	checkMobileNo : function checkMobileNo(num){
		return (new RegExp(/^1[3|4|5|7|8][0-9]\d{8}$/).test(num));
	},

	/* 检查电话号码的合法性  num:输入的电话号码  */
	checkTelephone : function checkTelephone(num){
		return (new RegExp(/^0\d{2,3}-?\d{7,8}$/).test(num));
	},

	/* 匹配身份证(15位或18位) */
	isidcard : function isidcard(str) {
		var result = str.match(/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/);
		if(result == null) return false;
		return true;
	},

	/* 判断输入是否是一个由 0-9 / A-Z / a-z 组成的字符串 */
	isalphanumber : function isalphanumber(str) {
		var result = str.match(/^[a-zA-Z0-9]+$/);
		if(result == null) return false;
		return true;
	},

	/* 拆分整数与小数  */
	splits : function splits(tranvalue) {
		var value = new Array('', '');
		temp = tranvalue.split(".");
		for(var i = 0; i < temp.length; i++) {
			value = temp;
		}
		return value;
	},

	/* 保留两位小数  value:需要保留两位小数的数字  */
	toDouble : function toDouble(value){
		return value.toFixed(2);
	},

	/* 测量有汉字时的字串实际长度，其中一个汉字占两个字符  str:需要测量的字符串  */
	strLength : function strLength(str){
		var length = 0;
		var tmpArr = str.split("");
		for(var i = 0; i < tmpArr.length; i++){
			length ++;
		}
		return length;
	},

	/* 字符串超出省略   str:传入你需要截取的字符串, len:设置你想要默认字符串长度  */
	cutStr: function cutStr(str,len) {
		var restr = str;
		var wlength = str.replace(/[^\x00-\xff]/g, "**").length;
		if(wlength > len) {
			for(var k = len / 2; k < str.length; k++) {
				if(str.substr(0, k).replace(/[^\x00-\xff]/g, "**").length >= len) {
					restr = str.substr(0, k) + "...";
					break;
				}
			}
		}
		return restr;
	},

	/* 判断某个值是否在数组中 arr:当前需要判断的数组  e:需要判断的某个值，直接穿进去即可   */
	inArray : function inArray(arr,e) {
		for(i = 0; i < arr.length; i++) {
			if(arr[i] == e)
				return true;
		}
		return false;
	},

	/* 判断数组中是否存在重复的元素 */
	confirmRepeat : function confirmRepeat(someArray) {
		tempArray = someArray.slice(0);
		/* 复制数组到临时数组 */
		for(var i = 0; i < tempArray.length; i++) {
			for(var j = i + 1; j < tempArray.length;) {
				if(tempArray[j] == tempArray[i]){
					/* 后面的元素若和待比较的相同,则删除并计数;  */
					/* 删除后,后面的元素会自动提前,所以指针j不移动   */
					return true;
				} else {
					j++;
				}
				/* 不同,则指针移动 */
			}
		}
		return false;
	},

	/* 删除数组中存在重复的元素   */
	getUnique : function getUnique(someArray) {
		tempArray = someArray.slice(0);
		/* 复制数组到临时数组 */
		for(var i = 0; i < tempArray.length; i++) {
			for(var j = i + 1; j < tempArray.length;) {
				if(tempArray[j] == tempArray[i]){
					/* 后面的元素若和待比较的相同,则删除并计数;  */
					/* 删除后,后面的元素会自动提前,所以指针j不移动   */
					tempArray.splice(j, 1);
				} else {
					j++;
				}
				/* 不同,则指针移动 */
			}
		}
		return tempArray;
	},

	/* 全角转半角  */
	fullToHalf: function fullToHalf(obj) {
		var result = "";
		for(var i = 0; i < obj.length; i++) {
			if(obj.charCodeAt(i) == 12288) {
				result += String.fromCharCode(obj.charCodeAt(i) - 12256);
				continue;
			}
			if(obj.charCodeAt(i) > 65280 && obj.charCodeAt(i) < 65375) {
				result += String.fromCharCode(obj.charCodeAt(i) - 65248);
			} else {
				result += String.fromCharCode(obj.charCodeAt(i));
			}
		}
		return result;
	},

	/* 点击回车键触发事件  objId:id选择器   */
	enterKeyDown : function enterKeyDown(objId){
	    document.onkeydown = keyUp;
	    function keyUp(e){
	        e = window.event || e;
	        var k = e.keyCode || e.which;
	        if(k == 13){
	            document.getElementById(ObjId).onclick();
	        }
	    }
	},

	/* 获取url参数  */
	getUrlParameter : function getUrlParameter(url){
		if(!url){
			url = location.search.substring(1);
		}else{
			url = url.substr(url.indexOf('?') + 1);
		}
		/* 声明并初始化一个"类" */
		var args = new Object();
		/* 获得地址(URL)"?"后面的字符串 */
		var query = decodeURI(url);
		/* 分割URL(别忘了'&'是用来连接下一个参数) */
		var pairs = query.split("&");
		for(var i = 0; i < pairs.length; i++) {
			var pos = pairs[i].indexOf('=');
			if(pos == -1) continue;
			/* 它在找有等号的 数组[i] */
			var argname = pairs[i].substring(0, pos);
			/* 参数名字   */
			var value = pairs[i].substring(pos + 1);
			/* 以键值对的形式存放到"args"对象中   */
			args[argname] = decodeURI(value);
		}
		return args;
	},

	/* 按大小要求加载图片  imgD:选择器,fitWidth:设置一个宽度,fitHeight:设置一个高度   */
	loadImg : function loadImg(imgD, fitWidth, fitHeight) {
		var image = new Image();
		image.src = imgD.src;
		var wb = image.width / fitWidth;
		var hb = image.height / fitHeight;
		var b = wb >= hb ? wb : hb;
		if(b > 1) {
			imgD.width = image.width / b;
			imgD.height = image.height / b;
		} else {
			imgD.width = image.width;
			imgD.height = image.height;
		}
	},

	/* 树形控件单选框联动  */
	onTreeNodeChecked : function onTreeNodeChecked() {
		var ele = window.event.srcElement;
		if(ele.type == 'checkbox') {
			var childrenDivID = ele.id.replace('CheckBox', 'Nodes');
			var div = $$(childrenDivID);
			if(div == null) return;
			var checkBoxs = div.getElementsByTagName('INPUT');
			for(var i = 0; i < checkBoxs.length; i++) {
				if(checkBoxs[i].type == 'checkbox')
					checkBoxs[i].checked = ele.checked;
			}
		}
	},

	/* 更换验证码  */
	/*
	 * changeImg : function changeImg(path) {
	 *		$$('checkCode').src = path + 'Inc/CheckCode.ashx?id=' + Math.random();
	 *	}
	 */

	/* iframe自适应高度  */
	resizeFrameHeight : function resizeFrameHeight(frmName) {
		var frm = $$(frmName);
		var subWeb = document.frames ? document.frames[frmName].document : frm.contentDocument;
		if(frm != null && subWeb != null) {
			frm.height = subWeb.body.scrollHeight;
		}
	},

	/* iframe自适应高度  如果iframe高度小于500就把默认可视窗口高度 */
	resizeFrameHeight2 : function resizeFrameHeight2(frmName) {
		var frm = $$(frmName);
		var subWeb = document.frames ? document.frames[frmName].document : frm.contentDocument;
		if(frm != null && subWeb != null) {
			frm.height = subWeb.body.scrollHeight;
			if(frm.height < 500) {
				frm.height = document.body.clientHeight;
			}
		}
	},

	/* 刷新父窗口  */
	refreshParent : function refreshParent() {
		if(window.opener != null && (!window.opener.closed)) {
			var parentForm = window.opener.document.forms[0];
			if(parentForm != null) {
				parentForm.submit();
			}
		}
	},

	/* 新开普通窗口  url:新代开的链接路径 ,width,heght:设置初始打开的窗口宽高度,  strip:设置滚动栏      */
	windowOpen : function windowOpen(url, wide, high, strip) {
		var s = "scrollbars=" + strip + ",";
		s += "toolbar=no,resizable=yes,status=yes," + "top=" + (screen.availHeight - high) / 2 + ",height=" + high + ",left=" + (screen.availWidth - wide) / 2 + ",width=" + wide;
		window.open(url, "_blank", s);
	},

	/* 新开模式窗口  url:新代开的链接路径 ,width,heght:设置初始打开的窗口宽高度 */
	showDialog : function showDialog(url, width, heght) {
		if(document.all) {
			return window.showModalDialog(url, null, "dialogWidth:" + width + "px;dialogHeight:" + heght + "px;scroll=no;status:no;help:no");
		} else {
			window.open(url, null, "width=" + width + ",height=" + heght + ",menubar=no,toolbar=no,location=no,scrollbars=no,status=no,modal=yes");
		}
	},

	/* 关闭窗口  */
	winClose : function winClose() {
		window.opener = null;
		window.open("", "_self");
		window.close();
	},

	/* 设为首页  homeurl:传入你需要设置为首页的url  */
	setHomepage : function setHomepage(homeurl) {
		if(document.all) {
			document.body.style.behavior = 'url(#default#homepage)';
			document.body.setHomePage(homeurl)
		} else if(window.sidebar) {
			if(window.netscape) {
				try {
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect")
				} catch(e) {
					alert("该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入about:config,然后将项 signed.applets.codebase_principal_support 值该为true");
				}
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage', homeurl)
		}
	},

	/* 加入收藏夹  */
	addFavorite : function addFavorite(url, title) {
		try {
			window.external.addFavorite(url, title);
		} catch(e) {
			try {
				window.sidebar.addPanel(title, url, "");
			} catch(e) {
				alert("加入收藏失败，有劳您手动添加。");
			}
		}
	},

	/* 转义html标签 */
	htmlEncode : function htmlEncode(text) {
		return text.replace(/&/g, '&').replace(/\"/g, '"').replace(/</g, '<').replace(/>/g, '>')
	},

	/* 跨浏览器绑定事件   */
	addEventSamp : function addEventSamp(obj, evt, fn) {
		if(!oTarget) { return; }
		if(obj.addEventListener) {
			obj.addEventListener(evt, fn, false);
		} else if(obj.attachEvent) {
			obj.attachEvent('on' + evt, fn);
		} else {
			oTarget["on" + sEvtType] = fn;
		}
	},

	/* 跨浏览器删除事件     */
	delEvt : function delEvt(obj, evt, fn) {
		if(!obj) { return; }
		if(obj.addEventListener) {
			obj.addEventListener(evt, fn, false);
		} else if(oTarget.attachEvent) {
			obj.attachEvent("on" + evt, fn);
		} else {
			obj["on" + evt] = fn;
		}
	},
	/* 判断是否移动设备访问  */
	isMobileUserAgent : function isMobileUserAgent() {
		return(/iphone|ipod|android.*mobile|windows.*phone|blackberry.*mobile/i.test(window.navigator.userAgent.toLowerCase()));
	},

	/* 完美判断是否为网址   strUrl:传入你要判断的url  */
	isURL : function isURL(strUrl) {
		var regular = /^\b(((https?|ftp):\/\/)?[-a-z0-9]+(\.[-a-z0-9]+)*\.(?:com|edu|gov|int|mil|net|org|biz|info|name|museum|asia|coop|aero|[a-z][a-z]|((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d))\b(\/[-a-z0-9_:\@&?=+,.!\/~%\$]*)?)$/i
		if(regular.test(strUrl)) {
			return true;
		} else {
			return false;
		}
	},

	/* 获取页面高度 */
	getPageHeight : function getPageHeight() {
		var g = document,
			a = g.body,
			f = g.documentElement,
			d = g.compatMode == "BackCompat" ? a : g.documentElement;
		return Math.max(f.scrollHeight, a.scrollHeight, d.clientHeight);
	},
	/* 获取页面宽度 */
	getPageWidth : function getPageWidth() {
		var g = document,
			a = g.body,
			f = g.documentElement,
			d = g.compatMode == "BackCompat" ? a : g.documentElement;
		return Math.max(f.scrollWidth, a.scrollWidth, d.clientWidth);
	},
	/* 获取页面可视宽度  */
	getPageViewWidth : function getPageViewWidth() {
		var d = document,
			a = d.compatMode == "BackCompat" ?
			d.body :
			d.documentElement;
		return a.clientWidth;
	},
	/* 获取页面可视高度 */
	getPageViewHeight : function getPageViewHeight() {
		var d = document,
			a = d.compatMode == "BackCompat" ?
			d.body :
			d.documentElement;
		return a.clientHeight;
	},

	/* 获取页面scrollLeft  */
	getPageScrollLeft : function getPageScrollLeft() {
		var a = document;
		return a.documentElement.scrollLeft || a.body.scrollLeft;
	},

	/* 获取页面scrollTop */
	getPageScrollTop : function getPageScrollTop() {
		var a = document;
		return a.documentElement.scrollTop || a.body.scrollTop;
	},

	/* 获取窗体可见范围的宽与高  */
	getViewSize : function getViewSize() {
		var de = document.documentElement;
		var db = document.body;
		var viewW = de.clientWidth == 0 ? db.clientWidth : de.clientWidth;
		var viewH = de.clientHeight == 0 ? db.clientHeight : de.clientHeight;
		return Array(viewW, viewH);
	},

	/* 随机数时间戳   */
	uniqueId : function uniqueId() {
		var a = Math.random,
			b = parseInt;
		return Number(new Date()).toString() + b(10 * a()) + b(10 * a()) + b(10 * a());
	},

	/* 获取网页被卷去的位置  */
	getScrollXY : function getScrollXY() {
		return document.body.scrollTop ? {
			x: document.body.scrollLeft,
			y: document.body.scrollTop
		} : {
			x: document.documentElement.scrollLeft,
			y: document.documentElement.scrollTop
		}
	},
    /**
	 * base64 加密
     * @param str
     * @returns {*}
     */
    base64_encode: function(str){
        var words = CryptoJS.enc.Utf8.parse(str);
        return CryptoJS.enc.Base64.stringify(words);
	},
    /**
	 * base64解密
     * @returns {string}
     */
    base64_decode :function(){
        var words = CryptoJS.enc.Base64.parse(str);
        return words.toString(CryptoJS.enc.Utf8);
	},
    /**
	 *  睡眠多少毫秒
     * @param numberMillis
     */
    sleep:function (numberMillis) {
		var now = new Date();
		var exitTime = now.getTime() + numberMillis;
		while (true) {
			now = new Date();
			if (now.getTime() > exitTime)
				return;
		}
	},
    /**
	 * 获取文件后缀名
     * @param fileName 文件名称
     * @return {string}
     */
	getFileExtension :function (fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    },
    /**
     * 获取文件后缀名
     * @param fileName 文件名称
     * @return {string}
     */
    getFileSize :function (file) {
        var fileSize = 0;
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        if (isIE && !file.files) {
            var filepath = file.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file1 = fileSystem.GetFile (filepath);
            fileSize = file1.Size;
        }else {
            fileSize = file.files[0].size;
        }
        return fileSize;
    },

	/* 不能输入特殊字符串 */
    noSpecialCharacter : function noSpecialCharacter(textbox){
        var IllegalString = "[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！#￥……&*（）——|{}【】‘；：”“'。，、？]‘’";
        var textboxvalue = textbox.value;
        var index = textboxvalue.length - 1;
        var s = textbox.value.charAt(index);
        if (IllegalString.indexOf(s) >= 0) {
            s = textboxvalue.substring(0, index);
            textbox.value = s;
        }
    },

	/* 只能输入数字 */
    onlyNumber : function onlyNumber(obj){
        obj.value = obj.value.replace(/\D|^0/g,'')
    },

}






