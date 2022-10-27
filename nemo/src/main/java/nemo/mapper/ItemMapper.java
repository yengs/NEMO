package nemo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import nemo.dto.ItemDto;
import nemo.dto.ItemDto;

@Mapper
public interface ItemMapper {
   
    List<ItemDto> selectItemList() throws Exception;
    int insertItem(ItemDto item) throws Exception;
    ItemDto selectItemDetail(int itemNum) throws Exception;
    
    int updateItem(ItemDto itemDto) throws Exception;
	int deleteItem(int itemNum) throws Exception;
}