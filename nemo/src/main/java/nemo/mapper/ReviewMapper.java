package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.ReviewDto;

@Mapper
public interface ReviewMapper {
	List<ReviewDto> selectMyReviewList() throws Exception;
	List<ReviewDto> selectYourReviewList() throws Exception;
	void insertReview(ReviewDto review) throws Exception;
	ReviewDto selectMyReviewDetail(int ReviewNum) throws Exception;
	ReviewDto selectYourReviewDetail(int ReviewNum) throws Exception;
	void updateReview(ReviewDto reviewDto) throws Exception;
	void deleteReview(int ReviewNum) throws Exception;
	
}
