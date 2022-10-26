package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.MemberDto;

@Mapper
public interface MemberMapper {
	List<MemberDto> selectMemberList() throws Exception;
	int insertMember(MemberDto member) throws Exception;
	MemberDto selectMemberDetail(int member_num) throws Exception;
	int updateMember(MemberDto memberDto) throws Exception;
	int deleteMember(int member_num) throws Exception;
	MemberDto login(MemberDto memberDto) throws Exception;
}
