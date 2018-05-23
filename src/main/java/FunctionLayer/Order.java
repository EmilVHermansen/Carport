package FunctionLayer;

public class Order
{

    private int idOrder;
    private int length;
    private int width;
    private String inclination;
    private String roofMaterial;
    private String shed;
    private int shedLength = 0;
    private int shedWidth = 0;
    private String name;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String comment = "";
    private int price;
    private String status = "Afventer behandling";
    private int angle = 0;

    public Order(int idOrder, int length, int width, String inclination, int angle, String roofMaterial, String shed, String name, String address, String zipCode, String phoneNumber, String email, int price)
    {
        this.idOrder = idOrder;
        this.length = length;
        this.width = width;
        this.inclination = inclination;
        this.angle = angle;
        this.roofMaterial = roofMaterial;
        this.shed = shed;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.price = price;
    }

    public Order(int length, int width, String inclination, int angle, String roofMaterial, String shed, String name, String address, String zipCode, String phoneNumber, String email, int price)
    {
        this.length = length;
        this.width = width;
        this.inclination = inclination;
        this.angle = angle;
        this.roofMaterial = roofMaterial;
        this.shed = shed;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.price = price;
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

    public int getPrice()
    {
        return price;
    }

    public String getStatus()
    {
        return status;
    }

    public int getAngle()
    {
        return angle;
    }

    public void setIdOrder(int idOrder)
    {
        this.idOrder = idOrder;
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

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setAngle(int angle)
    {
        this.angle = angle;
    }

    @Override
    public String toString()
    {
        return "Ordre: " + idOrder + ", længde: " + length
                + ", bredde: " + width + ", Med rejsning eller fladt tag: " + inclination
                + ", hældning: " + angle + ", tagmateriale: " + roofMaterial + ", Med eller uden skur: " + shed
                + ", skurlængde: " + shedLength + ", skurbredde: " + shedWidth
                + ", kommentar: " + comment + ", status: " + status;
    }
}
