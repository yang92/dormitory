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
<script>
	function fnChange(seqNo) {
		var state 
			= $("select[name=state_" + seqNo +"]").val();
		var exp
			= $("input[name=exp_" + seqNo + "]").val();
		$("input[name=state]").val(state);
		$("input[name=exp]").val(exp);
		$("input[name=seqNo]").val(seqNo);
// 		$("form").attr("action", "/adminReq.do");
// 		$("form").attr("method", "post");
		$("form").submit();
	}	
</script>
</head>
<body>
	<form method="post" action="/adminReq.admin">
		<input type="hidden" name="state" />
		<input type="hidden" name="exp" />
		<input type="hidden" name="seqNo" />
	</form>
	
	<table border="2">
		<thead>
			<tr>
				<th>순번</th>
				<th>신청자ID</th>
				<th>시작일자</th>
				<th>종료일자</th>
				<th>신청일자</th>
				<th>승인(반려)일자</th>
				<th>신청상태</th>
				<th>관리자 의견</th>
				<th>기능</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.SEQ_NO}</td>
					<td>${item.ID}</td>
					<td>${item.START_DATE}</td>
					<td>${item.END_DATE}</td>
					<td>${item.REQ_DATE}</td>
					<td>${item.APP_DATE}</td>
					<td>
						<select name="state_${item.SEQ_NO}">
							<option value="1"
								<c:if test="${item.STATE == 1}">
								selected="selected"
								</c:if>
							>신청</option>
							<option value="2"
								<c:if test="${item.STATE == 2}">
								selected="selected"
								</c:if>
							>승인</option>
							<option value="3"
								<c:if test="${item.STATE == 3}">
								selected="selected"
								</c:if>
							>반려</option>
						</select>
					</td>
					<td>
						<input type="text" name="exp_${item.SEQ_NO}"
							value="${item.EXP}" />
					</td>
					<td>
						<input type="button" value="변경"
							onclick="fnChange('${item.SEQ_NO}')" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="button" value="메인으로 이동" 
			onclick="javascript:location.href='/adminMain.admin'" />
	<input type="button" value="엑셀 다운로드" 
			onclick="javascript:location.href='/excelDownload.do'" />
</body>
</html>











