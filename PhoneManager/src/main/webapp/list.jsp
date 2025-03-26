<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.ProductDTO" %>
<%
	List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("ProductList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>휴대폰 상품 목록</h1>
	<table>
		<thead>
			<tr><th>상품번호</th><th>상품명</th><th>가격</th><th>재고</th></tr>
		</thead>
		<tbody>
<%
    for (ProductDTO p : productList) {
%>
    <tr>
        <td><%= p.getProductId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getPrice() %></td>
        <td><%= p.getStockQuantity() %></td>
    </tr>
<%
    }
%>
		</tbody>
	</table>
	<hr>
	<a href="/PhoneManager/insertForm.jsp">등록</a>
	<a href="/PhoneManager">홈</a>
</body>
</html>