package nemo.dto;

import lombok.Data;

@Data
public class MemberDto {
	private int member_num;
	private String member_nickname;
	private String member_name;
	private String member_id;
	private String member_pw;
	private String member_date;
	private String member_user;
	private String member_phone;
	private int member_mailauth;
	private String member_mailkey;
	private String member_email;
	private String member_address;
	private int member_warning;
	private int member_clean;
}
