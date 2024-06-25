package spring.mybatis.board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 직접 데이터가 존재하는 곳에 접근하는 객체
// SQL-MAPPING.XML 정의 sql들 6개 각각 실행 메소드 6개 정의
@Repository
public class MemberDAO {
	@Autowired
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	List<MemberDTO> memberList(){

	//전체회원List형태
	List<MemberDTO> list = session.selectList("memberList");
	//for(MemberDTO dto : list) {
	//	System.out.println(dto);
	//}
	return list;
	}
	MemberDTO oneMember(String id) {
	//1명회원조회
	MemberDTO dto = session.selectOne("oneMember" , id);
	//System.out.println(dto);
	return dto;
	}
	int memberCount(){
		int count = session.selectOne("memberCount" );
		//System.out.println(count + " 명의 회원이 있습니다.");
		return count;
	}
	
	int insertMember(MemberDTO dto) {
		int insertrows = session.insert("insertMemberDTO", dto);
		//System.out.println(insertrows + " 명의 회원 추가되었습니다.");
		return insertrows;
	}
	
	
	/*MemberDTO dto = new MemberDTO();
	dto.setId("mybatis2");
	dto.setName("김강산");
	dto.setPw(2222);  
	dto.setPhone("010-2345-5555");
	dto.setEmail("kang@c.com");
	
	int insertrows = session.insert("insertMemberDTO", 	dto);
	session.commit(); 
	System.out.println(insertrows + " 명의 회원 추가되었습니다.");
	*/
	
	int updateMember(MemberDTO dto) {
	//MemberDTO dto = new MemberDTO();
	//dto.setId("mybatis2");
	//dto.setPw(2000);  
	//dto.setEmail("mybatis2@c.com");
		return session.update("updateMember", dto);
	//System.out.println(dto.getId() + " 회원의 암호와 이메일을 수정하였습니다.");
	
	}
	
	int deleteMember(String id) {
		return session.delete("deleteMember",id );
	//System.out.println("mybatis2" + " 회원 장보 삭제되었습니다.");
	}
	
	//페이징처리리스트
	List<MemberDTO> memberPagingList(int [] limit){
		return session.selectList("memberPagingList", limit);
	}
	
	//암호검색리스트
	List<MemberDTO> searchMemberList(int[] pw_array){
		/*ArrayList<Integer> list = new Arraylist();
		list.add(1111);
		list.add(2222);
		list.add(3333);
		return session.selectList("searchMemberList", list);*/
		return session.selectList("searchMemberList", pw_array);
		
	}
	
	//컬럼명, 값에 맞는 검색리스트
	List<MemberDTO> searchMemberList2(HashMap map){
		return session.selectList("searchMemberList2", map);
	}

	//name 변수값 존재여부에 따른 검색리스트
	List<MemberDTO> searchMemberList3(MemberDTO dto){
		return session.selectList("searchMemberList3", dto); //dto.getName() 있다 where 
	}
	
	List<MemberDTO> searchMemberList4(MemberDTO dto){
		return session.selectList("searchMemberList4", dto); //dto.getName() 있다 where 
	}
	
	
	
	
	
}

//	RowBounds rb = new RowBounds(0,10);