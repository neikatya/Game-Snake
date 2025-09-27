import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeadTest {

    @Test
    public void testHeadInitialization() {
        Head head = new Head(Direction.RIGHT);
        assertEquals(Direction.RIGHT, head.getDirection());
    }

    @Test
    public void testTurnRightFromRight() {
        Head head = new Head(Direction.RIGHT);
        head.turnRight();
        assertEquals(Direction.DOWN, head.getDirection());
    }

    @Test
    public void testTurnRightFromDown() {
        Head head = new Head(Direction.DOWN);
        head.turnRight();
        assertEquals(Direction.LEFT, head.getDirection());
    }

    @Test
    public void testTurnRightFromLeft() {
        Head head = new Head(Direction.LEFT);
        head.turnRight();
        assertEquals(Direction.UP, head.getDirection());
    }

    @Test
    public void testTurnRightFromUp() {
        Head head = new Head(Direction.UP);
        head.turnRight();
        assertEquals(Direction.RIGHT, head.getDirection());
    }

    @Test
    public void testTurnLeftFromRight() {
        Head head = new Head(Direction.RIGHT);
        head.turnLeft();
        assertEquals(Direction.UP, head.getDirection());
    }

    @Test
    public void testTurnLeftFromUp() {
        Head head = new Head(Direction.UP);
        head.turnLeft();
        assertEquals(Direction.LEFT, head.getDirection());
    }

    @Test
    public void testTurnLeftFromLeft() {
        Head head = new Head(Direction.LEFT);
        head.turnLeft();
        assertEquals(Direction.DOWN, head.getDirection());
    }

    @Test
    public void testTurnLeftFromDown() {
        Head head = new Head(Direction.DOWN);
        head.turnLeft();
        assertEquals(Direction.RIGHT, head.getDirection());
    }

}