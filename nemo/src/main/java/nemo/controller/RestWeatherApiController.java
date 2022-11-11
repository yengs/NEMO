package nemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nemo.service.MemberService;

@RestController
@RequestMapping("/api")
public class RestWeatherApiController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	public void getWeatherInfo() throws Exception {
		
		String zipCode = "";
		
		final String url = "http://api.openweathermap.org/geo/1.0/zip?zip=" + zipCode + ",KR&appid=42c3249b2406895e257db260bf90bc97";
		
		StringBuilder urlBuilder = new StringBuilder(url);
		
	}

}
