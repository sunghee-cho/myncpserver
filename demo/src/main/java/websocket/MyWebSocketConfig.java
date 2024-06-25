package websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//import lombok.RequiredArgsConstructor;

@Configuration
//@RequiredArgsConstructor
@EnableWebSocket //현재 설정으로 등록된 웹소켓 활성화
public class MyWebSocketConfig implements WebSocketConfigurer{
	@Autowired
	ChatWebSocketHandler handler ;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler , "/chatws").setAllowedOrigins("*");
		
	}
	
	
	
}
