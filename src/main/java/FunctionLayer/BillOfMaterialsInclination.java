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

    public BillOfMaterialsInclination(Order order)
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

    // TODO: Tjek materialeId og materiale navne passer
    public void createBoM() // mastermethod
    {
        // Træ og træplader
        createPole(); // Stolper
        createWallPlate(); // Remme (carport and shed)
        createRafter(); // Spær
        createFasciaAndWaterBoard(); // Stern og vandbræt (over and under, front/back and sides)(front and sides)
        createRoofTiles(); // Teglsten
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
        createUniversalBracket(); // universal beslag til spær på rem
        createFasciaWaterBoardScrew();  // 4,5x60mm skruer til stern og vandbræt
        createBracketScrew(); // 40x50mm skruer til beslag
        createBoardScrewAndSquareWasher(); // bræddebolt 10x120mm og firkantskiver til rem på stolpe

    }

    private void createPole()
    {
        int length = length();
        LineItem pole = new LineItem("stk", "Stolper nedgraves 90 cm. i jord + skråstiver", idOrder(), 1);

        int poleAmount = 2; // minimum amount of poles

        if (shed())
        {
            poleAmount += 4; // mimimum addition of shedpoles (1 for the door)
            if (shedWidth() > maxPoleDistance)
            {
                poleAmount += 2;
            }
            if (shedLength() > maxPoleDistance)
            {
                poleAmount += 2;
            }
            if (shedLength() > 2 * maxPoleDistance)
            {
                poleAmount += 2;
            }
            if (shedLength() > length() - 150 - 1200)
            {
                if (fullShed())
                {
                    poleAmount -= 2;
                } else
                {
                    poleAmount -= 1;
                }

            }
            if (fullShed())
            {
                poleAmount -= 1;
                length -= shedLength();
            }
        }

        poleAmount += (length / maxPoleDistance * 2);

        if (length % maxPoleDistance > 1200)
        {
            poleAmount += 2;
        }
        pole.setQty(poleAmount);
        pole.setLength(3000);
        billOfMaterials.add(pole);
    }

    private void createWallPlate() // Remme (carport and shed)
    {
        LineItem wallPlateCarport = new LineItem("stk", "Remme i sider, sadles ned i stolper", idOrder(), 2);

        if (shed())
        {
            LineItem wallPlateShed = new LineItem("stk", "Remme i sider, sadles ned i stolper (skur del, deles)", idOrder(), 2);
            wallPlateShed.setQty(1);

            if (fullShed())
            {
                wallPlateShed.setLength(shedLength() * 2 + 300);
                wallPlateCarport.setLength(length() - shedLength());
                wallPlateCarport.setQty(2);
            } else
            {
                wallPlateShed.setLength(shedLength());
                wallPlateCarport.setLength(length());
                wallPlateCarport.setQty(1);
                LineItem wallPlateCarport2 = new LineItem("stk", "Remme i sider, sadles ned i stolper", idOrder(), 2);
                wallPlateCarport2.setLength(length() - shedLength());
                wallPlateCarport2.setQty(1);
                billOfMaterials.add(wallPlateCarport2);
            }
            billOfMaterials.add(wallPlateShed);
        } else
        {
            wallPlateCarport.setLength(length());
            wallPlateCarport.setQty(2);
        }
        billOfMaterials.add(wallPlateCarport);
    }

    private void createRafter()
    {
        LineItem rafter = new LineItem("stk", "Spær, monteres på rem", idOrder(), 2);
        rafter.setLength(width());
        int qty = 1 + ((length() - 645) / 945); //645 is 300mmm in each end + 45 for rafter width. 
        if ((length() - 45) % 945 > 0) // 900 is max distance between rafter + 45 of rafter width
        {
            qty++;
        }
        rafter.setQty(qty);
        billOfMaterials.add(rafter);
    }

    private void createFasciaAndWaterBoard()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createRoofTiles()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createZBacksideDoor()
    {
        LineItem zBacksideDoor = new LineItem("stk", "Til z på bagside	af dør", idOrder(), 7);
        zBacksideDoor.setQty(1);
        zBacksideDoor.setLength(4200);
        billOfMaterials.add(zBacksideDoor);
    }

    private void createShedFrame()
    {
        if (shed())
        {
            LineItem shedFrameFrontBack = new LineItem("stk", "Løsholter til skur gavle", idOrder(), 8);
            LineItem shedFrameSides = new LineItem("stk", "Løsholter til skur sider", idOrder(), 8);

            int sideSpaces = shedLength() / maxPoleDistance; // spaces between the poles
            if (shedLength() % maxPoleDistance > 0)
            {
                sideSpaces++;
            }

            int sidelength = shedLength() - ((sideSpaces + 1) * 97) / sideSpaces; // shedsidelength - polewidth divided by number of spaces
            sidelength += 300 - (sidelength % 300); // make sure the length is divisible by 300 mm and make sure there is more than enough.
            shedFrameSides.setLength(sidelength);

            shedFrameSides.setQty((sideSpaces * 2 + sideSpaces * (fullShed() ? 2 : 3)));

            int frontBackSpaces = shedWidth() / maxPoleDistance; // spaces between the poles
            if (shedWidth() % maxPoleDistance > 0)
            {
                frontBackSpaces++;
            }

            int frontBacklength = (shedWidth() - ((frontBackSpaces + 1) * 97)) / frontBackSpaces; // shedsidelength - polewidth divided by number of spaces
            frontBacklength += 300 - (frontBacklength % 300); // make sure the length is divisible by 300 mm and make sure there is more than enough.
            shedFrameFrontBack.setLength(frontBacklength);

            shedFrameFrontBack.setQty(frontBackSpaces * 3 * 2);

            billOfMaterials.add(shedFrameSides);
            billOfMaterials.add(shedFrameFrontBack);
        }
    }

    private void createShedCladding()
    {
        LineItem shedCladding = new LineItem("stk", "Til beklædning af skur 1 på 2", idOrder(), 5);
        shedCladding.setLength(2100);

        int avgBoardWidth = 74; //Based on example manual and blueprint, shed circumference divided by qty of cladding boards equals 74
        double circumference = shedLength() * 2 + shedWidth() * 2;

        int shedCladdingQty = (int) (circumference / avgBoardWidth);
        shedCladdingQty += (shedCladdingQty % avgBoardWidth == 0) ? 0 : 1;
        shedCladding.setQty(shedCladdingQty);
        billOfMaterials.add(shedCladding);
    }

    private void createShedDoorKnob()
    {
        LineItem shedDoorKnob = new LineItem("sæt", "Til lås på dør i skur", idOrder(), 10);
        shedDoorKnob.setQty(1);
        billOfMaterials.add(shedDoorKnob);
    }

    private void createShedDoorHinge()
    {
        LineItem shedDoorHinge = new LineItem("stk", "Til skurdør", idOrder(), 11);
        shedDoorHinge.setQty(2);
        billOfMaterials.add(shedDoorHinge);
    }

    private void createShedCladdingScrews()
    {
        LineItem outerScrew = new LineItem("stk", "Til montering af yderste beklædning", idOrder(), 19);
        LineItem innerScrew = new LineItem("stk", "Til montering af inderste beklædning", idOrder(), 20);

        int avgBoardWidth = 74; //Based on example manual and blueprint, shed circumference divided by qty of cladding boards equals 74
        double circumference = shedLength() * 2 + shedWidth() * 2;

        int shedCladdingQty = (int) (circumference / avgBoardWidth);
        shedCladdingQty += (shedCladdingQty % avgBoardWidth == 0) ? 0 : 1;

        int outerScrewQty = ((shedCladdingQty / 2) + (shedCladdingQty % 2)) * 8;
        outerScrewQty /= 400; // 400 screws per pack
        outerScrew.setQty(outerScrewQty);

        int innerScrewQty = ((shedCladdingQty / 2) + (shedCladdingQty % 2)) * 6;
        innerScrewQty /= 300; // 300 screws per pack
        innerScrew.setQty(innerScrewQty);

        billOfMaterials.add(outerScrew);
        billOfMaterials.add(innerScrew);
    }

    private void createAngleBracket()
    {
        if (shed())
        {
            LineItem angleBracket = new LineItem("stk", "Til montering af løsholter i skur", idOrder(), 21);
            int angleBracketQty;

            int sideSpaces = shedLength() / maxPoleDistance; // spaces between the poles
            if (shedLength() % maxPoleDistance > 0)
            {
                sideSpaces++;
            }

            angleBracketQty = sideSpaces * 2 + (fullShed() ? 0 : 1);

            int frontBackSpaces = shedWidth() / maxPoleDistance; // spaces between the poles
            if (shedWidth() % maxPoleDistance > 0)
            {
                frontBackSpaces++;
            }

            angleBracketQty += frontBackSpaces * 3;
            angleBracketQty *= 2;

            angleBracket.setQty(angleBracketQty);

            billOfMaterials.add(angleBracket);
        }
    }

    private void createUniversalBracket()
    {
        LineItem universalHingeLeft = new LineItem("stk", "Til montering af spær på rem", idOrder(), 12);
        LineItem universalHingeRight = new LineItem("stk", "Til	montering af spær på rem", idOrder(), 13);

        int qty = 1 + ((length() - 45) / 600);
        if ((length() - 45) % 600 > 0)
        {
            qty++;
        }
        universalHingeLeft.setQty(qty);
        universalHingeRight.setQty(qty);
        billOfMaterials.add(universalHingeLeft);
        billOfMaterials.add(universalHingeRight);
    }

    private void createFasciaWaterBoardScrew()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createBracketScrew()
    {
        LineItem bracketScrew = new LineItem("Pakke", "Til montering af universalbeslag & hulbånd", idOrder(), 16);
        int rafterQty = 1 + ((length() - 45) / 600);
        if ((length() - 45) % 600 > 0)
        {
            rafterQty++;

            /* We multiply with 2 because there are 2 brackets per rafter
           We multiply with 20 because we assume there is 20 screws per bracket
           We add 5 for the perforated band
             */
        }
        int totalScrews = rafterQty * 2 * 20 + 5;
        int qty = (totalScrews / 250) + ((totalScrews % 250 == 0) ? 0 : 1);
        bracketScrew.setQty(qty);
        billOfMaterials.add(bracketScrew);
    }

    private void createBoardScrewAndSquareWasher()
    {
        int length = length();
        LineItem boardScrew = new LineItem("stk", "Montering af rem på stolper", idOrder(), 17);
        LineItem squareWasher = new LineItem("stk", "Montering af rem på stolper", idOrder(), 18);

        int poleAmount = 2; // minimum amount of poles

        poleAmount += ((length() - shedLength()) / maxPoleDistance * 2);

        if (length % maxPoleDistance > 1200)
        {
            poleAmount += 2;
        }

        boardScrew.setQty(poleAmount * 3);
        squareWasher.setQty(poleAmount * 2);

        billOfMaterials.add(boardScrew);
        billOfMaterials.add(squareWasher);
    }
}
