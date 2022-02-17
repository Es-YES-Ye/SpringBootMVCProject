<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 게시글 보기</title>
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
	<h1>(｡•̀ᴗ-)✧ 글 상세보기 ⍤ ˙͜ʟ˙</h1>
	<form action="update" method="post">
		<div class="form-group">
			<!-- board번호는 반드시 넘긴다... -->
			<input type="hidden" class="form-control" id="bno" name="bno"
				value="${board.bno}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="bno">ღ제목ღ</label> <input type="text" class="form-control"
				id="title" name="title" value="${board.title}">
		</div>
		<div class="form-group">
			<label for="bno">ღ글쓴이ღ</label> <input type="text"
				class="form-control" id="writer" name="writer"
				value="${ board.writer }">
		</div>
		<div class="form-group">
			<label for="bno">ღ내용ღ</label> <input type="text" class="form-control"
				id="content" name="content" value="${ board.content }">
		</div>
		<div class="form-group">
			<input type="submit" value="ღ수정하기ღ">
		</div>
		<div class="form-group">
			<input type="button" id="delButton" value="ღ삭제하기ღ">
		</div>
		<div class="form-group">
			<input type="button" id="listButton" value="ღ목록으로ღ">
		</div>
	</form>

	<script>
		$(function() {
			$("#delButton").click(function() {
				if (confirm("삭제하시겠습니까?")) {
					location.href = "delete?bno=${board.bno}";
				}
			});

			$("#listButton").click(function() {
				location.href = "list";
				
			});
		});
	</script>

</body>
</html>