package nemo.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;

import nemo.mapper.ItemMapper;

@Service 
public class ItemServiceImpl implements ItemService{
   
   @Autowired
   private ItemMapper itemMapper;
   
   
   //상품 리스트
   @Override
    public List<ItemDto> selectItemList() throws Exception {
        return itemMapper.selectItemList();
     }
     
   //상품등록
   @Override
     public int insertItem(ItemDto item) throws Exception {
	   
	   
//	   String projectpath = System.getProperty("user.dir")+"/src/main/resources/static/files";
//	   UUID uuid = UUID.randomUUID();
//	   String itemImage = uuid+"_"+file.getOriginalFilename();
//	   File saveFile = new File(projectpath,itemImage);
//	   try {
//	         file.transferTo(saveFile);
//	      } catch (IllegalStateException e) {
//	         // TODO Auto-generated catch block
//	         e.printStackTrace();
//	      } catch (IOException e) {
//	         // TODO Auto-generated catch block
//	         e.printStackTrace();
//	      }
//	         item.setItemImage(itemImage);
//	         item.setItemImagepath("/files/"+itemImage);
	         
	         
	   //        item.setIDate(new Date()); 
        item.setItemWriter("잠시안녕");
        item.setItemImage("asdsa");
        item.setItemImagepath("asfasf");
        return itemMapper.insertItem(item);
     }
  
   //상품리스트상세 
   @Override
     public ItemDto selectItemDetail(int itemNum) throws Exception {
//         public ItemDto selectItemDetail(int iNum, String loginId) throws Exception {
//
//        ItemDto item = itemMapper.selectItemDetail(iNum);
//        if(loginId != null && loginId.equals(item.getItemWriter())) {
//           return item;
//        }else {
//           itemMapper.updateReadCount(iNum);
           return itemMapper.selectItemDetail(itemNum);
//        }
     }
   
   
   @Override
	public void updateItem(ItemDto itemDto) throws Exception {
		int count = itemMapper.updateItem(itemDto);
		System.out.println("***************** " + count);
	}

	@Override
	public void deleteItem(int itemNum) throws Exception {
		int count = itemMapper.deleteItem(itemNum);
		System.out.println("***************** " + count);
	}
    

  
   
}