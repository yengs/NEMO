package nemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.MemberDto;
import nemo.mapper.MemberMapper;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	

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

		return responseVo;
	}

	@Override
	public int join(MemberDto member) throws Exception {
		return memberMapper.join(member);
	}
	
	@Override
	public void memberUpdate(MemberDto memberDto) throws Exception {
		memberMapper.memberUpdate(memberDto);
	}

	@Override
//	회원정보 조회
	public MemberDto selectMemberInfo(int memberNum) throws Exception {
		MemberDto memberDto = new MemberDto();
		System.out.println("서비스 멤버정보확인::::::::::::"+memberDto);
		return memberMapper.selectMemberInfo(memberNum);
	}
	
	
}

