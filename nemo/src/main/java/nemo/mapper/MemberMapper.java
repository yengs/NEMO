package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.MemberDto;

@Mapper
public interface MemberMapper {
	List<MemberDto> selectMemberList() throws Exception;
	int join(MemberDto member) throws Exception;
	
//	jwt위한 추가 10.28 오전 11:51
	MemberDto login(MemberDto member) throws Exception;
	MemberDto findByMemberId(String username);
}
