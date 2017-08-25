
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="notice" method="get">

		<label>검색어</label>
		<input type="text" name="title" />
		<input type="submit"/>
	</form>
	<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	
	<%-- <% for (int i=0; i<list.size();i++){
	%> --%>
<%-- 	<c:forEach var="i" begin="0" end="3" step="2"> --%>
	<c:forEach var="n" items="${list}" >
	<tr>
		<td><%-- <%=list.get(i).getId() %> get(0)일ㄸ--%>${n.id}</td>
		<td><%-- <%=list.get(i).getTitle() %> --%>${n.title }</td>
		<td>지원</td>
		<td><%-- <%=list.get(i).getRegDate() %> --%>${n.regDate }</td>
		<td><%-- <%=list.get(i).getHit() %> --%>${n.hit }</td>
		
	</tr>
	</c:forEach>
	<%-- <%} %> --%>
	
	</table>
</body>
</html>