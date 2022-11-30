package nemo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import nemo.dto.ItemDto;

@Mapper
public interface ItemMapper {
   
	List<ItemDto> selectItemListRandom() throws Exception;
	List<ItemDto> selectItemListByWeather(String itemWeather) throws Exception;
	List<ItemDto> selectItemListWeeklyBest() throws Exception;
	List<ItemDto> selectItemListWeeklyBestByReadcount() throws Exception;
	List<ItemDto> selectItemBestStoreByReadcount() throws Exception; //베스트 스토어
    List<ItemDto> selectItemList(String itemMaincategory) throws Exception;
    List<ItemDto> selectItemsubList(String itemSubcategory) throws Exception;
    List<ItemDto> myItemList(String itemWriter) throws Exception;
    List<ItemDto> yourItemList(String itemWriter) throws Exception;
    
    void updateReadCount(int itemNum) throws Exception;
    int insertItem(ItemDto item) throws Exception;
    ItemDto selectItemDetail(int itemNum) throws Exception;
    
    int updateItem(ItemDto itemDto) throws Exception;
    int deleteItem(int itemNum) throws Exception;
	
}