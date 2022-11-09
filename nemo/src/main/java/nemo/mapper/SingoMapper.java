package nemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.SingoDto;

@Mapper
public interface SingoMapper {
	
	void insertSingo(SingoDto singoDto) throws Exception;

}
