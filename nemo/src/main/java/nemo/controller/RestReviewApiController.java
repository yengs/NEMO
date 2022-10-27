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

import board.dto.BoardDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import nemo.dto.ReviewDto;
import nemo.service.ReviewService;

@RestController
@RequestMapping("/api")
public class RestReviewApiController {
	
	@Autowired
	private ReviewService reviewService;
	
	@ApiOperation(value="나의 후기 목록 조회", notes = "내가 쓴 후기 목록")
	@RequestMapping(value="/myReview", method=RequestMethod.GET)
	public List<ReviewDto> myReviewList() throws Exception {
		return reviewService.selectMyReviewList();
	}
	
	@ApiOperation(value="타인의 후기 목록 조회", notes = "나의 상품에 타인이 쓴 후기 목록")
	@RequestMapping(value="/yourReview", method=RequestMethod.GET)
	public List<ReviewDto> yourReviewList() throws Exception {
		return reviewService.selectMyReviewList();
	}
	
	@ApiOperation(value = "후기 작성", notes = "후기를 작성하고 등록")
	@RequestMapping(value = "/reviewWrite", method = RequestMethod.POST)
	public void insertReview(
			@Parameter(description = "후기 정보", required = true, example = "{ reviewContents: 내용 }") @RequestBody ReviewDto review)
			throws Exception {
		reviewService.insertReview(review);
	}
	
	@ApiOperation(value = "나의 후기 상세 조회", notes = "나의 후기 상세 정보를 조회")
	@RequestMapping(value = "/myReview/{reviewNum}", method = RequestMethod.GET)
	public ResponseEntity<ReviewDto> myReviewDetail(
			@Parameter(description = "후기 번호", required = true, example = "1") @PathVariable("reviewNum") int reviewNum)
			throws Exception {
		ReviewDto reviewDto = reviewService.selectMyReviewDetail(reviewNum);
		if (reviewDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			// return ResponseEntity.status(HttpStatus.OK).body(boardDto);
			return ResponseEntity.ok(reviewDto);
		}
	}
	
	@ApiOperation(value = "상대방 후기 상세 조회", notes = "상대방 후기 상세 정보를 조회")
	@RequestMapping(value = "/yourReview/{reviewNum}", method = RequestMethod.GET)
	public ResponseEntity<ReviewDto> yourReviewDetail(
			@Parameter(description = "후기 번호", required = true, example = "1") @PathVariable("reviewNum") int reviewNum)
			throws Exception {
		ReviewDto reviewDto = reviewService.selectYourReviewDetail(reviewNum);
		if (reviewDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			// return ResponseEntity.status(HttpStatus.OK).body(boardDto);
			return ResponseEntity.ok(reviewDto);
		}
	}
	
	@RequestMapping(value = "/myReview/{reviewNum}", method = RequestMethod.PUT)
	public void updateReview(@PathVariable("reviewNum") int reviewNum, @RequestBody ReviewDto reviewDto) throws Exception {
		reviewDto.setReviewNum(reviewNum);
		reviewService.updateReview(reviewDto);
	}

	@RequestMapping(value = "/myReview/{reviewNum}", method = RequestMethod.DELETE)
	public void deleteReview(@PathVariable("reviewNum") int reviewNum) throws Exception {
		reviewService.deleteReview(reviewNum);
	}
}
