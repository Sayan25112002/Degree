package com.MATS.Degree.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degreeNumber;

    private String enrollNumber;

    private String universityName;

    private String centerName;

    private String naacLogo;

    private String universityLogo;

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