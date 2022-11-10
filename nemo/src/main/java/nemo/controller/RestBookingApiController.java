package nemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nemo.dto.BookingDto;
import nemo.service.BookingService;

@RestController
@RequestMapping("/api")
public class RestBookingApiController {
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value="/booking/bookingWrite", method = RequestMethod.POST)
	public void insertBooking(@RequestBody BookingDto booking) throws Exception {
		bookingService.insertBooking(booking);
	}


}
