package spring.mybatis.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisMain {//sql 호출 sql 실행 결과  - main(SERVLET/JSP MVC) - SERVICE -DTO/LIST - DAO(MAIN) - db
	public static void main(String args[]) throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = 
				builder.build(Resources.getResourceAsReader("mybatis/service/mybatis-config.xml"));
	
		SqlSession session = factory.openSession(true);//DML 자동 COMMIT 
		
		MemberDAO dao = new MemberDAO();
		dao.setSession(session);
		
		MemberServiceImpl service = new MemberServiceImpl();
		service.setDao(dao);
		
		/*System.out.println("====전체회원조회결과====");
		List<MemberDTO> list = service.memberList();
		for(MemberDTO listdto : list) {
			System.out.println(listdto);
		}
		
		System.out.println("====회원1명조회결과====");
		MemberDTO dto = service.oneMember("mybatis1");
		System.out.println(dto);
		
		System.out.println("====전체회원수조회결과====");
		int membercount = service.memberCount();
		System.out.println(membercount);
		
		System.out.println("====회원가입결과====");
		MemberDTO insertdto = new MemberDTO();
		insertdto.setId("mybatis4");
		insertdto.setName("김강산");
		insertdto.setPw(2222);  
		insertdto.setPhone("010-2345-5556");
		insertdto.setEmail("kang4@c.com");
		
		String insertresult = service.registerMember(insertdto);
		System.out.println(insertresult);
		
		System.out.println("====회원정보수정결과====");
		MemberDTO updatedto = new MemberDTO();
		updatedto.setId("mybatis4");
		updatedto.setPw(3333);  
		updatedto.setEmail("mybatis3@c.com");
		String updateresult = service.modifyMember(updatedto);
		System.out.println(updateresult);
		
		System.out.println("====회원탈퇴결과====");
		String deleteresult = service.deleteMember("mybatis4");
		System.out.println(deleteresult);
		*/
		
		//페이징처리리스트
		/*int page = 1;//현재사용자선택한페이지번호
		int membercount = 5; //한페이지에 보여줄 멤버수
		int start = (page-1)*membercount;
		int[] limit = {start, membercount};
		
		List<MemberDTO> list = service.memberPagingList(limit);
		for(MemberDTO dto:list) {
			System.out.println(dto);
		}*/
		
		//암호검색리스트
		/*int[] pw_array = {1111, 2222, 3333, 4444, 5555};
		List<MemberDTO> list2 = service.searchMemberList(pw_array);
		for(MemberDTO dto:list2) {
			System.out.println(dto);
		}
		*/
		//검색리스트- 2개파라미터 ( 컬럼명, 값)
		//HashMap
		/*HashMap<String, String> map = new HashMap();
		map.put("colname", "id");
		map.put("colvalue", "%db%");
		List<MemberDTO> list3 = service.searchMemberList2(map);
		for(MemberDTO dto:list3) {
			System.out.println(dto);
		}
		*/
		
		/*MemberDTO dto = new MemberDTO();
		//dto.setName("%길%");
		List<MemberDTO> list4 = service.searchMemberList3(dto);
		for(MemberDTO dto4:list4) {
			System.out.println(dto4);
		}*/
		
		MemberDTO dto = new MemberDTO();
		dto.setName("길");
		dto.setId("db");
		
		List<MemberDTO> list5 = service.searchMemberList4(dto);
		for(MemberDTO dto5:list5) {
			System.out.println(dto5);
		}
	}
}






