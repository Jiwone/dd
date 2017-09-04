<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	<jsp:include page="../../inc/header.jsp" />


	<!--   비주얼부분 -->
	<jsp:include page="../../admin/inc/visual.jsp" />

	<div id="body" class="clearfix">
		<div class="content-container">

			<!-- aside부분 -->
			<jsp:include page="../../admin/inc/aside.jsp" />
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
			<h2 class = "main title">공지사항</h2>
			

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
			
			<table class="table table-list">
				<tr>
					<th class="w60">번호</th>
					<th>제목</th>
					<th class="w100">작성자</th>
					<th class="w100">작성일</th>
					<th class="w60">조회수</th>
				</tr>
				<c:forEach var="n" items="${list}">
					<tr>
						<td>${ n.id }</td>
						<td class="title text-left text-indent"><a href="detail?id=${n.id}">${n.title}[${n.countCmt}]</a></td>
						<td>newlec</td>
						<td>${ n.regDate }</td>
						<td>${ n.hit }</td>
					</tr>
				</c:forEach>
			</table>
			
			<%-- ${fn:substringBefore((count/10),'.')} --%>
			<%-- <fn:substringBefore("23.45",",")/> --%>
			<c:set var="page" value="${param.p}"/>
			<!-- getparameter에서 p값 얻는 방법 : param.p -->
			<c:set var="startNum" value="${page-((page-1)%5) }"/>
			<c:set var="lastNum" value="${fn:substringBefore((count%10 == 0 ? count/10 : count/10 +1),'.')}"/>
			<!-- 조건문 ? 참일때 : 거짓일 때  -->
			<%-- <c:set var="lastNum" value="${count/10}"/> --%>
			 ${lastNum} 
			<div>
				<div><a href="?p=1">이전</a></div>
					<ul>
					<c:forEach var="i" begin="0" end="4">
					
					<c:set var="strong" value=""/>
					<c:if test="${page == startNum+i }">
						<c:set var="strong" value="text-strong"/>
						</c:if>
						
						<c:if test="${startNum+i <= lastNum}">
						<li><a class="${strong}" href="?p=${startNum+i}">${startNum+i}</a></li>
						</c:if>
							
					<%-- 	
						해당하는 번호만 오렌지색으로 나오게 !! if문 두개 쓰는게 너무 귀찮으니까 위처럼 하는 게 더 좋을 듯!
						<c:if test="${startNum+i==page}">
							<li><a class="text-strong" href="?p=${startNum+i}">${startNum+i}</a></li>
						</c:if>	
						<c:if test="${startNum+i!=page}">	
							<li><a href="?p=${startNum+i}">${startNum+i}</a></li>
						</c:if>	 --%>
					
					<!-- 	목록이 더이상 없으면 하이퍼링크 지움 -->
						<c:if test="${startNum+i > lastNum}">	
							<li>${startNum+i}</li>
						</c:if>
					</c:forEach>
					</ul>
				<div>
					<c:if test="${lastNum >= startNum+5 }">
					<a href="?p=${startNum+5}">다음</a>
					<!-- 게시물 목록이 다 끝나면 다음이 안보임  -->
					</c:if>
				</div>
				
			</div>
			
			<a class="btn btn-default" href="reg">글쓰기</a> 
			<a class="btn btn-img btn-cancel" href="">취소</a> </main>
		</div>
	</div>
	<!-- footer부분
 -->
	<jsp:include page="../../inc/footer.jsp" />



</body>
</html>