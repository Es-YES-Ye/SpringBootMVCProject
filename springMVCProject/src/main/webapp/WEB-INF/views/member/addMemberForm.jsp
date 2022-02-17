<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록 페이지</title>
<style type="text/css">
table {
	width: 500px;
}
</style>
<script type="text/javascript">
	function addMem() {
		let f = document.inputForm

		if (f.id.value == '') {
			alert('아이디를 입력하세요')
			f.id.focus()
			return false
		}

		if (f.name.value == '') {
			alert('이름을 입력하세요')
			f.name.focus()
			return false
		}

		if (f.password.value == '') {
			alert('비밀번호를 입력하세요')
			f.password.focus()
			return false
		}

		return true
	}
</script>
</head>
<body>
	<div align="center">
		<hr>
		<h2>회원 등록</h2>
		<hr>
		<br>
		<form action="register.do" method="post" onsubmit="return addMem()">
			<table border="1">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" required></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" required></td>
				</tr>
				<tr>
					<th width="100px">패스워드</th>
					<td><input type="password" name="password" required></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="email_id">&nbsp;@ <input
						type="text" name="email_domain" style="width: 30%"></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><input type="text" name="tel1" placeholder="000"
						style="width: 10%">&nbsp;- 
						<input type="text" name="tel2"
						placeholder="0000" style="width: 15%">&nbsp;- 
						<input
						type="text" name="tel3" placeholder="0000" style="width: 15%"></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td><input type="text" name="post"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="basic_addr" placeholder="주소"
						style="width: 50%"> 
						<input type="text" name="detail_addr"
						placeholder="상세 주소"></td>
				</tr>
			</table>
			<br> <input type="submit" value="등록">
		</form>
	</div>
</body>
</html>