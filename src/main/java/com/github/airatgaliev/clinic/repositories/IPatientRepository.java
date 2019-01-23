package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Patient;
import java.util.List;

public interface IPatientRepository {

  void addPatient(Patient patient);

  Patient getPatientById(int id);

  List<Patient> getPatients();
}
