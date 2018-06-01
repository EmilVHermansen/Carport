/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author emilv
 */
public class Material
{

    private int id;
    private String name;
    private int MSRP;

    public Material(int id, String name, int MSRP)
    {
        this.id = id;
        this.name = name;
        this.MSRP = MSRP;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getMSRP()
    {
        return MSRP;
    }

}
