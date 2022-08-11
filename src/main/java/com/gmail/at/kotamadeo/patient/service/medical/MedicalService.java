package com.gmail.at.kotamadeo.patient.service.medical;

import java.math.BigDecimal;

import com.gmail.at.kotamadeo.patient.entity.BloodPressure;

public interface MedicalService {

    void checkBloodPressure(String patientId, BloodPressure bloodPressure);

    void checkTemperature(String patientId, BigDecimal temperature);
}
