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
	<h1>도서 상세 및 수정</h1>
	<form action="/BookManager/books/update" method="post" > 
		<input type="text" name="bookId" value="<%=bookDto.getBookId()%>"></input><br>
		<input type="text" name="bookName" value="<%=bookDto.getBookName()%>"></input><br>
		<input type="text" name="publisher" value="<%=bookDto.getPublisher()%>"></input><br>
		<input type="text" name="price" value="<%=bookDto.getPrice()%>"></input><br>
		<button type="submit">수정</button>	
	</form>
	<a href="/BookManager/books/list">목록</a> <a href="/BookManager/books/delete?bookId=<%=bookDto.getBookId()%>">삭제</a>
	<hr>
</body>
</html>