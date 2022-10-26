package nemo.service;

import java.util.List;

import nemo.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> selectMemberList() throws Exception;
	public int join(MemberDto member) throws Exception;
}
