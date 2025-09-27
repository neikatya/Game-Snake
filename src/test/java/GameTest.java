
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock private Snake mockSnake;
    @Mock private Field mockField;
    @Mock private Graphics mockGraphics;

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.snake = mockSnake;
        game.field = mockField;
    }

    @Test
    public void testActionPerformedInGame() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        game.actionPerformed(mockEvent);
        verify(mockField).moveSnake();
    }

    @Test
    public void testActionPerformedGameOver() {
        game.endGame();
        ActionEvent mockEvent = mock(ActionEvent.class);
        game.actionPerformed(mockEvent);

        verify(mockField, never()).moveSnake();
    }

    @Test
    public void testActionPerformedWithException() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        doThrow(new RuntimeException("Game Over")).when(mockField).moveSnake();
        game.actionPerformed(mockEvent);

        assertFalse(game.getStateOfGame());
    }

    @Test
    public void testKeyListenerLeft() {
        Game.FieldKeyListener listener = game.new FieldKeyListener();
        KeyEvent mockEvent = mock(KeyEvent.class);
        when(mockEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);

        listener.keyPressed(mockEvent);

        verify(mockSnake).turnTo(Direction.LEFT);
    }

    @Test
    public void testKeyListenerRight() {
        Game.FieldKeyListener listener = game.new FieldKeyListener();
        KeyEvent mockEvent = mock(KeyEvent.class);
        when(mockEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);

        listener.keyPressed(mockEvent);

        verify(mockSnake).turnTo(Direction.RIGHT);
    }

    @Test
    public void testKeyListenerUp() {
        Game.FieldKeyListener listener = game.new FieldKeyListener();
        KeyEvent mockEvent = mock(KeyEvent.class);
        when(mockEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);

        listener.keyPressed(mockEvent);

        verify(mockSnake).turnTo(Direction.UP);
    }

    @Test
    public void testKeyListenerDown() {
        Game.FieldKeyListener listener = game.new FieldKeyListener();
        KeyEvent mockEvent = mock(KeyEvent.class);
        when(mockEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);

        listener.keyPressed(mockEvent);

        verify(mockSnake).turnTo(Direction.DOWN);
    }

    @Test
    public void testKeyListenerOtherKey() {
        Game.FieldKeyListener listener = game.new FieldKeyListener();
        KeyEvent mockEvent = mock(KeyEvent.class);
        when(mockEvent.getKeyCode()).thenReturn(KeyEvent.VK_SPACE);

        listener.keyPressed(mockEvent);

        verify(mockSnake, never()).turnTo(any());
    }

    @Test
    public void testPaintComponentInGame() {
        game.paintComponent(mockGraphics);

        verify(mockGraphics, atLeastOnce()).drawImage(any(), anyInt(), anyInt(), any());
    }

    @Test
    public void testPaintComponentGameOver() {
        game.endGame();
        game.paintComponent(mockGraphics);

        verify(mockGraphics).drawString(anyString(), anyInt(), anyInt());
    }
}
