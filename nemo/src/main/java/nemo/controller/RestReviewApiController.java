package nemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.BookingDto;
import nemo.dto.ItemDto;
import nemo.dto.ReviewDto;
import nemo.mapper.BookingMapper;
import nemo.service.BookingService;
import nemo.service.ReviewService;

@RestController
@RequestMapping("/api")
public class RestReviewApiController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BookingMapper bookingMapper;

	/* 내가 작성한 후기 목록 */
	@RequestMapping(value = "/review/myReview/{reviewWriter}", method = RequestMethod.GET)
	public List<ReviewDto> myReviewList(@PathVariable("reviewWriter") String reviewWriter) throws Exception {
		return reviewService.selectMyReviewList(reviewWriter);
	}

	/* 내 상품에 대해 상대방이 쓴 후기 목록 */
	@RequestMapping(value = "/review/yourReview/{reviewId}", method = RequestMethod.GET)
	public List<ReviewDto> yourReviewList(@PathVariable("reviewId") String reviewId) throws Exception {
		System.out.println(reviewId);
		return reviewService.selectYourReviewList(reviewId);
	}

	/* 후기 등록 */
	@RequestMapping(value = "/review/reviewWrite/{bookingNumm}", method = RequestMethod.POST)
	public void insertReview(@PathVariable("bookingNumm") int bookingNum, @RequestPart(value="reviewData",required=false) ReviewDto review, @RequestPart(value="reviewFiles",required=false) MultipartFile files) throws Exception {
	
		bookingMapper.updateReviewCount(bookingNum);
		reviewService.insertReview(review, files);
	}

	
	/* 후기 상세 */
	@RequestMapping(value = "/review/myReview/{reviewWriter}/{reviewNum}", method = RequestMethod.GET)
	public ResponseEntity<ReviewDto> selectMyReviewDetail (@PathVariable("reviewNum") int reviewNum) throws Exception {
		ReviewDto reviewDto = reviewService.selectMyReviewDetail(reviewNum);
		if (reviewDto == null) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	      } else {
	         return ResponseEntity.ok(reviewDto);
	      }
	   }
	
	/* 후기 수정 */
	@RequestMapping(value = "/review/myReview/{reviewWriter}/{reviewNum}", method = RequestMethod.PUT)
	public void updateReview(@PathVariable("reviewNum") int reviewNum, @RequestPart("data") ReviewDto reviewDto, @RequestPart(value = "reviewFiles", required = false) MultipartFile reviewFiles)
			throws Exception {
		reviewDto.setReviewNum(reviewNum);
		reviewService.updateReview(reviewDto,reviewFiles);
	}

	/* 후기 삭제 */
	@RequestMapping(value = "/review/myReview/{reviewWriter}/{reviewNum}", method = RequestMethod.DELETE)
	public void deleteReview(@PathVariable("reviewWriter") String reviewWriter, @PathVariable("reviewNum") int reviewNum) throws Exception {
		reviewService.deleteReview(reviewWriter, reviewNum);
	}

	// 내 상품에 대한 리뷰 중 가장 최근 리뷰 1개
	@RequestMapping(value = "/mypage/review1/{reviewId}", method = RequestMethod.GET)
	public List<ReviewDto> mostRecentReviewOfMyStore(String reviewId) throws Exception {
		return reviewService.mostRecentReviewOfMyStore(reviewId);
	}

	// 내가 작성한 리뷰 중 가장 최근 리뷰 2개
	@RequestMapping(value = "/mypage/review2/{reviewWriter}", method = RequestMethod.GET)
	public List<ReviewDto> twoOfMyMostRecentReviews(String reviewWriter) throws Exception {
		return reviewService.twoOfMyMostRecentReviews(reviewWriter);
	}

	// 아이템 밑에 후기 조회
	@RequestMapping(value = "/itemreview/{reviewProductIdx}", method = RequestMethod.GET)
	public List<ReviewDto> ItemReview(@PathVariable("reviewProductIdx") int reviewProductIdx) throws Exception {
		return reviewService.ItemReview(reviewProductIdx);
	}
	
	// 클린지수 조회
	@RequestMapping(value = "/clean/{reviewId}", method = RequestMethod.GET)
	public List<ReviewDto> selectSatisfaction(@PathVariable("reviewId") String reviewId) throws Exception {
		return reviewService.selectSatisfaction(reviewId);
	};
	
}
