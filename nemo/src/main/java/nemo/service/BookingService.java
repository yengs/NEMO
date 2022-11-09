package nemo.service;

import nemo.dto.BookingDto;

public interface BookingService {

	public void insertBooking(BookingDto booking) throws Exception; // 대여하기
	
}
