package nemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nemo.dto.MemberDto;
import nemo.service.MemberService;
//import nemo.service.SecurityService;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

@RestController
@RequestMapping("/api")
public class RestMemberApiController {

	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	private SecurityService securitySevice;

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public List<MemberDto> openMemberList() throws Exception {
		return memberService.selectMemberList();
	}
	
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
   public void join(@RequestBody MemberDto member) throws Exception {
		int randomMailKey = (int)(Math.random()*100000);
		String to = Integer.toString(randomMailKey);
		member.setMemberMailkey(to);
		memberService.join(member);
   }
	
	
//	jwt위한 추가 10.28 오전 12:04
	@RequestMapping(value="member/login", method = RequestMethod.POST)
	public ResponseEntity<MemberResponseVo> login(@RequestBody MemberRequestVo requestVo) throws Exception {
		System.out.println("로그인되나?");
		MemberResponseVo responseVo = memberService.login(requestVo);
		if(responseVo == null) {
			System.out.println(responseVo);
			System.out.println(requestVo);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(responseVo);
		}
	}
	
	
	

}
