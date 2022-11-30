package nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.BookingDto;
import nemo.dto.ItemDto;
import nemo.dto.MemberDto;
import nemo.service.BookingService;

@RestController
@RequestMapping("/api")
public class RestBookingApiController {
	
	@Autowired
	private BookingService bookingService;
	
	//대여하기
	@RequestMapping(value="/booking/bookingWrite", method = RequestMethod.POST)
	public void insertBooking(@RequestBody BookingDto bookingDto) throws Exception {
		bookingService.insertBooking(bookingDto);
	}
	
	//빌려줬어요
	@RequestMapping(value = "/mypage/mybooking/{bookingItemwriter}", method = RequestMethod.GET)
	   public List<BookingDto> myLendList(@PathVariable("bookingItemwriter") String bookingItemwriter) throws Exception {
	      return bookingService.myLendList(bookingItemwriter);
	   }
	
	//빌려왔어요
    @RequestMapping(value = "/mypagebookingmember/{bookingMember}", method = RequestMethod.GET)
    public List<BookingDto> myBookingList(@PathVariable("bookingMember") String bookingMember) throws Exception {
       return bookingService.myBookingList(bookingMember);
    }
    
    //예약취소
    @RequestMapping(value = "/mypage/mybooking/{bookingNum}", method = RequestMethod.DELETE)
    public void bookingCancel(@PathVariable("bookingNum") int bookingNum) throws Exception {
    	bookingService.bookingCancel(bookingNum);
    }
    
    //보증금상태 수정
    @RequestMapping(value="/mypage/mybooking/{bookingNum}", method = RequestMethod.PUT)
	public void updateBooking(@PathVariable("bookingNum") int bookingNum, @RequestBody BookingDto bookingDto) throws Exception {
		bookingDto.setBookingNum(bookingNum);
		bookingService.updateBooking(bookingDto);
	}
    
  //예약중 -> 대여중
    //대여중 -> 기간만료
    @RequestMapping(value = "/bookingState")
    public void updateBookingstate()
            throws Exception {
        bookingService.updateBookingstate();
        bookingService.updateBookingstate2();
    }


  //모든 대여날짜
    @RequestMapping(value = "/allbooking/{bookingItemnumber}", method = RequestMethod.GET)
       public List<BookingDto> allBooking(@PathVariable("bookingItemnumber") int bookingItemnum) throws Exception {
          return bookingService.allBooking(bookingItemnum);
       }


}
