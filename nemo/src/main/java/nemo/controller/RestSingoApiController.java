package nemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nemo.dto.SingoDto;
import nemo.service.SingoService;

@Data
@Slf4j
@RestController
@RequestMapping("/api")
public class RestSingoApiController {
	
	@Autowired
	private SingoService singoService;
	
	@RequestMapping(value = "/singo/take", method = RequestMethod.POST)
	public void insertSingo(@RequestBody SingoDto singoDto) {
		System.out.println("::::::"+singoDto);
		log.debug("와이라노");
		insertSingo(singoDto);
	}

}
