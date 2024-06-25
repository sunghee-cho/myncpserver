<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function(){
	var websocket;
	$("#enterbtn").on('click', function(){
	  websocket	 = new WebSocket("ws://localhost:9091/chatws");
	  websocket.onopen= function(){console.log("웹소켓 연결 성공");}
	 
	 websocket.onclose= function(){console.log("웹소켓 연결 해제 성공");}
	 
	 websocket.onmessage= function(server){
		 console.log("웹소켓으로부터 수신 성공"); 
		//$("#chatarea").append("<div>"+server.data+"</div>");
		//alert(server.data.constructor.name)
		if(server.data.indexOf($("#nickname").val()) >=0 ){
			$("#chatarea").append("<div class=me>"+server.data+"</div>");
			$(".me").css("color","green");
			
		}
		else{
			$("#chatarea").append("<div class=other>"+server.data+"</div>");
		}$(".other").css("color","black");
	 }
	});
	
	$("#exitbtn").on('click', function(){
		websocket.close();
	});
	
	$("#sendbtn").on('click', function(){//메시지전송버튼클릭시
		//nickname : message
		websocket.send($("#nickname").val() + ":"+ $("#message").val() );
		$("#message").val('');
	});
	
	$("#message").on('keydown', function(e){//메시지엔터시
		//엔터키 
		if(e.keyCode == 13){
			websocket.send($("#nickname").val() + ":"+ $("#message").val() );
			$("#message").val('');
		}
	});
});
</script>
</head>
<body>
닉네임 <input type=text id="nickname" value=${param.id } >
<input type=button value="입장" id="enterbtn">
<input type=button value="퇴장" id="exitbtn">

<h1>채팅 영역</h1>
<div id="chatarea" style="background-color:yellow;border:2px solid black">
 채팅내용표시 <br>
</div>
대화 : <input type=text id="message">
<input type=button value="메시지전송" id="sendbtn">

</body>
</html>