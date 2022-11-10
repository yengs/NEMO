package nemo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.BookingDto;
import nemo.mapper.BookingMapper;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingMapper bookingMapper;
	
	 //상품등록
	   @Override
	     public void insertBooking(BookingDto booking) throws Exception {
	       bookingMapper.insertBooking(booking);
	     }
	
	 //빌려줬어요
	   @Override
		public List<BookingDto> myLendList(String bookingItemwriter) throws Exception {
			return bookingMapper.myLendList(bookingItemwriter);
	   }
	   
	 //빌려왔어요
	      @Override
	      public List<BookingDto> myBookingList(String bookingMember) throws Exception {
	          return bookingMapper.myBookingList(bookingMember);
	       }
	   
	   

}
