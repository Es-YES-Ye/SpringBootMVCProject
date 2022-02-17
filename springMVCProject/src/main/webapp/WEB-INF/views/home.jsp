<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  ${ myname }!
</h1>

<P>  The time on the server is ${serverTime}. </P>


<!--  login 창 열기 -->
<h1> <a href = "test/login"> 로그인 창 열기 상대경로 </a></h1><br>
<h1> <a href = "/education/test/login"> 로그인 창 열기 절대경로 </a></h1><br>


<form action="test/login">
	<input type="submit" value="로그인가기3 form">
</form>

<form action="test/login" method="post">
	<input type="text" name="userid" value="sesac">
	<input type="text" name="userpass" value="1234">
	<input type="submit" value="로그인가기5 form post방식">
</form>

<hr>
<button id="btn1">로그인하기4(JS)</button>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	//고전적인 방법
	$(function(){ //이 식이 람다식
		$("#btn1").click(function(){
			//BOM (Browser Object Model)
			// 객체 : window, document, location, history, screen
			
			location.href="test/login"; //이게 get 방식
		});	
	});
</script>
<hr>
<form action ="test/helloParam.do">
<input type="text" name="userid" value="sesac"><br>
<input type="text" name="userpass" value="aaaa"><br>
<input type="submit" value="파라미터보내기">
</form>
</body>
</html>
