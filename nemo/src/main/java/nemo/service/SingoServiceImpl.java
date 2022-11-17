package nemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.MemberDto;
import nemo.dto.SingoDto;
import nemo.mapper.MemberMapper;
import nemo.mapper.SingoMapper;

@Service
public class SingoServiceImpl implements SingoService {
	
	@Autowired
	private SingoMapper singoMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void insertSingo(SingoDto singoDto) throws Exception {
		System.out.println("***insert 확인***");
		singoMapper.insertSingo(singoDto);
	}

	@Override
	public MemberDto selectPiName(String memberId) throws Exception {
		MemberDto memberDto = new MemberDto();
		System.out.println("피신고자 이름::::::::::::" + memberDto);
		return memberMapper.selectPiName(memberId);
	}
	

}
