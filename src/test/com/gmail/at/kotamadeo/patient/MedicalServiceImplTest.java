package com.gmail.at.kotamadeo.patient;

import com.gmail.at.kotamadeo.patient.entity.BloodPressure;
import com.gmail.at.kotamadeo.patient.entity.HealthInfo;
import com.gmail.at.kotamadeo.patient.entity.PatientInfo;
import com.gmail.at.kotamadeo.patient.repository.PatientInfoFileRepository;
import com.gmail.at.kotamadeo.patient.repository.PatientInfoRepository;
import com.gmail.at.kotamadeo.patient.service.alert.SendAlertService;
import com.gmail.at.kotamadeo.patient.service.medical.MedicalService;
import com.gmail.at.kotamadeo.patient.service.medical.MedicalServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

class MedicalServiceImplTest {
    @Test
    void testCheckBloodPressureNotNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BloodPressure currentPressure = new BloodPressure(60, 120);
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure(info.getId(), currentPressure);
        Mockito.verify(alertService, Mockito.times(1)).send(message);
    }

    @Test
    void testCheckBloodPressureNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BloodPressure currentPressure = new BloodPressure(120, 80);
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure(info.getId(), currentPressure);
        Mockito.verify(alertService, Mockito.times(0)).send(message);
    }

    @Test
    void testCheckTemperatureNotNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BigDecimal currentTemperature = new BigDecimal("34.9");
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature(info.getId(), currentTemperature);
        Mockito.verify(alertService, Mockito.times(1)).send(message);
    }

    @Test
    void testCheckTemperatureNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BigDecimal currentTemperature = new BigDecimal("36.6");
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature(info.getId(), currentTemperature);
        Mockito.verify(alertService, Mockito.times(0)).send(message);
    }
}