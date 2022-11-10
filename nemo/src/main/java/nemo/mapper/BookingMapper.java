package nemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.BookingDto;
import nemo.dto.ItemDto;


@Mapper
public interface BookingMapper {
	
	void insertBooking(BookingDto booking) throws Exception; //대여하기
	
	List<BookingDto> myLendList(String bookingItemwriter) throws Exception; //빌려줬어요
	
	List<BookingDto> myBookingList(String bookingMember) throws Exception; //빌려왔어요

}
