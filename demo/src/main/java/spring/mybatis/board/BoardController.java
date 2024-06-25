package spring.mybatis.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
 //boardlist 
 //boardwrite
// "/" = start.jsp	
	@RequestMapping("/")
	String start(){
		return "board/start";
	}
	@GetMapping("/boardwrite")
	String writeform() throws Exception{
		//new BoardDAO().insertBoardTransaction();
		return "board/writeform";
	}
	
	@PostMapping("/boardwrite")
	ModelAndView writeprocess(BoardDTO dto){//title, contents, writer, pw
		int insertrow = service.registerBoard(dto);
		ModelAndView mv = new ModelAndView();
		mv.addObject("insertrow", insertrow);
		mv.setViewName("board/start");
		return mv;
	}
	
	@RequestMapping("/boardlist")
	ModelAndView boardlist(@RequestParam(value="pagenum", required=false, defaultValue="1") int pagenum) {
		int pagecount = 3;
		int start = (pagenum -1) * pagecount;
		int limit[] = {start, pagecount};
		
	//모델 얻기 위한 sql 매핑 dao, service 메소드 
		int totalboard = service.getTotalBoard();
		List<BoardDTO> boardlist = service.getPagingBoardList(limit);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("totalboard", totalboard);
		mv.addObject("boardlist", boardlist);
		mv.setViewName("board/list");
		return mv;
		
	}
		//- 매개변수 : 페이지번호  미입력시 페이지번호는 1 로 처리)
	 
	@RequestMapping("/boarddetail")
	ModelAndView boarddetail(int seq) {
		BoardDTO dto = service.updateViewcountAndGetDetail(seq);
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("detaildto", dto);
		mv.setViewName("board/detail");
		return mv;
	}

	@RequestMapping("/boarddelete")
	String boarddelete(int seq) {
	//- seq 게시물 삭제 sql 실행 - 뷰이름(모델없고) redirect:/boardlist 리턴(boardlist.jsp 뷰)
	    int delete = service.deleteBoard(seq);
	   //delete - 1 확인
	    
	   // boardlist url 메소드 이미 구현 처리 
	   //게시물갯수 sql 수행 모델 저장
	   //?페이지 리슽 sql 수행 모델 저장
	   //boardlist view
	   return "redirect:/boardlist";
	   
	}
	
	@RequestMapping("/boardupdate")
	String boardupdate(BoardDTO dto) {
		service.updateBoard(dto);
		
	   // boardlist url 메소드 이미 구현 처리 
	   //게시물갯수 sql 수행
	   //?페이지 리슽 sql 수행 
	   //boardlist view
	   return "redirect:/boardlist";
	}
}





