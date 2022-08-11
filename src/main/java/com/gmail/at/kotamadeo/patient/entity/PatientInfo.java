package com.gmail.at.kotamadeo.patient.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
@NoArgsConstructor
@ToString
@Setter
@Getter
public class PatientInfo {

    private String id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private HealthInfo healthInfo;

    public PatientInfo(String id,
                       String name,
                       String surname,
                       LocalDate birthday,
                       HealthInfo healthInfo
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.healthInfo = healthInfo;
    }

    public PatientInfo(String name, String surname, LocalDate birthday, HealthInfo healthInfo) {
        this(null, name, surname, birthday, healthInfo);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public HealthInfo getHealthInfo() {
        return healthInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientInfo that = (PatientInfo) o;
        return id.equals(that.id) &&
            name.equals(that.name) &&
            surname.equals(that.surname) &&
            birthday.equals(that.birthday) &&
            healthInfo.equals(that.healthInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday, healthInfo);
    }
}
