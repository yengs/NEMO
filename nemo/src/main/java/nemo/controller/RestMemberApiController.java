package nemo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nemo.dto.MemberDto;
import nemo.service.MemberService;
import nemo.service.MemberServiceImpl;

@Slf4j
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
	
	
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
   public void join(@RequestBody MemberDto member) throws Exception {
		int randomMailKey = (int)(Math.random()*100000);
		String to = Integer.toString(randomMailKey);
		member.setMemberMailkey(to);
		memberService.join(member);
   }
	
	// 로그인 화면
		@RequestMapping(value = "/member/login", method = RequestMethod.GET)
		public String login() {
			return "login";
		}

		// 로그인 get하기
		@RequestMapping(value = "/member/login/get", method = RequestMethod.GET)
		public String loginGet(MemberDto memberDto) {
			log.info("loginGet()");
			return "/loginResult";
		}
		
		// 로그인 post하기
		@RequestMapping(value = "/member/login/post", method = RequestMethod.POST)
		public String postlogin(HttpSession session, MemberDto memberDto) {
			if(session.getAttribute("login") != null) {
				session.removeAttribute("login");
			}
			
			MemberDto member = MemberService.loginCheck(member);
			
			if(member != null) {
				session.setAttribute("login", member);
				log.info("login success");
				return "redirect:/";
			}
			log.info("login fail");
			return "redirect:/login";
		}

		// 로그아웃
		@RequestMapping(value="/member/logout", method = RequestMethod.GET)
		public String logout(HttpSession session) {
			session.invalidate();
			log.info("logout success");
			return "redirect:/";
		}

}
