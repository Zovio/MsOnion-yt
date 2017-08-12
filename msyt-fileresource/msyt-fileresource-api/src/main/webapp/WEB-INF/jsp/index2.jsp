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
			<h1 style="color:red; font-size:30px;">洋桃供应链-文件中心API 启动成功 COS上传！！！</h1>
			<br><br><br><br>

			<form name="userForm2" action="/cos/uploadFiles" enctype="multipart/form-data" method="post">
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
			<a href="/cos/download/2017072011110406728">測試下載</a>

			<a href="/cos/downFileZip?messageId=2017062711213142005">測試下載Zip</a>
			<a href="/cos/downAblumZip?idxs=40104,40095">測試相册下载Zip</a>
			<a href="http://yantaodev-1253852034.cosgz.myqcloud.com/test/20170627/20170627112130980_195.jpg">測試url下载</a>
		</div>

		<img src="http://yantaodev-1253852034.cosgz.myqcloud.com/test/20170627/20170627112130980_195.jpg" />
		<img src="http://gz.file.myqcloud.com/files/v2/1253371373/msytupload/test/panda.png" />


</body>
</html>