package nemo.vo;

import lombok.Data;

@Data
public class MemberRequestVo {
	
//	jwt위한 RequestVo 추가 10.28 오전 11:50
	private int memberNum;
	private String memberName;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String currentState;
}
