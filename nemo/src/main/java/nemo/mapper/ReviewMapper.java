package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.ReviewDto;

@Mapper
public interface ReviewMapper {
	
	List<ReviewDto> selectMyReviewList(String reviewWriter) throws Exception; // 내가 작성한 리뷰
	List<ReviewDto> selectYourReviewList(String reviewId) throws Exception; // 내 상품에 대한 리뷰

	void insertReview(ReviewDto review) throws Exception; // 리뷰 작성
	int updateReview(ReviewDto reviewDto) throws Exception; // 리뷰 업데이트
	void deleteReview(ReviewDto reviewDto) throws Exception; // 리뷰 삭제

	ReviewDto selectMyReviewDetail(int reviewNum) throws Exception; // 내가 작성한 리뷰 상세
	ReviewDto selectYourReviewDetail(int reviewNum) throws Exception; // 내 상품에 대한 리뷰 상세

	List<ReviewDto> mostRecentReviewOfMyStore(String reviewId) throws Exception; // 내 상품에 대한 리뷰 중 가장 최근 리뷰 1개
	List<ReviewDto> twoOfMyMostRecentReviews(String reviewWriter) throws Exception; // 내가 작성한 리뷰 중 가장 최근 리뷰 2개
	
	List<ReviewDto> ItemReview(int reviewProductIdx) throws Exception;	// 아이템 밑에 후기 조회
	
	List<ReviewDto> selectSatisfaction(String reviewId) throws Exception; // 클린지수 조회

}
