package FunctionLayer;

public class Order
{

    private int idOrder;
    private int length;
    private int width;
    private String inclination;
    private int angle;
    private String roofMaterial;
    private String shed;
    private int shedLength;
    private int shedWidth;
    private String name;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String comment = "";
    private int price = 0;
    private int salesprice = 0;
    private String status = "Afventer behandling";

   

    public Order(int length, int width, String inclination, int angle, String roofMaterial, String shed, String name, String address, String zipCode, String phoneNumber, String email)
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

    public int getSalesprice()
    {
        return salesprice;
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

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setInclination(String inclination)
    {
        this.inclination = inclination;
    }

    public void setRoofMaterial(String roofMaterial)
    {
        this.roofMaterial = roofMaterial;
    }

    public void setShed(String shed)
    {
        this.shed = shed;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public void setSalesprice(int salesprice)
    {
        this.salesprice = salesprice;
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

    // Test toString
//    @Override
//    public String toString()
//    {
//        return "Order{" + "idOrder=" + idOrder + ", length=" + length + ", width=" + width + ", inclination=" + inclination + ", angle=" + angle + ", roofMaterial=" + roofMaterial + ", shed=" + shed + ", shedLength=" + shedLength + ", shedWidth=" + shedWidth + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber + ", email=" + email + ", comment=" + comment + ", price=" + price + ", salesprice=" + salesprice + ", status=" + status + '}';
//    }
    
    
}
