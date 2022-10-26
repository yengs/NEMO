package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.MemberDto;

@Mapper
public interface MemberMapper {
	List<MemberDto> selectMemberList() throws Exception;
	
}
