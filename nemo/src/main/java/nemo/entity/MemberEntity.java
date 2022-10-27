package nemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity				
@Table(name="member")
public class MemberEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memberNum;
	
	@Column(nullable = false)
	private String memberNickname;
	
	@Column(nullable = false)
	private String memberName;
	
	@Column(nullable = false)
	private String memberId;
	
	@Column(nullable = false)
	private String memberPw;
	
	@Column(nullable = false)
	private String memberDate;
	
	@Column(nullable = false)
	private String memberUser;
	
	@Column(nullable = false)
	private String memberPhone;
	
	@Column(nullable = false)
	private String memberMailauth;

	@Column(nullable = false)
	private String memberMailkey;
	
	@Column(nullable = false)
	private String memberEmail;
	
	@Column(nullable = false)
	private String memberAddress;
	
	@Column(nullable = false)
	private String memberWarning;
	
	@Column(nullable = false)
	private String memberClean;
	
}