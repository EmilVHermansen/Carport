package FunctionLayer;

/**
 *
 * @author adams
 */
public class Customer
{
    private final String name;
    private final String address;
    private final String zipCity;
    private final String phoneNo;
    private final String email;

    public Customer(String name, String address, String zipCity, String phoneNo, String email)
    {
        this.name = name;
        this.address = address;
        this.zipCity = zipCity;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getZipCity()
    {
        return zipCity;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public String getEmail()
    {
        return email;
    }

    
    
    @Override
    public String toString()
    {
        return "Customer{" + "name=" + name + ", address=" + address + ", zipCity=" + zipCity + ", phoneNo=" + phoneNo + ", email=" + email + '}';
    }
    
    
    
    
}
