package nemo.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ReviewDto;


public interface ReviewService {
	
	public List<ReviewDto> selectMyReviewList(String reviewWriter) throws Exception;
	public List<ReviewDto> selectYourReviewList(String reviewId) throws Exception;

	public void insertReview(@RequestPart("reviewData") ReviewDto review,@RequestPart("reviewFiles") MultipartFile files) throws Exception;
	public void updateReview(@RequestPart("data") ReviewDto reviewDto, @RequestPart("reviewFiles") MultipartFile reviewFiles) throws Exception;
	public void deleteReview(String reviewWriter, int reviewNum) throws Exception;
	
	public ReviewDto selectMyReviewDetail(int reviewNum) throws Exception;
	public ReviewDto selectYourReviewDetail(int reviewNum) throws Exception;
	
	List<ReviewDto> mostRecentReviewOfMyStore(String reviewId) throws Exception; // 내 상품에 대한 리뷰 중 가장 최근 리뷰 1개
	List<ReviewDto> twoOfMyMostRecentReviews(String reviewWriter) throws Exception; // 내가 작성한 리뷰 중 가장 최근 리뷰 2개
	
	public List<ReviewDto> ItemReview(int reviewProductIdx) throws Exception;	//아이템 밑에 후기 조회
	
	public List<ReviewDto> selectSatisfaction(String reviewId) throws Exception; // 클린지수 조회
	
}
