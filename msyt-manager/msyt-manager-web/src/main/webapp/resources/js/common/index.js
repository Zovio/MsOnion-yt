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

/* 左边菜单栏 start  */
$(function(){

    $('#personMouseover').hover(function(){
        $('.set-center').show();
    },function(){
        $('.set-center').hide();
    });

    /* 收起左边菜单栏  */
    $("#menu-show").click(function(){
        var tabheadW = $(".tabs-header").width();
        var tabW = $(".tabs-wrap").width();
        $(".tabs-header").width(tabW + 210);
        $(".tabs-wrap").width(tabheadW + 210);
        $(".tabs-header").css("left","30px");
        $("#menu-hide").addClass("rotate");
        $(".yt-left-menu").animate({ left: "-240px" });
        $(".win-nav-cont").css("padding-left","30px");
        setTimeout(function(){
            $("#menu-hide").show();
        },250)
    });

    /* 展开左边菜单栏  */
    $("#menu-hide").click(function() {
        var tabheadW = $(".tabs-header").width();
        var tabW = $(".tabs-wrap").width();
        $("#menu-hide").hide();
        $(".yt-left-menu").animate({ left: "0px"});
        setTimeout(function(){
            $(".win-nav-cont").css("padding-left","240px");
            $(".tabs-header").css("left","240px");
            $(".tabs-header").width(tabW - 210);
            $(".tabs-wrap").width(tabheadW - 210);
        },250)
    });

    /* 个人修改 */
    $('#editMember').on('click', function () {
        var url=$(this).attr('url');
        //页面层
        layer.open({
            title:'个人设置',
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            // skin: 'layui-layer-rim', //加上边框
            area: ['670px', '540px'], //宽高
            content: url,
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.editForm();
                return false;
            }
        });
    });

    /* 密碼修改 */
    $('#editPassword').on('click', function () {
        var url=$(this).attr('url');
        //页面层
        layer.open({
            title:'修改密码',
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            //skin: 'layui-layer-rim', //加上边框
            area: ['650px', '350px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.editPassword();
                return false;
            }
        });
    });

})

/* 右键关闭tabs */
$(function(){
    /* 删除Tabs */
    function closeTab(menu, type) {
        var allTabs = $("#tabs").tabs('tabs');
        var allTabtitle = [];
        $.each(allTabs, function (i, n) {
            var opt = $(n).panel('options');
            if (opt.closable)
                allTabtitle.push(opt.title);
        });
        var curTabTitle = $(menu).data("tabTitle");
        var curTabIndex = $("#tabs").tabs("getTabIndex", $("#tabs").tabs("getTab", curTabTitle));
        if(type == 1){
            $("#tabs").tabs("close", curTabIndex);
        }
        if(type == 2){
            for (var i = 0; i < allTabtitle.length; i++) {
                $('#tabs').tabs('close', allTabtitle[i]);
            }
        }
        if(type == 3){
            for (var i = 0; i < allTabtitle.length; i++) {
                if (curTabTitle != allTabtitle[i])
                    $('#tabs').tabs('close', allTabtitle[i]);
            }
            $('#tabs').tabs('select', curTabTitle);
        }
        if(type == 4){
            for (var i = curTabIndex; i < allTabtitle.length; i++) {
                $('#tabs').tabs('close', allTabtitle[i]);
            }
            $('#tabs').tabs('select', curTabTitle);
        }
        if(type == 5){
            for (var i = 0; i < curTabIndex-1; i++) {
                $('#tabs').tabs('close', allTabtitle[i]);
            }
            $('#tabs').tabs('select', curTabTitle);
        }
        if(type == 6){
            var panel = $("#tabs").tabs("getTab", curTabTitle).panel("refresh");
        }
    }

    /* 监听右键事件，创建右键菜单     */
    $('#tabs').tabs({
        onContextMenu:function(e, title,index){
            e.preventDefault();
            if(index>0){
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        }
    });
    //右键菜单click
    $("#mm").menu({
        onClick : function (item) {
            closeTab(this, item.name);
        }
    });

})





