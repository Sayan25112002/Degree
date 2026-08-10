package com.MATS.Degree.service.implementation;

import com.MATS.Degree.dto.requestDto.DegreeRequestDto;
import com.MATS.Degree.dto.responseDto.DegreeResponseDto;
import com.MATS.Degree.entity.Degree;
import com.MATS.Degree.mapper.DegreeMapper;
import com.MATS.Degree.repository.DegreeRepository;
import com.MATS.Degree.service.DegreeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {

    private final DegreeRepository degreeRepository;
    private final DegreeMapper degreeMapper;

    @Override
    public DegreeResponseDto createDegree(DegreeRequestDto degreeRequestDto) throws IOException {
        Degree degree = degreeMapper.toDegree(degreeRequestDto);
        degree.setNaacLogo(saveFile(degreeRequestDto.getNaacLogoFile()));
        degree.setUniversityLogo(saveFile(degreeRequestDto.getUniversityLogoFile()));
        Degree savedDegree = degreeRepository.save(degree);
        return degreeMapper.toDegreeResponseDto(savedDegree);
    }

    @Override
    public DegreeResponseDto getDegree(Long id) {
        Degree degree = degreeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Degree not found with id " + id));
        return degreeMapper.toDegreeResponseDto(degree);
    }

    @Override
    public byte[] generateDegree(Long id) throws JRException {
        String resourceDir = System.getProperty("user.dir")+"\\src\\resources\\report\\";
        Path degreePath = Paths.get(resourceDir, "Degree.jrxml");
        JasperReport degreeReport = JasperCompileManager.compileReport(degreePath.toString());
        Degree degree = degreeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Degree not found with id " + id));
        JRBeanCollectionDataSource degreeDataSource = new JRBeanCollectionDataSource(Collections.singletonList(degree));
        Map<String, Object> data = new HashMap<>();
        for(Field field : degree.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                data.put(field.getName(), field.get(degree));
            } catch (IllegalAccessException e) {
                throw new JRRuntimeException(e);
            }
        }
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("data", data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(degreeReport, parameter, degreeDataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String uploadDir = System.getProperty("user.dir")+"\\src\\main\\resources\\webapp\\images\\";
        Files.createDirectories(Paths.get(uploadDir));
        String fileName = System.currentTimeMillis()+"-"+file.getOriginalFilename();
        Path path = Paths.get(uploadDir,fileName);
        Files.write(path, file.getBytes());
        return path.toString();
    }

}
