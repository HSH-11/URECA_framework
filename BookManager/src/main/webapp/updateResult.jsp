<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.BookDto" %>
<%
	BookDto bookDto = (BookDto) request.getAttribute("bookDto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>도서 수정 완료</h1>
	<a href='/BookManager/books/list'>도서 목록</a><br>
</body>
</html>