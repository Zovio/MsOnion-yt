<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<section class="main">
    <div class="table-container">
        <div class="yt-row">
            <c:forEach items="${statusMap}" var="m">
                <button type="button" id="ablumBtn-${m.key}" value="${m.key}"  class="btn-white" style="float: left;margin-right: 20px;">${m.value}</button>
            </c:forEach>

            <shiro:hasPermission name="collect:ablum:download">
            <button type="button" class="brandBtn" id="download">下载</button>
            </shiro:hasPermission>
            <div class="search-xc"><input  id="ablum-qry-val" type="text" maxlength="15" /><i id="ablum-qry"></i></div>
        </div>
        <div class="main-content" style="min-height: 600px;">
            <div class="fuIxtL">
                <div class="fuIxtL-left" style="display: none;">
                    <a class="goBack" href="javascript:;">返回上一级</a>
                </div>
                <div class="fuIxtL-right">
                    <a class="fuIxtLName" href="javascript:;" onclick="$breadClick(this);">全部文件</a>
                </div>
                <div class="allData" id="ablum-allTotal"></div>
            </div>

            <div class="fileContainer">
                <div class="checkAll-cont" >
                    <span class="span-checkbox"></span>
                    <label class="checkAll">全选</label>
                </div>
                <!-- 一层目录 -->
                <div id="oneCategory-div"></div>
                <script id="oneCategory" type="text/html">
                    {{#  if(!d || d.data == 0){ }}
                    <div class="misc" >
                        <img src="/resources/images/emptyfile.png" />
                        <span class="sp-misc-hit">您还没上传文件哦！</span>
                    </div>

                    {{#  } else{ }}
                        {{#  if(d.data[0].dirType == '1'){ }}
                            <!-- 通过ext字段判断，文件模板 -->
                        <ul id="layer-photos" class="picture layer-photos-demo" >
                            {{#  layui.each(d.data, function(index, item){ }}
                            <li class="photo-content" onclick="$picClick(this);">
                                <img layer-src="{{ item.relativePath}}" src="{{ item.relativePath}}" alt="{{ item.name}}" onerror="this.src='/resources/images/default.png'">
                                <div class="sourceCont" >
                                    <div class="bianma"><span title="{{ item.name}}">{{ item.name}}</span></div>
                                    <div class="source">
                                        <span class="checkbox" data-idx="{{ item.idx }}" ></span>
                                        <span class="sourceName" title="{{ item.remark}}">{{ item.remark}}</span>
                                    </div>
                                </div>
                            </li>
                            {{#  }); }}
                        </ul>

                    {{#  } else{ }}
                            <!-- 文件夹模板 -->
                            <ul class="Menus" id="oneCategory-ul">
                                {{#  layui.each(d.data, function(index, item){ }}
                                <li>
                                    <div class="fileCont">
                                        <span class="span-checkbox" data-idx="{{ item.idx }}"  onclick="$checkBoxList(this);"></span>
                                        <div id="first-folder-{{ item.idx}} " class="right-folder" data-category="{{ item.category}}"  data-idx="{{ item.idx}}" onclick="$dirClick(this);">
                                            <div class="photoFile">
                                                <span>
                                                    {{#  if(item.totalNum){ }}
                                                         {{ item.totalNum }} 张
                                                    {{#  }  }}
                                                 </span>
                                            </div>
                                            <span class="filename" title="{{ item.name}}">{{ item.name}}</span>
                                        </div>
                                    </div>
                                </li>
                                {{#  }); }}
                             </ul>
                        {{# } }}
                    {{# } }}
                </script>
                <!-- 分页控件 -->
                <div class="ablum-result">
                 <div id="Menus-page" class="tcdPageCode"></div>
                </div>


            </div>


        </div>
    </div>

    <script type="text/javascript" src="/resources/js/common/jquery.page.js" ></script>

    <script type="text/javascript">
        var checkAllCont= $('.fileContainer .checkAll-cont');	/* 全选容器 */
        var checkAll = $('.fileContainer .checkAll-cont .span-checkbox');	/* 一级全选checkbox */
        var ablum ={}; /* 相册对象 */

        // 查询相册对象
        function $queryAblumDate(url, params, sucFn, errFn){
            url = url ? url : '/collect/ablum/qureyDate';
            layer.load(2, {shade : 0.01});
            $.ajax({
                timeout : Ms.AJAX_TIME_OUT ,
                type : "POST",
                data : params,
                url : url,
                cache : false,
            }).done(function (result) {
                sucFn(result);
            }).fail(function(e) {
                errFn(e);
            });
        }
        // 组装公共分页对象
        function $createPage(pageCount,curPage,backFn){
            //$("#Menus-page").remove();
            $(".ablum-result").append("<div  id='Menus-page' class='tcdPageCode'></div>");

            $('#Menus-page').createPage({
                pageCount : pageCount,
                current : curPage,
                 backFn:function(p){
                     backFn(p);
                }
            });
        }

        // 初始化总条数文本提示
        function $initTotalTxt(res){
            var txt = '已加载'+res.data.length+'个，共'+res.totalCounts+'个';
            $('#ablum-allTotal').html(txt);
        }

        // 列表checkbox 选中事件
        function $checkBoxList(obj){
            var $this = $(obj);
            if($this.hasClass("active")){
                $this.removeClass("active");
                checkAll.removeClass("active");
            }else{
                $this.addClass("active");
            }
        }
        // 点击分页
        function paging(p){
            var $val = $('button[id^="ablumBtn-"]').filter(".active").eq(0).val();
            ablum ={};
            ablum.ablumType = $val;
            var $a = $('.fuIxtL-right').find('a:last');
            ablum.pidx = $a.data('idx');
            ablum.ablumCatagory = $a.data('category');
            ablum.pageNum = p;
            ablum.ablumName = $('#ablum-qry-val').val();
            $queryAblumDate(null, ablum, function(res){
                    $proceeTplData($('#oneCategory'), $('#oneCategory-div'), res);
                    $initTotalTxt(res);
                },
                function(res){
                    layer.msg('加载失败', {icon: 2});
                });
        }

        // 处理layui模板数据
        function $proceeTplData($tplObj, $domObj, data) {
            checkAll.removeClass("active");
            layui.use('laytpl', function() {
                var laytpl = layui.laytpl;
                var getTpl =$tplObj.html();
                laytpl(getTpl).render(data, function (html) {
                    $domObj.html(html);
                });
            });

        }

        // 文件夹点击事件
        function $dirClick(obj){
            var $this = $(obj);
            var pidx = $this.data('idx'), category = $this.data('category');
            var secondAllName = $(obj).children("span").html();

            $('.fuIxtL-left').show();
            $('.fuIxtL-right').find('a:last').after('<span class="leaveFor">></span><a class="fuIxtLName" data-idx="'+pidx+'" data-category="'+category+'" href="javascript:void(0);" onclick="$breadClick(this);" >'+secondAllName+'</a>');
            // 当前名称、pidx、类目
            var $val = $('button[id^="ablumBtn-"]').filter(".active").eq(0).val();
            ablum ={};
            ablum.ablumType = $val;
            ablum.pidx = pidx;
            ablum.ablumCatagory = category;
            $queryAblumDate(null, ablum, function(res){
                    $proceeTplData($('#oneCategory'), $('#oneCategory-div'), res);
                    $initTotalTxt(res);
                    $("#Menus-page").remove();
                    if(res.status == Ms.SUC_CODE){
                        $createPage(res.totalPages,res.pageNum,function(p){
                            paging(p);
                        });
                    }
                },
                function(res){
                    layer.msg('加载失败', {icon: 2});
                });
        }

        // 文件点击事件
        function $picClick(obj){
            var $this = $(obj);
            var $box = $this.find('.source').find('.checkbox');
            if($box.hasClass("active")){
                $box.removeClass("active");
                checkAll.removeClass("active");
            }else{
                $box.addClass("active");
            }
            /* 相册层 */
            layer.photos({
                photos: '#layer-photos',
                anim: 5
            });


        }

        // 面包屑点击事件
        function $breadClick(obj) {
            var $this = $(obj);
            // 清空当前面包屑后面的，如果点击的是 全部文件，需要隐藏 返回上一级
            $this.nextAll().remove();
            !$this.data('idx') ? $('.fuIxtL-left').hide():'';
            // 当前名称、pidx、类目
            var $val = $('button[id^="ablumBtn-"]').filter(".active").eq(0).val();
            ablum ={};
            ablum.ablumType = $val;
            ablum.pidx = $this.data('idx');
            ablum.ablumCatagory = $this.data('category');
            $queryAblumDate(null, ablum, function(res){
                    $proceeTplData($('#oneCategory'), $('#oneCategory-div'), res);
                    $initTotalTxt(res);
                    $("#Menus-page").remove();
                    if(res.status == Ms.SUC_CODE){
                        $createPage(res.totalPages,res.pageNum,function(p){
                            paging(p);
                        });
                    }
                },
                function(res){
                    layer.msg('加载失败', {icon: 2});
                });
        }



        /* 商品相册 */
        $(function(){

            // 相册按钮点击事件,默认第一个点击
            $('button[id^="ablumBtn-"]').on('click',function () {
                $(this).addClass("active").siblings().not('#download').removeClass("active");
                // 初始化面包屑
                $('.fuIxtL-left').hide();
                $('.fuIxtL-right').find('a:first').nextAll().remove();
                // 清空搜索框
               // $('#ablum-qry-val').val('');
                var $val =$(this).attr('value');
                ablum ={};
                ablum.ablumType = $val;
               // ablum.ablumCatagory = $val;
               // ablum.ablumName = $val;
               // ablum.pageNum = $val;
                $queryAblumDate(null, ablum, function(res){
                        // console.info(res);

                        $proceeTplData($('#oneCategory'), $('#oneCategory-div'), res);

                        $initTotalTxt(res);
                        $("#Menus-page").remove();
                        if(res.status == Ms.SUC_CODE){
                            $createPage(res.totalPages,res.pageNum,function(p){
                                paging(p);
                            });
                        }
                    },
                    function(res){
                        layer.msg('加载失败', {icon: 2});
                    });

            }).eq(0).click();


            /* 全选 */
            checkAllCont.on('click', function(){
                var $picUL = $('#layer-photos');
                var $dirUL = $('#oneCategory-ul');

                if ($dirUL.length >0){
                    // 文件夹视图
                    var $oneUl = $('#oneCategory-ul .fileCont .span-checkbox');
                    if(checkAll.hasClass("active")){
                        checkAll.removeClass("active");
                        $oneUl.removeClass("active");
                    }else{
                        checkAll.addClass("active");
                        $oneUl.addClass("active");
                    }
                } else if($picUL.length > 0){
                    // 文件视图
                    var $box = $('.photo-content .sourceCont .source .checkbox');
                    if(checkAll.hasClass("active")){
                        checkAll.removeClass("active");
                        $box.removeClass("active");
                    }else{
                        checkAll.addClass("active");
                        $box.addClass("active");
                    }
                }

            });

            // 搜索
            $('#ablum-qry').on('click',function () {
                var val = $('#ablum-qry-val').val();
              /*  if(!val){
                    layer.msg('请输入要搜索的内容', {time:1500,icon: 6});
                    return false;
                }*/
                var $val = $('button[id^="ablumBtn-"]').filter(".active").eq(0).val();
                ablum ={};
                ablum.ablumType = $val;
                ablum.ablumName = val;
                var $a = $('.fuIxtL-right').find('a:last');
                ablum.pidx = $a.data('idx');
                ablum.ablumCatagory = $a.data('category');
                $queryAblumDate(null, ablum, function(res){
                        $proceeTplData($('#oneCategory'), $('#oneCategory-div'), res);
                        $initTotalTxt(res);
                        $("#Menus-page").remove();
                        if(res.status == Ms.SUC_CODE){
                            $createPage(res.totalPages,res.pageNum,function(p){
                                paging(p);
                            });
                        }
                    },
                    function(res){
                        layer.msg('加载失败', {icon: 2});
                    });
            })


            /* 返回上一级 */
            $('.goBack').click(function(){
                var $bread = $('.fuIxtLName');
                var $leaveFor = $('.leaveFor');
                var len1 = $bread.length;
                var len2 = $leaveFor.length;

                $('.fuIxtL-right').find('.fuIxtLName').eq(len1-1).remove();
                $('.fuIxtL-right').find('.leaveFor').eq(len2-1).remove();

                if(len1 == 2){
                    $('.fuIxtL-left').hide();
                }
                // 调用点击事件
                $('.fuIxtL-right').find('.fuIxtLName').eq(len1-2).click();

            });
            // 下载事件
            $('#download').on('click',function () {
                // 获取选中的文件、文件夹
                var $picUL = $('#layer-photos');
                var $dirUL = $('#oneCategory-ul');
                var idxArr = [];
                if ($dirUL.length >0){
                    // 文件夹视图
                    var $oneUl = $('#oneCategory-ul .fileCont .span-checkbox');
                    $oneUl.each(function () {
                        if($(this).hasClass("active")){
                            idxArr.push($(this).data('idx'))
                        }
                    })
                } else if($picUL.length > 0){
                    // 文件视图
                    var $box = $('.photo-content .sourceCont .source .checkbox');
                    $box.each(function () {
                        if($(this).hasClass("active")){
                            idxArr.push($(this).data('idx'))
                        }
                    })
                }
                if(idxArr.length == 0){
                    layer.msg('请选择要下载的文件或文件夹', {time:1500, icon: 2});
                    return false;
                }
                // 下载
                layer.msg('文件过多时，下载过程会有点慢，请耐心等待', {icon: 6});
                var $form = $("<form></form>");
                $form.attr('action',fileResourceApi + "/cos/downAblumZip").attr('method', 'POST');
                var $input = $("<input type='hidden' name='idxs' value='"+Ms.base64_encode(idxArr.join(","))+"' />");
                var $input2 = $("<input type='hidden' name='ablumName' value='" + $('button[id^="ablumBtn-"]').filter(".active").eq(0).text() + "' />");
                $form.append($input).append($input2).appendTo("body").css('display','none').submit();
                // window.location.href = '${fileResouruceUrl}' + "?idxs=" + Ms.base64_encode(idxArr.join(","));
            });


        });


    </script>

<jsp:include page="../../footer.jsp"/>
