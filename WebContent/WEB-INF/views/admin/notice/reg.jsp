<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../../css/style.css" type="text/css" rel="stylesheet" />

</head>
<body>
<!-- 헤더부분 -->
<!-- jsp action tag -->
<jsp:include page="../../inc/header.jsp"/>

<!--   비주얼부분 -->
<jsp:include page="../../customer/inc/visual.jsp"/>

   <div id="body" class="clearfix">
      <div class="content-container">

<!-- aside부분 -->
<jsp:include page="../../customer/inc/aside.jsp"/>
      <!--    <aside id="aside">

            <h1>고객센터</h1>
         
         <nav>
            <h1>고객센터 메뉴</h1>
            <ul>
               <li><a href="">공지사항</a></li>
               <li><a href="">1:1고객문의</li>
               <li><a href="">학습안내</li>
            </ul>
         </nav>
      
         <nav>
            <h1>추천사이트</h1>
            <ul>
               <li>앤서이즈</li>
               <li>W3C</li>
               <li>마이크로소프트</li>
            </ul>
         </nav>
         
         <nav>
            <h1>구글광고</h1>
         </nav>
         </aside> -->

         <main id="main">
         <h2>공지사항</h2>

         <div>
            <h3>경로</h3>
            <ol>
               <li>home</li>
               <li>고객센터</li>
               <li>공지사항</li>
            </ol>
         </div>

         <div>
            <h3>공지사항 검색 폼</h3>
            <form action="/admin/notice" method="get">
               <label>검색어</label> <input type="text" name="title" /> <input
                  type="submit" />
            </form>
         </div>
        <!--  form action=notice-list로 하면 현재 페이지와 같은 url이니까 지워도 상관없음 -->
         <form method="post">
         	<table class="table">
         
         <tr>
          	<th>제목</th>
          	<td colspan="3"> <input name = "title"/> </td>
         </tr>

          <tr>
          	<th>첨부파일</th>
          	<td colspan="3"></td>
         </tr>
         
          <tr>
          	<td colspan="4">
          	<textarea name = "content"></textarea></td>
         </tr>
 
         </table>
       <%--   <table border="1">
            <tr>
               <th>번호</th>
               <th>제목</th>
               <th>작성자</th>
               <th>작성일</th>
               <th>조회수</th>
            </tr>
            <c:forEach var="n" items="${list}">
               <tr>
                  <td>${ detail.id } </td>
                  <td>${ detail.title }</td>
                  <td>newlec</td>
                  <td>${ detail.regDate } </td>
                  <td> ${ detail.hit }</td>
               </tr>
            </c:forEach>
         </table> --%>
       
       <div>
       
        <input type="submit" class="btn btn-default" value = "등록"></a>
    <!--     수정할 내용이 담겨있어야 해 --> <!-- 추가옵션이 필요. 뒤에 ?필요  쿼리스트림이라고 함 (옵션값) --> 
        <a href="list" class="btn btn-default">취소</a> 
       
       </div>
         </form>
       <!-- 
     
         <span class="btn btn-default" href="">글쓰기</span> 
         <a class="btn btn-img btn-cancel" href="">취소</a>  -->
         </main>

      </div>
   </div>
<!-- footer부분
 -->
<jsp:include page="../../inc/footer.jsp"/>

</body>
</html>