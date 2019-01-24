package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements IPatientRepository {

  List<Patient> patients;

  public PatientRepositoryImpl() {
    this.patients = new ArrayList<>();
  }

  @Override
  public void addPatient(Patient patient) {
    patients.add(patient);
  }

  @Override
  public List<Patient> getPatients() {
    return patients;
  }
}
