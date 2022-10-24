package nemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.MemberDto;
import nemo.mapper.MemberMapper;

@Service 
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	
	//예시입니다!! ex ))) 멤버 리스트 가져올 때
	@Override
	public List<MemberDto> selectMemberList() throws Exception {
		return memberMapper.selectMemberList();
	}

}
