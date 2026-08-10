package com.MATS.Degree.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DegreeResponseDto {

    private Long id;

    private String degreeNumber;

    private String enrollNumber;

    private String universityName;

    private String centerName;

    private String naacLogo;

    private String universityLogo;

    private String courseName;

    private String name;

    private String year;

    private String division;

    private String shikshaNaam;

    private String naam;

    private String bhaag;

}
