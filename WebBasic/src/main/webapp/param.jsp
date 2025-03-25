<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 실행되는 자바코드
	String searchWord = request.getParameter("searchWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Your searchWord: <%= searchWord%></h1> <!-- jsp에서 자바 코드를 가져오는 것 -->
</body>
</html>

<%--
1. jsp는 compiler에 의해 servlet 변환
2. Html like 코드 <= html 안에 자바 코드를 위치
3. <%__%>
--%>