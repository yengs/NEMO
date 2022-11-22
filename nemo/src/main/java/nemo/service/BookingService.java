package nemo.service;

import java.util.List;

import nemo.dto.BookingDto;

public interface BookingService {

	public void insertBooking(BookingDto bookingDto) throws Exception; // 대여하기
	
	List<BookingDto> myLendList(String bookingItemwriter) throws Exception; //빌려줬어요
	
	public List<BookingDto> myBookingList(String bookingMember) throws Exception; //빌려왔어요
	
	public void bookingCancel(int bookingNum) throws Exception; //예약취소
	
	public void updateBooking(BookingDto bookingDto) throws Exception;//보증금 상태 수정
	
	public void updateBookingstate() throws Exception;  //예약중->대여중
    public void updateBookingstate2() throws Exception;  //대여중 -> 기간만료

    public List<BookingDto> allBooking(int bookingItemnum) throws Exception; //모든 대여날짜
    
}
