package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Doctor;
import java.util.List;

public interface IDoctorRepository {

  public void addDoctor();

  public Doctor getDoctorById();

  public List<Doctor> getDoctors();
}
