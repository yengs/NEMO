package nemo.service;

import java.util.Random;

public class MailCode {

	
	private int num = new Random().nextInt(10000) + 10000;
	private String joinCode = String.valueOf(num);

	public String getJoinCode() {
		return this.joinCode;
	}
}
