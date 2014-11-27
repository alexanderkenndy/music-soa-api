<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("contextPath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试测试uploadmetadata接口</title>
</head>
<body>
	<form id="testForm" target="songinfoWin" action="${contextPath}/rest"
		method="post" enctype="multipart/form-data" >
		<input type="hidden" name="method" value="tp.statclientusage" /> 
		<input type="hidden" name="appkey" value="test" /> 
		<input type="hidden" name="appsig" value="1" /> 
		<input type="hidden" name="token" value="test" />
		<table>
			<tr>
				<td>选择文件：</td>
				<td>
				<input type="file" name="file" />
				
				</td>
			</tr>
				<tr>
				<td>名字：</td>
				<td>
				<input type="text" name="filename" >
				
				</td>
				
			</tr>
			<tr>
				<td><input type="submit" value="上传" /></td>
				<td><input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>