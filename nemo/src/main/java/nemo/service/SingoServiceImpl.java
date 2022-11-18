package nemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.ItemDto;
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
	
	// 신고 insert
	@Override
	public void insertSingo(SingoDto singoDto) throws Exception {
		System.out.println("***insert 확인***");
		singoMapper.insertSingo(singoDto);
	}

	// 피신고자 이름 불러오기
	@Override
	public MemberDto selectPiName(String memberId) throws Exception {
		MemberDto memberDto = new MemberDto();
		System.out.println("피신고자 이름::::::::::::" + memberDto);
		return memberMapper.selectPiName(memberId);
	}
	
	// 관리자 - 접수하기 (warning +1)
	@Override
	public void confirmWarn(MemberDto memberDto) throws Exception{
		int count = memberMapper.memberUpdate(memberDto);
		System.out.println("누적횟수 " + count + "회 추가됨");
	}
	
	// 관리자페이지로 피신고자 이름 가져오기
	@Override
	public List<SingoDto> ReasonNwriter(String memberId) throws Exception {
		return singoMapper.ReasonNwriter(memberId);
	}

	// 신고 상세보기 가져오기
	@Override
	public List<SingoDto> selectDetail(int singoNum) throws Exception {
		SingoDto singoDto = new SingoDto();
		return singoMapper.selectDetail(singoNum);
	}
	
	

}
