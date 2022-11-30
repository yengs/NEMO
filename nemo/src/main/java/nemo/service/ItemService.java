package nemo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;

public interface ItemService {

   public List<ItemDto> selectItemListRandom() throws Exception;
   public List<ItemDto> selectItemListByWeather(String itemWeather) throws Exception;
   public List<ItemDto> selectItemListWeeklyBest() throws Exception;
   public List<ItemDto> selectItemListWeeklyBestByReadcount() throws Exception;
   public List<ItemDto> selectItemBestStoreByReadcount() throws Exception; //베스트 스토어
   public List<ItemDto> selectItemList(String itemMaincategory) throws Exception;
   public List<ItemDto> selectItemsubList(String itemSubcategory) throws Exception;
   public List<ItemDto> myItemList(String itemWriter) throws Exception;
   public List<ItemDto> yourItemList(String itemWriter) throws Exception;
   
   public int insertItem(@RequestPart("data") ItemDto item, @RequestPart("files") MultipartFile files) throws Exception;
   public ItemDto selectItemDetail(int itemNum) throws Exception;
   
   public void updateItem(@RequestPart("data") ItemDto itemDto, @RequestPart("files") MultipartFile files) throws Exception;
   public void deleteItem(int itemNum) throws Exception;
   
}