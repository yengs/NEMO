package nemo.service;

import java.util.List;

import nemo.dto.BookingDto;
import nemo.dto.ItemDto;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.MemberDto;
import nemo.dto.SingoDto;

public interface SingoService {

	// 신고 insert
	public int insertSingo(@RequestPart("data") SingoDto singoDto, @RequestPart("singoImage") MultipartFile singoImage) throws Exception;
	
	// 피신고자 이름 가져오기
	public MemberDto selectPiName(String memberId) throws Exception;
	
	// 관리자 접수 - 누적횟수 +1
	public void confirmWarn(SingoDto singoDto) throws Exception;

	//  관리자페이지로 피신고자 이름 가져오기
	public List<SingoDto> ReasonNwriter(String memberId) throws Exception;

	// 신고 상세보기 가져오기
	public List<SingoDto> selectDetail(int singoNum) throws Exception;

	// 관리자 접수 취소
	public void deleteWarn(int singoNum) throws Exception;

	// 신고 5번 이상 회원 신고내역에서 지우기
	public void deleteMember(int singoNum) throws Exception;
	
	// 신고 5번 누적 -> 정지회원(Y) 값으로 바꾸기
	public void currentState(SingoDto singoDto) throws Exception;
	
}
