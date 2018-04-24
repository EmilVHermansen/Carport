package FunctionLayer;

import java.util.ArrayList;

public class LegoHouse {

    private int length;
    private int width;
    private int height;
    private ArrayList<Brick> bricks = new ArrayList();

    public LegoHouse(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Brick> HasEvenNumbers() {
        int qty = (((length + height) * 2 - 8) / 4) * height;
        bricks.add(new Brick(4, qty));
        return bricks;

    }

    public ArrayList<Brick> OneUnevenOneEven() {
        int qty = (((length - 2) / 4) * 2 + (width - 2) / 4 * 2) * height;
        bricks.add(new Brick(4, qty));
        bricks.add(new Brick(1, 2 * height));
        return bricks;
    }

    public ArrayList<Brick> HasUnevenNumbers() {
        int qty = ((length - 2) / 4 * 2 + (width - 2) / 4 * 2) * height;
        bricks.add(new Brick(4, qty));
        bricks.add(new Brick(1, 4 * height));
        return bricks;
    }

}
