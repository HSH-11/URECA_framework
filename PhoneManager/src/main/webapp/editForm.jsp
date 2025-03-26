<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.CustomerDTO, dao.CustomerDAO" %>
<%
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");

	CustomerDAO dao = new CustomerDAO();
	CustomerDTO customer = dao.findCustomer(name, phone);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>고객 수정</h2>
<form action="/PhoneManager/customer/update" method="post">
    <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
    이름: <input type="text" name="name" value="<%= customer.getName() %>"><br>
    이메일: <input type="text" name="email" value="<%= customer.getEmail() %>"><br>
    전화: <input type="text" name="phone" value="<%= customer.getPhone() %>"><br>
    주소: <input type="text" name="address" value="<%= customer.getAddress() %>"><br>
    <input type="submit" value="수정 완료">
</form>
</body>
</html>
</body>
</html>