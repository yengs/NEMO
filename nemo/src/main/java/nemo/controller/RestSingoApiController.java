package nemo.controller;

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
	
	// 신고접수
	@RequestMapping(value = "/singo/take", method = RequestMethod.POST)
	public ResponseEntity<String> insertSingo(@RequestPart("data") SingoDto singoDto, @RequestPart("singoImage") MultipartFile singoImage) throws Exception {
		int itemNum = singoService.insertSingo(singoDto,singoImage);
        if (itemNum > 0) {
           return ResponseEntity.status(HttpStatus.OK).body("등록성공");
        } else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록실패");
        }
     }
	
	// 피신고자 이름 불러오기
	@RequestMapping(value = "/userstoreinfo/warn/{memberId}", method = RequestMethod.GET)
	public MemberDto selectPiName(@PathVariable("memberId") String memberId) throws Exception {
		System.out.println(memberId);
		return memberService.selectPiName(memberId);
	}

}
