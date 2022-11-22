package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.BookingDto;
import nemo.dto.ItemDto;


@Mapper
public interface BookingMapper {
	
	void insertBooking(BookingDto bookingDto) throws Exception; //대여하기
	
	List<BookingDto> myLendList(String bookingItemwriter) throws Exception; //빌려줬어요
	
	List<BookingDto> myBookingList(String bookingMember) throws Exception; //빌려왔어요

	void bookingCancel(int bookingNum) throws Exception; //예약취소
	
	int updateBooking(BookingDto bookingDto) throws Exception; //보증금상태 수정
	
	void updateBookingstate() throws Exception;  //예약중->대여중
    void updateBookingstate2() throws Exception;  //대여중 -> 기간만료

    List<BookingDto> allBooking(int bookingItemnum) throws Exception; //모든 대여날짜
    
    void updateReviewCount(int bookingNum) throws Exception; //후기작성 여부
}
