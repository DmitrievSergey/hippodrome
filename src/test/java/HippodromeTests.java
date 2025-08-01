import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HippodromeTests {

    Horse horse = Mockito.mock(Horse.class);

    @Test
    void constructorParameterCannotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorParameterCannotBeEmptyList() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesReturnCorrectList() {
        List<Horse> horses = getTestHorses(30);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveCallForAllHorses() {

        List<Horse> horses = getMockedHorses(50);
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horse, Mockito.times(horses.size())).move();
    }

    @Test
    void getWinnerReturnHorseWithMaxDistance() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 1.0),
                new Horse("Ace of Spades", 2.5, 2.5),
                new Horse("Zephyr", 2.6, 4.0),
                new Horse("Blaze", 2.7, 4.0),
                new Horse("Lobster", 2.8, 3.0),
                new Horse("Pegasus", 2.9, 2.9),
                new Horse("Cherry", 3, 1.9)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.stream().max(Comparator.comparingDouble(Horse::getDistance)).get(), hippodrome.getWinner());
    }

    private List<Horse> getMockedHorses(int count) {

        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            //Horse horse = new Horse(getHorseName(), getHorseSpeed());
            horses.add(horse);
        }
        return horses;
    }

    private List<Horse> getTestHorses(int count) {
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            Horse horse = new Horse(getHorseName(), getHorseSpeed());
            horses.add(horse);
        }
        return horses;
    }

    private String getHorseName() {
        return RandomStringUtils.random(7);
    }

    private double getHorseSpeed() {
        double max = 3.0;
        double min = 0.1;
        return (Math.random() * (max - min + 1)) + min;
    }
}
