package nemo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ReviewDto;
import nemo.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public List<ReviewDto> selectMyReviewList(String reviewWriter) throws Exception {
		return reviewMapper.selectMyReviewList(reviewWriter);
	}

	@Override
	public List<ReviewDto> selectYourReviewList(String reviewId) throws Exception {
		return reviewMapper.selectYourReviewList(reviewId);
	}

	@Override
	public void insertReview(@RequestPart("reviewData") ReviewDto review, @RequestPart("reviewFiles") MultipartFile files) throws Exception {
		if(files!=null) {
		String projectpath = "C:\\nemo\\git\\NEMO-react\\nemo-project\\public\\reviewFiles";
		UUID uuid = UUID.randomUUID();
		   String filename = uuid+"_"+files.getOriginalFilename();
		   File saveFile = new File(projectpath,filename);
		   review.setReviewFiles(filename);
		   try {
		         files.transferTo(saveFile);
		      } catch (IllegalStateException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
	}
		reviewMapper.insertReview(review);
	}
	
	@Override
	public ReviewDto selectMyReviewDetail(int reviewNum) throws Exception {
		return reviewMapper.selectMyReviewDetail(reviewNum);
	}

	@Override
	public ReviewDto selectYourReviewDetail(int reviewNum) throws Exception {
		return reviewMapper.selectYourReviewDetail(reviewNum);
	}

	@Override
	public void updateReview(ReviewDto reviewDto) throws Exception {
		reviewMapper.updateReview(reviewDto);
	}

	@Override
	public void deleteReview(int reviewNum) throws Exception {
		reviewMapper.deleteReview(reviewNum);
	}
}
