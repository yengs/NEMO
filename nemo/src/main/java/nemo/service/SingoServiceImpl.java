package nemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.SingoDto;
import nemo.mapper.SingoMapper;

@Service
public class SingoServiceImpl implements SingoService {
	
	@Autowired
	private SingoMapper singoMapper;
	
	@Override
	public void insertSingo(SingoDto singoDto) throws Exception {
		System.out.println("서비스 타나??? 신고신고");
		singoMapper.insertSingo(singoDto);
	}
	

}
