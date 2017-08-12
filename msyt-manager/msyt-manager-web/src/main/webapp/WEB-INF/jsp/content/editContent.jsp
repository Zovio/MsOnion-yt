<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <title>洋桃供应链后台管理系统-内容列表</title>
    <jsp:include page="../member/common/memberCommon.jsp"></jsp:include>
    <script type="text/javascript" charset="utf-8" src="/resources/plugins/ueditor/ueditor.config.js?_v=${js$version}"></script>
    <script type="text/javascript" charset="utf-8" src="/resources/plugins/ueditor/ueditor.all.min.js?_v=${js$version}"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/resources/plugins/ueditor/lang/zh-cn/zh-cn.js?_v=${js$version}"></script>
</head>
<body>
<section class="main" style="margin-bottom: 0;">
    <div class="table-container">
        <div class="main-content">
            <!--编辑采集信息 start-->
            <div class="bigWinCont">
                <div class="gatherEdit">
                    <form id="dataForm">
                        <input type="hidden" id="idx" name="idx" value="${content.data.idx}"/>
                    <div class="gather-row">
                        <p class="identification"><span class="identification-sp">基本信息</span></p>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>名称：</label>
                            <input id="name" name="name"  type="text" class="right-value" value="${content.data.name}" />
                            <p class="gather-bt hint1">*名称必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>编码：</label>
                            <input id="code" type="text" ${!empty content.data.code?'readonly':''}  class="right-value" value="${content.data.code}" />
                            <p class="gather-bt hint2">*编码必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>有效开始时间：</label>
                            <input id="startTime" type="text" class="right-value" value="<fmt:formatDate value="${content.data.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                   readonly onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',lang:'zh-cn', skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
                            <p class="gather-bt hint3">*有效开始时间必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>有效结束时间：</label>
                            <input id="endTime" type="text" class="right-value" value="<fmt:formatDate value="${content.data.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                   readonly onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',lang:'zh-cn', skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                            <p class="gather-bt hint4">*有效结束时间必填</p>
                        </div>
                        <div class="col-chunk">
                            <label class="left-key"><i>*</i>类型：</label>
                            <select id="type" class="drop-down">
                                <option value="1" ${content.data.type eq 1?'selected':''}>HTML</option>
                               <!-- <option value="2" ${content.data.type eq 2?'selected':''}>图片</option>-->
                            </select>
                            <i class="downIcon"></i>
                            <p class="gather-bt">*类型必填</p>
                        </div>
                        <div class="col-chunk-h">
                            <label class="left-key">备注：</label>
                            <textarea id="remark" class="right-value-area">${content.data.remark}</textarea>
                        </div>
                    </div>
                    <div id="transcript" style="background-color: #FFFFFF;">
                        <p class="identification"><span class="identification-sp">HTML</span></p>
                        <div class="utf-editor">
                            <script id="content" type="text/plain" class="editorContainer">${content.data.content}</script>
                        </div>
                    </div>
                    </form>
                    <div id="picture" style="display: none;">
                        <div class="gather-row" >
                            <p class="identification">图片</p>
                            <div class="col-chunk-video">
                                <label class="left-key" style="width: 110px;">图片：</label>
                                <input type="file" class="right-value-video" />
                            </div>
                            <div class="upCont picTr">
                                <div class="col-chunk">
                                    <label class="left-key">名称：</label>
                                    <input type="text" name="name" class="right-value"/>
                                </div>
                                <div class="col-chunk">
                                    <label class="left-key">title：</label>
                                    <input type="text" name="title" class="right-value"/>
                                </div>
                                <div class="col-chunk">
                                    <label class="left-key">排序号：</label>
                                    <input type="text" name="orderNumber" class="right-value"
                                           onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)"/>
                                </div>
                                <div class="col-chunk">
                                    <label class="left-key">链接地址：</label>
                                    <input type="text" name="linkUrl" class="right-value"/>
                                </div>
                                <div class="col-chunk-img">
                                    <label class="left-key">图片：</label>
                                    <img src="../../images/default2.png" alt="默认"/>
                                    <input type="hidden" name="imageSmall"/>
                                    <input type="hidden" name="imageMiddle"/>
                                    <input type="hidden" name="imageBig"/>
                                </div>
                            </div>

                        <c:forEach items="${content.data.contentImageList}" var="m">
                            <div class="upCont picTr">
                                <div class="col-chunk">
                                    <label class="left-key">名称：</label>
                                    <input type="text" name="name" class="right-value" value="${m.name}"/>
                                </div>
                                <div class="col-chunk">
                                    <label class="left-key">title：</label>
                                    <input type="text" name="title" class="right-value" value="${m.title}"/>
                                </div>
                                <div class="col-chunk">
                                    <label class="left-key">排序号：</label>
                                    <input type="text" name="orderNumber" class="right-value"
                                            onblur="checkNum($(this));" value="${m.orderNumber}"/>
                                </div>
                                <div class="col-chunk">
                                    <label class="left-key">链接地址：</label>
                                    <input type="text" name="linkUrl" onblur="checkUrl($(this));"   class="right-value" value="${m.linkUrl}"/>
                                </div>
                                <div class="col-chunk-img">
                                    <label class="left-key">图片：</label>
                                    <img src="../../images/default2.png" alt="默认"/>
                                    <input type="hidden" name="imageSmall" value="${m.imageSmall}"/>
                                    <input type="hidden" name="imageMiddle" value="${m.imageMiddle}"/>
                                    <input type="hidden" name="imageBig" value="${m.imageBig}"/>
                                </div>
                            </div>

                        </c:forEach>

                        </div>
                    </div>

                </div>
            </div>
            <!--编辑采集信息 end-->
            <div class="fixed-save">
                <input type="button" id="savaContent" class="save-chunk" value="保存" />
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var $ue;
        $(function () {
            // 编辑器
            $ue = UE.getEditor('content', {
                toolbars: [
                    [   'source',
                        'undo', // 撤销
                        'redo', // 重做
                        'bold', // 加粗
                        'indent', // 首行缩进
                        'italic', // 斜体
                        'underline', // 下划线
                        'strikethrough', // 删除线
                        'subscript', // 下标
                        'pasteplain', // 纯文本粘贴模式
                        'selectall', // 全选
                        'horizontal', // 分隔线
                        'removeformat', // 清除格式
                        'unlink', // 取消链接
                        'inserttitle', // 插入标题
                        'fontfamily', // 字体
                        'fontsize', // 字号
                        'paragraph', // 段落格式
                        'link', // 超链接
                        'searchreplace', // 查询替换
                        'justifyleft', // 居左对齐
                        'justifyright', // 居右对齐
                        'justifycenter', // 居中对齐
                        'justifyjustify', // 两端对齐
                        'forecolor', // 字体颜色
                        'backcolor', // 背景色
                        'insertorderedlist', // 有序列表
                        'insertunorderedlist', // 无序列表
                        'directionalityltr', // 从左向右输入
                        'directionalityrtl', // 从右向左输入
                        'rowspacingtop', // 段前距
                        'rowspacingbottom', // 段后距
                        'imagecenter', // 居中
                        'lineheight', // 行间距
                        'autotypeset', // 自动排版
                        'touppercase', // 字母大写
                        'tolowercase', // 字母小写
                        'simpleupload', //单图上传
                        'insertimage' //多图上传
                        //'insertvideo' //视频
                    ]
                ]
            });
            $ue.ready(function() {
                $ue.execCommand('serverparam', {
                    'filePath': '${imgurl}'
                });
            });
            // select变换值
            $("#type").change(function(){
                var typeValue=$(this).val();
                if(typeValue==1){
                    $("#content").show();
                    $("#imageDiv").hide();
                }else{
                    $("#imageDiv").show();
                    $("#content").hide();
                }
            });
            // 提交表单
            $("#savaContent").click(function(){
                submitForm();
            });
        });


        // 提交表单
        function submitForm() {
            var idx=$.trim($('#idx').val())
            if (!$.trim($('#name').val())) {
                layer.msg("请填写名称", {icon: 0});
                $('#name').css("border", "1px solid #ff2c41");
                return false;
            }else{
                $('#name').css("border", "1px solid #dbdcde");
            }

            if (!$.trim($('#code').val())) {
                layer.msg("请填写编码", {icon: 0});
                $('#code').css("border", "1px solid #ff2c41");
                return false;
            }else{
                $('#code').css("border", "1px solid #dbdcde");
            }

            var startTime = $.trim($('#startTime').val());
            if (!startTime) {
                layer.msg("请填写有效开始时间", {icon: 0});
                $('#startTime').css("border", "1px solid #ff2c41");
                return false;
            }else{
                $('#startTime').css("border", "1px solid #dbdcde");
            }

            var endTime = $.trim($('#endTime').val());
            if (!endTime) {
                layer.msg("请填写有效结束时间", {icon: 0});
                $('#endTime').css("border", "1px solid #ff2c41");
                return false;
            }else{
                $('#endTime').css("border", "1px solid #dbdcde");
            }

            var typeValue = $.trim($('#type').val());
            if (typeValue == "" || typeValue == 0) {
                layer.msg("请选择类型", {icon: 0});
                $('#type').css("border", "1px solid #ff2c41");
                return false;
            }else{
                $('#type').css("border", "1px solid #dbdcde");
            }
            var contentHtml = $ue.getContent();
            if (typeValue == 1
                && (contentHtml == "")) {
                layer.msg("请填写内容", {icon: 0});
                return false;
            }
            var param2 = [];
            if (typeValue == 2
                && (parame2 == [])) {
                layer.msg("请上传图片", {icon: 0});
                return false;
            }
            // 提交content对象数据

            // 图片集合对象数据
            var checkCode = true;
            $("#picture picTr").each(function (i, e) {
                if (!checkNum($(this).find("input[name='orderNumber']"))) {
                    checkCode = false;
                    return false;
                }

                if (!checkUrl($(this).find("input[name='linkUrl']"))) {
                    checkCode = false;
                    return false;
                }

                var image = {};
                image.name = $.trim($(this).find("input[name='name']").val());
                image.title = $.trim($(this).find("input[name='title']").val());
                image.orderNumber = $.trim($(this).find("input[name='orderNumber']").val());
                image.linkUrl = $.trim($(this).find("input[name='linkUrl']").val());
                image.imageSmall = $.trim($(this).find("input[name='imageSmall']").val());
                image.imageMiddle = $.trim($(this).find("input[name='imageMiddle']").val());
                image.imageBig = $.trim($(this).find("input[name='imageBig']").val());
                param2.push(image);
            });
            if(!checkCode){
                return false;
            }
            // 组装提交数据
            var dataParam = {};
            dataParam.idx = idx;
            dataParam.name = $.trim($('#name').val());
            dataParam.code = $.trim($('#code').val());
            dataParam.type = $.trim($('#type').val());
            dataParam.startTimeStr = startTime;
            dataParam.endTimeStr = endTime;
            dataParam.remark = $.trim($('#remark').val());
            dataParam.content = contentHtml;
            dataParam.imageListStr = MS.base64_encode(JSON.stringify(param2));
            $.ajax({
                type: "POST",
                url: "/content/list/doEditContent",
                data: dataParam,
                dataType: 'json'
            }).done(function (data) {
                if (data.status == 200) {
                    layer.msg('操作成功！', {icon: 6},function(){
                        var index=parent.$("#tabs").tabs("getTab", "新增内容")
                        var curTabIndex =  parent.$("#tabs").tabs("getTabIndex",index);
                        parent.$contentGrid.datagrid('reload', {});
                        parent.$("#tabs").tabs("close", curTabIndex);
                    });
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            }).fail(function (result) {
               layer.msg(result.msg, {icon: 2});
            });
        }
        /**
         * 删除图片
         */
        function removeTr(obj){
            layer.confirm('您确定要删除吗？', {
                title: false,
                shade: 0,
                btn: ['确定', '关闭']
            }, function (index) {
                layer.msg('操作成功！', {icon: 6});
                $(obj).parents("tr").remove();
            });

        }
        /**
         * 检查数字
         */
        function checkNum(obj) {
            var thisVal = $($.trim($(obj).val()))
            if (!MS.isNumber(thisVal)) {
                layer.msg('必须填写数字！', {icon: 0});
                $(obj).focus();
                return false;
            }
        }
        /**
         * 检查URL
         */
        function checkUrl(obj){
            var thisVal = $($.trim($(obj).val()))
            if (thisVal
                    && !MS.isNumber(thisVal)) {
                layer.msg('必须填写正确的格式！', {icon: 0});
                $(obj).focus();
                return false;
            }
        }

        /**
         * 上传图片
         */
        function uploadImage(){

            /**
             *  <tr>
             <td><img src="http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg" width="100px" height="100px" >
             <input type="hidden" name="imageSmall"/>
             <input type="hidden" name="imageMiddle"/>
             <input type="hidden" name="imageBig"/></td>
             <td><input class="brandTxt required" style="width: 100px !important;"type="text" name="name" /></td>
             <td><input class="brandTxt required" style="width: 100px !important;" type="text" name="title" /></td>
             <td><input class="brandTxt required" style="width: 100px !important;"  type="text" name="orderNumber" /></td>
             <td><input class="brandTxt required" style="width: 100px !important;" type="text" name="linkUrl" /></td>
             <td><a href="javascript:void(0);" onclick="javascript:removeTr($(this));">删除</a></td>
             </tr>
             */
        }
    </script>
</section>

</body>
</html>
