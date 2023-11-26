import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Test
    void constructor_NullNameParamPassed_ThrowIllegalArgumentException() {
        String name = null;
        double speed = 10.0;
        double distance = 0.0;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void constructor_NullNamePassed_ExceptionContainsNameCannotBeNull() {
        String name = null;
        double speed = 10.0;
        double distance = 0.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\t \n"})
    void constructor_BlankNamePassed_ThrowIllegalArgumentException(String s) {
        double speed = 10.0;
        double distance = 0.0;

        assertThrows(IllegalArgumentException.class, () -> new Horse(s, speed, distance));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\t \n"})
    void constructor_BlankNamePassed_ExceptionContainsNameCannotBeBlank(String s) {
        double speed = 10.0;
        double distance = 0.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(s, speed, distance));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructor_NegativeSpeedPassed_ThrowIllegalArgumentException() {
        String name = "Patrick";
        double speed = -10.0;
        double distance = 0.0;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void constructor_NegativeSpeedPassed_ExceptionContainsSpeedCannotBeNegative() {
        String name = "Patrick";
        double speed = -10.0;
        double distance = 0.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructor_NegativeDistancePassed_ThrowIllegalArgumentException() {
        String name = "Patrick";
        double speed = 10.0;
        double distance = -15.0;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void constructor_NegativeDistancePassed_ExceptionContainsDistanceCannotBeNegative() {
        String name = "Patrick";
        double speed = 10.0;
        double distance = -15.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameShouldReturnName() {
        Horse horse = mock(Horse.class);
        when(horse.getName()).thenReturn("Bob");

        assertEquals("Bob", horse.getName());
    }

    @Test
    void getSpeedShouldReturnSpeed() {
        Horse horse = mock(Horse.class);
        when(horse.getSpeed()).thenReturn(10.0);

        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    void getDistanceShouldReturnDistance() {
        Horse horse = mock(Horse.class);
        when(horse.getDistance()).thenReturn(10.0);

        assertEquals(10.0, horse.getDistance());
    }

    @Test
    void getDistance_ZeroDistancePassed_ReturnDistanceInConstructorWithTwoParams() {
        String name = "Patrick";
        double speed = 10.0;

        assertEquals(0.0, new Horse(name, speed).getDistance());
    }

    @Test
    void move_CallsGetRandomDoubleMethodWithCorrectParams() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            String name = "Patrick";
            double speed = 10.0;
            double distance = 5.0;

            Horse horse = new Horse(name, speed, distance);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void moveMethodWithMockedRandomDouble(double fakeRandomValue) {
        double min = 0.2;
        double max = 0.9;
        String name = "Patrick";
        double speed = 10.0;
        double distance = 5.0;
        Horse horse = new Horse(name, speed, distance);
        double expectedDistance = distance + speed * fakeRandomValue;

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);
            horse.move();
        }
        assertEquals(expectedDistance, horse.getDistance());
    }

}