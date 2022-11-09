package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.ItemDto;
import nemo.dto.ReviewDto;

@Mapper
public interface ReviewMapper {
	
	List<ReviewDto> selectMyReviewList(String reviewWriter) throws Exception; // 내가 작성한 리뷰

	List<ReviewDto> selectYourReviewList(String reviewId) throws Exception; // 내 상품에 대한 리뷰

	void insertReview(ReviewDto review) throws Exception; // 리뷰 작성

	ReviewDto selectMyReviewDetail(int ReviewNum) throws Exception; // 내가 작성한 리뷰 상세

	ReviewDto selectYourReviewDetail(int ReviewNum) throws Exception; // 내 상품에 대한 리뷰 상세

	void updateReview(ReviewDto reviewDto) throws Exception; // 리뷰 업데이트

	void deleteReview(int ReviewNum) throws Exception; // 리뷰 삭제
	
	// ------------------------ 
	
	

}
