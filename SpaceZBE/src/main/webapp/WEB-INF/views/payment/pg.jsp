<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<html>
<head>
<title>SpaceZ</title>
<script src="https://code.jquery.com/jquery-3.6.0.slim.js"
	integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<!-- jQuery CDN --->
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

	function cancelPay() {
		jQuery.ajax({
			"url" : "http://localhost:8090/spaceZBE/refund", // 예: http://www.myservice.com/payments/cancel
			"type" : "POST",
			"contentType" : "application/json",
			"data" : JSON.stringify({
				"merchant_uid" : "imp_314911968246", // 예: ORD20180131-0000011
				"cancel_request_amount" : 10, // 환불금액
				"reason" : "테스트 결제 환불", // 환불사유
				"refund_holder" : "홍길동", // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
				"refund_bank" : "88", // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
				"refund_account" : "56211105948400" // [가상계좌 환불시 필수입력] 환불 수령계좌 번호
			}),
			"dataType" : "json"
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


	<button onclick="cancelPay()">환불하기</button>
</body>
</html>
