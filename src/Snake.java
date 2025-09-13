public class Snake {
    private final Head head;
    private final Tail tail;
    private final int stepLength = 1;


    public Snake(){
        this.head = new Head(Direction.LEFT);
        this.tail = new Tail(0);
    }

    public Vector move(){
        Vector vector = null;
        if (head.getDirection() == Direction.RIGHT){
            vector = new Vector(stepLength, 0);
        }
        if (head.getDirection() == Direction.LEFT){
            vector = new Vector(-1 * stepLength, 0);
        }
        if (head.getDirection() == Direction.UP){
            vector = new Vector(0, -1 * stepLength);
        }
        if (head.getDirection() == Direction.DOWN) {
            vector = new Vector(0, stepLength);
        }
        return vector;
    }

    public int getLength(){
        return tail.getLength() + 1;
    }

    public void eating(Apple apple){
        tail.increase(apple.satiety);
    }

    public void turnTo(Direction direction) {
        Direction currDirection = head.getDirection();
        Side sideForHeadTurn = selectSideForHeadTurn(currDirection, direction);

        switch (sideForHeadTurn) {
            case RIGHT -> head.turnRight();
            case LEFT -> head.turnLeft();
        }
    }

    private Side selectSideForHeadTurn(Direction from, Direction to) {
        int distanceBetweenDirectionsLeft = modN(to.getIndex() - from.getIndex(), 4);
        int distanceBetweenDirectionsRight = modN(from.getIndex() - to.getIndex(),4);
        if (distanceBetweenDirectionsLeft %2 == 0) {
            throw new IllegalStateException();
        }
        if (distanceBetweenDirectionsLeft <= distanceBetweenDirectionsRight) {
            return Side.RIGHT;
        } else {
            return Side.LEFT;
        }
    }

    private int modN(int number, int n) {
        while (number<0) number+=n;
        return number%n;
    }

    private enum Side {
        LEFT,
        RIGHT
    }
}
