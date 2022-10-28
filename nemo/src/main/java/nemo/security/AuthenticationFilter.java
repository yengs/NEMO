package nemo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import nemo.dto.RequestLogin;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Override
	public  Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getMemberId(),
							creds.getMemberPw(),
							new ArrayList<>())
					);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		// TODO 로그인에 성공했을 때 처리 -> JWT 토큰을 발행
		log.debug(((User)authResult.getPrincipal()).getUsername());	// 인증에 사용한 사용자 이름(여기에서는 이메일)
	}

}
