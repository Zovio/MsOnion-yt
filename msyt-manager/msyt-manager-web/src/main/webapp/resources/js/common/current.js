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
/* 产品管理js  */
$(function(){
	/* 点击功能按钮的选中状态  */    
    $('.yt-row .brandBtn').click(function(){
    	$(this).addClass("active").siblings().removeClass("active");
    });
    
	/* 输入框获取焦点时的样式变化 */
	$(".winCont .brandTxt").focus(function() {
		$(this).css("border", "1px solid #00d5b4");
	});
	$(".winCont .brandTxt").blur(function() {
		$(this).css("border", "1px solid #dbdcde");
	});	
	
	/* 新增品牌弹框里的填写内容提示 */	
	$('#addDrandSave').click(function(){
		var brandCName = $('#brandCName').val();
		var brandEName = $('#brandEName').val();
		if(brandCName == "" && brandEName == ""){
			$('#brandCapacity .hint').show();
			$('#brandCName').css("border","1px solid #ff2c41");
			$('#brandEName').css("border","1px solid #ff2c41");
		}
	});
	$('#brandCapacity #brandCName').on('keyup',function(){
		$(this).next().hide();
		$('#brandCName').css("border","1px solid #dbdcde");	
		$('#brandEName').css("border","1px solid #dbdcde");	
	});
	$('#brandCapacity #brandEName').on('keyup',function(){
		$(this).parent().prev().children("p").hide();
		$('#brandCName').css("border","1px solid #dbdcde");	
		$('#brandEName').css("border","1px solid #dbdcde");		
	});
	
	/* 新增品牌弹框 */
	$('#showBrandCapacity').on('click', function() {
		layer.open({
			type: 1,
			title: '新增品牌',
			area: ['490px', '540px'],
			btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#brandCapacity'),
		});
	});
	
	/* 修改品牌弹框 */
	$('#editBrandCapacity').on('click', function() {
		layer.open({
			type: 1,
			title: '新增品牌',
			area: ['490px', '540px'],
			btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#brandCapacity'),
		});
	});

    /* 选择停用或启用 */
   $(document).click(function(){
	    $('.state .state2').hide();
	});	
    $('.state .state1').click(function(){
    	event.stopPropagation();
    	$('.state .state2').toggle();
    });    
    $('.state .state2').click(function(){
    	var option1 = $('.state1 b').html();
    	var option2 = $('.state2 b').html();
    	$('.state .state2').css("display","none");
    	if(option2 == "启用"){
    		$('.state1 b').html("启用");
    		$('.state2 b').html("停用");
    	}else if(option2 == "停用"){
    		$('.state1 b').html("停用");
    		$('.state2 b').html("启用");
    	}
    });
    
    /* 更改表格的高度 */
    $('.datagrid-header').height(40);
    $('.datagrid-htable').height(40);
    $('.datagrid-header-row').height(40);
});

/* 类目管理 js */
$(function(){
	/* 新增类目弹框里的填写内容提示 */					
	$('#addCategorySave').click(function(){
		var TypeCoding = $('#TypeCoding').val();
		var categoryName = $('#categoryName').val();
		if(TypeCoding == ""){
			$('#categoryCapacity .hint1').show();
			$('#TypeCoding').css("border","1px solid #ff2c41");							
		}
		if(categoryName == ""){
			$('#categoryCapacity .hint2').show();
			$('#categoryName').css("border","1px solid #ff2c41");
		}
	});
	$('#categoryCapacity .required').on('keyup',function(){
		$(this).next().hide();
	});

	//弹框
	$('#showCategoryCapacity').on('click', function() {
		layer.open({
			type: 1,
			title: '新增类目',
			area: ['490px', '480px'],
			btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#categoryCapacity'),
		});
	});
});
/* 产地管理js */
$(function(){
	/* 新增品牌弹框里的填写内容提示 */	
	$('#addPlaceSave').click(function(){
		var placeCoding = $('#placeCoding').val();
		var customsCoding = $('#customsCoding').val();
		var placeName = $('#placeName').val();
		if(placeCoding == ""){
			$('#placeCapacity .hint1').show();
			$('#placeCoding').css("border","1px solid #ff2c41");
		}if(customsCoding == ""){
			$('#placeCapacity .hint2').show();
			$('#customsCoding').css("border","1px solid #ff2c41");
		}if(placeName == ""){
			$('#placeCapacity .hint3').show();
			$('#placeName').css("border","1px solid #ff2c41");
		}
	});
	$('#placeCapacity .required').on('keyup',function(){
		$(this).next().hide();
	});
	
	/* 打开新增产地 */
	$('#showPlaceCapacity').on('click', function() {
		layer.open({
			type: 1,
			title: '新增产地',
			area: ['490px', '540px'],
            btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭			
			content: $('#placeCapacity'),
		});
	});
});

