public class Tail {
    private int length;

    public Tail(int length){
        this.length = length;
    }
    public void increase(int increase) {
        try {
            if (increase <= 0) {
                throw new IllegalArgumentException("Значение increase должно быть положительным: " + increase);
            }
            length += increase;
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка увеличения длины: " + e.getMessage());
        }
    }

    public int getLength(){
        return length;
    }

}
