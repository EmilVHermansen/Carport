package FunctionLayer;

public class Brick {

    private int length;
    private int qty;

    public Brick(int length, int qty) {
        this.length = length;
        this.qty = qty;
    }

    public int getLength() {
        return length;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        switch (length) {
            case 1:
                return qty + " pcs. of: 2x" + length + " [:]";
            case 4:
                return qty + " pcs. of: 2x" + length + " [::::]";
            default:
                return qty + " pcs. of: 2x" + length + " [::]";
        }
    }

}
