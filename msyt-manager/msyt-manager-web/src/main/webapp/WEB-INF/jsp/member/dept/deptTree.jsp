<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>洋桃供应链后台管理系统</title>
</head>
<body>

		<h1 style="color: red;text-align: center;">洋桃供应链后台管理系统</h1>
		<h3 style="color: bisque;text-align: center;">部门树Demo</h3>
		<br>
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	
	<script type="text/javascript">
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            }
		};


		$(function(){
            $.post("/dept/treeData", {},
                function(data){
                    if (data.status == 200) {
                        var nodeJson = data.data;
                        $.fn.zTree.init($("#treeDemo"), setting, nodeJson);
                    } else {
                        alert(data.msg);
                    }
                }, "json");


           // $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	
	
	</script>
	
</body>
</html>