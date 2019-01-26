package com.github.airatgaliev.clinic.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BMI calculation service should")
class BMICalculationServiceTest {

  @Test
  @DisplayName("calculation BMI to two places correctly via inches and pounds")
  public void calculateBMICorrectly() {
    assertAll(
        () -> assertEquals(22.09, new BMICalculationService(70, 154).calculateBMI()),
        () -> assertEquals(22.5, new BMICalculationService(75, 180).calculateBMI())
    );
  }
}