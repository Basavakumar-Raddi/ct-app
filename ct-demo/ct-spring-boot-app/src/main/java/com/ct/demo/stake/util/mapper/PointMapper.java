package com.ct.demo.stake.util.mapper;

import com.ct.demo.stake.entity.Point;
import com.ct.demo.stake.dto.PointDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PointMapper {

	//TestInfo mapEntityToInfo(TestEntity testEntity);

	@Mappings({
			@Mapping(target="measurementDay",expression = "java( com.ct.demo.stake.util.DateUtil.getMillisFromString(pointDTO.getMeasurementDay()))")
	})
	public Point mapDtoToEntity(PointDTO pointDTO);

	@Mappings({
			@Mapping(source = "id", target="id"),
			@Mapping(target="measurementDay",expression = "java( com.ct.demo.stake.util.DateUtil.getStringFromMillis(point.getMeasurementDay()))")
	})
	public PointDTO mapEntityToDto(Point point);

}
