import java.sql.SQLOutput;

public class Head {
    private Direction direction;

    public Head(Direction direction){
        this.direction = direction;
    }

    public void turnRight(){
        switch (direction) {
            case LEFT -> direction = Direction.UP;
            case UP -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.LEFT;
        }

    }

    public void turnLeft(){
        switch (direction) {
            case LEFT -> direction = Direction.DOWN;
            case UP -> direction = Direction.LEFT;
            case RIGHT -> direction = Direction.UP;
            case DOWN -> direction = Direction.RIGHT;
        }

    }

    public Direction getDirection(){
        return direction;
    }
}
