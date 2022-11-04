package nemo.service;

import java.util.List;
import nemo.dto.ItemDto;

public interface ItemService {

   public List<ItemDto> selectItemList(String itemMaincategory) throws Exception;
   public List<ItemDto> selectItemsubList(String itemSubcategory) throws Exception;
   public List<ItemDto> myItemList() throws Exception;
   
   public int insertItem(ItemDto item) throws Exception;
   public ItemDto selectItemDetail(int itemNum) throws Exception;
   
   public void updateItem(ItemDto itemDto) throws Exception;
   public void deleteItem(int itemNum) throws Exception;
   
}