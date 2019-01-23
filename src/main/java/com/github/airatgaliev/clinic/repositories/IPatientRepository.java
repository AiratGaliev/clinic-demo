package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Patient;
import java.util.List;

public interface IPatientRepository {

  public void addPatient();

  public Patient getPatientById();

  public List<Patient> getPatients();
}
