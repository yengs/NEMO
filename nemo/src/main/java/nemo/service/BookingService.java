package nemo.service;

import nemo.dto.BookingDto;

public interface BookingService {

	public int insertBooking(BookingDto booking) throws Exception; // 대여하기
	
}
