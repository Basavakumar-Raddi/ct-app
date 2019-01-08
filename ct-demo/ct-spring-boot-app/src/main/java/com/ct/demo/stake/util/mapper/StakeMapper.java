package com.ct.demo.stake.util.mapper;

import com.ct.demo.stake.dto.PointDTO;
import com.ct.demo.stake.dto.StakeDTO;
import com.ct.demo.stake.entity.Point;
import com.ct.demo.stake.entity.Stake;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StakeMapper {

	@Mappings({
			@Mapping(target="initiationDate",expression = "java( com.ct.demo.stake.util.DateUtil.getMillisFromStringCTDemo(stakeDTO.getInitiationDate()))")
	})
	public Stake mapDtoToEntity(StakeDTO stakeDTO);

	@Mappings({
			@Mapping(source = "id", target="id"),
			@Mapping(target="initiationDate",expression = "java( com.ct.demo.stake.util.DateUtil.getStringFromMillisCTDemo(stake.getInitiationDate()))")
	})
	public StakeDTO mapEntityToDto(Stake stake);

}
