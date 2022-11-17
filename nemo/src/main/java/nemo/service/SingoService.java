package nemo.service;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.MemberDto;
import nemo.dto.SingoDto;

public interface SingoService {

	// 신고 insert
	public int insertSingo(@RequestPart("data") SingoDto singoDto, @RequestPart("singoImage") MultipartFile singoImage) throws Exception;
	
	// 피신고자 이름 가져오기
	public MemberDto selectPiName(String memberId) throws Exception;
	
}
