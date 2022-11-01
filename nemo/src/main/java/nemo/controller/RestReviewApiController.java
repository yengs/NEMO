package nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import nemo.dto.ReviewDto;
import nemo.service.ReviewService;

@RestController
@RequestMapping("/api")
public class RestReviewApiController {

	@Autowired
	private ReviewService reviewService;

	/* 내가 작성한 후기 목록 */
	@RequestMapping(value = "/review/myReview", method = RequestMethod.GET)
	public List<ReviewDto> myReviewList(String reviewWriter) throws Exception {
		return reviewService.selectMyReviewList(reviewWriter);
	}

	/* 내 상품에 대해 상대방이 쓴 후기 목록 */
	@RequestMapping(value = "/review/yourReview", method = RequestMethod.GET)
	public List<ReviewDto> yourReviewList(String reviewId) throws Exception {
		return reviewService.selectYourReviewList(reviewId);
	}

	/* 후기 등록 */
	@RequestMapping(value = "/reivew/reviewWrite", method = RequestMethod.POST)
	public void insertReview(@RequestBody ReviewDto review) throws Exception {
		review.setReviewId("민주");
		review.setReviewWriter("슬기");
		reviewService.insertReview(review);
	}

	/* 내가 쓴 후기 상세 페이지 */
	@RequestMapping(value = "/review/myReview/{reviewNum}", method = RequestMethod.GET)
	public ResponseEntity<ReviewDto> myReviewDetail(@PathVariable("reviewNum") int reviewNum) throws Exception {
		ReviewDto reviewDto = reviewService.selectMyReviewDetail(reviewNum);
		if (reviewDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.ok(reviewDto);
		}
	}

	/* 내 상품에 대한 상대방의 후기 상세 페이지 */
	@RequestMapping(value = "/review/yourReview/{reviewNum}", method = RequestMethod.GET)
	public ResponseEntity<ReviewDto> yourReviewDetail(@PathVariable("reviewNum") int reviewNum) throws Exception {
		ReviewDto reviewDto = reviewService.selectYourReviewDetail(reviewNum);
		if (reviewDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.ok(reviewDto);
		}
	}

	/* 후기 수정 */
	@RequestMapping(value = "/review/myReview/{reviewNum}", method = RequestMethod.PUT)
	public void updateReview(@PathVariable("reviewNum") int reviewNum, @RequestBody ReviewDto reviewDto)
			throws Exception {
		reviewDto.setReviewNum(reviewNum);
		reviewService.updateReview(reviewDto);
	}

	/* 후기 삭제 */
	@RequestMapping(value = "/review/myReview/{reviewNum}", method = RequestMethod.DELETE)
	public void deleteReview(@PathVariable("reviewNum") int reviewNum) throws Exception {
		reviewService.deleteReview(reviewNum);
	}
}
