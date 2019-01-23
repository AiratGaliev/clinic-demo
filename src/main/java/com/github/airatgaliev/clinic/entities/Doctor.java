package com.github.airatgaliev.clinic.entities;

public class Doctor {

  private int id;
  private String firstName;
  private String lastName;
  private String specialization;

  public Doctor(int id, String firstName, String lastName, String specialization) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.specialization = specialization;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }
}
