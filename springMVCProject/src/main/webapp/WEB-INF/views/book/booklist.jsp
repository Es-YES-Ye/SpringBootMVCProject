<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$(".delButton").click(function(){
			var bno = $(this).attr("data-bno");
			if(confirm("삭제하시겠습니까?")){
				location.href="delete?bno=" + bno;
			}
		});	
	});
</script>
<body>
<p>${message}</p>
<c:set var ="path" value="${pageContext.request.contextPath}/book"/>
<a href = "${path}/insert">신규 책 등록하기</a>
<h1 align="center"> 책의 목록</h1>
<table border="1">
	<tr>
		<td>책번호</td>
		<td>책제목</td>
		<td>작가</td>
		<td>출판사</td>
		<td>출판일</td>
		<td>상태</td>
		<td>삭제하기</td>
	</tr>
	<c:forEach items="${booklist}" var="book">
		<tr>
			<td><a href="${path}/detail?bno=${book.bno}">${book.bno}</a></td>
			<td>${book.title}</td>
			<td>${book.author}</td>
			<td>${book.pub}</td>
			<td>${book.pubDate}</td>
			<td>${book.status}</td>
			<td><button class="delButton" data-bno="${book.bno}">삭제</button></td>
		</tr>
	</c:forEach>


</table>

</body>
</html>