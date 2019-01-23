package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl implements IDoctorRepository {

  List<Doctor> doctors;

  public DoctorRepositoryImpl() {
    this.doctors = new ArrayList<>();
  }

  @Override
  public void addDoctor(Doctor doctor) {
    doctors.add(doctor);
  }

  @Override
  public Doctor getDoctorById(int id) {
    return doctors.get(id);
  }

  @Override
  public List<Doctor> getDoctors() {
    return doctors;
  }
}
