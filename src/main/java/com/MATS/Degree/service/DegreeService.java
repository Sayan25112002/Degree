package com.MATS.Degree.service;

import com.MATS.Degree.dto.requestDto.DegreeRequestDto;
import com.MATS.Degree.dto.responseDto.DegreeResponseDto;

import java.io.IOException;

public interface DegreeService {

    DegreeResponseDto createDegree(DegreeRequestDto degreeRequestDto) throws IOException;

    DegreeResponseDto getDegree(Long id);

    byte[] generateDegree(Long id);

}
