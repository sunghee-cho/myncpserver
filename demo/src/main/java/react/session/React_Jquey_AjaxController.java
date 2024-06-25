package react.session;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@RestController
@Controller
public class React_Jquey_AjaxController {
	@RequestMapping("/hellostart")
	public String start() {
		System.out.println("start");
	   return "ajaxhello";
	}
	
//get+post 모두 기본(전송데이터없음)
/*@CrossOrigin(origins="*")
@RequestMapping("/helloajax")
@ResponseBody
public LoginDTO test() {
   return new LoginDTO("ID",1234);//json자동변환(스프링부트내장)
}
*/
//get + 전송데이터있음
//주의할 점으로 GET 통신에서는 @RequestParam을 사용하지만, POST 통신에서는 @RequestBody를 사용한다.

  @CrossOrigin(origins="*")
//@CrossOrigin(origins="*", allowCredentials = "true"
  @GetMapping("/helloajaxparam")
  @ResponseBody 
  public LoginDTO test(String id , int password ) {
  System.out.println(id+":"+password); 
  return new LoginDTO("get-"+id, password);//json자동변환(스프링부트내장) 
  }

  @CrossOrigin(origins="*") 
//@CrossOrigin(origins="*", allowCredentials = "true")
@PostMapping("/helloajaxparam")
@ResponseBody
public LoginDTO test2(@RequestBody LoginDTO dto) {
	 System.out.println("post전달받음"+ dto.id+":"+dto.password); 
   return new LoginDTO("post-"+dto.getId(), dto.getPassword());//json자동변환(스프링부트내장)
}

//세션전달.origins은 "*"설정시 보안문제로 세션못받음. 요청하는 클라이언트 ip로 설정함.
//allowCredentials = "true" 설정하면 세션 인증 활용 가능함.
@CrossOrigin(origins="http://localhost:3002", allowCredentials = "true")
@PostMapping("/loginsession")
@ResponseBody
public LoginDTO test3(@RequestBody LoginDTO dto, HttpSession session) throws Exception {
	 System.out.println("post전달받음"+ dto.id+":"+dto.password); 
	 session.setAttribute("sessiondto", dto);
	 return new LoginDTO("post-"+((LoginDTO)session.getAttribute("sessiondto")).getId(), ((LoginDTO)session.getAttribute("sessiondto")).getPassword());//json자동변환(스프링부트내장)
}
 
//세션확인
//@CrossOrigin(origins="*") 
@CrossOrigin(origins="http://localhost:3002", allowCredentials = "true")
@PostMapping("/loginsessionconfirm")
@ResponseBody
public LoginDTO test4(HttpSession session, HttpServletResponse response) throws Exception {
 LoginDTO dto = (LoginDTO)session.getAttribute("sessiondto");
return dto;//json자동변환(스프링부트내장)
}

//세션소멸
//@CrossOrigin(origins="*") 
@CrossOrigin(origins="http://localhost:3002", allowCredentials = "true")
@PostMapping("/loginsessionremove")
@ResponseBody
public LoginDTO test5(HttpSession session, HttpServletResponse response) throws Exception {
LoginDTO dto = (LoginDTO)session.getAttribute("sessiondto");
if(dto != null) {
	session.removeAttribute("sessiondto");
}
return (LoginDTO) session.getAttribute("sessiondto");//json자동변환(스프링부트내장)
}

@CrossOrigin(origins="*")
@GetMapping("/helloajaxarray")
@ResponseBody 

//react는 아래처럼 선언. 미리 paramsSerializer 로 분리하여 전송하므로!!!.
//public String testarray(int[] ids) {

//jquery $.ajax는 아래처럼. 배열은 ArrayList로 매핑.
public String testarray(@RequestParam(value="ids[]") ArrayList<Integer> ids) {
System.out.println(ids.toString()); 
return ids.toString();//json자동변환(스프링부트내장) 
}

/*int count=0;
@CrossOrigin(origins="*")
@RequestMapping("/helloajaxobject")
@ResponseBody
//jquery ajax - post 시에는 public String testobject(@RequestParam Map<String, Object> parameters)  로 선언할 것!!!
//react axios - post 시에는 아래.
public String testobject(@RequestBody Map<String, Object> parameters)  throws Exception{
	   String json = parameters.get("dtos").toString();
	   System.out.println(++count +":"+json);
	   //이 부분 여러번(갯수도 달라짐) 출력은 react가 자체적 렌더링할 때 여러번 호출해서 그럼.
	   //버튼 만들어 이벤트처리로 호출시 1번만 호출
	   ObjectMapper mapper = new ObjectMapper();
	   List<Object> dtoList = mapper.readValue(json, new TypeReference<ArrayList<Object>>(){});
	   return dtoList.get(0).toString();
}*/


@CrossOrigin(origins="*")
@RequestMapping("/playerInfo.do")
@ResponseBody
//jquery ajax - post 시에는 public String playerList(@RequestParam Map<String, Object> parameters)
//react axios - post 시에는 아래.
public String playerList(@RequestBody Map<String, Object> parameters) throws Exception{
   
   //playerList로 넘어온 데이터를 문자열로 변환 
   String json = parameters.get("playerList").toString();
   System.out.println(json); 
   ObjectMapper mapper = new ObjectMapper();
   
   //변환된 데이터를 List형태에 저장
   //JSON 파일을 Java 객체로 deserialization 하기 위해서 ObjectMapper의 readValue() 메서드를 이용
   //List<Map<String, Object>> playerList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
   //return playerList.get(0).get("player").toString();
   
   //리스트를 DTO로 받아야할 경우는 아래와 같이 사용 가능
   List<Object> playerList = mapper.readValue(json, new TypeReference<ArrayList<Object>>(){});
   return playerList.get(0).toString();
   
}


}

class LoginDTO{
	String id;
	int password;
	public LoginDTO() {}
	public LoginDTO(String id, int password) {
		this.id = id;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", password=" + password + "]";
	}
	
}


