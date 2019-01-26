package com.github.airatgaliev.clinic.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BMICalculationService {

  private final int inches;
  private final int pounds;

  public BMICalculationService(int inches, int pounds) {
    this.inches = inches;
    this.pounds = pounds;
  }

  public double calculateBMI() {
    double bmi = (double) pounds * 703 / (inches * inches);
    return BigDecimal.valueOf(bmi).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
