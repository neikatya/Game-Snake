import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TailTest {

    private Tail tail;

    @BeforeEach
    public void setUp() {
        tail = new Tail(3);
    }

    @Test
    public void testTailInitialization() {
        assertEquals(3, tail.getLength());
    }

    @Test
    public void testIncreaseTail() {
        tail.increase(2);
        assertEquals(5, tail.getLength());
    }

    @Test
    public void testIncreaseTailByZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> tail.increase(0)
        );

        assertEquals("Значение increase должно быть положительным: 0", exception.getMessage());
        assertEquals(3, tail.getLength());
    }

    @Test
    public void testIncreaseTailByNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> tail.increase(-2)
        );

        assertEquals("Значение increase должно быть положительным: -2", exception.getMessage());
        assertEquals(3, tail.getLength());
    }

    @Test
    public void testIncreaseTailMultipleTimes() {
        tail.increase(1);
        assertEquals(4, tail.getLength());

        tail.increase(3);
        assertEquals(7, tail.getLength());

    }

    @Test
    public void testTailWithZeroInitialLength() {
        Tail zeroTail = new Tail(0);
        assertEquals(0, zeroTail.getLength());

        zeroTail.increase(5);
        assertEquals(5, zeroTail.getLength());
    }
}
