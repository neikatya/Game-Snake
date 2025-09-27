import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
    private final int SIZE = 20;
    private final int scaler = 16;
    private Image dotImage;
    private Image appleImage;
    private Timer timer;
    private boolean inGame = true;
    Snake snake;
    Field field;
    String string;


    public Game(){
        setBackground(Color.black);
        loadImages();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
        snake = new Snake();
        field = new Field(snake, SIZE);
        timer = new Timer(500,this);
        timer.start();
    }


    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        appleImage = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dotImage = iid.getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (inGame){
            field.getAppleCoordinates().forEach(
                    appleCoordinates -> g.drawImage(appleImage, appleCoordinates.x()* scaler, appleCoordinates.y()* scaler, this)
            );
            field.getSnakeCoordinates().forEach(
                    snakeCoordinates -> g.drawImage(dotImage, snakeCoordinates.x()* scaler, snakeCoordinates.y()* scaler, this)
            );

        } else{
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str,125,SIZE*16/2);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            try {
                field.moveSnake();
            } catch (RuntimeException exception) {
                //string = exception.getMessage();
                endGame();
            }
        }
        repaint();
    }
    
    public void endGame() {
        inGame = false;
    }

    public boolean getStateOfGame(){
        return inGame;
    }


    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT){
                snake.turnTo(Direction.LEFT);
            }
            if(key == KeyEvent.VK_RIGHT){
                snake.turnTo(Direction.RIGHT);
            }

            if(key == KeyEvent.VK_UP){
                snake.turnTo(Direction.UP);
            }
            if(key == KeyEvent.VK_DOWN){
                snake.turnTo(Direction.DOWN);
            }
        }

    }

}
