package nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nemo.dto.BookingDto;
import nemo.dto.ItemDto;
import nemo.service.BookingService;

@RestController
@RequestMapping("/api")
public class RestBookingApiController {
	
	@Autowired
	private BookingService bookingService;
	
	//대여하기
	@RequestMapping(value="/booking/bookingWrite", method = RequestMethod.POST)
	public void insertBooking(@RequestBody BookingDto booking) throws Exception {
		bookingService.insertBooking(booking);
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


}
