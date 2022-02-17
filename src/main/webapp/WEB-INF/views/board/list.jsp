<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>게시 목록</title>
<style>
h1{
background-color: pink;
text-align: center;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

</head>
<body>
	<h1>(｡•̀ᴗ-)✧ 게시 목록 ⍤</h1>
	<br>
	<a href="insert">글 쓰기</a>
	<br>
	<table class="table table-striped table-bordered table-hover ">
		<tr>
			<th width="5%">ღ순서ღ</th>
			<th width="5%">ღ번호ღ</th>
			<th>ღ제목ღ</th>
			<th width="10%">ღ글쓴이ღ</th>
			<th>ღ내용ღ</th>			
			<th width="10%">ღ등록일ღ</th>
			<th width="10%">ღ수정일ღ</th>
			<td></td>			
		</tr>

		<c:forEach items="${ blist.result.content }" var="board" varStatus="bstatus">
			<tr>
			<td>${ bstatus.count}</td>
			<td>${ board.bno}</td>			
			<td><a href="detail?bno=${ board.bno}"><strong>${ board.title }</strong></a> <span class="badge"> ${ board.replies.size()}</span></td>
			<td>${ board.writer }</td>
			<td><strong>${ board.content }</strong></td>
			<td>${ board.regdate }</td>
			<td>${ board.updatedate }</td>
			<td><button class="delButton" data-bno="${ board.bno}">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
<!-- paging -->
  <nav>
   <div>
     <ul class="pagination">
       <c:if test="${blist.prevPage}">
         <li class="page-item">
           <a href="${blist.prevPage.pageNumber+1}">PREV ${blist.prevPage.pageNumber+1}</a>
         </li>
       </c:if>
       <c:forEach items="${blist.pageList}" var="p">
        <li class="page-item" class="${p.pageNumber==blist.currentPageNum-1}?active:''" >     
         <a href="${p.pageNumber}">${p.pageNumber+1}</a>
       </li> 
       </c:forEach>
        <c:if test="${blist.nextPage}">
         <li class="page-item">
           <a href="${blist.nextPage.pageNumber+1}">NEXT ${blist.nextPage.pageNumber+1}</a>
         </li>
       </c:if>
     </ul>
   </div>
  </nav>

<!-- 현재 페이지를 다시 조회하러 가야한다. action에 list -->
<form id="f1" action="list" method="get">
    <input type="hidden" name="page" value="${blist.currentPageNum}">
    <input type="hidden" name="size" value="${blist.currentPage.pageSize}">
</form> 
	
	
	
	<script>
		$(function(){
			$(".delButton").click(function(){
				location.href="delete?bno=" + $(this).attr("data-bno");
			});

			$(".pagination a").click(function(e){
	    		alert($(this).attr('href'));
	    	e.preventDefault();//본래 기능은 취소한다.
	    	$("#f1").find("[name='page']").val($(this).attr('href'));
	    	//아이디가 f1인 form에서 이름이 page인 애를 찾아서 그 value를 위에서 지정한 href 속성으로 바꿔라 
	    	$("#f1").submit();
	    	});

			
		});
	</script>
</body>
</html>


