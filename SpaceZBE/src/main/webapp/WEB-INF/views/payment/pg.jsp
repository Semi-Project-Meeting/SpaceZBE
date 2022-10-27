<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<html>
<head>
<title>SpaceZ</title>
<script src="https://code.jquery.com/jquery-3.6.0.slim.js"
	integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY="
	crossorigin="anonymous"></script>
<script>
	$(document).ready(function() {
		$("#importPayment").click(function() {
			payment(); //버튼 클릭하면 호출
		});
	})

	//버튼 클릭 시 실행
	function payment(data) {
		IMP.init("imp76177137");
		console.log("여기1");
		// IMP.request_pay(param, callback) 결제창 호출
		IMP.request_pay({ // param
			pg : "kakaopay.TC0ONETIME",
			pay_method : "card",
			merchant_uid : "${merchant_uid}",
			name : "노르웨이 회전 의자",
			amount : 10,
			buyer_email : "gildong@gmail.com",
			buyer_name : "홍길동",
			buyer_tel : "010-4242-4242",
			buyer_addr : "서울특별시 강남구 신사동",
			buyer_postcode : "01181"
		}, function(rsp) { // callback
			if (rsp.success) {
				alert("결제완료");
				// jQuery로 HTTP 요청
				jQuery.ajax({
					url : "http://localhost:8090/spaceZBE/payOK",
					method : "POST",
					headers : {
						"Content-Type" : "application/json"
					},
					data : {
						imp_uid : rsp.imp_uid,
						merchant_uid : rsp.merchant_uid
					}
				}).done(function(data) {
					// 가맹점 서버 결제 API 성공시 로직
				})
			} else {
				alert("실패: 코드(" + rsp.error_code + ") /메세지(" + rsp.error_msg
						+ ")");
			}
		});
	}
</script>

</head>
<body>
	<!-- jQuery -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<!-- iamport.payment.js -->
	<script type="text/javascript"
		src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>


	<h1>IAMPORT 결제 데모</h1>

	<button id="importPayment" type="button">결제테스트</button>
</body>
</html>
