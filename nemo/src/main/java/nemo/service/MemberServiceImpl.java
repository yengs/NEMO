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

	
}
