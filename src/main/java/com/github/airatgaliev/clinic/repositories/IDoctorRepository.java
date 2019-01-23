package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Doctor;
import java.util.List;

public interface IDoctorRepository {

  void addDoctor(Doctor doctor);

  Doctor getDoctorById(int id);

  List<Doctor> getDoctors();
}
