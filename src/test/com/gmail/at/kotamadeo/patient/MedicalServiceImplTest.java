package com.gmail.at.kotamadeo.patient;

import com.gmail.at.kotamadeo.patient.entity.BloodPressure;
import com.gmail.at.kotamadeo.patient.entity.HealthInfo;
import com.gmail.at.kotamadeo.patient.entity.PatientInfo;
import com.gmail.at.kotamadeo.patient.repository.PatientInfoFileRepository;
import com.gmail.at.kotamadeo.patient.repository.PatientInfoRepository;
import com.gmail.at.kotamadeo.patient.service.alert.SendAlertService;
import com.gmail.at.kotamadeo.patient.service.medical.MedicalService;
import com.gmail.at.kotamadeo.patient.service.medical.MedicalServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalServiceImplTest {
    private static PatientInfo patientInfo;
    private static PatientInfo info;

    @BeforeAll
    static void initEntities() {
        patientInfo = new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));
        info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
    }

    @Test
    void testCheckBloodPressureNotNormal() {
        BloodPressure currentPressure = new BloodPressure(60, 120);
        String message = String.format("Warning, patient with id: %s, need help", info.getId());
        PatientInfoRepository patientInfoRepository = mock(PatientInfoFileRepository.class);
        when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = mock(SendAlertService.class);
        doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure(info.getId(), currentPressure);
        verify(alertService, only()).send(message);
        assertNotEquals(patientInfo.getHealthInfo().getBloodPressure(), currentPressure);
    }

    @Test
    void testCheckBloodPressureNormal() {
        BloodPressure currentPressure = new BloodPressure(120, 80);
        String message = String.format("Warning, patient with id: %s, need help", info.getId());
        PatientInfoRepository patientInfoRepository = mock(PatientInfoFileRepository.class);
        when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = mock(SendAlertService.class);
        doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure(info.getId(), currentPressure);
        verify(alertService, never()).send(message);
        assertEquals(patientInfo.getHealthInfo().getBloodPressure(), currentPressure);
    }

    @Test
    void testCheckTemperatureNotNormal() {
        BigDecimal currentTemperature = new BigDecimal("34.9");
        String message = String.format("Warning, patient with id: %s, need help", info.getId());
        PatientInfoRepository patientInfoRepository = mock(PatientInfoFileRepository.class);
        when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = mock(SendAlertService.class);
        doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature(info.getId(), currentTemperature);
        verify(alertService, only()).send(message);
        assertTrue(patientInfo.getHealthInfo()
                .getNormalTemperature()
                .subtract(new BigDecimal("1.5"))
                .compareTo(currentTemperature) > 0);
    }

    @Test
    void testCheckTemperatureNormal() {
        BigDecimal currentTemperature = new BigDecimal("36.6");
        String message = String.format("Warning, patient with id: %s, need help", info.getId());
        PatientInfoRepository patientInfoRepository = mock(PatientInfoFileRepository.class);
        when(patientInfoRepository.getById(info.getId())).thenReturn(info);
        SendAlertService alertService = mock(SendAlertService.class);
        doNothing().when(alertService).send(message);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature(info.getId(), currentTemperature);
        verify(alertService, never()).send(message);
        assertFalse(patientInfo.getHealthInfo()
                .getNormalTemperature()
                .subtract(new BigDecimal("1.5"))
                .compareTo(currentTemperature) > 0);
    }
}