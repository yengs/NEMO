package nemo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;

public interface ItemService {

   public List<ItemDto> selectItemList() throws Exception;
   public int insertItem(ItemDto item) throws Exception;
   public ItemDto selectItemDetail(int itemNum) throws Exception;
   
   public void updateItem(ItemDto itemDto) throws Exception;
   public void deleteItem(int itemNum) throws Exception;
   
}