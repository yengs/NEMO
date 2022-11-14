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
	
	public void memberUpdate(MemberDto memberDto) throws Exception;
	
	// 아이디 중복
	public int checkMemberId(String memberId) throws Exception;
	
	// 이메일 중복
	public int checkEmail(String memberEmail) throws Exception;
}
