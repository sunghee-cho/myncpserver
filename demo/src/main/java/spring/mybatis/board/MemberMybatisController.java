package spring.mybatis.board;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberMybatisController {
	
	@Autowired
	//@Qualifier("service")
	MemberService service;
	
	@GetMapping("boardlogin")
	String loginform(){
		return "board/loginform";
	}
	
	@PostMapping("boardlogin")
	String loginprocess(String id, int pw , HttpSession session){
		MemberDTO dto = service.oneMember(id);
		if(dto != null) {
			if(dto.getPw()== pw) {
				session.setAttribute("sessionid", dto.getId());
			}
//			else {
//				// 암호가 다르다
//			}
		}
//		else {
//			"회원가입부터 하세요"
//		}
	 return "board/start";
	}
	
	@RequestMapping("/boardlogout")
	String logout(HttpSession session){
		if(session.getAttribute("sessionid") != null) {
			session.removeAttribute("sessionid");
		}
		return "board/start";
	}
	
}



