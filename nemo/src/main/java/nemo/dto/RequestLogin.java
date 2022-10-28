package nemo.dto;

import lombok.Data;

@Data
public class RequestLogin {
	
//	jwt위한 RequestLogin 추가 10.28 오전 11:50
	private String memberId;
	private String memberPw;
	
}
