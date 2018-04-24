package FunctionLayer;

import java.util.ArrayList;

public class Order {

    private int id;
    private int length;
    private int width;
    private int height;
    private String status;
    private int userId;
    private ArrayList<Brick> bricks = new ArrayList();

    public Order(int length, int width, int height, int userId) {
        this.length = length;
        this.width = width;
        this.height = height;
        status = "notShipped";
        this.userId = userId;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order number: ").append(id).append(" " + status).append("<br>");
        sb.append("Dimensions (L x W x H): ");
        sb.append(length + " x " + width + " x " + height);
        for (Brick brick : bricks) {
            sb.append("<br>");
            sb.append(brick);
        }
        String str = sb.toString();
        return str;
    }

}
