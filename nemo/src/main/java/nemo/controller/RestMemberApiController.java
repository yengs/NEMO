package nemo.controller;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nemo.dto.ItemDto;
import nemo.dto.MemberDto;
import nemo.service.MailSenderRunner;
import nemo.service.MemberService;
//import nemo.service.SecurityService;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

@Data
@Slf4j
@RestController
@RequestMapping("/api")
public class RestMemberApiController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MailSenderRunner mail;
	
	
//	public RestMemberApiController (Environment env) {
//		this.env = env;
//	}

	
//	@Autowired
//	private SecurityService securitySevice;
	
	public RestMemberApiController() {
		
	}


	@RequestMapping(value = "/member/info/{memberNum}", method = RequestMethod.GET)
	public MemberDto selectMemberInfo(@PathVariable("memberNum") int memberNum) throws Exception {
		System.out.println(memberNum);
		return memberService.selectMemberInfo(memberNum);
	}
	
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
   public void join(@RequestBody MemberDto member) throws Exception {
		memberService.join(member);
   }
	
	
//	jwt위한 추가 10.28 오전 12:04
	@RequestMapping(value="/member/login", method = RequestMethod.POST)
	public ResponseEntity<MemberResponseVo> login(@RequestBody MemberRequestVo requestVo, HttpServletResponse response) throws Exception {
		MemberResponseVo responseVo = memberService.login(requestVo);
		MemberDto memberDto = new MemberDto();
		if(responseVo == null) {
			System.out.println(responseVo);
			System.out.println(requestVo);
			return ResponseEntity.status(HttpStatus.OK).body(responseVo);
		} else {
			String secret = env.getProperty("token.secret");
			Key hmacKey = new SecretKeySpec(
					Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName()
			);
			
			Instant now = Instant.now();
			Long expirationTime = Long.parseLong(env.getProperty("token.expiration-time"));
			String jwtToken = Jwts.builder()
					.claim("memberName", responseVo.getMemberName())
					.claim("memberEmail", responseVo.getMemberEmail())
					.setSubject(String.valueOf(responseVo.getMemberImg()))
					.setId(UUID.randomUUID().toString())
					.setIssuedAt(Date.from(now))
					.setExpiration(Date.from(now.plus(expirationTime, ChronoUnit.MILLIS)))
					.signWith(hmacKey)
					.compact();
			
			
			
//			String secret = env.getProperty("token.secret");
//			Key hmacKey = new SecretKeySpec(
//					Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName()
//			);
//			
//			Instant now = Instant.now();
//			Long expirationTime = Long.parseLong(env.getProperty("token.expiration-time"));
//			String jwtToken = Jwts.builder()
//					.claim("name", memberEntity.getMemberName())
//					.claim("email", memberEntity.getMemberEmail())
//					.setSubject(String.valueOf(memberEntity.getMemberSeq()))
//					.setId(UUID.randomUUID().toString())
//					.setIssuedAt(Date.from(now))
//					.setExpiration(Date.from(now.plus(expirationTime, ChronoUnit.MILLIS)))
//					.signWith(hmacKey)
//					.compact();
			
			
			log.debug("토큰~~~~~~~!!!!!!!!!!!!!!---------------"+jwtToken);
			log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+memberDto);
			response.setHeader("jwtToken", jwtToken);
//			response.getWriter().write(jwtToken);
			return ResponseEntity.status(HttpStatus.OK).body(responseVo);
			
		}
	}
	
	@RequestMapping(value="/member/update/{memberNum}", method = RequestMethod.PUT)
	public void updateMember(@PathVariable("memberNum") int memberNum, @RequestBody MemberDto memberDto) throws Exception {
		System.out.println("회원정보 업데이트 컨트롤러");
		memberDto.setMemberNum(memberNum);
		System.out.println(memberDto);
		memberService.memberUpdate(memberDto);
	}
	
	
	@Autowired
	private ApplicationArguments applicationArguments;
	
	//이메일 받는
	@RequestMapping(value="/mail")
	public String tomail(@RequestParam String memberEmail) throws Exception {
		
		   System.out.println("aaaaaaaaaaaaaaaaa:"+memberEmail);
		return mail.run(applicationArguments,memberEmail);
		
	}
	
	//코드 생성
	private int num = new Random().nextInt(10000) + 10000;
	private String code = String.valueOf(num);
	
	
	//코드 전달
	public String getJoinCode() {
		return this.code;
	}
	
	// 회원가입 로그인 중복여부
	@RequestMapping(value = "/member/join/checkid", method = RequestMethod.POST)
	public String checkMemberId(@ModelAttribute("memberId") String memberId) throws Exception {
		System.out.println(memberId);
		int result = memberService.checkMemberId(memberId);
		System.out.println(result);
		if (result > 0) {
			return "fail";
		}else{
			return "success";
		}
	}
	
	// 회원가입 이메일 중복여부
		@RequestMapping(value = "/member/join/checkemail", method = RequestMethod.POST)
		public String checkEmail(@ModelAttribute("memberEmail") String memberEmail) throws Exception {
			System.out.println(memberEmail);
			int email = memberService.checkEmail(memberEmail);
			System.out.println(email);
			if (email > 0) {
				return "fail";
			} else {
				return "success";
			}
		}
		
		// 회원가입 닉네임 중복여부
		@RequestMapping(value = "/member/join/checknickname", method = RequestMethod.POST)
		public String checkNickname(@ModelAttribute("memberNickname") String memberNickname) throws Exception {
			System.out.println(memberNickname);
			int nickname = memberService.checkNickname(memberNickname);
			System.out.println(nickname);
			if (nickname > 0) {
				return "fail";
			} else {
				return "success";
			}
		}
		// 아이디 비밀번호 찾기 ----------------------------------------------------
		// 아이디 찾기 입력창
		@RequestMapping(value = "/member/id", method = RequestMethod.POST)
		public ResponseEntity<MemberResponseVo> findId(@RequestBody MemberRequestVo requestVo, HttpServletResponse response) throws Exception{
			MemberResponseVo responseVo = memberService.findId(requestVo);
			if(responseVo == null) {
				System.out.println(responseVo);
				System.out.println(requestVo);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(responseVo);
			}
		}
		
		// 아이디 찾기 결과창
		@RequestMapping(value = "/member/id/find/{memberEmail}", method = RequestMethod.GET)
		public MemberDto findResult(@PathVariable String memberEmail) throws Exception{
			System.out.println(memberEmail);
			return memberService.findResult(memberEmail);
		}
		
		// 비밀번호 찾기 입력창
		@RequestMapping(value = "/member/pw", method = RequestMethod.POST)
		public ResponseEntity<MemberResponseVo> findPw(@RequestBody MemberRequestVo requestVo, HttpServletResponse response) throws Exception{
			MemberResponseVo responseVo = memberService.findPw(requestVo);
			if(responseVo == null) {
				System.out.println(responseVo);
				System.out.println(requestVo);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(responseVo);
			}
		}
		
		// 비밀번호 찾기 결과창
		@RequestMapping(value = "/member/pw/find/{memberId}", method = RequestMethod.GET)
		public MemberDto findPwResult(@PathVariable String memberId) throws Exception{
			System.out.println(memberId);
			return memberService.findPwResult(memberId);
		}
		

		//프로필 사진 수정
		//프사 GET
		@RequestMapping(value = "/memberimg/{memberNum}", method = RequestMethod.GET)
		public MemberDto selectMyImg(@PathVariable("memberNum") int memberNum) throws Exception {
			return memberService.selectMyImg(memberNum);
		}
			
		//프사 수정
		   @RequestMapping(value = "/memberimg/update/{memberNum}", method = RequestMethod.PUT)
		   public void memberImgUpdate(@PathVariable("memberNum") int memberNum, @RequestPart("data") MemberDto memberDto, @RequestPart(value = "memberImg", required = false) MultipartFile memberImg) throws Exception {
		      memberDto.setMemberNum(memberNum);
		      memberService.memberImgUpdate(memberDto, memberImg);
		   }

		//회원 탈퇴
		 @RequestMapping(value = "/member/delete/{memberNum}", method = RequestMethod.DELETE)
		   public void delete(@PathVariable("memberNum") int memberNum) throws Exception {
			 
			 memberService.delete3(memberNum);
			 memberService.delete4(memberNum);
			 memberService.delete2(memberNum);
			 memberService.delete(memberNum);
		   }
		   
		 
}
