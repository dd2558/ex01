<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
<%
	HttpSession usersession = request.getSession();
	usersession.invalidate();
	out.println("<h1>로그아웃 되었습니다");
%>
	<button onClick="index()">메인화면</button>
</body>
<script>
	const index = () => {
		location.href="/";
	}
</script>