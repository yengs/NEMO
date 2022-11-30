package nemo.service;

import java.util.List;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;
import nemo.dto.MemberDto;
import nemo.dto.SingoDto;
import nemo.mapper.ItemMapper;
import nemo.mapper.MemberMapper;
import nemo.mapper.SingoMapper;

@Service
public class SingoServiceImpl implements SingoService {
	
	@Autowired
	private SingoMapper singoMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberDto memberDto;
	
	// 신고 insert
	@Override
	public int insertSingo(@RequestPart("data") SingoDto singoDto, @RequestPart("singoImage") MultipartFile singoImage) throws Exception {
		 String projectpath = "C:\\nemo\\git\\NEMO-react\\NEMO-react\\nemo-project\\public\\files_singo";
		   
		   UUID uuid = UUID.randomUUID();
		   String filename = uuid+"_"+singoImage.getOriginalFilename();
		   File saveFile = new File(projectpath,filename);
		   singoDto.setSingoImage(filename);
		   try {
			   singoImage.transferTo(saveFile);
		      } catch (IllegalStateException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
		   
		return singoMapper.insertSingo(singoDto);
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
	public void confirmWarn(SingoDto singoDto) throws Exception {
		singoMapper.confirmWarn(singoDto);
		singoMapper.currentState(singoDto);
		singoMapper.deleteMember(singoDto.getSingoNum());
		memberService.deletePost(memberDto.getMemberId());
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

	// 관리자 신고 취소
	@Override
	public void deleteWarn(int singoNum) throws Exception {
		singoMapper.deleteWarn(singoNum);
	}

	// 신고 5번 이상 회원글 신고내역에서 삭제
	@Override
	public void deleteMember(int singoNum) throws Exception {
		singoMapper.deleteMember(singoNum);
	}

	// 정지회원 state Y로 바꾸기
	@Override
	public void currentState(SingoDto singoDto) throws Exception {
		singoMapper.currentState(singoDto);
	}
}
