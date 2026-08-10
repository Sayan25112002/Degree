package com.MATS.Degree.mapper;

import com.MATS.Degree.dto.requestDto.DegreeRequestDto;
import com.MATS.Degree.dto.responseDto.DegreeResponseDto;
import com.MATS.Degree.entity.Degree;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DegreeMapper {

    Degree toDegree(DegreeRequestDto degreeRequestDto);

    DegreeResponseDto toDegreeResponseDto(Degree degree);

    List<DegreeResponseDto> toDegreeResponseDtoList(List<Degree> degrees);

}
