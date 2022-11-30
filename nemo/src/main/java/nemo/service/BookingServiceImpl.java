package nemo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemo.dto.BookingDto;
import nemo.mapper.BookingMapper;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingMapper bookingMapper;
	
	
	 //상품등록
	   @Override
	     public void insertBooking(BookingDto bookingDto) throws Exception {
	       bookingMapper.insertBooking(bookingDto);
	     }
	
	 //빌려줬어요
	   @Override
		public List<BookingDto> myLendList(String bookingItemwriter) throws Exception {
			return bookingMapper.myLendList(bookingItemwriter);
	   }
	   
	 //빌려왔어요
	      @Override
	      public List<BookingDto> myBookingList(String bookingMember) throws Exception {
	          return bookingMapper.myBookingList(bookingMember);
	       }
	      
	 //예약취소
		@Override
		public void bookingCancel(int bookingNum) throws Exception {
			bookingMapper.bookingCancel(bookingNum);
		}
		
	 //보증금 상태 수정
		@Override
		public void updateBooking(BookingDto bookingDto) throws Exception {
			int count = bookingMapper.updateBooking(bookingDto);
			System.out.println("***************** " + count);
		}
		
		//예약중->대여중
        @Override
          public void updateBookingstate() throws Exception {
            bookingMapper.updateBookingstate();
          }


     //대여중 -> 기간만료
        @Override
          public void updateBookingstate2() throws Exception {
            bookingMapper.updateBookingstate2();
          }


      //모든 대여날짜
        @Override
        public List<BookingDto> allBooking(int bookingItemnum) throws Exception {
            return bookingMapper.allBooking(bookingItemnum);
       }
       

	   
	   

}
