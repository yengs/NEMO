package nemo.controller;

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

}
