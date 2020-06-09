import java.util.List;
import java.util.function.Function;

public class PwValidator {

    /**
     * Validate password meets complexity requirements
     * Thread sleeps simulate each check taking 1s as per
     * TDD Kata 3 point 8
     *
     * Tests took 36s before optimisation, 20s after.
     */
    public boolean isValid(String pw) {
        int conditionsMet = 0;

        if(pw != null) {
            conditionsMet++;
        } else { // Can't continue if password is null
            throw new IllegalArgumentException("Null password");
        }
        sleep();

        if(pw.chars().anyMatch(Character::isLowerCase)) {
            conditionsMet++;
        } else { // Lower case character required, exit early
            throw new IllegalArgumentException("Password must contain a lower case character");
        }
        sleep();

        List<Function<String, Boolean>> checks = List.of(
                p -> p.length() > 8,
                p -> p.chars().anyMatch(Character::isUpperCase),
                p -> p.chars().anyMatch(Character::isDigit));
        for (var c : checks) {
            if (c.apply(pw)) {
                conditionsMet++;
            }
            sleep();
            if (conditionsMet == 3) {
                return true;
            }
        }
        throw new IllegalArgumentException("Three conditions must be met");
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
