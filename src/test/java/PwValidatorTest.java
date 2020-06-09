import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PwValidatorTest {
    private PwValidator pwValidator = new PwValidator();

    @Test
    public void validPwThreeConditionsMet() {
        assertThat(pwValidator.isValid("a3456"), is(true));
        assertThat(pwValidator.isValid("a345678919"), is(true));
        assertThat(pwValidator.isValid("aAAAAAAAA"), is(true));
    }

    @Test
    public void pwMoreThan8Long() {
        assertThrows(IllegalArgumentException.class,
                () -> pwValidator.isValid("AAAAAAAA"));
        assertThrows(IllegalArgumentException.class,
                () -> pwValidator.isValid("A"));
    }

    @Test
    public void pwNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> pwValidator.isValid(null));
    }

    @Test
    public void pwContainsUpperCase() {
        assertThrows(IllegalArgumentException.class,
                () -> pwValidator.isValid("asdfasdf"));
    }

    @Test
    public void pwContainsNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> pwValidator.isValid("FDSAFDSA"));
    }

    @Test
    public void pwLowerCaseRequired() {
        assertThrows(IllegalArgumentException.class,
                () -> pwValidator.isValid("123456789A"));
    }
}
