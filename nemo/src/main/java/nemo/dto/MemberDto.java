package nemo.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MemberDto {
	private int memberNum;
	private String memberNickname;
	private String memberName;
	private String memberId;
	private String memberPw;
	private String memberDate;
	private String memberUser;
	private String memberPhone;
	private int memberMailauth;
	private String memberMailkey;
	private String memberEmail;
	private String memberAddress;
	private int memberWarning;
	private int memberClean;
	private String memberZipCode;
	private String memberImg;
	
	private String memberLat;
	private String memberLon;
	
	private String memberSigungu;

	private String currentState;
}
