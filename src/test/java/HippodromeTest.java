import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    void constructor_NullParamPassed_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructor_NullParamPassed_ExceptionContainsHorsesCannotBeNull() {
        List<Horse> horses = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructor_EmptyListPassed_ExceptionContainsHorsesCannotBeEmpty() {
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ReturnsAllHorsesInOrder() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome);
        assertEquals(30, horses.size());
        assertEquals("Horse0", hippodrome.getHorses().get(0).getName());
        assertEquals("Horse10", hippodrome.getHorses().get(10).getName());
        assertEquals("Horse29", hippodrome.getHorses().get(29).getName());
    }

    @Test
    void move_CallsMoveMethodForEachHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse hors : horses) {
            verify(hors, times(1)).move();
        }
    }

    @Test
    void getWinner_ReturnsHorseWithBiggestDistance() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Horse10", 1, 10),
                new Horse("Horse20", 2, 20),
                new Horse("Horse30", 3, 30)
        ));

        assertEquals("Horse30", hippodrome.getWinner().getName());
    }
}