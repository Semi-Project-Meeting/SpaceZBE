<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height:100%;">
<head>
<meta charset="UTF-8">
<title>검색</title>
</head>
<style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .type {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .price {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
</style>
<body style="height:100%;">
<h1>test iframe</h1>
	<div id="map" style="width:90%;height:90%;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1111fdab3612baa8eaaf70fa4585ebdf&libraries=services"></script>
<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 4 // 지도의 확대 레벨 
	    }; 
	
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
	if (navigator.geolocation) {
	    
	    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
	    navigator.geolocation.getCurrentPosition(function(position) {
	        
	        var lat = position.coords.latitude, // 위도
	            lon = position.coords.longitude; // 경도
	        
	        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
	            message = '<div style="width:150px;text-align:center;padding:6px 0;font-size:13px;">현 위치</div>'; // 인포윈도우에 표시될 내용입니다
	        
	        // 마커와 인포윈도우를 표시합니다
	        displayMarker(locPosition, message);
	            
	      });
	    
	} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
	    
	    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
	        message = 'geolocation을 사용할수 없어요..'
	        
	    displayMarker(locPosition, message);
	}
	
	// JSON 받아오기!!
	var vos = {
	    "vos": [
	        {
	            "spaceId": 6,
	            "companyId": 1,
	            "memberId": 0,
	            "spaceName": "우리 집",
	            "info": null,
	            "moreInfo": null,
	            "imgName": "hello.png",
	            "type": "office",
	            "location": "경기 용인시 기흥구 기흥로116번길 100 (초원마을성원상떼빌)",
	            "price": 60000
	        },
	        {
	            "spaceId": 7,
	            "companyId": 1,
	            "memberId": 0,
	            "spaceName": "왕두꺼비 부대찌개 오리점",
	            "info": null,
	            "moreInfo": null,
	            "imgName": "hello.png",
	            "type": "desk",
	            "location": "경기 성남시 분당구 구미로 11 1층103호",
	            "price": 10000
	        },
	        {
	            "spaceId": 1,
	            "companyId": 1,
	            "memberId": 0,
	            "spaceName": "수원시청역",
	            "info": null,
	            "moreInfo": null,
	            "imgName": "hello.png",
	            "type": "meeting",
	            "location": "경기 수원시 권선구 효원로 지하 270",
	            "price": 40000
	        }
	    ]
	}
	
	var geocoder = new kakao.maps.services.Geocoder();

	var bounds = new kakao.maps.LatLngBounds();

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	for(var i = 0; i < vos.vos.length; i++) {
		let spaceId = vos.vos[i].spaceId;
		let spaceName = vos.vos[i].spaceName;
		let typeName = vos.vos[i].type;
		let priceNum = vos.vos[i].price;
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(vos.vos[i].location, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
				var imageSize = new kakao.maps.Size(24, 35);
				var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords,
		            title: spaceName,
			        image: markerImage
		        });
		        
// 		        '<div class="wrap">' + 
// 	            '    <div class="info">' + 
// 	            '        <div class="title">' + 
// 	            '            공간명' + 
// 	            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
// 	            '        </div>' + 
// 	            '        <div class="body">' + 
// 	            '            <div class="img">' +
// 	            '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
// 	            '           </div>' + 
// 	            '            <div class="desc">' + 
// 	            '                <div class="type">type</div>' + 
// 	            '                <div class="price">price</div>'
// 	            '            </div>' + 
// 	            '        </div>' + 
// 	            '    </div>' +    
// 	            '</div>';

		        var type = document.createElement('div');
		        type.className = 'type';
		        var typeValue = null;
		        if(typeName == 'office') {
		        	typeValue = document.createTextNode('오피스');
		        } else if(typeName == 'desk') {
		        	typeValue = document.createTextNode('데스크');
		        } else {
		        	typeValue = document.createTextNode('회의실');
		        }
		        type.appendChild(typeValue);
		        
		        var price = document.createElement('div');
		        price.className = 'price';
		        var priceValue = null;
		        if(typeName == 'office') {
		        	priceValue = document.createTextNode(priceNum + '원 / 시간');
		        } else {
		        	priceValue = document.createTextNode(priceNum + '원 / 일');
		        }
		        price.appendChild(priceValue);
		        
		        var linkDiv = document.createElement('div');
		        var linkA = document.createElement('a');
		        linkA.href = 'spaceInfo?spaceId=' + spaceId;
		        linkA.target = '_self';
		        linkA.className = 'link';
		        linkA.appendChild(document.createTextNode('장소 상세보기'));
		        linkDiv.appendChild(linkA);

		        var desc = document.createElement('div');
		        desc.className = 'desc';
		        desc.appendChild(type);
		        desc.appendChild(price);
		        desc.appendChild(linkDiv);
		        
		        var img = document.createElement('div');
		        img.className = 'img';
		        var imgTag = document.createElement('img');
		        imgTag.src = '';//추가 필요
		        imgTag.style.width = '73';
		        imgTag.style.height = '70';
		        img.appendChild(imgTag);

		        var body = document.createElement('div');
		        body.className = 'body';
		        body.appendChild(img);
		        body.appendChild(desc);
		        
		        var title = document.createElement('div');
		        title.className = 'title';
		        var titleValue = document.createTextNode(spaceName);
		        title.appendChild(titleValue);
		        
		        var closeBtn = document.createElement('div');
		        closeBtn.className = 'close';
		        title.appendChild(closeBtn);

		        var info = document.createElement('div');
		        info.className = 'info';
		        info.appendChild(title);
		        info.appendChild(body);
		        
		        var overlayContent = document.createElement('div');
		        overlayContent.className = 'wrap';
		        overlayContent.appendChild(info);
		        
		        var img = document.createElement('div');
		        img.className = 'img';
		        var imgTag = document.createElement('img');
		        imgTag.style.width = '73';
		        imgTag.style.height = '70';
		        img.appendChild(imgTag);
		        body.appendChild(img);
		        
		        
		        
		        info.appendChild(body);
		        overlayContent.appendChild(info);
		        
		        var overlay = new kakao.maps.CustomOverlay({
		            map: map,
		            position: marker.getPosition()       
		        });
		        
		        overlay.setContent(overlayContent);
		        
		        overlay.setMap(null);
		        
		     	// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		        kakao.maps.event.addListener(marker, 'click', function() {
		            overlay.setMap(map);
		        });
		     	
		     	closeBtn.onclick = function() {
		     		overlay.setMap(null);
		     	}

		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;font-size:13px;">' + spaceName +'</div>'
		        });
		        infowindow.open(map, marker);
		        
		        bounds.extend(coords);
		    } 
		    map.setBounds(bounds);
		});
	}
	
	// 지도에 마커와 인포윈도우를 표시하는 함수입니다
	function displayMarker(locPosition, message) {
		
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({  
	        map: map, 
	        position: locPosition
	    }); 
	    
	    var iwContent = message, // 인포윈도우에 표시할 내용
	        iwRemoveable = true;
	 
	    // 인포윈도우를 생성합니다
	    var infowindow = new kakao.maps.InfoWindow({
	        content : iwContent,
	        removable : iwRemoveable
	    });
	    
	    // 인포윈도우를 마커위에 표시합니다 
	    infowindow.open(map, marker);
	}
</script>
</body>
</html>