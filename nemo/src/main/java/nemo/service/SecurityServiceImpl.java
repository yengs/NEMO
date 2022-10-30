//package nemo.service;
//
//import java.security.Key;
//import java.util.Date;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class SecurityServiceImpl implements SecurityService {
//	
//	public static final String secretKey = "keyedhashmessageauthenticationcodehashbasedmessageauth";
//	
////	token발행
//	@Override
//	public String createToken(String subject, long ttlMillis) {
//		if(ttlMillis == 0) {
//			throw new RuntimeException("토큰 만료기간은 0 이상이어야 합니다.");
//		}
////		HS256방식으로 암호화 방식 설정
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//		byte[] apiKeySeBytes = DatatypeConverter.parseBase64Binary(secretKey);
//		Key signingKey = new SecretKeySpec(apiKeySeBytes, signatureAlgorithm.getJcaName());
//		
//		JwtBuilder builder = Jwts.builder()
//								.setSubject(subject)
//								.signWith(signatureAlgorithm, signingKey);
//		
//		long nowMillis = System.currentTimeMillis();
//		builder.setExpiration(new Date(nowMillis + ttlMillis));
//		return builder.compact();
//	}
//	
//	@Override
//	public String getSubject(String token) {
//		Claims claims = Jwts.parser()
//							.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
//							.parseClaimsJws(token).getBody();
//		
//		return claims.getSubject();
//	}
//	
//	
//}
