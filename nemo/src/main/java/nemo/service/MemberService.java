package nemo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;
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
	//프사수정
	public void memberImgUpdate(@RequestPart("data") MemberDto memberDto, @RequestPart("memberImg") MultipartFile memberImg) throws Exception;
	//프사get
	public MemberDto selectMyImg(int memberNum) throws Exception;
	
	// 아이디 중복
	public int checkMemberId(String memberId) throws Exception;

	// 이메일 중복
	public int checkEmail(String memberEmail) throws Exception;
	
	// 닉네임 중복
	public int checkNickname(String memberNickname) throws Exception;
	
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
	
	// 회원탈퇴
	public int delete(int memberNum);
	
	// 회원탈퇴시 리뷰 삭제
	public int delete2(int memberNum);
	
	//회원 탈퇴시 대여삭제
	public int delete3(int memberNum);
	
	//회원 탈퇴시 대여삭제2
	public int delete4(int memberNum);
	
	// 정지회원 작성글 삭제
	public void deletePost(String memberId) throws Exception;


}