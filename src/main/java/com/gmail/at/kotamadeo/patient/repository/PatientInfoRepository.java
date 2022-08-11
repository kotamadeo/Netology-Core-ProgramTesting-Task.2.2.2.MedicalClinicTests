package com.gmail.at.kotamadeo.patient.repository;

import com.gmail.at.kotamadeo.patient.entity.PatientInfo;

public interface PatientInfoRepository {

    PatientInfo getById(String id);

    String add(PatientInfo patientInfo);

    PatientInfo remove(String id);

    PatientInfo update(PatientInfo patientInfo);
}
