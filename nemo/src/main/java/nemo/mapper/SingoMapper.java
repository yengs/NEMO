package nemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.MemberDto;
import nemo.dto.SingoDto;

@Mapper
public interface SingoMapper {
	
	// 신고 insert
	void insertSingo(SingoDto singoDto) throws Exception;

	// 피신고자 이름 가져오기
	MemberDto selectPiName(String memberId) throws Exception;
}
