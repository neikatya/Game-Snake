import java.util.*;

public class Field {
    private final Snake snake;
    private final Deque<Coordinates> snakeCoordinates;
    private final HashMap<Coordinates, Apple> applesOnField;
    public int size = 20;

    public Field(Snake snake, int size){
        this.applesOnField = new HashMap<>();
        this.snake = snake;
        this.snakeCoordinates = new LinkedList<>();
        for (int i=0; i<snake.getLength(); i++) {
            snakeCoordinates.addLast(new Coordinates(size/2, size/2));
        }
        addApple(generateAppleCoordinates(), generateApple());
    }

    public void moveSnake(){
        Vector move = snake.move();
        Coordinates head = snakeCoordinates.element();
        Coordinates newHead = new Coordinates(head.x() + move.x(), head.y() + move.y());
        checkBorder(newHead);
        checkTail(newHead);

        //
        if (applesOnField.containsKey(newHead)) {
            snake.eating(applesOnField.get(newHead));
            applesOnField.remove(newHead);
            addApple(generateAppleCoordinates(), generateApple());
        }
        //
        snakeCoordinates.addFirst(newHead);
        while (snake.getLength() < snakeCoordinates.size()) {
            snakeCoordinates.removeLast();
        }


    }

    public void checkBorder(Coordinates coordinates){
        if (coordinates.x() < 0 | coordinates.y() < 0 | coordinates.x() > size | coordinates.y() > size ) {
            throw new RuntimeException("выход за границу поля");
        }
    }

    public void checkTail(Coordinates coordinates){
        if (snakeCoordinates.contains(coordinates)){
            throw new RuntimeException("врезание в хвост");
        }
    }

    public void addApple(Coordinates coordinates, Apple apple){
        applesOnField.put(coordinates, apple);
    }

    public Coordinates generateAppleCoordinates(){
        Coordinates appleCoordinates = new Coordinates(new Random().nextInt(size), new Random().nextInt(size));
        while (snakeCoordinates.contains(appleCoordinates)) {
            appleCoordinates = new Coordinates(new Random().nextInt(size), new Random().nextInt(size));
        }
        return appleCoordinates;

    }
    public Apple generateApple(){
        return new Apple(new Random().nextInt(3));
    }

    public List<Coordinates> getAppleCoordinates() {
        return applesOnField.keySet().stream().toList();
    }

    public List<Coordinates> getSnakeCoordinates() {
        return snakeCoordinates.stream().toList();
    }

}