/* 属性管理js */
$(function(){
	/* 选择停用或启用 */
    $(document).click(function(){
	    $('.option .option2').hide();
	});	
    $('.option .option1').click(function(){
    	event.stopPropagation();
    	$('.option .option2').toggle();
    });    
    $('.option .option2').click(function(){
    	var option1 = $('.option1 b').html();
    	var option2 = $('.option2 b').html();
    	$('.option .option2').css("display","none");
    	if(option2 == "是"){
    		$('.option1 b').html("是");
    		$('.option2 b').html("否");
    	}else if(option2 == "否"){
    		$('.option1 b').html("否");
    		$('.option2 b').html("是");
    	}
    });
    
    
    
    
    /* 打开新增属性值的弹框 */
	$('#showPropertyCapacity').on('click', function() {
		layer.open({
			type: 1,
			title: '新增属性值',
			area: ['490px', '420px'],
			btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#propertyCapacity'),
		});
	});    
	
	/* 打开修改属性值的弹框 */
	$('#editProperty').on('click', function() {
		layer.open({
			type: 1,
			title: '新增属性值',
			area: ['490px', '420px'],
			btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#propertyCapacity'),
		});
	}); 
   	
   	/* 打开新增属性值弹框 */
    $('#showOptionCapacity').on('click', function() {
		layer.open({
			type: 1,
			title: '选择属性值',
			area: ['490px', '280px'],
			btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#optionCapacity'),
		});
	});   
    
    /* 打开新增属性组弹框 */
    $('#showGroupCapacity').on('click', function() {
		layer.open({
			type: 1,
			title: '新增属性组选项',
			area: ['490px', '260px'],
			 btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#groupCapacity'),
		});
	});   
    
    /* 打开选择属性组弹框 */
    $('#selectProperty').on('click', function() {
		layer.open({
			type: 1,
			title: '选择属性值',
			area: ['490px', '460px'],
			btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#optionPropertyPOP'),
		});
	});   
	 /* 打开修改属性组弹框 */
    $('#editGroup').on('click', function() {
		layer.open({
			type: 1,
			title: '新增属性组选项',
			area: ['490px', '260px'],
			 btn: ['保存','取消'],
			shadeClose: true, //点击遮罩关闭
			content: $('#groupCapacity'),
		});
	});
	
});

/* 商品管理 */
$(function(){
	/* 打开新增商品弹框 */
    $('#showCommodity').on('click', function() {
		layer.open({
			type: 1,
			title: '新增商品',
			area: ['618px', '480px'],
			shadeClose: true, //点击遮罩关闭
			content: $('#commodityCapacity'),
		});
	});  
	
	/* 打开上传表格弹框 */
    $('#showUpTable').on('click', function() {
		layer.open({
			type: 1,
			title: '新增商品',
			area: ['618px', '480px'],
			shadeClose: true, //点击遮罩关闭
			content: $('#commodityCapacity'),
		});
	}); 
	
	/* 打开上传表格弹框 */
    $('#showUpTable').on('click', function() {
		layer.open({
			type: 1,
			title: '上传表格',
			area: ['490px', '237px'],
			shadeClose: true, //点击遮罩关闭
			content: $('#upTableCapacity'),
		});
	}); 
	
});

















