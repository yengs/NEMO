package nemo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import nemo.dto.MemberDto;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

public interface MemberService extends UserDetailsService {
	public List<MemberDto> selectMemberList() throws Exception;
	public int join(MemberDto member) throws Exception;
	
//	jwt위한 추가 10.28 오전 11:51
	public MemberResponseVo login(MemberRequestVo requestVo) throws Exception; 
}
