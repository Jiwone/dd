<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
<!-- 헤더부분 -->
<!-- jsp action tag -->
<jsp:include page="../../inc/header.jsp"/>
   <div id="visual">
      <div class="content-container"></div>
   </div>


   <div id="body" class="clearfix">
      <div class="content-container">

         <aside id="aside">

            <!-- <h1>고객센터</h1>
         
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
         </nav> -->
         </aside>

         <main id="main"> 공지사항
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
            <form action="/customer/notice" method="get">
               <label>검색어</label> <input type="text" name="title" /> <input
                  type="submit" />
            </form>
         </div>
         <table border="1">
            <tr>
               <th>번호</th>
               <th>제목</th>
               <th>작성자</th>
               <th>작성일</th>
               <th>조회수</th>
            </tr>
            <c:forEach var="n" items="${list}">
               <tr>
                  <td><a href = "notice-detail">${ n.id }-${ sessionScope.x }</td>
                  <td>${ n.title }</td>
                  <td>newlec</td>
                  <td>${ n.regDate }</td>
                  <td>${ n.hit }</td>
               </tr>
            </c:forEach>
         </table>
         <span class="btn btn-default" href="">글쓰기</span> 
         <a class="btn btn-img btn-cancel" href="">취소</a> 
         </main>
      </div>
   </div>
   <footer id="footer">
      <div class="content-container"></div>
   </footer>

   <div id="floating">
      플로팅 박스
      <div></div>
   </div>
</body>
</html>