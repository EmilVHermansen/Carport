
import FunctionLayer.LineItem;
import static java.lang.Math.cos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilv
 */
public class demo
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        double bLength = 3600 / 2;
//        int bargeBoardLength = (int) ((bLength * sin(Math.toRadians((90)))) / sin(Math.toRadians(180-90-angle())));
        int bargeBoardLength = (int) (bLength / cos(Math.toRadians(45)));
        bargeBoardLength *= 2;
        bargeBoardLength += (300 - bargeBoardLength % 300);
        
        System.out.println(bargeBoardLength);
        
        
    }
    
}
