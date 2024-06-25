package boardmapper;

import java.util.HashMap;
import java.util.List;

public interface MemberService {
	List<MemberDTO> memberList();//회원리스트조회
    MemberDTO oneMember(String id);//해당id회원조회
    int memberCount();//전체회원수조회
    String registerMember(MemberDTO dto);//회원가입(dto id - select + insert)
    String modifyMember(MemberDTO dto);//회원정보수정
    String deleteMember(String id);//회원탈퇴
    List<MemberDTO> memberPagingList(int[] limit);//페이징처리리스트
    List<MemberDTO> searchMemberList(int[] pw_array);
    List<MemberDTO> searchMemberList2(HashMap map);
    List<MemberDTO> searchMemberList3(MemberDTO dto);
    List<MemberDTO> searchMemberList4(MemberDTO dto);
}
