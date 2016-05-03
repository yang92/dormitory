<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>외박 일수 관리</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
	function fnChangePermission(seqNo) {
		var grade = $("input[name=grade_" + seqNo + "]").val();
		var posDate = $("input[name=posDate_" + seqNo + "]").val();
		var useYn = $("select[name=useYn_" + seqNo + "]").val();
		$.ajax({
			url : "/permissionChange.admin",
			type : "post",
			data : {"grade" : grade, 
				    "posDate" : posDate, 
				    "useYn" : useYn,
				    "seqNo" : seqNo},
			success : function(data) {
				if(data.result == true) {
					alert("수정 성공");
					window.location.reload();
				} else {
					alert("수정 실패");
				}
			},
			error : function(error) {
				alert(error);
			}
		});
	}
	
	function fnDeletePermission(seqNo) {
		$.ajax({
			url : "/permissionDelete.admin",
			type : "post",
			data : {"seqNo" : seqNo},
			success : function(data) {
				if(data.result == true) {
					alert("삭제 성공");
					window.location.reload();
				} else {
					alert("삭제 실패");
				}
			},
			error : function(error) {
				alert(error);
			}
		});
	}
	
</script>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>학년</th>
				<th>외박일수</th>
				<th>사용유무</th>
				<th>기능</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>
						<input type="text" 
							name="grade_${item.SEQ_NO}"
							value="${item.GRADE}" />
					</td>
					<td>
						<input type="text" 
							name="posDate_${item.SEQ_NO}"
							value="${item.POS_DATE}" />
						</td>
					<td>
						<select name="useYn_${item.SEQ_NO}">
							<option value="Y" 
								<c:if test="${item.USE_YN == 'Y'}">
									selected="selected"
								</c:if>
							>
								사용
							</option>
							<option value="N"
								<c:if test="${item.USE_YN == 'N'}">
									selected="selected"
								</c:if> 
							>
								사용안함
							</option>
						</select>
					</td>
					<td>
						<input type="button" value="변경" 
							onclick="fnChangePermission('${item.SEQ_NO}')" />
						<input type="button" value="삭제"
							onclick="fnDeletePermission('${item.SEQ_NO}')" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form method="post" action="/permission.admin">
		학년 : <input type="text" name="grade" />
		<br />
		외박일수 : <input type="text" name="posDate" />
		<br />
		사용 유무 : 
		<input type="radio" name="useYn" value="Y" checked="checked" /> 사용
		<input type="radio" name="useYn" value="N" /> 사용안함
		<br />
		<input type="submit" value="외박일수 등록" />
		<input type="button" value="메인으로 이동"
		onclick="javascript:location.href='/adminMain.admin'" />
	</form>
</body>
</html>











