package nemo.dto;

import java.sql.Date;

import lombok.Data;

@Data
	public class BookingDto {
		
		private int bookingNum;
		private int bookingItemnum;
        private String bookingWrite; 
        private String bookingBookingstate;
        private String bookingDepositstate;
        private Date bookingDate;

}
