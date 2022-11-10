package nemo.controller;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import nemo.dto.MemberDto;
import nemo.service.MailCode;
import nemo.service.MailSenderRunner;
import nemo.service.MemberService;
//import nemo.service.SecurityService;
import nemo.vo.MemberRequestVo;
import nemo.vo.MemberResponseVo;

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
		int randomMailKey = (int)(Math.random()*100000);
		String to = Integer.toString(randomMailKey);
		member.setMemberMailkey(to);
		memberService.join(member);
   }
	
	
//	jwt위한 추가 10.28 오전 12:04
	@RequestMapping(value="member/login", method = RequestMethod.POST)
	public ResponseEntity<MemberResponseVo> login(@RequestBody MemberRequestVo requestVo, HttpServletResponse response) throws Exception {
		MemberResponseVo responseVo = memberService.login(requestVo);
		if(responseVo == null) {
			System.out.println(responseVo);
			System.out.println(requestVo);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
					.setSubject(String.valueOf(responseVo.getMemberNum()))
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
			response.setHeader("jwtToken", jwtToken);
//			response.getWriter().write(jwtToken);
			return ResponseEntity.status(HttpStatus.OK).body(responseVo);
			
		}
	}
	
	@RequestMapping(value="/member/update/{memberNum}", method = RequestMethod.PUT)
	public void updateMember(@PathVariable("memberNum") int memberNum, @RequestBody MemberDto memberDto) throws Exception {
		System.out.println("회원정보 업데이트 컨트롤러");
		memberDto.setMemberNum(memberNum);
//		memberDto.setMemberDate(memberDto.getMemberDate());
//		memberDto.setMemberUser(memberDto.getMemberUser());
//		memberDto.setMemberMailkey(memberDto.getMemberMailkey());
		System.out.println(memberDto);
		memberService.memberUpdate(memberDto);
	}
	
	
	
	@Autowired
	private ApplicationArguments applicationArguments;
	
	@RequestMapping(value="/mail")
	public void tomail(@RequestParam String memberEmail) throws Exception {
		
		   System.out.println("aaaaaaaaaaaaaaaaa:"+memberEmail);
		mail.run(applicationArguments,memberEmail);
	}

	

	@RequestMapping(value="/code")

	public String code() throws Exception {
		MailCode code = new MailCode();
		String joinCode = code.getJoinCode();
		
		System.out.println("asmfdlkamflk" + joinCode);
		return joinCode;
		
	}

}
