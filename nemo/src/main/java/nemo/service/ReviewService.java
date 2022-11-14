package nemo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ReviewDto;


public interface ReviewService {
	public List<ReviewDto> selectMyReviewList(String reviewWriter) throws Exception;
	public List<ReviewDto> selectYourReviewList(String reviewId) throws Exception;
	public void insertReview(@RequestPart("reviewData") ReviewDto review,@RequestPart("reviewFiles") MultipartFile files) throws Exception;
	public ReviewDto selectMyReviewDetail(int ReviewNum) throws Exception;
	public ReviewDto selectYourReviewDetail(int ReviewNum) throws Exception;
	public void updateReview(ReviewDto reviewDto) throws Exception;
	public void deleteReview(int ReviewNum) throws Exception;
}
