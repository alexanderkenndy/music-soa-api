<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("contextPath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试测试jlucene接口</title>
</head>
<body>
	<form id="testForm" target="songinfoWin" action="${contextPath}/rest"
		method="get" >
		<input type="hidden" name="appkey" value="test" /> 
		<input type="hidden" name="appsig" value="1" /> 
		<input type="hidden" name="token" value="test" />
		<table align="center" border="1">
			<tr>
				<td colspan="4" align="center">个人搜索测试</td>
			</tr>
			<tr>
				<td colspan="4">
				  <input name="method" type="radio" value="tp.jlucenesearchsongs" checked="checked">歌曲搜索
				  <input name="method" type="radio" value="tp.jlucenesearchartists">歌手搜索
				  <input name="method" type="radio" value="tp.jlucenesearchalbums">专辑搜索
				  <input name="method" type="radio" value="tp.jlucenesearchkeywords">关键字搜索
				</td>
			</tr>
			<tr id="songTag">
				<td align="right">歌曲：</td>
				<td colspan="3"><input type="text" name="song" /></td>
			</tr>
			<tr id="artistTag">
				<td align="right">歌手：</td>
				<td colspan="3"><input type="text" name="artist" /></td>
			</tr>
			<tr id="albumTag">
				<td align="right">专辑：</td>
				<td  colspan="3"><input type="text" name="album" /></td>
			</tr>
			<tr>
				<td align="right">关键字：</td>
				<td colspan="3"><input type="text" name="keyname" ></td>
			</tr>
			<tr>
				<td  colspan="4" align="center">第:<select name="page">
					<option selected="selected">1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
					<option>7</option>
					<option>8</option>
					<option>9</option>
					<option>10</option>
				</select>
				页&nbsp;&nbsp;
				每页:<select name="pagecount">
					<option selected="selected">10</option>
					<option>20</option>
					<option>30</option>
					<option>40</option>
					<option>50</option>
					<option>60</option>
					<option>70</option>
					<option>80</option>
					<option>90</option>
					<option>100</option>
				</select>条
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit"  value="搜索" />&nbsp;&nbsp;<input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>