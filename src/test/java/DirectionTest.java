import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    public void testDirectionValues() {
        Direction[] directions = Direction.values();
        assertEquals(4, directions.length);
        assertArrayEquals(new Direction[]{Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT}, directions);
    }

    @Test
    public void testDirectionIndexes() {
        assertEquals(0, Direction.UP.getIndex());
        assertEquals(1, Direction.RIGHT.getIndex());
        assertEquals(2, Direction.DOWN.getIndex());
        assertEquals(3, Direction.LEFT.getIndex());
    }

    @Test
    public void testValueOf() {
        assertEquals(Direction.UP, Direction.valueOf("UP"));
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"));
        assertEquals(Direction.DOWN, Direction.valueOf("DOWN"));
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"));
    }

    @Test
    public void testOrdinal() {
        assertEquals(0, Direction.UP.ordinal());
        assertEquals(1, Direction.RIGHT.ordinal());
        assertEquals(2, Direction.DOWN.ordinal());
        assertEquals(3, Direction.LEFT.ordinal());
    }

    @Test
    public void testGetIndexMethod() {
        assertAll("All directions should have correct indexes",
                () -> assertEquals(0, Direction.UP.getIndex()),
                () -> assertEquals(1, Direction.RIGHT.getIndex()),
                () -> assertEquals(2, Direction.DOWN.getIndex()),
                () -> assertEquals(3, Direction.LEFT.getIndex())
        );
    }

    @Test
    public void testDirectionOrder() {
        Direction[] clockwise = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT};
        for (int i = 0; i < clockwise.length; i++) {
            assertEquals(i, clockwise[i].getIndex());
        }
    }

    @Test
    public void testUniqueIndexes() {
        assertEquals(4, java.util.Arrays.stream(Direction.values())
                .map(Direction::getIndex)
                .distinct()
                .count());
    }

    @Test
    public void testOppositeDirections() {
        assertEquals(Direction.UP.getIndex(), (Direction.DOWN.getIndex() + 2) % 4);
        assertEquals(Direction.RIGHT.getIndex(), (Direction.LEFT.getIndex() + 2) % 4);
    }
}