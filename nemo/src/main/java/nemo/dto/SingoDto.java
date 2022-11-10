package nemo.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SingoDto {
	
	private int singoNum;
	private String singoWriter;
	private String singoReason;
	private String singoContent;
	private Date singoDate;
	private String singoImage;

}