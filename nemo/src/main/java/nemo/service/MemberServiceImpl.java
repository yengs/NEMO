package nemo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;
import nemo.dto.MemberDto;
import nemo.mapper.MemberMapper;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	private MemberDto memberDto;

//	jwt위한 추가 10.28 오전 11:53 START
	@Override
	public MemberResponseVo login(MemberRequestVo requestVo) throws Exception {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(requestVo.getMemberId());
		memberDto.setMemberPw(requestVo.getMemberPw());

		MemberDto resultDto = memberMapper.login(memberDto);
		if (resultDto == null) {
			return null;
		}

		MemberResponseVo responseVo = new MemberResponseVo();
		responseVo.setMemberNum(resultDto.getMemberNum());
		responseVo.setMemberNickname(resultDto.getMemberNickname());
		responseVo.setMemberName(resultDto.getMemberName());
		responseVo.setMemberPhone(resultDto.getMemberPhone());
		responseVo.setMemberEmail(resultDto.getMemberEmail());
		responseVo.setMemberAddress(resultDto.getMemberAddress());
		responseVo.setMemberClean(resultDto.getMemberClean());
		responseVo.setMemberZipCode(resultDto.getMemberZipCode());
		responseVo.setMemberImg(resultDto.getMemberImg());
		responseVo.setCurrentState(resultDto.getCurrentState());
		

		return responseVo;
	}

	@Override
	public int join(MemberDto member) throws Exception {
		return memberMapper.join(member);
	}
	
	//회원수정
	@Override
	public void memberUpdate(MemberDto memberDto) throws Exception {
		memberMapper.memberUpdate(memberDto);
	}
	

	@Override
//	회원정보 조회
	public MemberDto selectMemberInfo(int memberNum) throws Exception {
		MemberDto memberDto = new MemberDto();
		System.out.println("서비스 멤버정보확인::::::::::::" + memberDto);
		return memberMapper.selectMemberInfo(memberNum);
	}

	// 아이디 중복
	public int checkMemberId(String memberId) throws Exception {
		return memberMapper.checkMemberId(memberId);
	}

	// 이메일 중복
	public int checkEmail(String memberEmail) throws Exception {
		return memberMapper.checkEmail(memberEmail);
	}
	
	// 닉네임 중복
	public int checkNickname(String memberNickname) throws Exception {
		return memberMapper.checkNickname(memberNickname);
	}

	// 아이디 비밀번호 찾기 ---------------------------------------------------------
	// 아이디 찾기 입력창
	@Override
	public MemberResponseVo findId(MemberRequestVo requestVo) throws Exception {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberName(requestVo.getMemberName());
		memberDto.setMemberEmail(requestVo.getMemberEmail());

		MemberDto resultDto = memberMapper.findId(memberDto);
		if (resultDto == null) {
			return null;
		}

		MemberResponseVo responseVo = new MemberResponseVo();
		responseVo.setMemberName(resultDto.getMemberName());
		responseVo.setMemberEmail(resultDto.getMemberEmail());

		return responseVo;
	}

	// 아이디 찾기 결과창
	@Override
	public MemberDto findResult(String memberEmail) throws Exception {
		return memberMapper.findResult(memberEmail);
	}

	// 비밀번호 찾기 입력창
	@Override
	public MemberResponseVo findPw(MemberRequestVo requestVo) throws Exception {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(requestVo.getMemberId());
		memberDto.setMemberEmail(requestVo.getMemberEmail());

		MemberDto resultDto = memberMapper.findPw(memberDto);
		if (resultDto == null) {
			return null;
		}

		MemberResponseVo responseVo = new MemberResponseVo();
		responseVo.setMemberId(resultDto.getMemberId());
		responseVo.setMemberEmail(resultDto.getMemberEmail());

		return responseVo;
	}

	// 비밀번호 찾기 결과창
	@Override
	public MemberDto findPwResult(String memberId) throws Exception {
		return memberMapper.findPwResult(memberId);
	}
	
	// 신고 - 피신고자 이름 불러오기
	@Override
	public MemberDto selectPiName(String memberName) throws Exception {
		MemberDto memberDto = new MemberDto();
		System.out.println("피신고자 이름::::::::::::" + memberDto);
		return memberMapper.selectPiName(memberName);
	}
	
	
	//프사수정
	 @Override
		public void memberImgUpdate(@RequestPart("data") MemberDto memberDto, @RequestPart("memberImg") MultipartFile memberImg) throws Exception {
			
		   if ( memberImg != null) {
		      String projectpath = "C:\\nemo\\git\\NEMO-react\\NEMO-react\\nemo-project\\public\\memberImg";
			   
			   UUID uuid = UUID.randomUUID();
			   String filename = uuid+"_"+memberImg.getOriginalFilename();
			   File saveFile = new File(projectpath,filename);
			   memberDto.setMemberImg(filename);
			   try {
				   memberImg.transferTo(saveFile);
			      } catch (IllegalStateException e) {
			         e.printStackTrace();
			      } catch (IOException e) {
			         e.printStackTrace();
			      }	
		   }
		   	int count = memberMapper.memberImgUpdate(memberDto);
			System.out.println("***************** " + count);
		}

	//프사 get
	@Override
	public MemberDto selectMyImg(int memberNum) throws Exception {
		return memberMapper.selectMyImg(memberNum);
	}

	//회원 탈퇴 
	@Override
	public int delete(int memberNum) {
	      return memberMapper.delete(memberNum);
	   }
	//회원 탈퇴시 리뷰삭제
	@Override
	public int delete2(int memberNum) {
	      return memberMapper.delete2(memberNum);
	   }
	
	//회원 탈퇴시 대여삭제
	@Override
	public int delete3(int memberNum) {
	      return memberMapper.delete3(memberNum);
	   }
	
	//회원 탈퇴시 대여삭제2
	@Override
	public int delete4(int memberNum) {
	      return memberMapper.delete4(memberNum);
	   }

	// 정지회원 작성글 삭제
	@Override
	public void deletePost(String memberId) throws Exception {
		memberMapper.deletePost(memberId);
	}

}