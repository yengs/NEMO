package nemo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;

public interface ItemService {

   public List<ItemDto> selectItemList(String itemMaincategory) throws Exception;
   public List<ItemDto> selectItemsubList(String itemSubcategory) throws Exception;
   public List<ItemDto> myItemList() throws Exception;
   
   public int insertItem(@RequestPart("data") ItemDto item, @RequestPart("files") MultipartFile files) throws Exception;
   public ItemDto selectItemDetail(int itemNum) throws Exception;
   
   public void updateItem(ItemDto itemDto) throws Exception;
   public void deleteItem(int itemNum) throws Exception;
   
}