package dmit2015.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BMITest {

    @ParameterizedTest(name = "weight = {0}, height = {1} expected weight = {2} ")
    @CsvSource({
            "100,66,16.1",
            "140,66,22.6",
            "175,66,28.2",
            "200,66,32.3",
            //"132,67,normal"
    })
    void bmiCategory_ValidValue_ReturnsBMIValue(int weight, int height, double expectedWeight) {
        // Arrange
        // BMI bmi1 = new BMI(weight, height);
        BMI bmi1 = new BMI();
        // Act
        bmi1.setWeight(weight);
        bmi1.setHeight(height);
        // Assert
        assertEquals(expectedWeight, bmi1.bmi(), 0.50);
    }

    @ParameterizedTest(name = "weight = {0}, height = {1} expected weight = {2} ")
    @CsvSource({
            "100,66,underweight",
            "140,66,normal",
            "175,66,overweight",
            "200,66,obese",
            "132,67,normal"
    })
    void bmiCategory_ValidValue_ReturnsBMICategory(int weight, int height, String expectedWeight) {
        // Arrange
        // BMI bmi1 = new BMI(weight, height);
        BMI bmi1 = new BMI();
        // Act
        bmi1.setWeight(weight);
        bmi1.setHeight(height);
        // Assert
        assertEquals(expectedWeight, bmi1.bmiCategory());
    }
}