<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃供应链后台管理系统-主页</title>

    <jsp:include page="../common/memberCommon.jsp"></jsp:include>


</head>
<body>
<!--头部logo和核心导航 start-->

<div class="yt-head">
    <div class="home"><a class="yt-logo" href="/"></a></div>
    <div class="yt-nav">
        <ul>
          <c:forEach items="${sessionScope.topshow$msyt}" var="menu" varStatus="status">
              <li class="nav-list" id="${menu.id}"><a href="javascript:void(0)">${menu.text}</a></li>
          </c:forEach>
        </ul>
        <div class="likeLink">
        <div id="ycWWWTitle" class="ycWWWTitle">
            <c:if test="${ycWWWResult.status==200}">
                ${ycWWWResultContent}
            </c:if>
        </div>
        <div id="ycindexTitle" class="ycindexTitle">
            <c:if test="${ycIndexResult.status==200}">
                ${ycIndexResultContent}
            </c:if>
        </div>
        <div id="indexTitle" class="indexTitle">
            <c:if test="${indexResult.status==200}">
                ${indexResultContent}
            </c:if>
        </div>
        </div>
        <div id="personMouseover">
            <div class="yt-personal">
                <span class="personal-icon"></span>
                <span class="personal-name">${sessionScope.member$msyt.name}<i></i></span>
            </div>
            <div class="set-center">
                <i class="set-center-icon"></i>
                <div class="set-help">
                    <ol>
                        <li> <a class="set" id="editMember" url="/sys/edit/${sessionScope.member$msyt.idx}"><i></i>个人设置</a></li>
                        <li> <a class="password" id="editPassword" url="/sys/updatePassword/${sessionScope.member$msyt.idx}"><i></i>修改密码</a></li>
                        <%--<li><a class="help"><i></i>帮助</a></li>--%>
                        <li> <a class="out" href="/sys/logout"><i></i>退出</a></li>
                    </ol>
                </div>

            </div>
        </div>
    </div>
</div>

<!--头部logo和核心导航开始 end-->
<!--左边菜单栏 start-->
<div class="yt-left-container">
    <span id="menu-hide" style="display: none;"><i></i> </span>
    <div class="yt-left-menu">
        <div class="menu-top">导航菜单<i id="menu-show"></i></div>
        <div class="menu-c" >
            <ul id="menu" class="easyui-tree" >

            </ul>
        </div>
    </div>
</div>
<!--左边菜单栏 end-->
<!--右边内容框 start-->
<div class="yt-right-container">
    <!--tabs 窗口 start-->
    <div class="win-nav">
        <div data-options="region:'center',title:''">
            <div id="tabs" data-options="tools:'#tab-tools',tabHeight:31" class="easyui-tabs win-nav-cont">
                <div title="主页" style="background-color: white;padding: 10px;padding-bottom:30px;box-sizing: border-box;">
                <c:if test="${systemIndexResult.status==200}">
                    ${systemIndexResult.data.content}
                </c:if>
                </div>
            </div>
        </div>

    </div>
</div>
<!--右边内容框 end-->
<!--底部版权信息 start-->
<div class="yt-footer">
    <div class="yt-footer-container" >
        <c:if test="${copyrightResult.status==200}">
          <c:out value="${copyrightResult.data.content}" escapeXml="false"/>
        </c:if>
    </div>
</div>
<!--底部版权信息 end-->
<%--右键关闭tabs标签--%>
<div class="yt-right-hand">
    <div id="mm" class="easyui-menu" style="width: 140px;">
        <div id="mm-tabclose" name="6">刷新</div>
        <div id="Div1" name="1">关闭</div>
        <div id="mm-tabcloseall" name="2">关闭全部</div>
        <div id="mm-tabcloseother" name="3">关闭其他标签页</div>
        <div id="mm-tabcloseright" name="4">关闭右侧标签页</div>
        <div id="mm-tabcloseleft" name="5">关闭左侧标签页</div>
    </div>
</div>
<%--右键关闭tabs标签--%>

<script>
    var treeJson = ${sessionScope.menuJson$msyt};
    $(function() {
        /* tabs 标签 */

        var $tree = $('#menu');
        $tree.tree({
            data : treeJson,
            animate:true,
            onClick: function (node) {
                // var height = $(window).height()-136;
                var tmp = node ,nodeId = node.id;
                while ($tree.tree('getParent',tmp.target) != null) {
                    tmp=$tree.tree('getParent',tmp.target);
                    nodeId = tmp.id;
                }

                $("#" + nodeId).length > 0 ? $("#" + nodeId).addClass("active").siblings().removeClass("active"):$('.yt-nav .nav-list').removeClass("active");
                $tree.tree('toggle', node.target).tree('select', node.target);

                if ($tree.tree("isLeaf", node.target)) {
                    var tabs = $("#tabs");
                    var tab = tabs.tabs("getTab", node.text);
                    if (tab) {
                        tabs.tabs("select", node.text);
                        tab.panel('refresh', node.attributes[0].url);
                    } else {
                        tabs.tabs('add', {
                            title: node.text,
                            href: node.attributes[0].url,
                            // content:'<iframe  scrolling="auto" src="'+node.attributes[0].url+'" frameborder="0" style="width:100%;height:'+height+'px;"></iframe>',
                            closable: true,
                            bodyCls: "content",
                        });
                    }
                }
            }
        });


        /* 点击头部的核心菜单关联左边菜单栏 */
        $('.nav-list').click(function(){
            $(this).addClass("active").siblings().removeClass("active");
            var node = $tree.tree('find', $(this).attr("id"));
            $tree.tree('collapseAll').tree('expand', node.target).tree('select', node.target);
            // $('#menu').tree('scrollTo', node.target);
        });

        $('.nav-list') && $('.nav-list:eq(0)').click();

       var height = $(window).height()-110;
        /*var mainHtmlcontent = '<iframe scrolling="auto" frameborder="0"  src="/memberMain" style="width:100%;height:'+height+'px;"></iframe>';

        $("#tabs").tabs('add', {
            title: "主页",
            content:mainHtmlcontent,
            closable: false,
            bodyCls: "content",
        });*/
    });

</script>

<script type="text/javascript" src="${static$domain}/js/common/index.js?_v=${js$version}"></script>
</body>
</html>

