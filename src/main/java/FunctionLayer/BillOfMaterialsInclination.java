/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s_ele
 */
public class BillOfMaterialsInclination
{
    private List<LineItem> billOfMaterials;
    private final int maxPoleDistance = 3000; // 3000 mm
    private final Order order;

    public BillOfMaterialsFlat(Order order)
    {
        this.billOfMaterials = new ArrayList();
        this.order = order;
    }

    private int idOrder()
    {
        return order.getIdOrder();
    }

    private int length()
    {
        return order.getLength() * 10;
    }

    private int width()
    {
        return order.getWidth() * 10;
    }

    private boolean shed()
    {
        return order.getShed().equals("shed");
    }

    private int shedLength()
    {
        return order.getShedLength() * 10;
    }

    private int shedWidth()
    {
        return order.getShedWidth() * 10;
    }

    private boolean fullShed()
    {
        return shedWidth() == (width() - 700); // shedWidth == width
    }

    public List<LineItem> getBillOfMaterials()
    {
        return billOfMaterials;
    }

    public void createBoM() // mastermethod
    {
        // Træ og træplader
        createPole(); // Stolper
        createWallPlate(); // Remme (carport and shed)
        createRafter(); // Spær
        createFasciaAndWaterBoard(); // Stern og vandbræt (over and under, front/back and sides)(front and sides)
        createRoofPlate(); // Tagplader
        if (shed())
        {
            createZBacksideDoor();
            createShedFrame(); // skur løsholter 
            createShedCladding(); // skur beklædning
        }

        // Beslag og skruer
        if (shed())
        {
            createShedDoorKnob(); // stalddørsgreb
            createShedDoorHinge(); // Skurhængsler
            createShedCladdingScrews(); // 4,5x50mm and 4,5x70mm screws for inner and outer cladding
            createAngleBracket(); // vinkelbeslag til skurets løsholter
        }
        createPerforatedBand(); // hulbånd
        createUniversalBracket(); // universal beslag til spær på rem
        createBottomScrew(); // bundskruer til tagplader
        createFasciaWaterBoardScrew();  // 4,5x60mm skruer til stern og vandbræt
        createBracketScrew(); // 40x50mm skruer til beslag
        createBoardScrewAndSquareWasher(); // bræddebolt 10x120mm og firkantskiver til rem på stolpe

    }
}
