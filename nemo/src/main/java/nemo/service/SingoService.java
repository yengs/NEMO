package nemo.service;

import java.util.List;

import nemo.dto.BookingDto;
import nemo.dto.ItemDto;
import nemo.dto.MemberDto;
import nemo.dto.SingoDto;

public interface SingoService {

	// 신고 insert
	public void insertSingo(SingoDto singoDto) throws Exception;
	
	// 피신고자 이름 가져오기
	public MemberDto selectPiName(String memberId) throws Exception;
	
	// 관리자 접수 - 누적횟수 +1
	public void confirmWarn(MemberDto memberDto) throws Exception;

	//  관리자페이지로 피신고자 이름 가져오기
	public List<SingoDto> ReasonNwriter(String memberId) throws Exception;

	// 신고 상세보기 가져오기
	public List<SingoDto> selectDetail(int singoNum) throws Exception;
}
