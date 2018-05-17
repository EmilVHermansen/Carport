/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Adam
 */
public class TechDrawing
{

    public String mmToMeter(int i)
    {
        i = Math.abs(i);
        String cm = Integer.toString(i);
        if (i < 1000)
        {
            return "0," + cm.substring(0, 2);
        } else
        {
            return cm.substring(0, 1) + "," + cm.substring(1, 3);
        }
    }
}
