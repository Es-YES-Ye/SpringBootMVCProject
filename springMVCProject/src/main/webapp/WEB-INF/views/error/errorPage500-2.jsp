<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{ background-color: pink;}
</style>
</head>
<body>
<h1>오류 정보입니다.(기타오류)</h1><br>
<%=exception %><br>
<%=exception.getMessage() %><br>
</body>
</html>