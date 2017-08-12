/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
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
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
/**
 * @Company: 广州市两棵树网络科技有限公司
 * @Author DorenWu DorenWu@msyc.cc
 * @Date 2017年07月24日 15:08:28
 */
;(function ($, window, document, undefined) {
    var valueArr;
    var thisObj, settings;
    // 插件的默认值
    var defaults = {
        // 请求验证码图片地址
        url: "",
        // 请求验证码访问码地址
        accessCodeUrl:"",
        // 图片尺寸
        picSize: 68,
        // 验证码图片个数
        picNum: 4,
        // 验证码图片旋转一次的次数
        ring: 4,
        // 个数最大值
        minNum: 2,
        // 个数最大值
        maxNum: 5,
        // 验证码图片提示
        tips: "请点击图片旋转至正向朝上",
        // 验证码刷新按钮
        refreshBtnText: "换一组",
    };
    // 插件方法
    var methods = {
        // 初始化插件
        init: function (options) {

            // 将一个空对象做为第一个参数
            settings = $.extend({}, defaults, options);

            if (!settings.url || settings.url == "") {
                alert("请求验证码地址不正确");
                return;
            }
            if (!settings.accessCodeUrl || settings.accessCodeUrl == "") {
                alert("请求验证码访问码地址不正确");
                return;
            }
            if (settings && settings.picNum) {
                if (settings.picNum > settings.maxNum
                    || settings.picNum < settings.minNum) {
                    alert("图片数目设置不正确");
                    return;
                }
            }
            // 链式调用
            return this.each(function () {
                thisObj = $(this);

                gemImageDiv(thisObj, settings);

                showPic(thisObj, settings);

                eventOperate(thisObj, settings);
            });

        },
        getCodeValue: function () {
            return window.probd;
        },
        getAccessCodeValue: function () {
            return window.probc;
        },
        refresh: function () {
            showPic(thisObj, settings);
        }
    };


    // 插件继承
    $.fn.pictureCode = function (method) {

        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.pictureCode');
        }
    };

    // 生成DOM元素
    function gemImageDiv($dom, $settings) {
        // 生成dom
        $dom.addClass("pictureCodeMain");
        // 刷新区域
        var refreshDiv = $("<div class='pictureCode-refresh'><span >" + $settings.tips + "</span><a>" + $settings.refreshBtnText + "</a></div>");
        $dom.append(refreshDiv);
        // 图片区域
        var imgDiv = $("<div></div>");
        imgDiv.addClass("pictureCode-imgDiv");
        var picWidth = $settings.picSize * $settings.picNum;
        var picHeight = $settings.picSize * $settings.ring;
        for (var i = 0; i < $settings.picNum; i++) {
            var imgDivRow = $("<div></div>");
            imgDivRow.addClass("imgRow");
            imgDivRow.css("width", $settings.picSize);
            imgDivRow.css("height", $settings.picSize);
            var imgDivRowImg = $("<img id='_imgcheck_image_div" + i + "' src=''>");
            imgDivRowImg.attr("width", picWidth);
            imgDivRowImg.attr("height", picHeight);
            imgDivRowImg.attr("codePic", i);
            imgDivRow.append(imgDivRowImg);
            imgDiv.append(imgDivRow);
        }
        $dom.append(imgDiv);
    }

    /**
     * 显示图片
     */
    function showPic($dom, $settings) {
        $.ajax({
            url:$settings.accessCodeUrl,
            type:'POST',
            dataType:"json",
            success:function(data){
                if (data.status == 200) {
                    var accessCode = data.data;
                    window.probc = accessCode;
                    var allUrl = "";
                    if ($settings.url.indexOf("?") > -1) {
                        allUrl = $settings.url + "&rand=" + Math.random() + "&accessCode=" + accessCode;
                    } else {
                        allUrl = $settings.url + "?rand=" + Math.random() + "&accessCode=" + accessCode;
                    }
                    var imgObjct = allUrl;
                    var codePic = $($dom).find("img[codePic]");
                    for (var i = 0; i < codePic.length; i++) {
                        var codePicDom = codePic[i];
                        $(codePicDom).attr("src", imgObjct);
                        var leftValue = -$settings.picSize * i;
                        $(codePicDom).css("left", leftValue);
                    }
                    setValueArr($settings);
                }else{
                    alert("验证码加载失败！");
                }
            }
        });
    }

    /**
     * 事件操作
     */
    function eventOperate($dom, $settings) {
        // 刷新
        $($dom).find(".pictureCode-refresh a").on("click", function () {
            showPic($dom, $settings);
        });
        // 图片点击
        $($dom).find("img[codePic]").on("click", function () {
            var $this = $(this);
            var picCodeIndex = $this.attr("codePic");
            valueArr[picCodeIndex]++;
            var leftValue = -$settings.picSize * picCodeIndex;
            $this.css("left", leftValue);
            var topValue = -$settings.picSize * (valueArr[picCodeIndex] % $settings.ring);
            $this.css("top", topValue);
            window.probd = "";
            for (var k = 0; k < $settings.picNum; k++) {
                window.probd += valueArr[k]
            }
        });
    }

    /**
     * 初始化数组的值
     * @param $settings
     */
    function setValueArr($settings) {
        valueArr = new Array($settings.picNum);
        window.probd = "";
        for (var i = 0; i < $settings.picNum; i++) {
            valueArr[i] = 0;
            window.probd += 0;
        }
    }

})(jQuery, window, document);