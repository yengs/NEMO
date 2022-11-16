package nemo.service;

import java.util.ArrayList;
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
	
	// 내가 쓴 리뷰 전체 불러오기 
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
		String projectpath = "C:\\react\\NEMO-react\\nemo-project\\public\\files_review";
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
//		ReviewDto reviewDto1 = new ReviewDto();
//		reviewDto1.setReviewFiles(null);
//		reviewDto1.setReviewContents(null);
//		reviewDto1.setReviewSatisfaction(0);
//		reviewMapper.updateReview(reviewDto1);
		reviewMapper.updateReview(reviewDto);
	}

	@Override
	public void deleteReview(String reviewWriter, int reviewNum) throws Exception {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setReviewWriter(reviewWriter);
		reviewDto.setReviewNum(reviewNum);
		reviewMapper.deleteReview(reviewDto);
	}

	@Override
	public List<ReviewDto> mostRecentReviewOfMyStore(String reviewId) throws Exception {
		return reviewMapper.mostRecentReviewOfMyStore(reviewId);
	}

	@Override
	public List<ReviewDto> twoOfMyMostRecentReviews(String reviewWriter) throws Exception {
		return reviewMapper.twoOfMyMostRecentReviews(reviewWriter);
	}
	
}
