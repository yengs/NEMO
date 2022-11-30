package nemo.vo;

import lombok.Data;

@Data
public class MemberResponseVo {

//	jwt위한 ResponseVo 추가 10.28 오전 11:50
	private int memberNum;
	private String memberNickname;
	private String memberName;
	private String memberId;
//	private String memberPw;
	private String memberPhone;
	private String memberEmail;
	private String memberAddress;
	private int memberClean;
	private int memberWarning;
	private String memberZipCode;
	private String memberImg;
	
//	private String memberLat;
//	private String memberLon;
	
	private String memberSigungu;
	private String currentState;
}
