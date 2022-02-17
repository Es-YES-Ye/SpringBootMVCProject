<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 등록</title>
<style>
h1 {
	background-color: pink;
	text-align: center;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
</head>
<body>
	<h1>(｡•̀ᴗ-)✧ 글 등록하기 ⍤ ˙͜ʟ˙</h1>
	<form action="insert" method="post">
		<div class="form-group">
			<label for="bno">ღ제목ღ</label> <input type="text" class="form-control"
				id="title" name="title" required="required">
		</div>
		<div class="form-group">
			<label for="bno">ღ글쓴이ღ</label> <input type="text"
				class="form-control" id="writer" name="writer" required="required">
		</div>
		<div class="form-group">
			<label for="bno">ღ내용ღ</label> <input type="text" class="form-control"
				id="content" name="content" required="required" >
		</div>
		<div class="form-group">
			<input type="submit" value="ღ등록하기ღ">
		</div>
	</form>
</body>
</html>