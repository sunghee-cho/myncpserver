package boardmapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired //MemberDAO BEAN - 다른 이름 
	MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<MemberDTO> memberList() {
		return dao.memberList();
	}

	@Override
	public MemberDTO oneMember(String id) {
		return dao.oneMember(id);
	}

	@Override
	public int memberCount() {
		return dao.memberCount();
	}

	@Override
	public String registerMember(MemberDTO dto) {
		System.out.println(dto.getId());
		MemberDTO mybatis_dto = dao.oneMember(dto.getId());
		if(mybatis_dto != null ) {
			return "아이디 중복 - 회원가입할 수 없습니다.";
		}
		else {
			dao.insertMemberDTO(dto);
			return "정상적으로 회원가입되었습니다.";
		}
	}

	@Override
	public String modifyMember(MemberDTO dto) {
		MemberDTO mybatis_dto = dao.oneMember(dto.getId());
		if(mybatis_dto == null) {
			return "수정할 회원 정보가 없습니다.";
		}
		else {
			dao.updateMember(dto);
			return "정상적으로 회원정보 수정되었습니다.";
		}
	}

	@Override
	public String deleteMember(String id) {
		MemberDTO mybatis_dto = dao.oneMember(id);
		if(mybatis_dto == null) {
			return "삭제할 회원 정보가 없습니다.";
		}
		else {
			dao.deleteMember(id);
			return "정상적으로 탈퇴처리되었습니다.";
		}
	}

	@Override
	public List<MemberDTO> memberPagingList(int[] limit) {
		return dao.memberPagingList(limit);
	}

	@Override
	public List<MemberDTO> searchMemberList(int[] pw_array) {
		return dao.searchMemberList(pw_array);
	}

	@Override
	public List<MemberDTO> searchMemberList2(HashMap map) {
		return dao.searchMemberList2(map);
	}

	@Override
	public List<MemberDTO> searchMemberList3(MemberDTO dto) {
		return dao.searchMemberList3(dto);
	}

	@Override
	public List<MemberDTO> searchMemberList4(MemberDTO dto) {
		return dao.searchMemberList4(dto);
	}

	
	
	
}
