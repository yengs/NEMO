package nemo.service;

import java.util.List;

import nemo.dto.MemberDto;

public interface MemberService {
	
	//예시입니다!! ex ))) 멤버 리스트 가져올 때
	public List<MemberDto> selectMemberList() throws Exception;
}
