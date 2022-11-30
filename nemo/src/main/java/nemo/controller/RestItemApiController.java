package nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;
import nemo.service.ItemService;



@RestController
@RequestMapping("/api")
public class RestItemApiController {

   @Autowired
   private ItemService itemService;
   
   
   //메인페이지 로그인 안되어있을 때 날씨추천리스트에 랜덤으로 띄워줄 데이터조회
   @RequestMapping(value = "/item/random", method = RequestMethod.GET)
   public List<ItemDto> selectItemListRandom() throws Exception {
	   return itemService.selectItemListRandom();
   }
   
   //날씨별 상품리스트 조회
   @RequestMapping(value = "/item/weather/{itemWeather}", method = RequestMethod.GET)
   public List<ItemDto> selectItemListByWeather(@PathVariable("itemWeather") String itemWeather) throws Exception {
	   return itemService.selectItemListByWeather(itemWeather);
   }
   
   // 주간베스트 아이템 조회
   @RequestMapping(value = "/item/best", method = RequestMethod.GET)
   public List<ItemDto> selectItemListWeeklyBest() throws Exception {
	   // 이번주 대여이력이 있으면 대여횟수가 많은 순서대로 정렬하고, 대여이력이 없으면 조회순으로 정렬해서 보여준다
	   if(itemService.selectItemListWeeklyBest().isEmpty()) {
		   return itemService.selectItemListWeeklyBestByReadcount();
	   }else {
		   return itemService.selectItemListWeeklyBest();
	   }
   }
   
   //베스트 스토어
   @RequestMapping(value = "/item/beststore", method = RequestMethod.GET)
   public List<ItemDto> selectItemBestStoreByReadcount() throws Exception {
		   return itemService.selectItemBestStoreByReadcount();
   }
   
   
   // 상품리스트 main 조회
   @RequestMapping(value = "/item/cate/{itemMaincategory}", method = RequestMethod.GET)
   public List<ItemDto> selectItemList(@PathVariable("itemMaincategory") String itemMaincategory) throws Exception {
      return itemService.selectItemList(itemMaincategory);
   }
   
   // 상품리스트 sub 조회
   @RequestMapping(value = "/item/cate/sub/{itemSubcategory}", method = RequestMethod.GET)
   public List<ItemDto> selectItemsubList(@PathVariable("itemSubcategory") String itemSubcategory) throws Exception {
      return itemService.selectItemsubList(itemSubcategory);
   }
   
   // 내 상품리스트 조회
   @RequestMapping(value = "/mypage/mypageitem/{itemWriter}", method = RequestMethod.GET)
   public List<ItemDto> myItemList(@PathVariable("itemWriter") String itemWriter) throws Exception {
      return itemService.myItemList(itemWriter);
   }

   // 너의 상품리스트 조회
   @RequestMapping(value = "/mypage/yourpageitem/{itemWriter}", method = RequestMethod.GET)
   public List<ItemDto> yourItemList(@PathVariable("itemWriter") String itemWriter) throws Exception {
	   return itemService.yourItemList(itemWriter);
   }

   //상품 등록 
   @RequestMapping(value = "/item", method = RequestMethod.POST)
      public ResponseEntity<String> insertItem(@RequestPart("data") ItemDto item, @RequestPart("files") MultipartFile files) throws Exception {
         int itemNum = itemService.insertItem(item,files);
         if (itemNum > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("등록성공");
         } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록실패");
         }
      }


   //상품리스트 상세
   @RequestMapping(value = "/item/detail/{itemNum}", method = RequestMethod.GET)
   public ResponseEntity<ItemDto> selectItemDetail (@PathVariable("itemNum") int itemNum) throws Exception {
      ItemDto itemDto = itemService.selectItemDetail(itemNum);
      if (itemDto == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      } else {
         return ResponseEntity.ok(itemDto);
      }
   }
   
 
   //상품 수정
   @RequestMapping(value = "/item/{itemNum}", method = RequestMethod.PUT)
   public void updateItem(@PathVariable("itemNum") int itemNum, @RequestPart("data") ItemDto itemDto, @RequestPart(value = "files", required = false) MultipartFile files) throws Exception {
      itemDto.setItemNum(itemNum);
      itemService.updateItem(itemDto,files);
   }

   
   //상품 삭제
   @RequestMapping(value = "/item/{itemNum}", method = RequestMethod.DELETE)
   public void deleteItem(@PathVariable("itemNum") int itemNum) throws Exception {
      itemService.deleteItem(itemNum);
   }
   
   

}