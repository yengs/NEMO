package nemo.service;

import java.util.List;

import nemo.dto.MemberDto;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

public interface MemberService {

//	회원정보 조회
	public MemberDto selectMemberInfo(int memberNum) throws Exception;

	public int join(MemberDto member) throws Exception;

//	jwt위한 추가 10.28 오전 11:51
	public MemberResponseVo login(MemberRequestVo requestVo) throws Exception;

	// 아이디 중복
	public int checkMemberId(String memberId) throws Exception;

	// 이메일 중복
	public int checkEmail(String memberEmail) throws Exception;

	// 아이디 찾기 입력창
	public MemberResponseVo findId(MemberRequestVo requestVo) throws Exception;

	// 아이디 찾기 결과창
	public MemberDto findResult(String memberEmail) throws Exception;

	// 비밀번호 찾기 입력창
	public MemberResponseVo findPw(MemberRequestVo requestVo) throws Exception;

	// 비밀번호 찾기 결과창
	public MemberDto findPwResult(String memberId) throws Exception;

	// 피신고자 이름 불러오기
	public MemberDto selectPiName(String memberName) throws Exception;

	// 관리자 접수하기 - 누적횟수 +1
	public void confirmWarn(MemberDto memberDto) throws Exception;
	
	// 회원 상태 수정
	public void memberUpdate(MemberDto memberDto) throws Exception;


}
