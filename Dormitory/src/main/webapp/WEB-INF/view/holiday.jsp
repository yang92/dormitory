<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>휴일 관리</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
		$("input[name=hDate]").datepicker({
			showOn: "both", // 버튼과 텍스트 필드 모두 캘린더를 보여준다.
			//buttonImage: "/application/db/jquery/images/calendar.gif", // 버튼 이미지
			//buttonImageOnly: true, // 버튼에 있는 이미지만 표시한다.
			changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
			changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
			minDate: '-100y', // 현재날짜로부터 100년이전까지 년을 표시한다.
			nextText: '다음 달', // next 아이콘의 툴팁.
			prevText: '이전 달', // prev 아이콘의 툴팁.
			//numberOfMonths: [2,3], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다.
			//stepMonths: 3, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가. 
			yearRange: 'c-100:c+10', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
			showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
			currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
			closeText: '닫기',  // 닫기 버튼 패널
			dateFormat: "yy-mm-dd", // 텍스트 필드에 입력되는 날짜 형식.
			showAnim: "slide", //애니메이션을 적용한다.
			showMonthAfterYear: true , // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다. 
			dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] // 월의 한글 형식.
		});
	});
	
	function fnValid() {
		var id = $.trim($("input[name=id]").val());
		var name = $.trim($("input[name=name]").val());
		var grade = $.trim($("input[name=grade]").val());
		var startDate = $.trim($("input[name=startDate]").val());
		var endDate = $.trim($("input[name=endDate]").val());
		if(id == '' || name == '' || grade == '' || startDate == '' || endDate == '') {
			alert("모든 항목을 입력해주세요.");
			return false;
		}
		return true;
	}
	
	function fnChangeHoliday(seqNo) {
		var hDate = $("td[name=hDate_" + seqNo + "]").text();
		var hExp = $("td[name=hExp_" + seqNo + "]").text();
		var useYn = $("select[name=useYn_" + seqNo + "]").val();
		$.ajax({
			url : "/holidayChange.admin",
			type : "post",
			data : {"hDate" : hDate, 
				    "hExp" : hExp, 
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
	
	function fnDeleteHoliday(seqNo) {
		$.ajax({
			url : "/holidayDelete.admin",
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
				<th>일자</th>
				<th>설명</th>
				<th>사용유무</th>
				<th>기능</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td name="hDate_${item.SEQ_NO}">${item.H_DATE}</td>
					<td name="hExp_${item.SEQ_NO}">${item.H_EXP}</td>
					<td><select name="useYn_${item.SEQ_NO}">
							<option value="Y"
								<c:if test="${item.USE_YN == 'Y'}">
									selected="selected"
								</c:if>>
								사용</option>
							<option value="N"
								<c:if test="${item.USE_YN == 'N'}">
									selected="selected"
								</c:if>>
								사용안함</option>
					</select></td>
					<td><input type="button" value="변경"
						onclick="fnChangeHoliday('${item.SEQ_NO}')" /> <input
						type="button" value="삭제"
						onclick="fnDeleteHoliday('${item.SEQ_NO}')" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form method="post" action="/holiday.admin">
		휴일 : <input type="text" name="hDate" /> <br /> 설명 : <input
			type="text" name="hExp" /> <br /> 사용 유무 : <input type="radio"
			name="useYn" value="Y" checked="checked" /> 사용 <input type="radio"
			name="useYn" value="N" /> 사용안함 <br /> <input type="submit"
			value="휴일 등록" /> <input type="button" value="메인으로 이동"
			onclick="javascript:location.href='/adminMain.admin'" />
	</form>
</body>
</html>











