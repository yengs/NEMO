package nemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;
import nemo.service.ItemService;



@RestController
@RequestMapping("/api")
public class RestItemApiController {

   @Autowired
   private ItemService itemService;
   
   
   // 상품리스트 조회
//   @RequestMapping(value = "/item", method = RequestMethod.GET)
//   public List<ItemDto> selectItemList() throws Exception {
//      return itemService.selectItemList();
//   }
   
   
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

   @RequestMapping(value = "/mypage", method = RequestMethod.GET)
   public List<ItemDto> myItemList() throws Exception {
      return itemService.myItemList();
   }
  

   //상품 등록 
   @RequestMapping(value = "/item", method = RequestMethod.POST)
      public ResponseEntity<String> insertItem(@RequestBody ItemDto item, MultipartFile file) throws Exception {
         int itemNum = itemService.insertItem(item);
         if (itemNum > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("등록성공");
         } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록실패");
         }
      }


   //상품리스트 상세
   @RequestMapping(value = "/item/{itemNum}", method = RequestMethod.GET)
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
   public void updateItem(@PathVariable("itemNum") int itemNum, @RequestBody ItemDto boardDto) throws Exception {
      boardDto.setItemNum(itemNum);
      itemService.updateItem(boardDto);
   }

   
   //상품 삭제
   @RequestMapping(value = "/item/{itemNum}", method = RequestMethod.DELETE)
   public void deleteItem(@PathVariable("itemNum") int itemNum) throws Exception {
      itemService.deleteItem(itemNum);
   }

}