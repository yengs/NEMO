package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.ItemDto;
import nemo.dto.MemberDto;

@Mapper
public interface MemberMapper {
//	List<MemberDto> selectMemberList() throws Exception;

	int join(MemberDto member) throws Exception;

//	jwt위한 추가 10.28 오전 11:51
	MemberDto login(MemberDto member) throws Exception;

	MemberDto selectMemberInfo(int memberNum) throws Exception;

	void memberUpdate(MemberDto memberDto) throws Exception;
	int memberImgUpdate(MemberDto memberDto) throws Exception; //프사수정
	MemberDto selectMyImg(int memberNum) throws Exception; //프사 get

	int checkMemberId(String memberId) throws Exception;

	int checkEmail(String memberEmail) throws Exception;

	// 아이디 찾기
	MemberDto findId(MemberDto member) throws Exception;
	MemberDto findResult(String memberEmail) throws Exception;

	// 비밀번호 찾기
	MemberDto findPw(MemberDto member) throws Exception;
	MemberDto findPwResult(String memberId) throws Exception;

	// 피신고자 이름 불러오기
	MemberDto selectPiName(String memberId) throws Exception;
	
	//회원 탈퇴
	 int delete(int memberNum);


}
