package FunctionLayer;

import java.util.ArrayList;

public class Order {

    private int idOrder;
    private int length;
    private int width;
    private String inclination;
    private String roofMaterial;
    private String shed;
    private int shedLength;
    private int shedWidth;
    private String name;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String comment;
    private String status;

    public Order(int idOrder, int length, int width, String inclination, String roofMaterial, String shed, String name, String address, String zipCode, String phoneNumber, String email, String status)
    {
        this.idOrder = idOrder;
        this.length = length;
        this.width = width;
        this.inclination = inclination;
        this.roofMaterial = roofMaterial;
        this.shed = shed;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }

    public int getIdOrder()
    {
        return idOrder;
    }

    public int getLength()
    {
        return length;
    }

    public int getWidth()
    {
        return width;
    }

    public String getInclination()
    {
        return inclination;
    }

    public String getRoofMaterial()
    {
        return roofMaterial;
    }

    public String getShed()
    {
        return shed;
    }

    public int getShedLength()
    {
        return shedLength;
    }

    public int getShedWidth()
    {
        return shedWidth;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public String getComment()
    {
        return comment;
    }

    public String getStatus()
    {
        return status;
    }

    public void setShedLength(int shedLength)
    {
        this.shedLength = shedLength;
    }

    public void setShedWidth(int shedWidth)
    {
        this.shedWidth = shedWidth;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    
}
