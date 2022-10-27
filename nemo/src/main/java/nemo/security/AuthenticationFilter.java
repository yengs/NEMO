package nemo.security;

import java.io.IOException;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import nemo.dto.RequestLogin;
import nemo.entity.MemberEntity;
import nemo.service.MemberService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private MemberService memberService;
	private Environment env;
	
	public AuthenticationFilter(MemberService memberService, Environment env) {
		this.memberService = memberService;
		this.env = env;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			// 요청(request)에서 인증 처리에 필요한 정보를 추출
			RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
			
			return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
					creds.getMemberId(),
					creds.getMemberPw(), 
					new ArrayList<>()
				)				
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	@Override 
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		String memberId = ((User)authResult.getPrincipal()).getUsername();
//		MemberEntity memberEntity = memberService.getMemberDetailByEmail(memberId);
//		log.debug(memberEntity.toString());
//		
//		String secret = env.getProperty("token.secret");
//		Key hmacKey = new SecretKeySpec(
//				Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName()
//		);
//		
//		Instant now = Instant.now();
//		Long expirationTime = Long.parseLong(env.getProperty("token.expiration-time"));
//		String jwtToken = Jwts.builder()
//				.claim("name", memberEntity.getMemberName())
//				.claim("email", memberEntity.getMemberEmail())
//				.setSubject(String.valueOf(memberEntity.getMemberNum()))
//				.setId(UUID.randomUUID().toString())
//				.setIssuedAt(Date.from(now))
//				.setExpiration(Date.from(now.plus(expirationTime, ChronoUnit.MILLIS)))
//				.signWith(hmacKey)
//				.compact();
//		log.debug(jwtToken);
//		
//		response.setHeader("token", jwtToken);
//	}
}
