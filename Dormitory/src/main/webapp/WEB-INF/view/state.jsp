<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>외박신청 현황</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
	외박 사용 가능 일수 : ${state.posDate}일
	<br />
	외박 실시 일수 : ${state.useDate}일
	<br />
	<table border="2">
		<thead>
			<tr>
				<th>순번</th>
				<th>시작일자</th>
				<th>종료일자</th>
				<th>신청일자</th>
				<th>승인(반려)일자</th>
				<th>신청상태</th>
				<th>관리자 의견</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.SEQ_NO}</td>
					<td>${item.START_DATE}</td>
					<td>${item.END_DATE}</td>
					<td>${item.REQ_DATE}</td>
					<td>${item.APP_DATE}</td>
					<td>
						<c:if test="${item.STATE == 1}">
						신청
						</c:if>
						<c:if test="${item.STATE == 2}">
						승인
						</c:if>
						<c:if test="${item.STATE == 3}">
						반려
						</c:if>
					</td>
					<td>${item.EXP}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="button" value="메인으로 이동" 
			onclick="javascript:location.href='/main.do'" />
</body>
</html>











