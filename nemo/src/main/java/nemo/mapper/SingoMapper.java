package nemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import nemo.dto.SingoDto;

@Mapper
public interface SingoMapper {
	
	int insertSingo(SingoDto singoDto) throws Exception;

}
