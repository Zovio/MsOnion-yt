<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>洋桃供应链-系统服务</title>
</head>
<body>
		<div style="text-align: center">
			<h1 style="color:red; font-size:30px;">洋桃供应链-文件中心API 启动成功！！！</h1>
			<br><br><br><br>

			<form name="userForm2" action="/fr/uploadFiles" enctype="multipart/form-data" method="post">
			<div>模块路径：<input type="input" name="filePath" value="${path}"></div>
			<div>备注：<input type="input" name="remark" ></div>
			<div >
				<input type="file" name="file1">
				<input type="file" name="file2">
			</div>
			<br>
			<div >
				<input type="submit" value="上传" >
			</div>
			</form>
			<br><br><br><br>
			<a href="/fr/downFile?messageId=2017060511000408111">測試下載</a>
			<a href="/fr/downFileZip?messageId=433c1150-8b43-41b4-befb-7dd2775dfa93">測試下載Zip</a>
			<a href="/fr/downAblumZip?idxs=40004&name=test">測試相册下载Zip</a>
			<a href="/fr/delFile?messageId=0195e87d-e589-4821-abd3-d1e6933c4b7c">測試删除单个文件</a>
		</div>




</body>
</html>