package com.github.airatgaliev.clinic.entities;

public enum Doctor {
  martinez("John Martinez"), goodman("Ralf Goodman"), white("Walter White");
  private String name;

  Doctor(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
