<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" charset="utf-8"
	src="${static$domain}/js/jquery-3.1.1.min.js"></script>



<title>洋桃供应链后台管理系统</title>
</head>
<body>

		<h1 style="color: red;text-align: center;">洋桃供应链后台管理   菜单页面</h1>

		<h1 style="color: red">测试部门 ${type}</h1>
		<br /> <br />


		<div style="padding: 10px 10px 10px 10px;text-align: center;">
			<form id="registerForm" class="registerForm" method="post">
				<input type="hidden" id="idx" value="${dept.idx}"/>
				<table cellpadding="5">
					<tr>
						<td>部门名称:</td>
						<td><input type="text" id="name" style="width: 280px;" value="${dept.name}"></input></td>
					</tr>
					<tr>
						<td>编码:</td>
						<td><input type="text" id="code" style="width: 280px;" value="${dept.code}" ></input></td>
					</tr>


					<tr>
						<td>上级部门:</td>
						<td><input type="text" id="pidx" style="width: 280px;" value="${dept.pidx}" ></input></td>
					</tr>


					<tr>
						<td>排序:</td>
						<td><input type="text" id="zindex" style="width: 280px;" value="${dept.zindex}"></input></td>
					</tr>

					<tr>
						<td>部门级别:</td>
						<td><select id="level" name="level">
								<option value="0">请选择</option>
							    <option value="1">一级部门</option>
							     <option value="2">二级部门</option>
						     </select>
						</td>
					</tr>

					<tr>
						<td>备注:</td>
						<td><textarea rows="5" cols="42" id="remark">${dept.remark}</textarea></td>
					</tr>


				</table>

			</form>
			<div style="padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
				   onclick="submitForm()">提交</a> <a href="javascript:void(0)"
													class="easyui-linkbutton" onclick="clearForm()">重置</a>
			</div>
		</div>



	
	<script type="text/javascript">
	
	$(function(){
		

		

	});

	// 提交表单
    function submitForm(){
        var params={};
        params.idx = $("#idx").val();
        params.name = $("#name").val();
        params.code = $("#code").val();
        params.pidx = $("#pidx").val();
        params.zindex = $("#zindex").val();
        params.level = $("#level").val();
        params.remark = $("#remark").val();
        $.ajax({
            type: "POST",
            url: "/dept/do-saveOrEdit",
            data: {
                idx:params.idx,
                name:params.name,
                code:params.code,
                pidx:params.pidx,
                zindex:params.zindex,
                level:params.level,
                remark:params.remark
			},
			dataType: 'json',
            success: function(data){
                if (data.status == 200) {
                    alert('操作成功!');
                } else {
                    alert(data.msg);
                }
            }
        });

    }
	
	</script>
	
</body>
</html>