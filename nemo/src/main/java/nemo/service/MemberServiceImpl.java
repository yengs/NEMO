package nemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.vo.RequestVo;
import board.vo.ResponseVo;
import nemo.dto.MemberDto;
import nemo.mapper.MemberMapper;

@Service 
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> selectMemberList() throws Exception {
		return memberMapper.selectMemberList();
	}

	@Override
	public int insertMember(MemberDto member) throws Exception {
		memberMapper.insertMember(member);
		return member.getMember_num();
	}

	@Override
	public MemberDto selectMemberDetail(int member_num) throws Exception {
		return memberMapper.selectMemberDetail(member_num);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public void deleteMember(int member_num) throws Exception {
		memberMapper.deleteMember(member_num);
	}

	@Override
	public ResponseVo login(RequestVo requestVo) throws Exception {
		MemberDto memberDto = new MemberDto();
		memberDto.setMember_id(requestVo.getMember_id());
		memberDto.setMember_pw(requestVo.getMember_pass());
		
		MemberDto resultDto = memberMapper.login(memberDto);
		if(resultDto == null)
			return null;
		
		ResponseVo responseVo = new ResponseVo();
		responseVo.setMember_num(resultDto.getMember_num());
		responseVo.setMember_name(resultDto.getMember_name());
		responseVo.setMember_email(resultDto.getMember_email());
		return responseVo;
	}

}
