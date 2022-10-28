package nemo.vo;

import lombok.Data;

@Data
public class MemberRequestVo {
	
//	jwt위한 RequestVo 추가 10.28 오전 11:50
	private String memberId;
	private String memberPw;
	
}
