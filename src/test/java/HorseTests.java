import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTests {


    @Test
    void firstConstructorParameterCannotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5, 5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", " " })
    void firstConstructorParameterCannotBeBlank(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5, 5));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void secondConstructorParameterCannotBeNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -5, 5));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void thirdConstructorParameterCannotBeBlank() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 5, -5));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameShouldReturnName() {
        String horseName = "horseName";
        Horse horse = new Horse(horseName, 5, 5);
        assertEquals(horseName, horse.getName());
    }

    @Test
    void getSpeedShouldReturnSpeed() {
        double horseSpeed = 10;
        Horse horse = new Horse("horseName", horseSpeed, 5);
        assertEquals(horseSpeed, horse.getSpeed());
    }

    @Test
    void getDistanceShouldReturnDistance() {
        double horseDistance = 10;
        Horse horse = new Horse("horseName", 5, horseDistance);
        assertEquals(horseDistance, horse.getDistance());
    }

    @Test
    void getDistanceShouldReturnZeroIfNotSet() {
        double horseDistance = 10;
        Horse horse = new Horse("horseName", 5);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveCallGetRandomDoubleWithCorrectParams() {
        try (MockedStatic<Horse> utilities =  Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("horseName", 5);
            horse.move();
            utilities.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = { 2.0, 5.0, 1.1 })
    void moveSetDistanceCorrectValue(double value) {
        try (MockedStatic<Horse> utilities =  Mockito.mockStatic(Horse.class)) {
            utilities.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(value);
            Horse horse = new Horse("horseName", 5);
            double initialDistance = horse.getDistance();
            horse.move();
            assertEquals(horse.getDistance(), initialDistance + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
