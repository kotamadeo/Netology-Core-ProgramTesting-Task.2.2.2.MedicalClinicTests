package com.gmail.at.kotamadeo.patient;

import com.gmail.at.kotamadeo.patient.entity.BloodPressure;
import com.gmail.at.kotamadeo.patient.entity.HealthInfo;
import com.gmail.at.kotamadeo.patient.entity.PatientInfo;
import com.gmail.at.kotamadeo.patient.repository.PatientInfoFileRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class PatientInfoFileRepositoryTest {
    private static PatientInfoFileRepository patientInfoFileRepository;

    @BeforeAll
    public static void initSuite() {
        patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoFileRepository.getById(any())).thenReturn(new PatientInfo("1", "Ivan", "Ivanov",
                LocalDate.of(1980, 12, 21), new HealthInfo(new BigDecimal("36.6"),
                        new BloodPressure(140, 90))));
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("All tests complete!");
    }

    @Test
    @DisplayName("Test for method 'getById()' ")
    void getByIdTest(TestInfo getByIdTestInfo) {
        PatientInfo patientInfo = new PatientInfo("1", "Ivan", "Ivanov", LocalDate.of(1980, 12, 21),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(140, 90)));
        assertEquals(patientInfo, patientInfoFileRepository.getById("1"),
                getByIdTestInfo.getDisplayName() + " is NO complete!");
        System.out.println(getByIdTestInfo.getDisplayName() + " complete!");
    }
}
