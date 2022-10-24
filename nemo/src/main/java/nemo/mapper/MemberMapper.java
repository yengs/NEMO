package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.MemberDto;

@Mapper
public interface MemberMapper {
	
	//예시입니다!! ex ))) 멤버 리스트 가져올 때
	List<MemberDto> selectMemberList() throws Exception;
	
}
