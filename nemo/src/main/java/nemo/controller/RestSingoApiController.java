package nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nemo.dto.ItemDto;
import nemo.dto.MemberDto;
import nemo.dto.SingoDto;
import nemo.service.MemberService;
import nemo.service.SingoService;

@Data
@Slf4j
@RestController
@RequestMapping("/api")
public class RestSingoApiController {

	@Autowired
	private SingoService singoService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberDto memberDto;

	@Autowired
	private SingoDto singoDto;

	// 유저 - 신고접수
	@RequestMapping(value = "/singo/take", method = RequestMethod.POST)
	public ResponseEntity<String> insertSingo(@RequestPart("data") SingoDto singoDto,
			@RequestPart("singoImage") MultipartFile singoImage) throws Exception {
		int itemNum = singoService.insertSingo(singoDto, singoImage);
		if (itemNum > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("등록성공");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록실패");
		}
	}

	// 유저 신고페이지 - 피신고자 이름 불러오기
	@RequestMapping(value = "/userstoreinfo/{memberId}", method = RequestMethod.GET)
	public MemberDto selectPiName(@PathVariable("memberId") String memberId) throws Exception {
		System.out.println(memberId);
		return memberService.selectPiName(memberId);
	}

	// 관리자 - 내역에 피신고자 이름 가져오기
	@RequestMapping(value = "/dec/dec/{memberId}", method = RequestMethod.GET)
	public List<SingoDto> ReasonNwriter(@PathVariable("memberId") String memberId) throws Exception {
		System.out.println("***** 신고당한 사람 아이디 >>> " + memberId + " <<< *****");
		return singoService.ReasonNwriter(memberId);
	}

	// 신고 상세보기 가져오기
	@RequestMapping(value = "/dec/detail/{singoNum}", method = RequestMethod.GET)
	public List<SingoDto> selectDetail(@PathVariable("singoNum") int singoNum) throws Exception {
		System.out.println(">>> " + singoNum + " <<< 자세히 보기 !!");
		return singoService.selectDetail(singoNum);
	}

	// 관리자 - 신고 접수하기 (warning +1)
	@RequestMapping(value = "/dec/detail/{singoNum}", method = RequestMethod.PUT)
	public void confirmWarn(@PathVariable("singoNum") int singoNum, @RequestBody SingoDto singoDto) throws Exception {
		System.out.println("***** WARNING + 1 *****");
		singoService.confirmWarn(singoDto);
	}

	// 관리자 - 신고 취소하기
	@RequestMapping(value = "/dec/detail/{singoNum}", method = RequestMethod.DELETE)
	public void deleteWarn(@PathVariable("singoNum") int singoNum) throws Exception {
		singoService.deleteWarn(singoNum);
	}
	
//	// 신고 5번 이상 회원 정지
//	@RequestMapping(value = "/dec/dec/{singoNum}", method = RequestMethod.DELETE)
//	public void deleteMember(@PathVariable("singoNum") int singoNum) throws Exception{
//		singoService.deleteMember(singoNum);
//	}
}
