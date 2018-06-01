package FunctionLayer;

import java.util.List;

/**
 *
 * @author emilv
 */
public class PriceCalculator
{

    public static int calcPrice(List<LineItem> BoM)
    {
        double totalPrice = 0;

        for (LineItem lineItem : BoM)
        {
            double lineItemPrice = 0;
            lineItemPrice += lineItem.getQty() * lineItem.getPrice();

            if (lineItem.getLength() != 0)
            {
                lineItemPrice *= (((double) lineItem.getLength()) / 1000);
            }
            totalPrice += lineItemPrice;
        }

        return (int) totalPrice;
    }

}
