package nemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.BookingDto;
import nemo.dto.ItemDto;
import nemo.mapper.BookingMapper;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingMapper bookingMapper;
	
//	 @Autowired
//     private ItemService service2;
	
	 //상품등록
	   @Override
	     public int insertBooking(BookingDto booking) throws Exception {
//		   ItemDto item = service2.selectItemDetail(itemNum);
//		   	booking.setBookingItemnum(item);
//	        booking.setBookingWrite("loginId");
	        return bookingMapper.insertBooking(booking);
	     }

}
