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
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import nemo.dto.MemberDto;
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
	
	private Environment env;
	
	public RestMemberApiController (Environment env) {
		this.env = env;
	}

	
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
	
	// 회원가입 로그인 중복여부
	@RequestMapping(value = "/member/join/checkId", method = RequestMethod.POST)
	public String checkMemberId(@ModelAttribute("memberId") String memberId) throws Exception {
		System.out.println(memberId);
		int result = memberService.checkMemberId(memberId);
		if (result > 0) {
			return "fail";
		} else {
			return "success";
		}
	}

	
}
