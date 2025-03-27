<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page import = "java.util.List" %>
<%
	List<String> strList = (List<String>)request.getAttribute("strList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>String List</h1>
<%
	for( String str: strList){
		
%>
	<h4><%= str %></h4>
<%
	}
%>
</body>
</html>