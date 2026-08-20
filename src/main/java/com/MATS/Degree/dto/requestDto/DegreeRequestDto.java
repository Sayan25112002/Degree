package com.MATS.Degree.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DegreeRequestDto {

    private String degreeNumber;

    private String enrollNumber;

    private String universityName;

    private String centerName;

    private String naacLogo;

    private MultipartFile naacLogoFile;

    private String universityLogo;

    private MultipartFile universityLogoFile;

    private String barCode;

    private String qrCode;

    private String courseName;

    private String name;

    private String year;

    private String division;

    private String shikshaNaam;

    private String naam;

    private String bhaag;

}
