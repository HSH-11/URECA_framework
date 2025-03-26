<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>도서 등록</h1>
	<form action="/BookManager/books/insert" method="post" > 
		<input type="text" name="bookId"></input><br>
		<input type="text" name="bookName"></input><br>
		<input type="text" name="publisher"></input><br>
		<input type="text" name="price"></input><br>
		<button type="submit">등록</button>	
	</form>
	<hr>
	<a href="/BookManager/books/list">목록</a>
</body>
</html>