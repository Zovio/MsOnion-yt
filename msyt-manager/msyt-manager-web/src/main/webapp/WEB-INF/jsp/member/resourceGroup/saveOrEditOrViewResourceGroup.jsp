<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统_资源组管理</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>

<style type="text/css">
    .func-button{width: 60px;height: 22px;background:#00d5b4;text-align:center;color: #fff;cursor: pointer;margin-top: 10px;}
</style>

<!--权限管理弹框 start-->
<div id="authorityCapacity" style="overflow-y: hidden;overflow-x: hidden;height: 419px;">
    <input type="hidden" id="idx" value="${resourceGroup.idx}"/>
    <div class="authorityRow">
        <label class="lb-name">资源组名称：</label>
        <input type="text" class="txt-name" id="resourceGroupName" maxlength="30" value="${resourceGroup.name}"/>
        <label class="lb-name">资源组编码：</label>
        <input type="text" class="txt-name" id="resourceGroupCode" onkeyup="MS.onlyEnOrNumber(this);" onblur="MS.onlyEnOrNumber(this);" maxlength="20" value="${resourceGroup.code}" ${type ne 'add' ? 'readonly="readonly"' :''}/>
    </div>
    <div class="authorityRow2">
        <label class="lb-name">选择权限：</label>
        <div class="authorityCont">
            <div class="contTop">
                <span class="qxMenu">权限菜单</span>
                <span class="qxMenu">
                    <label class="lb">功能按钮（</label><input class="checkbox" type="checkbox" id="button-all-checked" />
                    <label class="lb" style="cursor: pointer;" id="button-all-checked-txt">全选</label><label class="lb">）</label></span>
            </div>
            <div class="left-Menu">
                <ul id="authorityManageTree" class="easyui-tree"></ul>
            </div>
            <div class="right-check" id="authorityManageButtons">
               <%-- <p>
                    <input class="checkbox" type="checkbox" name="button-name"/>
                    <label class="lb">恢复</label>
                </p>--%>




            </div>
        </div>
    </div>
</div>
<!--权限管理弹框 end-->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>
<script type="text/javascript">
    var $mnTree = $('#authorityManageTree'),$buttonsDiv = $('#authorityManageButtons');
    var buttonsArr =[],menusArr=[];

    $(function(){
        // 加载菜单树
        var menuResourcesJson = ${menuResourcesJson};
        var buttonResourcesJson = ${buttonResourcesJson};
        $mnTree.html('<span style="font-size: 12px;">拼命加载中...</span>')
        $.ajax({
            timeout : Ms.AJAX_TIME_OUT,
            type : "POST",
            url : '/menu/getMenuData',
            cache : false,
        }).done(function (result) {
              // 初始化树菜单，
            $mnTree.tree({
                  data: result,
                  animate: true,
                  checkbox:true,
                  onClick: function (node) {
                      $mnTree.tree('toggle', node.target).tree('check',node.target);
                      // 如果是最底层菜单查询按钮列表
                      $('#button-all-checked').prop("checked",false);
                      if ($mnTree.tree("isLeaf", node.target)) {
                          initButtons(node.id);
                      }else {
                          // 清空按钮列表
                          $buttonsDiv.empty();
                      }
                  },
                  onLoadSuccess:function(){
                      // 初始化选中的树
                      menuResourcesJson &&  $.each(menuResourcesJson,function(i,item){
                          var node = $mnTree.tree('find',item.menuButtonIdx);
                          if(node && $mnTree.tree("isLeaf", node.target)){
                              $mnTree.tree('check',node.target);
                          }
                      });

                      // remove menu
                      if('${hideMenuIdxs}'){
                          var menuIdxs = '${hideMenuIdxs}';
                          var menuIdxArr = menuIdxs.split(",");
                          for (x in menuIdxArr) {
                              var node = $mnTree.tree('find',menuIdxArr[x]);
                              $mnTree.tree('remove', node.target);
                          }
                      }

                  }

            });
        }).fail(function(result) {
            layer.msg('加载菜单树失败,请重试...', {timeout:1500,icon: 2});
        });


        // 初始化按钮资源
        if(buttonResourcesJson){
            $.each(buttonResourcesJson,function(i,item){
                var str =item.ext+"_"+item.menuButtonIdx;
                buttonsArr.push(str);
            });
        }

        // 点击 功能按钮全选
        $('#button-all-checked').on('click',function () {
            var $subbox =$('input[name="button-name"]');
            $subbox.prop("checked",this.checked);
            $subbox.each(function () {
                doArr(this);
            });
        });

        $('#button-all-checked-txt').on('click', function () {
            $('#button-all-checked').click();
        });

    });

    /**
     * 查询菜单下面的buttons
     * @param menuIdx 菜单idx
     */
    function  initButtons(menuIdx){
        $.ajax({
            timeout : Ms.AJAX_TIME_OUT,
            type : "POST",
            url : '/menu/button/findButtonsByMenuIdx',
            data : {menuIdx : menuIdx},
            cache : false,
        }).done(function (result) {
            if(Ms.SUC_CODE == result.status){
                $buttonsDiv.empty();
                var $p='';
                $.each(result.data,function(i,item){
                   var checkedHtml ='',str =item.menuIdx+'_'+item.idx;
                   if($.inArray(str, buttonsArr)>-1){
                       checkedHtml ='checked="checked"';
                   }

                   $p += '<p> <input class="checkbox" type="checkbox" value="'+item.idx+'" menuIdx="'+item.menuIdx+'" '+checkedHtml+' name="button-name" onclick="doArr(this);"/><label class="lb" style="cursor: pointer" onclick="doArrTxt(this);">'+item.name+'</label> </p>';

                });
                if($p && $('#idx').val()){
                    $p +='<div class="func-button" onclick="saveCurButton();" title="保存当前按钮，不需要全部保存">保存</div>'
                }

                $buttonsDiv.append($p);
            }else{
                layer.msg('加载功能按钮失败,请重试...', {timeout:1500,icon: 2});
            }
        }).fail(function(result) {
            layer.msg('加载功能按钮失败,请重试...', {timeout:1500,icon: 2});
        });
    }

    /**
     * 按钮点击事件
     */
    function doArr(obj) {
        var $this = $(obj);
        var str =$this.attr("menuIdx")+"_"+$this.val();
        if (obj.checked) {
            // 从数组中清空
            buttonsArr.push(str);
        } else {
            // 存入数组中清空
            buttonsArr.splice($.inArray(str, buttonsArr), 1);
        }
        // console.info(buttonsArr);
    }

    function doArrTxt(obj) {
        $(obj).prev().click();
    }
    
    function saveMenuAndButton() {
        var nodes = $mnTree.tree('getChecked', ['checked','indeterminate']);
        menusArr = [];
        for(var i=0; i<nodes.length; i++){
            menusArr.push(nodes[i].id);
        }
        var name = $.trim($('#resourceGroupName').val());
        var code = $.trim($('#resourceGroupCode').val());
        var idx = $('#idx').val();
        if (!name) {
            layer.msg('请填写资源名称！', {icon: 2});
            parent.layer.closeAll('loading');
            return false;
        }
        if (!code) {
            layer.msg('请填写资源编码！', {icon: 2});
            parent.layer.closeAll('loading');
            return false;
        }
        if(menusArr.length == 0){
            layer.msg('请选择权限菜单！', {icon: 2});
            parent.layer.closeAll('loading');
            return false;
        }
        var buttonsStr = buttonsArr.join(",");
        var menuStr = menusArr.join(",");
//         console.info("buttonsArr: " + buttonsStr);
//         console.info("menusArr: " + menuStr);
//         return false;
        $.ajax({
           // timeout :Ms.AJAX_TIME_OUT,
            type: "POST",
            url: "/authManage/do-saveOrEdit",
            data: {
                idx: idx,
                name: name,
                code: code,
                buttonsStr: buttonsStr,
                menuStr: menuStr
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
                parent.layer.msg(MSYT.SUC_MSG, {time: 3000, icon: 6});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
            parent.$('#resourceGourplist').datagrid('reload', {});
        }).fail(function (result) {
            parent.layer.msg(Ms.ERROR_MSG, {icon: 2});
        });

    }

    // 保存当前按钮
    function saveCurButton() {
        parent.layer.load(2, {shade : 0.01, time : 50000});

        var idx = $('#idx').val();
        var menuIdx = '', arr = [];;
        var $subbox =$('input[name="button-name"]');
        $subbox.each(function () {
           if($(this).prop('checked')){
               menuIdx = $(this).attr('menuidx');
               arr.push($(this).val());
           }
        });
        var buttonsStr = arr.join(',');
        // 清空全部按钮的时候
        if(arr.length == 0){
            var node = $mnTree.tree('getSelected');
            menuIdx =node.id;
        }
        $.ajax({
            timeout :Ms.AJAX_TIME_OUT,
            type: "POST",
            url: "/authManage/saveCurButtons",
            data: {
                idx: idx,
                buttonsStr: buttonsStr,
                menuIdx: menuIdx
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                parent.layer.msg('保存按钮成功！', {time: 1500, icon: 6});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function (result) {
            parent.layer.msg(Ms.ERROR_MSG, {icon: 2});
        });
    }
</script>
</body>
</html>
