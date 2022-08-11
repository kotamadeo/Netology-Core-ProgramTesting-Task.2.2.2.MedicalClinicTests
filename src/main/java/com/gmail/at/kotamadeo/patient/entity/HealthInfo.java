package com.gmail.at.kotamadeo.patient.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class HealthInfo {

    private BigDecimal normalTemperature;

    private BloodPressure bloodPressure;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthInfo that = (HealthInfo) o;
        return normalTemperature.equals(that.normalTemperature) &&
            bloodPressure.equals(that.bloodPressure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalTemperature, bloodPressure);
    }
}
