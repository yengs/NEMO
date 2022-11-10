package nemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nemo.dto.SingoDto;
import nemo.service.SingoService;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestSingoApiController {
	
	@Autowired
	private SingoService singoService;
	
//	신고접수
	@RequestMapping(value = "/singo/take", method = RequestMethod.POST)
	public void insertSingo(@RequestBody SingoDto singoDto) throws Exception {
		System.out.println("::::::"+singoDto);
		log.debug("와이라노");
		singoService.insertSingo(singoDto);
	}

}
