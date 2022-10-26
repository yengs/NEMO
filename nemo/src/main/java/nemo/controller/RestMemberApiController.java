package nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import board.vo.RequestVo;
import board.vo.ResponseVo;
import nemo.dto.MemberDto;
import nemo.service.MemberService;

@RestController
@RequestMapping("/api")
public class RestMemberApiController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/nemo/member", method = RequestMethod.GET)
	public List<MemberDto> openMemberList() throws Exception {
		return memberService.selectMemberList();
	}
	
	@RequestMapping(value = "/nemo/member/login", method = RequestMethod.POST)
	public ResponseEntity<String> insertMember(@RequestBody MemberDto member) throws Exception{
		int member_num = memberService.insertMember(member);
		if(member_num > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("등록 성공");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 실패");
		}
	}
	
	@RequestMapping(value="/nemo/member/{member_num}", method=RequestMethod.GET)
	public ResponseEntity<MemberDto> openMemberDetail(@PathVariable("member_num") int member_num) throws Exception{
		MemberDto memberDto = memberService.selectMemberDetail(member_num);
		if(memberDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else {
			return ResponseEntity.ok(memberDto);
		}
	}
	
	@RequestMapping(value="/nemo/member/{member_num}", method = RequestMethod.PUT)
	public void updateMember(@PathVariable("member_num") int member_num, @RequestBody MemberDto memberDto) throws Exception{
		memberDto.setMember_num(member_num);
		memberService.updateMember(memberDto);
	}
	
	@RequestMapping(value="/nemo/member/{member_num}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable("member_num") int member_num) throws Exception{
		memberService.deleteMember(member_num);
	}
	
	@RequestMapping(value="/nemo/member/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseVo> login(@RequestBody RequestVo requestVo) throws Exception{
		ResponseVo responseVo = memberService.login(requestVo);
		if(responseVo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(responseVo);
		}
	}

}
