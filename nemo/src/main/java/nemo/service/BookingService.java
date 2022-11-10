package nemo.service;

import java.util.List;

import nemo.dto.BookingDto;

public interface BookingService {

	public void insertBooking(BookingDto booking) throws Exception; // 대여하기
	
	List<BookingDto> myLendList(String bookingItemwriter) throws Exception; //빌려줬어요
	
	public List<BookingDto> myBookingList(String bookingMember) throws Exception; //빌려왔어요
	
}
