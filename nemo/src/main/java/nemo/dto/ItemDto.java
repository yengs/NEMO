package nemo.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ItemDto {
   private int itemNum;
   private String itemMaincategory ;
   private String itemSubcategory;
   private String itemName;
   private int itemPrice;
   private int itemDeposit;
   private Date itemDate;
   private int itemReadcount;
   private String itemDetail;
   private String itemWriter;
   private String itemWeather;
   private String itemTopsize;
   private String itemBottomsize;
   private String itemEtcsize;
   private Date itemRentalstart;
   private Date itemRentalend;
   private String files;
   
   private String memberImg;
   private String memberNickname;

   
}