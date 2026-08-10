package com.MATS.Degree.service.implementation;

import com.MATS.Degree.dto.requestDto.DegreeRequestDto;
import com.MATS.Degree.dto.responseDto.DegreeResponseDto;
import com.MATS.Degree.entity.Degree;
import com.MATS.Degree.mapper.DegreeMapper;
import com.MATS.Degree.repository.DegreeRepository;
import com.MATS.Degree.service.DegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    }

    @Override
    public DegreeResponseDto getDegree(Long id) {
        return null;
    }

    @Override
    public byte[] generateDegree(Long id) {
        return new byte[0];
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
