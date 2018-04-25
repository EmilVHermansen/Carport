package FunctionLayer;

public class User {



    private int id;
    private String empnumber;

    public User(int id, String empnumber)
    {
        this.id = id;
        this.empnumber = empnumber;
    }

    public int getId()
    {
        return id;
    }

    public String getEmpnumber()
    {
        return empnumber;
    }

}
