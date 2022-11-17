package nemo.service;

import nemo.dto.MemberDto;
import nemo.dto.SingoDto;

public interface SingoService {

	// 신고 insert
	public void insertSingo(SingoDto singoDto) throws Exception;
	
	// 피신고자 이름 가져오기
	public MemberDto selectPiName(String memberId) throws Exception;
	
}
