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
   
   //로그인 안했을 때 메인 날씨추천리스트에 랜덤으로 뿌려줄 데이터
   @Override
    public List<ItemDto> selectItemListRandom() throws Exception {
        return itemMapper.selectItemListRandom();
     }
   
   //상품 날씨별 리스트
   @Override
    public List<ItemDto> selectItemListByWeather(String itemWeather) throws Exception {
        return itemMapper.selectItemListByWeather(itemWeather);
     }
   
   //주간베스트 아이템 리스트
   @Override
    public List<ItemDto> selectItemListWeeklyBest() throws Exception {
        return itemMapper.selectItemListWeeklyBest();
     }

   //주간베스트 아이템 리스트
   @Override
   public List<ItemDto> selectItemListWeeklyBestByReadcount() throws Exception {
	   return itemMapper.selectItemListWeeklyBestByReadcount();
   }
   
 //베스트 스토어 리스트
   @Override
   public List<ItemDto> selectItemBestStoreByReadcount() throws Exception {
	   return itemMapper.selectItemBestStoreByReadcount();
   }
   
   
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
   public List<ItemDto> myItemList(String itemWriter) throws Exception {
       return itemMapper.myItemList(itemWriter);
    }

   //너의 상품 리스트
   @Override
   public List<ItemDto> yourItemList(String itemWriter) throws Exception {
	   return itemMapper.yourItemList(itemWriter);
   }
     
   //상품등록
   @Override
     public int insertItem( @RequestPart("data") ItemDto item, @RequestPart("files") MultipartFile files) throws Exception {
	   
	   String projectpath = "C:\\nemo\\git\\NEMO-react\\NEMO-react\\nemo-project\\public\\files";
	   
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
        return itemMapper.insertItem(item);
     }
  
   //상품리스트상세 
   @Override
     public ItemDto selectItemDetail(int itemNum) throws Exception {
           itemMapper.updateReadCount(itemNum);
           return itemMapper.selectItemDetail(itemNum);
     }
   
   
   
   //상품수정
   @Override
	public void updateItem(@RequestPart("data") ItemDto itemDto, @RequestPart("files") MultipartFile files) throws Exception {
		
	   if ( files != null) {
	      String projectpath = "C:\\nemo\\git\\NEMO-react\\NEMO-react\\nemo-project\\public\\files";
		   
		   UUID uuid = UUID.randomUUID();
		   String filename = uuid+"_"+files.getOriginalFilename();
		   File saveFile = new File(projectpath,filename);
		   itemDto.setFiles(filename);
		   try {
		         files.transferTo(saveFile);
		      } catch (IllegalStateException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }	
	   }
	   int count = itemMapper.updateItem(itemDto);
		System.out.println("***************** " + count);
	}
   
   //상품삭제
	@Override
	public void deleteItem(int itemNum) throws Exception {
		int count = itemMapper.deleteItem(itemNum);
		System.out.println("***************** " + count);
	}
	
    

  
   
}