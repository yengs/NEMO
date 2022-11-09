package nemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.BookingDto;


@Mapper
public interface BookingMapper {
	
	int insertBooking(BookingDto booking) throws Exception; // 대여하기

}
