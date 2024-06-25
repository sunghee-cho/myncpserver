package boardmapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 직접 데이터가 존재하는 곳에 접근하는 객체
// SQL-MAPPING.XML 정의 sql들 6개 각각 실행 메소드 6개 정의
@Repository
@Mapper
public interface MemberDAO {
	
	List<MemberDTO> memberList();
	
	MemberDTO oneMember(String id) ;//게시판 로그인
	
	int memberCount();
	
	int insertMemberDTO(MemberDTO dto);
		
	int updateMember(MemberDTO dto);
	
	int deleteMember(String id);
	
	//페이징처리리스트
	List<MemberDTO> memberPagingList(int [] limit);
	
	//암호검색리스트
	List<MemberDTO> searchMemberList(int[] pw_array);
	
	//컬럼명, 값에 맞는 검색리스트
	List<MemberDTO> searchMemberList2(HashMap map);

	//name 변수값 존재여부에 따른 검색리스트
	List<MemberDTO> searchMemberList3(MemberDTO dto);
	
	List<MemberDTO> searchMemberList4(MemberDTO dto);
	
}