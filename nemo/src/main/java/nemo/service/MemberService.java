package nemo.service;

import java.util.List;

import board.vo.RequestVo;
import board.vo.ResponseVo;
import nemo.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> selectMemberList() throws Exception;
	public int insertMember(MemberDto member) throws Exception;
	public MemberDto selectMemberDetail(int member_num) throws Exception;
	public void updateMember(MemberDto memberDto) throws Exception;
	public void deleteMember(int member_num) throws Exception;
	public ResponseVo login(RequestVo requestVo) throws Exception;
}
