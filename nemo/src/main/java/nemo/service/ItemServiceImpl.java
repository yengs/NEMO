package nemo.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nemo.dto.ItemDto;

import nemo.mapper.ItemMapper;

@Service 
public class ItemServiceImpl implements ItemService{
   
   @Autowired
   private ItemMapper itemMapper;
   
   
   //상품 main 리스트
   @Override
    public List<ItemDto> selectItemList(String itemMaincategory) throws Exception {
        return itemMapper.selectItemList(itemMaincategory);
     }
   //상품 sub 리스트
   @Override
    public List<ItemDto> selectItemsubList(String itemSubcategory) throws Exception {
        return itemMapper.selectItemsubList(itemSubcategory);
     }
   
   //내 상품 리스트
   @Override
   public List<ItemDto> myItemList() throws Exception {
       return itemMapper.myItemList();
    }
     
   //상품등록
   @Override
     public int insertItem( @RequestPart("data") ItemDto item, @RequestPart("files") MultipartFile files) throws Exception {
	   
	   String projectpath = "C:\\Users\\hi\\git\\NEMO-react\\nemo-project\\public\\files";
	   UUID uuid = UUID.randomUUID();
	   String filename = uuid+"_"+files.getOriginalFilename();
	   File saveFile = new File(projectpath,filename);
	   item.setFiles(filename);
	   try {
	         files.transferTo(saveFile);
	      } catch (IllegalStateException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	         
	         
	   // item.setIDate(new Date()); 
        item.setItemWriter("잠시안녕");
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
           itemMapper.updateReadCount(itemNum);
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