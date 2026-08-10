package com.MATS.Degree.service;

import com.MATS.Degree.dto.requestDto.DegreeRequestDto;
import com.MATS.Degree.dto.responseDto.DegreeResponseDto;

public interface DegreeService {

    DegreeResponseDto createDegree(DegreeRequestDto degreeRequestDto);

    DegreeResponseDto getDegree(Long id);

    byte[] generateDegree(Long id);

}
