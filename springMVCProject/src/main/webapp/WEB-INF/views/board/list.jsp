
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="java.sql.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href="${ pageContext.request.contextPath }/resources/css/layout.css" >
<link rel = "stylesheet" href="${ pageContext.request.contextPath }/resources/css/board.css" >
<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
   
   $(document).ready(function() {
      $('#insertBtn').click(function() {
         location.href = "${pageContext.request.contextPath}/board/writeForm.do"
      })
      
      $("#search").on("click", function(){
    	  var mydata = {"keyword":$("#keyword").val(),
 					"contents":$("#contents").val()
 					};
    	  console.log(mydata);
    	  alert("검색 분류 : " + mydata.keyword + "검색 내용 : " + mydata.contents);
   	
    	//서버에 다녀오기(화면의 이동이 없이 이자리로 돌아온다)
    	//list.do?keyword=title&contents=aaa
		$.ajax({
	    	  url:"list.do",
	    	  data: mydata, //위에서 json으로 만든 형태를 데이터로 보낸다
	    	  success:function(responseData){
	    		  alert("다녀옴" + responseData);
	    		  $("#here").html(responseData);
	    	  }			
		});
    	  
    	  
      });
   })

      
   
   function doAction(boardNo){
  
       location.href = '${ pageContext.request.contextPath }/board/detail.do?no='+boardNo
/*<c:choose>
            <c:when test="${ not empty userVO }">
               location.href = '${ pageContext.request.contextPath }/board/detail.do?no='+boardNo
                     
         </c:when>
            <c:otherwise>
               if(confirm('로그인 서비스가 필요합니다\n로그인페이지로 이동하시겠습니까?')){
                  location.href = '${ pageContext.request.contextPath }/login.do'
               }   
            </c:otherwise>
         </c:choose>
  */ }
</script>
<style>
span{ background-color: pink;
}
</style>
</head>
<body>
   <header>
   
   </header>
   <section>
      <div align="center">
      <hr>
      <h2>게시판 목록
      <span>전체건수: ${boardCnt}</span>
      <span>${message}</span>
      </h2>
      <hr>
      검색 :
      <select id="keyword">
      	<option value="title">글제목</option>
      	<option value="writer" selected>글쓴이</option>
      </select>
      <input type="text" id="contents" placeholder="입력하세요">
      <button id="search">검색</button>
      <hr>
      <br>
      <div id="here">
     	 <table border = "1" class="list">
         <tr>
            <th width="7%">번호</th>
            <th>제목</th>
            <th width = "16%">글쓴이</th>
            <th width = "10%">조회수</th>
            <th width = "20%">등록일</th>
         </tr>
       
      <c:forEach items="${ list }" var="board" varStatus="bstatus">
       <tr>
          <td>${boardCnt-bstatus.count+1}</td>
          <td>
             <a href="javascript:doAction(${ board.no })">
                <c:out value="${ board.title }" />
             </a>
             
          </td>
          <td>${ board.writer }</td>
          <td>${ board.viewCnt }</td>
          <td>${ board.regDate }</td>
       </tr>
      </c:forEach>
      </table>
     </div>
      <br>
   <!--  <c:if test="${ not empty userVO }">
</c:if> -->
      <button id="insertBtn">새글등록</button>
      
   </div>   
   </section>    
  <!--  <footer>
   
   </footer>   -->
</body>
</html>

