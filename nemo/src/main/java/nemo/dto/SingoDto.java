package nemo.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SingoDto {
	
	private int singoNum;
	private String singoWriter;
	private String singoReason;
	private String singoContent;
	private Date singoDate;
	private String singoImage;
	private String singoPisingoja;
	private int singoWarning;
	
	private int memberNum;
	private String memberName;

}