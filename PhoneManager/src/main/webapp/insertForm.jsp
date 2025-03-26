<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>휴대폰 상품 등록</h1>
	<form action="/PhoneManager/product/insert" method="post" > 
		<p>상품명:<input type="text" name="name"></input></p>
		<p>가격:<input type="text" name="price"></input></p>
		<P>수량:<input type="text" name="stock"></input></P>
		<button type="submit">등록</button>	
	</form>
	<hr>
	<a href="/PhoneManager/product/list">목록</a>
</body>
</html>