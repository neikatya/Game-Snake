
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SnakeTest {

    private Snake snake;
    private Head mockHead;
    private Tail mockTail;

    @BeforeEach
    public void setUp() {
        mockHead = mock(Head.class);
        mockTail = mock(Tail.class);

        snake = new Snake();
    }

    @Test
    public void testSnakeInitialization() {
        Snake snake = new Snake();
        assertNotNull(snake);
        assertEquals(1, snake.getLength());
    }

    @Test
    public void testMoveRight() {
        when(mockHead.getDirection()).thenReturn(Direction.RIGHT);

        Vector result = snake.move();
        assertNotNull(result);
        assertEquals(1, result.x());
        assertEquals(0, result.y());
    }

    @Test
    public void testMoveLeft() {
        when(mockHead.getDirection()).thenReturn(Direction.LEFT);

        Vector result = snake.move();
        assertNotNull(result);
        assertEquals(-1, result.x());
        assertEquals(0, result.y());
    }

    @Test
    public void testMoveUp() {
        when(mockHead.getDirection()).thenReturn(Direction.UP);

        Vector result = snake.move();
        assertNotNull(result);
        assertEquals(0, result.x());
        assertEquals(-1, result.y());
    }

    @Test
    public void testMoveDown() {
        when(mockHead.getDirection()).thenReturn(Direction.DOWN);

        Vector result = snake.move();
        assertNotNull(result);
        assertEquals(0, result.x());
        assertEquals(1, result.y());
    }

    @Test
    public void testGetLength() {
        when(mockTail.getLength()).thenReturn(3);

        int length = snake.getLength();
        assertEquals(4, length);
    }

    @Test
    public void testEating() {
        Apple apple = new Apple(2);

        snake.eating(apple);
        verify(mockTail).increase(2);
    }

    @Test
    public void testEatingWithDifferentSatiety() {
        Apple apple1 = new Apple(1);
        Apple apple2 = new Apple(5);

        snake.eating(apple1);
        snake.eating(apple2);

        verify(mockTail).increase(1);
        verify(mockTail).increase(5);
    }

    @Test
    public void testTurnToSameDirection() {
        when(mockHead.getDirection()).thenReturn(Direction.RIGHT);
        snake.turnTo(Direction.RIGHT);
        verify(mockHead, never()).turnRight();
        verify(mockHead, never()).turnLeft();
    }

    @Test
    public void testTurnToOppositeDirection() {
        when(mockHead.getDirection()).thenReturn(Direction.RIGHT);
        snake.turnTo(Direction.LEFT);
        verify(mockHead, times(2)).turnRight();
    }
}
