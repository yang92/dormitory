<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=557843fc0fdf2131880c6c5dab8ceb77"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				// ����, �浵
				var oPoint = new nhn.api.map.LatLng(35.152618, 129.111548);
				// ��ô ����
				var defaultLevel = 14;
				
				// Main Class ����
				var oMap = new nhn.api.map.Map(document.getElementById('map'), {
					point : oPoint, // ǥ���Ǵ� ������ �߽� ��ǥ
					zoom : defaultLevel, // ��ô ����
					enableWheelZoom : true, // ���� Ȯ��/��� ���콺 �� ���
					enableDragPan : true, // �巡�׷� ���� ��ġ �̵�
					enableDblClickZoom : false, // ���� Ŭ������ ���� Ȯ��
					mapMode : 0, // ���� ��� (0 : �Ϲ�, 1 : ��ħ, 2 : ����)
					activateTrafficMap : false, // �ǽð� ���� ���� Ȱ��ȭ
					activateBicycleMap : false, // ������ ���� Ȱ��ȭ
					minMaxLevel : [ 1, 14 ], // ������ �ּ�/�ִ� ��ô ����
					size : new nhn.api.map.Size(500, 500) // ������ ũ��
				});
				
				// ���� Ȯ��/��� ���۹� ���
				var oSlider = new nhn.api.map.ZoomControl();
				oMap.addControl(oSlider);
				oSlider.setPosition({
					top : 10, left : 10
				});
				
				// ���� ���(�Ϲ�/����) ���� ���� ��ư ���
				var oMapTypeBtn = new nhn.api.map.MapTypeBtn();
				oMap.addControl(oMapTypeBtn);
				oMapTypeBtn.setPosition({
					bottom : 10,
					right : 80
				});
				
				// ��Ŀ ���
				var oSize = new nhn.api.map.Size(28, 37);
				var oOffset = new nhn.api.map.Size(14, 37);
				var oIcon = new nhn.api.map.Icon('/marker.png', oSize, oOffset);
// 				var oIcon = new nhn.api.map.Icon(
// 						'http://static.naver.com/maps2/icons/pin_spot2.png',
// 						oSize, oOffset);
				var oLabel = new nhn.api.map.MarkerLabel(); // - ��Ŀ �� ����.
				oMap.addOverlay(oLabel); // - ��Ŀ �� ������ �߰�. �⺻�� ���� ������ �ʴ� ���·� �߰���.
				var oMarker = new nhn.api.map.Marker(oIcon, {
					title : '�λ�IT���������б�'
				});
				oMarker.setPoint(oPoint); // ��Ŀ��ġ
				oMap.addOverlay(oMarker); // ��Ŀ ������ �߰�
				oLabel.setVisible(true, oMarker); // ��Ŀ �� �����ֱ�
				
				// �ǽð� ���� ���� ��ư ���
				var trafficButton = new nhn.api.map.TrafficMapBtn();
				trafficButton.setPosition({
					bottom : 10,
					right : 150
				});
				oMap.addControl(trafficButton);

				// �̺�Ʈ ��� 
				oMap.attach('mouseenter', function(oCustomEvent) {
					var oTarget = oCustomEvent.target;
					// ��Ŀ���� ���콺 �ö󰣰Ÿ�
					if (oTarget instanceof nhn.api.map.Marker) {
						var oMarker = oTarget;
						oLabel.setVisible(true, oMarker); // Ư�� ��Ŀ�� �����Ͽ� �ش� ��Ŀ�� title�� �����ش�.
					}
				});
				oMap.attach('mouseleave', function(oCustomEvent) {
					var oTarget = oCustomEvent.target;
					// ��Ŀ������ ���콺 �����Ÿ�
					if (oTarget instanceof nhn.api.map.Marker) {
						oLabel.setVisible(false);
					}
				});
			});
</script>
</head>
<body>
	<div id="map" style="border: 1px solid #000;"></div>
</body>
</html>










