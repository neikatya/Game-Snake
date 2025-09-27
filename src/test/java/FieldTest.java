import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Nested
@ExtendWith(MockitoExtension.class)
class FieldTest {

    @Mock private Snake mockSnake;

    private Field field;
    private final int FIELD_SIZE = 20;

    @BeforeEach
    public void setUp() {
        when(mockSnake.getLength()).thenReturn(3);

        field = new Field(mockSnake, FIELD_SIZE);
    }

    @Test
    public void testFieldInitialization() {
        assertNotNull(field);
        assertEquals(FIELD_SIZE, field.size);

        List<Coordinates> snakeCoords = field.getSnakeCoordinates();
        assertEquals(3, snakeCoords.size());

        Coordinates center = new Coordinates(FIELD_SIZE / 2, FIELD_SIZE / 2);
        assertTrue(snakeCoords.contains(center));

        List<Coordinates> appleCoords = field.getAppleCoordinates();
        assertFalse(appleCoords.isEmpty());
    }

    @Test
    public void testMoveSnakeWithoutApple() {
        when(mockSnake.move()).thenReturn(new Vector(1, 0)); // Движение вправо

        assertDoesNotThrow(() -> field.moveSnake());

        List<Coordinates> snakeCoords = field.getSnakeCoordinates();
        Coordinates head = snakeCoords.get(0);
        assertEquals(new Coordinates(FIELD_SIZE / 2 + 1, FIELD_SIZE / 2), head);
    }

    @Test
    public void testMoveSnakeWithApple() {
        when(mockSnake.move()).thenReturn(new Vector(1, 0));

        Coordinates appleCoord = new Coordinates(FIELD_SIZE / 2 + 1, FIELD_SIZE / 2);
        field.addApple(appleCoord, new Apple(2));

        assertDoesNotThrow(() -> field.moveSnake());

        verify(mockSnake).eating(any(Apple.class));

        List<Coordinates> appleCoords = field.getAppleCoordinates();
        assertFalse(appleCoords.contains(appleCoord));
    }

    @Test
    public void testCheckBorderValid() {
        Coordinates valid1 = new Coordinates(0, 0);
        Coordinates valid2 = new Coordinates(FIELD_SIZE, FIELD_SIZE);
        Coordinates valid3 = new Coordinates(5, 10);

        assertDoesNotThrow(() -> field.checkBorder(valid1));
        assertDoesNotThrow(() -> field.checkBorder(valid2));
        assertDoesNotThrow(() -> field.checkBorder(valid3));
    }

    @Test
    public void testCheckBorderInvalid() {
        Coordinates invalid1 = new Coordinates(-1, 5);
        Coordinates invalid2 = new Coordinates(5, -1);
        Coordinates invalid3 = new Coordinates(FIELD_SIZE + 1, 5);
        Coordinates invalid4 = new Coordinates(5, FIELD_SIZE + 1);

        assertThrows(RuntimeException.class, () -> field.checkBorder(invalid1));
        assertThrows(RuntimeException.class, () -> field.checkBorder(invalid2));
        assertThrows(RuntimeException.class, () -> field.checkBorder(invalid3));
        assertThrows(RuntimeException.class, () -> field.checkBorder(invalid4));
    }

    @Test
    public void testCheckTailValid() {
        Coordinates freeCoord = new Coordinates(0, 0);
        assertDoesNotThrow(() -> field.checkTail(freeCoord));
    }

    @Test
    public void testCheckTailInvalid() {
        Coordinates tailCoord = new Coordinates(FIELD_SIZE / 2, FIELD_SIZE / 2);
        assertThrows(RuntimeException.class, () -> field.checkTail(tailCoord));
    }

    @Test
    public void testAddApple() {
        Coordinates coord = new Coordinates(3, 7);
        Apple apple = new Apple(2);

        field.addApple(coord, apple);

        List<Coordinates> appleCoords = field.getAppleCoordinates();
        assertTrue(appleCoords.contains(coord));
    }

    @Test
    public void testGenerateAppleCoordinates() {
        Coordinates coords = field.generateAppleCoordinates();

        assertTrue(coords.x() >= 0 && coords.x() <= FIELD_SIZE);
        assertTrue(coords.y() >= 0 && coords.y() <= FIELD_SIZE);

        List<Coordinates> snakeCoords = field.getSnakeCoordinates();
        assertFalse(snakeCoords.contains(coords));
    }

    @Test
    public void testGenerateApple() {
        Apple apple = field.generateApple();
        assertNotNull(apple);
        assertTrue(apple.satiety() >= 0 && apple.satiety() <= 2);
    }

    @Test
    public void testGetAppleCoordinates() {
        List<Coordinates> appleCoords = field.getAppleCoordinates();
        assertNotNull(appleCoords);
        assertFalse(appleCoords.isEmpty());

        for (Coordinates coord : appleCoords) {
            assertTrue(coord.x() >= 0 && coord.x() <= FIELD_SIZE);
            assertTrue(coord.y() >= 0 && coord.y() <= FIELD_SIZE);
        }
    }

    @Test
    public void testGetSnakeCoordinates() {
        List<Coordinates> snakeCoords = field.getSnakeCoordinates();
        assertNotNull(snakeCoords);
        assertEquals(3, snakeCoords.size());

        for (Coordinates coord : snakeCoords) {
            assertTrue(coord.x() >= 0 && coord.x() <= FIELD_SIZE);
            assertTrue(coord.y() >= 0 && coord.y() <= FIELD_SIZE);
        }
    }

    @Test
    public void testSnakeEatsAppleAndGrows() {
        when(mockSnake.move()).thenReturn(new Vector(1, 0));

        Coordinates appleCoord = new Coordinates(FIELD_SIZE / 2 + 1, FIELD_SIZE / 2);
        field.addApple(appleCoord, new Apple(2));

        int initialLength = field.getSnakeCoordinates().size();

        field.moveSnake();

        int newLength = field.getSnakeCoordinates().size();
        assertEquals(initialLength, newLength);

        when(mockSnake.getLength()).thenReturn(5);
        when(mockSnake.move()).thenReturn(new Vector(1, 0));

        field.moveSnake();
        newLength = field.getSnakeCoordinates().size();
        assertTrue(newLength > initialLength);
    }

    @Test
    public void testBorderCollisionThrowsException() {
        Field smallField = new Field(mockSnake, 5);
        when(mockSnake.move()).thenReturn(new Vector(-1, 0));

        assertThrows(RuntimeException.class, smallField::moveSnake);
    }

    @Test
    public void testTailCollisionThrowsException() {
        when(mockSnake.move()).thenReturn(new Vector(0, 0));

        assertThrows(RuntimeException.class, field::moveSnake);
    }
}

