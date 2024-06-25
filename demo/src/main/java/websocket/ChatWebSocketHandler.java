package websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatWebSocketHandler implements WebSocketHandler{
	//채팅클라이언트 모아놓은 리스트
	List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//웹소켓 연결시 1번 실행
		list.add(session);
		System.out.println
		("클라이언트수=" + list.size() + " - " +  session.getRemoteAddress() + " ip에서 접속");
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 연결 도중 여러번 실행
		//1. 1개 클라이언트 메시지 수신
		//String msg = (String)message.getPayload();//크롬-ok, edge-error(sts 콘솔 출력)
		String msg = String.valueOf(message.getPayload());////크롬-ok, edge-ok
/*edge는 메시지송수신 없이 수초 정도 대기후 java.nio.HeapByteBuffer 객체를 자동으로 보내는데 이 부분이
 *String 형변환안되고 오류 발생하면서 자동 연결해제함. 
 *그래서 객체를 String.valueOf(Object) 메소드 이용하여 처리.
 *출력값은 java.nio.HeapByteBuffer[....] 형식의 값이므로 
 *아래처럼 java.nio.HeapByteBuffer 포함시 클라이언트로 전송안함.
 **/
		
		if(!msg.contains("java.nio.HeapByteBuffer")) {//for edge
		//2. 접속 모든 클라이언트 메시지 송신
		for(WebSocketSession socket : list) {
			WebSocketMessage<String> sendmsg = new TextMessage(msg);
			socket.sendMessage(sendmsg);
		}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// 웹소켓 연결해제시 1번 실행
		list.remove(session);
		System.out.println
		("클라이언트수=" + list.size() + " - " +  session.getRemoteAddress() + " ip에서 접속해제");

	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		//오류처리.사용x
	}


	@Override
	public boolean supportsPartialMessages() {
		//부가정보 생성. 사용x
		return false;
	}
	
}
