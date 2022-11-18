package nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
	
	// 신고접수
	@RequestMapping(value = "/singo/take", method = RequestMethod.POST)
	public void insertSingo(@RequestBody SingoDto singoDto) throws Exception {
		System.out.println("***"+singoDto+"***");
		log.debug("***insert error***");
		singoService.insertSingo(singoDto);
	}
	
	// 피신고자 이름 불러오기
	@RequestMapping(value = "/userstoreinfo/warn/{memberId}", method = RequestMethod.GET)
	public MemberDto selectPiName(@PathVariable("memberId") String memberId) throws Exception {
		System.out.println(memberId);
		return memberService.selectPiName(memberId);
	}
	
	// 관리자 - 접수하기 (warning +1)
	@RequestMapping(value = "/dec/dec/{memberId}", method = RequestMethod.PUT)
	public void confirmWarn(@PathVariable("memberId") String memberId, @RequestBody MemberDto memberDto) throws Exception{
		System.out.println("***** WARNING + 1 *****");
		memberService.confirmWarn(memberDto);
	}
	
	// 관리자 - 신고 취소하기
//	@RequestMapping(value = "/dec/dec/{memberId}", method = RequestMethod.DELETE)
//	public void deleteWarn(@PathVariable("memberId") String memberId) throws Exception{
//		memberService.deleteWarn(memberId);
//	}
	
	// 관리자페이지로 피신고자 이름 가져오기
	@RequestMapping(value = "/dec/dec/{memberId}", method = RequestMethod.GET)
	public List<SingoDto> ReasonNwriter(@PathVariable("memberId") String memberId) throws Exception{
		System.out.println("***** 신고당한 사람 아이디 >>> " + memberId + " <<< *****");
		return singoService.ReasonNwriter(memberId);
	}
	
	// 신고 상세보기 가져오기
	@RequestMapping(value = "/dec/detail/{singoNum}", method = RequestMethod.GET)
	public List<SingoDto> selectDetail(@PathVariable("singoNum") int singoNum) throws Exception{
		System.out.println(">>> " + singoNum + " <<< 자세히 보기 !!");
		return singoService.selectDetail(singoNum);
	}

}
