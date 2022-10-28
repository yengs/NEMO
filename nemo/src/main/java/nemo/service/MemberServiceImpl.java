package nemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nemo.dto.MemberDto;
import nemo.mapper.MemberMapper;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
//	jwt를 위해 추가 10.28 오후 12:58 START
	private BCryptPasswordEncoder passwordEncoder;

	public MemberServiceImpl(MemberMapper memberMapper, BCryptPasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}
//	jwt를 위해 추가 10.28 오후 12:58 END

	
	
	@Override
	public List<MemberDto> selectMemberList() throws Exception {
		return memberMapper.selectMemberList();
	}

	
	@Override
	public int join(MemberDto member) throws Exception {
		member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
		return memberMapper.join(member);
	}

	
	
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


}
