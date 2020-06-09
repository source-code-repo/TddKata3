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
    public boolean isValid(String pw) throws InterruptedException {
        int conditionsMet = 0;

        if(pw != null) {
            conditionsMet++;
        } else { // Can't continue if password is null
            throw new IllegalArgumentException("Null password");
        }
        Thread.sleep(1000);

        if(pw.chars().anyMatch(Character::isLowerCase)) {
            conditionsMet++;
        } else { // Lower case character required, exit early
            throw new IllegalArgumentException("Password must contain a lower case character");
        }
        Thread.sleep(1000);

        List<Function<String, Boolean>> checks = List.of(
                p -> p.length() > 8,
                p -> p.chars().anyMatch(Character::isUpperCase),
                p -> p.chars().anyMatch(Character::isDigit)
                );
        for (Function<String, Boolean> check : checks) {
            if (check.apply(pw)) {
                conditionsMet++;
            }
            Thread.sleep(1000);
            if(conditionsMet == 3) {
                return true;
            }
        }

        throw new IllegalArgumentException("Three conditions must be met");
    }
}
