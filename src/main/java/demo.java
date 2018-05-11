
import FunctionLayer.LineItem;

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
        int length = 6000; 
        int width = 7800;
        double amountOfTotalScrews = 600;
        double screwsPerSqrMilimeter = amountOfTotalScrews / (length * width); //bases on existing example of BoM and manual
        int qty = (int) (length * width * screwsPerSqrMilimeter);
        
        System.out.println(qty);
    }
    
}
