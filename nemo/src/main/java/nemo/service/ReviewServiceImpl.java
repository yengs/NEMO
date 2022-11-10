package nemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void insertReview(ReviewDto review) throws Exception {
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

	@Override
	public List<ReviewDto> mostRecentReviewOfMyStore(String reviewId) throws Exception {
		return reviewMapper.mostRecentReviewOfMyStore(reviewId);
	}

	@Override
	public List<ReviewDto> twoOfMyMostRecentReviews(String reviewWriter) throws Exception {
		return reviewMapper.twoOfMyMostRecentReviews(reviewWriter);
	}
	
}
