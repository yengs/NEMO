package nemo.dto;

import java.sql.Date;

import lombok.Data;

@Data
	public class BookingDto {
		
		private int bookingNum;
        private String bookingMember; 
        private String bookingBookingstate;
        private String bookingDepositstate;
        private Date bookingDate;
        private int bookingItemnum;
        private String bookingItemname;
        private int bookingItemprice;
        private String bookingItemwriter;
        private String bookingItemfiles;
        private String bookingYn;
        private int reviewCount;
        
        private String bookingDepositstateN;
        
        private String memberNickname;
        private String memberImg;

}
