import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private final List<Horse> horses;
    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            logger.info("Horses list is null.");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            logger.info("Horses list is empty.");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        logger.info("Creation of Hippodrome, horses [{}]", horses.size());
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
