<%@ page import="java.util.*" import="java.io.*" %>
<%
Properties props = new Properties();
String path = application.getRealPath("/WEB-INF/version.properties");
props.load(new FileInputStream(path));
String configs = props.getProperty("version");
%>
<%=configs%>
