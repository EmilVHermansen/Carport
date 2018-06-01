/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * ALL UNITS ARE IN MILIMETER
 *
 * @author s_ele
 */
public class BillOfMaterialsFlat
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
        return (order.getShed().equals("Med skur") && shedLength() != 0 && shedWidth() != 0);
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
        return shedWidth() >= (width() - 600); // shedWidth == width
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

    void createPole() // Stolper  
    {

        int length = length();
        LineItem pole = new LineItem("stk", "Stolper nedgraves 90 cm. i jord", idOrder(), 1);

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

    void createWallPlate() // Remme (carport and shed)
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

    void createRafter() // Spær
    {
        LineItem rafter = new LineItem("stk", "Spær, monteres på rem", idOrder(), 2);
        rafter.setLength(width());
        int qty = 1 + ((length() - 45) / 945);
        if ((length() - 45) % 945 > 0)
        {
            qty++;
        }
        rafter.setQty(qty);
        billOfMaterials.add(rafter);
    }

    void createFasciaAndWaterBoard() // Stern (over and under, front/back and sides) og vandbræt (front and sides)
    {
        LineItem fasciaBoardUnderFrontBack = new LineItem("stk", "Understernbrædder til for & bag ende", idOrder(), 3);
        fasciaBoardUnderFrontBack.setQty(4);

        LineItem fasciaBoardUnderSides = new LineItem("stk", "Understernbrædder til siderne", idOrder(), 3);
        fasciaBoardUnderSides.setQty(4);

        LineItem fasciaBoardFront = new LineItem("stk", "Oversternbrædder til forenden", idOrder(), 4);
        fasciaBoardFront.setQty(2);

        LineItem fasciaBoardSides = new LineItem("stk", "Oversternbrædder til siderne", idOrder(), 4);
        fasciaBoardSides.setQty(4);

        LineItem waterBoardFront = new LineItem("stk", "Vandbræt på stern i forende", idOrder(), 5);
        waterBoardFront.setQty(2);

        LineItem waterBoardSides = new LineItem("stk", "Vandbræt på stern i sider", idOrder(), 5);
        waterBoardSides.setQty(4);

        int frontBackLength = width() / 2 + (300 - width() % 300);
        
        fasciaBoardUnderFrontBack.setLength(frontBackLength);
        fasciaBoardFront.setLength(frontBackLength);
        waterBoardFront.setLength(frontBackLength);

        int sideLength = length() / 2 + (300 - length() % 300);

        fasciaBoardUnderSides.setLength(sideLength);
        fasciaBoardSides.setLength(sideLength);
        waterBoardSides.setLength(sideLength);

        billOfMaterials.add(fasciaBoardUnderFrontBack);
        billOfMaterials.add(fasciaBoardUnderSides);
        billOfMaterials.add(fasciaBoardFront);
        billOfMaterials.add(fasciaBoardSides);
        billOfMaterials.add(waterBoardFront);
        billOfMaterials.add(waterBoardSides);

    }

    void createRoofPlate() // Tagplader
    {
        LineItem roofPlate1 = new LineItem("stk", "Tagplader monteres på spær", idOrder(), 6);
        int qty = width() / 1040; //width of a roofplate on Fogs website

        if (width() % 1040 > 0)
        {
            qty++;
        }

        roofPlate1.setQty(qty);

        if (length() > 6000)
        {
            roofPlate1.setLength(6000);
            LineItem roofPlate2 = new LineItem("stk", "Tagplader monteres på spær", idOrder(), 6);
            roofPlate2.setQty(qty);
            int length2 = length() - 6000;
            if (length2 <= 2400)
            {
                roofPlate2.setLength(2400);
            }
//            else if (length2 <= 3000) {
//                roofPlate2.setLength(3000);
//            } else if (length2 <= 3600) {
//                roofPlate2.setLength(3600);
//            } else if (length2 <= 4200) {
//                roofPlate2.setLength(4200);
//            } else if (length2 <= 4800) {
//                roofPlate2.setLength(4800);
//            } else if (length2 <= 6000) {
//                roofPlate2.setLength(6000);
            billOfMaterials.add(roofPlate1);
            billOfMaterials.add(roofPlate2);
        } else
        {
            int length = length();
            if (length <= 2400)
            {
                roofPlate1.setLength(2400);
            } else if (length <= 3000)
            {
                roofPlate1.setLength(3000);
            } else if (length <= 3600)
            {
                roofPlate1.setLength(3600);
            } else if (length <= 4200)
            {
                roofPlate1.setLength(4200);
            } else if (length <= 4800)
            {
                roofPlate1.setLength(4800);
            } else if (length <= 6000)
            {
                roofPlate1.setLength(6000);
            }
            billOfMaterials.add(roofPlate1);
        }
    }

    void createZBacksideDoor()
    {
        LineItem zBacksideDoor = new LineItem("stk", "Til z på bagside	af dør", idOrder(), 7);
        zBacksideDoor.setQty(1);
        zBacksideDoor.setLength(4200);
        billOfMaterials.add(zBacksideDoor);
    }

    void createShedFrame() // skur løsholter
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

    void createShedCladding() // skur beklædning 
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

    void createPerforatedBand() // hulbånd
    {
        int length;
        int width;
        int diagonal;

        int divSubLength = length() / 600;
        if (length() % 600 > 0)
        {
            divSubLength++;
        }

        int sublength1 = length() / divSubLength;

        int sublength2;

        if (shed())
        {
            sublength2 = shedLength() + 150;
        } else
        {
            sublength2 = sublength1;
        }

        //Calculation
        length = length() - sublength1 - sublength2;
        width = width() - (35 * 2);
        diagonal = (int) Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2)); //Pythagoras

        int perforatedBandQty = 2;
        if (diagonal > 10)
        {
            perforatedBandQty *= 2;
        }

        LineItem perforatedBand = new LineItem("stk", "Til vindkryds på spær", idOrder(), 9);

        perforatedBand.setQty(perforatedBandQty);

        billOfMaterials.add(perforatedBand);
    }

    void createShedDoorKnob()
    {
        LineItem shedDoorKnob = new LineItem("sæt", "Til lås på dør i skur", idOrder(), 10);
        shedDoorKnob.setQty(1);
        billOfMaterials.add(shedDoorKnob);
    }

    void createShedDoorHinge()
    {
        LineItem shedDoorHinge = new LineItem("stk", "Til skurdør", idOrder(), 11);
        shedDoorHinge.setQty(2);
        billOfMaterials.add(shedDoorHinge);
    }

    void createUniversalBracket()
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

    private void createBottomScrew()
    {
        LineItem bottomScrew = new LineItem("pk.", "Skruer til tagplader", idOrder(), 14);
        //based on existing example of BoM and manual
        int length = 7800;
        int width = 6000;
        double amountOfTotalScrews = 600;

        double screwsPerSqrMilimeter = amountOfTotalScrews / (length * width);
        int totalScrews = (int) (length * width * screwsPerSqrMilimeter);
        int qty = (totalScrews / 200) + ((totalScrews % 200 == 0) ? 0 : 1);

        bottomScrew.setQty(qty);
        billOfMaterials.add(bottomScrew);
    }

    private void createFasciaWaterBoardScrew()
    {
        LineItem FasciaWaterBoardScrew = new LineItem("pk.", "Til montering af stern & vandbræt", idOrder(), 15);
        FasciaWaterBoardScrew.setQty(1);
        billOfMaterials.add(FasciaWaterBoardScrew);

    }

    private void createBracketScrew()
    {
        LineItem bracketScrew = new LineItem("pk.", "Til montering af universalbeslag & hulbånd", idOrder(), 16);
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

}//CLASS
