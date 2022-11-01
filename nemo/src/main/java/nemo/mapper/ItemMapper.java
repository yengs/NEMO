package nemo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import nemo.dto.ItemDto;

@Mapper
public interface ItemMapper {
   
    List<ItemDto> selectItemList(String itemMaincategory) throws Exception;
    List<ItemDto> selectItemsubList(String itemSubcategory) throws Exception;
    void updateReadCount(int itemNum) throws Exception;
    int insertItem(ItemDto item) throws Exception;
    ItemDto selectItemDetail(int itemNum) throws Exception;
    
    int updateItem(ItemDto itemDto) throws Exception;
	int deleteItem(int itemNum) throws Exception;
	
}