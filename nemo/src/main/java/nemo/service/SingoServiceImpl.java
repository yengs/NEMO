package nemo.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
	public int insertSingo(@RequestPart("data") SingoDto singoDto, @RequestPart("singoImage") MultipartFile singoImage) throws Exception {
		 String projectpath = "C:\\react\\NEMO-react\\nemo-project\\public\\files_singo";
		   
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

	@Override
	public MemberDto selectPiName(String memberId) throws Exception {
		MemberDto memberDto = new MemberDto();
		System.out.println("피신고자 이름::::::::::::" + memberDto);
		return memberMapper.selectPiName(memberId);
	}


}
