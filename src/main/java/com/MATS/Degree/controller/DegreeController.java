package com.MATS.Degree.controller;

import com.MATS.Degree.dto.requestDto.DegreeRequestDto;
import com.MATS.Degree.dto.responseDto.DegreeResponseDto;
import com.MATS.Degree.entity.Degree;
import com.MATS.Degree.service.DegreeService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DegreeController {

    private final DegreeService degreeService;

    @PostMapping("/addDegree")
    public DegreeResponseDto addDegree(@ModelAttribute DegreeRequestDto degree) throws IOException {
        return degreeService.createDegree(degree);
    }

    @GetMapping("/getDegreeById/{id}")
    public DegreeResponseDto getDegree(@PathVariable Long id) {
        return degreeService.getDegree(id);
    }

    @GetMapping("/generateDegree/{id}")
    public HttpEntity<byte[]> generateDegree(@PathVariable Long id) throws JRException {
        byte[] degreeCertificate = degreeService.generateDegree(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("application","Degree.pdf");
        return new HttpEntity<>(degreeCertificate, headers);
    }
}
