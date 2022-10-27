package nemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.ReviewDto;
import nemo.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public List<ReviewDto> selectMyReviewList() throws Exception {
		return reviewMapper.selectMyReviewList();
	}
	
	@Override
	public List<ReviewDto> selectYourReviewList() throws Exception {
		return reviewMapper.selectYourReviewList();
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
}

