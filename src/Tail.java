public class Tail {
    private int length;

    public Tail(int length){
        this.length = length;
    }
    public void increase(int increase){
        length += increase;
    }

    public int getLength(){
        return length;
    }

}
