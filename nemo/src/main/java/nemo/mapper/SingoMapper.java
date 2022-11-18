package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.MemberDto;
import nemo.dto.SingoDto;

@Mapper
public interface SingoMapper {
	
	// 신고 insert
	int insertSingo(SingoDto singoDto) throws Exception;

	// 피신고자 이름 가져오기
	MemberDto selectPiName(String memberId) throws Exception;

	// 관리자페이지로 피신고자 이름 가져오기
	List<SingoDto> ReasonNwriter(String memberId) throws Exception;

	// 신고 상세보기 가져오기
	List<SingoDto> selectDetail(int singoNum) throws Exception;
}
