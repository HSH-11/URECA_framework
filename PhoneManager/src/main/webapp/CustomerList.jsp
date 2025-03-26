<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.CustomerDTO" %>
<%
    List<CustomerDTO> customerList = (List<CustomerDTO>) request.getAttribute("customerList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>고객 목록</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #aaa; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        a { margin: 0 5px; }
    </style>
</head>
<body>
    <h1 style="text-align:center;">📋 고객 목록</h1>

    <table>
        <thead>
            <tr>
                <th>고객번호</th>
                <th>고객명</th>
                <th>이메일</th>
                <th>전화번호</th>
                <th>주소</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (customerList != null && !customerList.isEmpty()) {
                for (CustomerDTO c : customerList) {
        %>
            <tr>
                <td><%= c.getCustomerId() %></td>
                <td><%= c.getName() %></td>
                <td><%= c.getEmail() %></td>
                <td><%= c.getPhone() %></td>
                <td><%= c.getAddress() %></td>
                <td>
					<a href="/PhoneManager/editForm.jsp?name=<%= c.getName() %>&phone=<%= c.getPhone() %>">수정</a>
                    <a href="/PhoneManager/customer?job=delete&customerId=<%= c.getCustomerId() %>"
                       onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="6">등록된 고객이 없습니다.</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <div style="text-align:center;">
        <a href="/PhoneManager/insertForm.jsp">➕ 고객 등록</a>
        <a href="/PhoneManager">🏠 홈</a>
    </div>
</body>
</html>
