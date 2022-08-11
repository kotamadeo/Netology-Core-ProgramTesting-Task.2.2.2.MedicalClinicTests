package com.gmail.at.kotamadeo.patient.entity;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class BloodPressure {

    private int high;

    private int low;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BloodPressure that = (BloodPressure) o;
        return high == that.high && low == that.low;
    }

    @Override
    public int hashCode() {
        return Objects.hash(high, low);
    }
}
